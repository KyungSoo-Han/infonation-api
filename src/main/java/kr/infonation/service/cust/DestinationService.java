package kr.infonation.service.cust;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Destination;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.dto.cust.DestinationDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.cust.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;
    private final BizRepository bizRepository;
    private final CustomerRepository customerRepository;


    @Transactional
    public Map<String, Object> createDestination(DestinationDto.CreateRequest request) throws CustomException {

        Biz biz = bizRepository.findById(request.getBizId())
                .orElseThrow(() -> new CustomException("사업장을 찾을 수 없습니다."));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomException("화주사를 찾을 수 없습니다."));

        Destination destination = destinationRepository.save(request.toEntity(biz, customer));

        Map<String, Object> rtnObj = new HashMap<>();
        rtnObj.put("biz", biz);
        rtnObj.put("customer", customer);
        rtnObj.put("destination", destination);

        return rtnObj;
    }

    public List<DestinationDto.Response> findDestination(Long bizId, Long customerId) {
        List<Destination> destination = destinationRepository.findDestination(bizId, customerId);
        List<DestinationDto.Response> responseList = destination
                                                        .stream()
                                                        .map(d -> new DestinationDto.Response(d)).collect(Collectors.toList());
        return responseList;
    }
}
