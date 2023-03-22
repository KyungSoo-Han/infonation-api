package kr.infonation.repository.outbound;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.infonation.domain.outbound.QOutbound;
import kr.infonation.dto.outbound.OutboundQueryDto;
import kr.infonation.dto.outbound.OutboundSrchCond;
import kr.infonation.dto.outbound.OutboundQueryDto;
import kr.infonation.dto.outbound.OutboundSrchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static kr.infonation.domain.biz.QBiz.biz;
import static kr.infonation.domain.center.QCenter.center;
import static kr.infonation.domain.cust.QCustomer.customer;
import static kr.infonation.domain.cust.QDestination.destination;
import static kr.infonation.domain.inbound.QInbound.inbound;
import static kr.infonation.domain.outbound.QOutbound.outbound;
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

    public List<OutboundQueryDto> findOutboundList(OutboundSrchCond srchCond) {
        System.out.println("srchCond = " + srchCond);
        log.info("srchCond", srchCond);
        return getOutboundQueryDtoJPAQuery()
                .where(eqBizId(srchCond.getBizId()),betweenDate(srchCond.getFromDate(), srchCond.getToDate()),
                        eqOutboundNo(srchCond.getOutboundNo()), eqCustomerId(srchCond.getCustomerId()),eqSupplierId(srchCond.getDestinationId()))
                .fetch();
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

    private BooleanExpression eqBizId(Long bizId){
        return bizId != null ? outbound.biz.id.eq(bizId) : null;
    }
    private BooleanExpression eqOutboundNo(String outboundNo){
        return StringUtils.hasText(outboundNo) ? outbound.outboundNo.contains(outboundNo) : null;
    }
    private BooleanExpression betweenDate(String fromDate, String toDate){
        return fromDate != null && toDate != null ? outbound.outboundDate.between(fromDate, toDate) : null;
    }
    private BooleanExpression eqCustomerId(Long customerId){
        return customerId != null ? outbound.customer.id.eq(customerId) : null;
    }
    private BooleanExpression eqSupplierId(Long destinationId){
        return destinationId != null ? outbound.destination.id.eq(destinationId) : null;
    }
}
