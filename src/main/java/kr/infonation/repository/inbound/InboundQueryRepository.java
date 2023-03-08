package kr.infonation.repository.inbound;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.infonation.dto.inbound.InboundQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                .leftJoin(inboundItem.item, item)
                .where(inbound.inboundNo.eq(inboundNo))
                .fetch();
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
