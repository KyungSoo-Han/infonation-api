package kr.infonation.service.outbound;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Destination;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.outbound.Outbound;
import kr.infonation.dto.inbound.InboundDto;
import kr.infonation.dto.outbound.OutboundDto;
import kr.infonation.repository.base.SlipNoService;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.center.CenterRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.cust.DestinationRepository;
import kr.infonation.repository.cust.SupplierRepository;
import kr.infonation.repository.outbound.OutboundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OutboundService {

    private final OutboundRepository outboundRepository;

    private final BizRepository bizRepository;
    private final CenterRepository centerRepository;
    private final CustomerRepository customerRepository;
    private final DestinationRepository destinationRepository;
    private final SlipNoService slipNoService;

    public OutboundDto.CreateResponse createOutbound (OutboundDto.CreateRequest request) throws CustomException {

        Biz biz = findByIdOrThrow(bizRepository, request.getBizId(), "사업장을 찾을 수 없습니다.");
        Center center = findByIdOrThrow(centerRepository, request.getCenterId(), "센터를 찾을 수 없습니다.");
        Customer customer = findByIdOrThrow(customerRepository, request.getCustomerId(), "화주사를 찾을 수 없습니다.");
        Destination destination = findByIdOrThrow(destinationRepository, request.getDestinationId(), "공급사를 찾을 수 없습니다.");

        // 전표번호 가져오기 -> 이미 채번된 전표일 경우 그대로 반환
        String slipNo = getSlipNo(request);
        Outbound outbound = outboundRepository.save(request.toEntity(slipNo, biz, center, customer, destination));

        return new OutboundDto.CreateResponse(outbound,
                                                biz.getId(),
                                                biz.getName(),
                                                center.getId(),
                                                center.getName(),
                                                customer.getId(),
                                                customer.getName(),
                                                destination.getId(),
                                                destination.getName());

    }

    /***
     * 전표번호 생성
     * @param request
     * @return
     * @throws CustomException
     */
    private String getSlipNo(OutboundDto.CreateRequest request) throws CustomException {

        String slipNo = request.getOutboundNo().isEmpty()
                ? slipNoService.generateSlipNo("O", request.getOutboundDate().replace("-", ""))
                                                                    : request.getOutboundNo();
        return slipNo;
    }

    // findByIdOrThrow 메서드
    private <T> T findByIdOrThrow(CrudRepository<T, Long> repository, Long id, String errorMessage)
            throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }

}
