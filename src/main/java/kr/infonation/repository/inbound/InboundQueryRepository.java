package kr.infonation.repository.inbound;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.infonation.dto.inbound.InboundDto;
import kr.infonation.dto.inbound.InboundQueryDto;
import kr.infonation.dto.inbound.InboundSrchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static kr.infonation.domain.biz.QBiz.biz;
import static kr.infonation.domain.center.QCenter.center;
import static kr.infonation.domain.cust.QCustomer.customer;
import static kr.infonation.domain.cust.QSupplier.supplier;
import static kr.infonation.domain.inbound.QInbound.inbound;
import static kr.infonation.domain.inbound.QInboundItem.inboundItem;
import static kr.infonation.domain.item.QItem.item;

@Repository
@RequiredArgsConstructor
public class InboundQueryRepository {

    private final JPAQueryFactory queryFactory ;
    public List<InboundQueryDto> findInbound(String inboundNo) {

        return getInboundQueryDtoJPAQuery()
                .where(inbound.inboundNo.eq(inboundNo))
                .fetch();
    }


    public List<InboundQueryDto> findInboundList(InboundSrchCond srchCond) {
        System.out.println("srchCond = " + srchCond);
        return getInboundQueryDtoJPAQuery()
                .where(eqBizId(srchCond.getBizId()),betweenDate(srchCond.getFromDate(), srchCond.getToDate()),
                            eqInboundNo(srchCond.getInboundNo()), eqCustomerId(srchCond.getCustomerId()),eqSupplierId(srchCond.getSupplierId()))
                .fetch();
    }

    private JPAQuery<InboundQueryDto> getInboundQueryDtoJPAQuery() {
        return queryFactory.select
                        (Projections.constructor
                                (InboundQueryDto.class, inbound.inboundNo, inbound.inboundDate, inbound.biz.id, biz.name,
                                        inbound.center.id, center.name, inbound.customer.id, customer.name,
                                        inbound.supplier.id, supplier.name, inbound.remark, inbound.inBoundGbn, inbound.inboundExpDate,
                                        inboundItem.inboundSeq, inboundItem.item.id, item.name, inboundItem.qty, inboundItem.price,
                                        inboundItem.status, inboundItem.subRemark, inboundItem.expDate, inboundItem.makeLotNo, inboundItem.makeDate))
                .from(inbound)
                .leftJoin(inbound.inboundItemList, inboundItem)
                .leftJoin(inbound.biz, biz)
                .leftJoin(inbound.center, center)
                .leftJoin(inbound.customer, customer)
                .leftJoin(inbound.supplier, supplier)
                .leftJoin(inboundItem.item, item);
    }

    private BooleanExpression eqBizId(Long bizId){
        return bizId != null ? inbound.biz.id.eq(bizId) : null;
    }
    private BooleanExpression eqInboundNo(String inboundNo){
        return StringUtils.hasText(inboundNo) ? inbound.inboundNo.contains(inboundNo) : null;
    }
    private BooleanExpression betweenDate(String fromDate, String toDate){
        return fromDate != null && toDate != null ? inbound.inboundDate.between(fromDate, toDate) : null;
    }
    private BooleanExpression eqCustomerId(Long customerId){
        return customerId != null ? inbound.customer.id.eq(customerId) : null;
    }
    private BooleanExpression eqSupplierId(Long supplierId){
        return supplierId != null ? inbound.supplier.id.eq(supplierId) : null;
    }

    public void inboundApprove(List<InboundDto.InboundApproveRequest> request){
        queryFactory.update(inboundItem)
                    .set(inboundItem.status, true)
                    .where(inboundItem
                            .inbound
                            .inboundNo.in(request.stream()
                                        .map(InboundDto.InboundApproveRequest::getInboundNo).collect(Collectors.toList())))
                    .execute();
    }

    public void inboundCancel(List<InboundDto.InboundCancelRequest> request) {
        queryFactory.update(inboundItem)
                .set(inboundItem.status, false)
                .where(inboundItem
                        .inbound
                        .inboundNo.in(request.stream()
                                .map(InboundDto.InboundCancelRequest::getInboundNo).collect(Collectors.toList())))
                .execute();
    }

/*
    public InboundDto.Response findInbound (String inboundNo){
        QInbound inbound = new QInbound("i");
        QInboundItem inboundItem = new QInboundItem("ii");
        QBiz qBiz = new QBiz("biz");
        QCenter qCenter = new QCenter("center");
        QItem qItem = new QItem("item");
        QCustomer qCustomer = new QCustomer("customer");
        QSupplier qSupplier = new QSupplier("supplier");

        return queryFactory.select(new QInboundDto_Response(inbound, inbound.biz.id, qBiz.name, inbound.center.id, qCenter.name,
                        inbound.customer.id, qCustomer.name, inbound.supplier.id, qSupplier.name,
                        new QInboundDto_ItemResponse(inboundItem, inboundItem.item.id, qItem.name)))
                .from(inbound)
                .leftJoin(inbound.inboundItemList, inboundItem)
                .leftJoin(inbound.biz, qBiz)
                .leftJoin(inbound.center, qCenter)
                .leftJoin(inbound.customer, qCustomer)
                .leftJoin(inbound.supplier, qSupplier)
                .leftJoin(inboundItem.item, qItem)
                .fetchOne();
    }*/

}
