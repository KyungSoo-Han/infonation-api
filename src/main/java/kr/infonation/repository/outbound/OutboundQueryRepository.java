package kr.infonation.repository.outbound;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.infonation.dto.inbound.InboundQueryDto;
import kr.infonation.dto.outbound.OutboundQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kr.infonation.domain.biz.QBiz.biz;
import static kr.infonation.domain.center.QCenter.center;
import static kr.infonation.domain.cust.QCustomer.customer;
import static kr.infonation.domain.cust.QDestination.destination;
import static kr.infonation.domain.inbound.QInbound.inbound;
import static kr.infonation.domain.item.QItem.item;
import static kr.infonation.domain.outbound.QOutbound.outbound;
import static kr.infonation.domain.outbound.QOutboundItem.outboundItem;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OutboundQueryRepository {

    private final JPAQueryFactory queryFactory ;
    public List<OutboundQueryDto> findOutbound(String outboundNo) {

        return getOutboundQueryDtoJPAQuery()
                .where(outbound.outboundNo.eq(outboundNo))
                .fetch();
    }

    public Optional<OutboundQueryDto> findOutboundOptional(String outboundNo) {

        return getOutboundQueryDtoJPAQuery()
                .where(outbound.outboundNo.eq(outboundNo))
                .stream().findFirst();
    }

    private JPAQuery<OutboundQueryDto> getOutboundQueryDtoJPAQuery() {
        return queryFactory.select
                        (Projections.constructor
                                (OutboundQueryDto.class, outbound.outboundNo, outbound.outboundDate, outbound.biz.id, biz.name,
                                        outbound.center.id, center.name, outbound.customer.id, customer.name,
                                        outbound.destination.id, destination.name, outbound.remark, outbound.outBoundGbn, outbound.outboundExpDate,
                                        outboundItem.outboundSeq, outboundItem.item.id, item.name, outboundItem.qty, outboundItem.price,
                                        outboundItem.status, outboundItem.subRemark, outboundItem.expDate, outboundItem.makeLotNo, outboundItem.makeDate))
                .from(outbound)
                .leftJoin(outbound.outboundItemList, outboundItem)
                .leftJoin(outbound.biz, biz)
                .leftJoin(outbound.center, center)
                .leftJoin(outbound.customer, customer)
                .leftJoin(outbound.destination, destination)
                .leftJoin(outboundItem.item, item);
    }
    
}
