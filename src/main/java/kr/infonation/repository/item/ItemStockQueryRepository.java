package kr.infonation.repository.item;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.infonation.domain.item.ItemStock;
import kr.infonation.domain.item.QItemStock;
import kr.infonation.dto.item.ItemStockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static kr.infonation.domain.biz.QBiz.biz;
import static kr.infonation.domain.center.QCenter.center;
import static kr.infonation.domain.cust.QCustomer.customer;
import static kr.infonation.domain.inbound.QInbound.inbound;
import static kr.infonation.domain.item.QItem.item;
import static kr.infonation.domain.item.QItemStock.itemStock;
import static kr.infonation.domain.outbound.QOutbound.outbound;
import static kr.infonation.domain.outbound.QOutboundItem.outboundItem;

@Repository
@RequiredArgsConstructor
public class ItemStockQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<ItemStock> getItemStock(Long bizId, Long centerId, Long customerId, Long itemId,
                                           String makeLotNo, String makeDate, String expDate, String location){
        return Optional.ofNullable(queryFactory.selectFrom(itemStock)
                .leftJoin(itemStock.biz, biz)
                .leftJoin(itemStock.center, center)
                .leftJoin(itemStock.customer, customer)
                .leftJoin(itemStock.item, item)
                .where(eqBizId(bizId).and(eqCenterId(centerId)).and(eqCustomerId(customerId))
                        .and(eqItemId(itemId)).and(eqMakeLotNo(makeLotNo)).and(eqMakeDate(makeDate))
                        .and(eqExpDate(expDate)).and(eqLocation(location)))
                .fetchOne());
    }
    public List<ItemStockDto.Response> findItemStockList(Long bizId, Long centerId, Long customerId, Long itemId,
                                          String makeLotNo, String makeDate, String expDate, String location){
        return queryFactory.select(Projections.constructor
                                    (ItemStockDto.Response.class, biz.id, center.id, customer.id, item.id,
                                            itemStock.makeLotNo, itemStock.makeDate, itemStock.expDate,
                                            itemStock.location, itemStock.location, itemStock.stockQty))
                    .from(itemStock)
                    .leftJoin(itemStock.biz, biz)
                    .leftJoin(itemStock.center, center)
                    .leftJoin(itemStock.customer, customer)
                    .leftJoin(itemStock.item, item)
                    .where(eqBizId(bizId).and(eqCenterId(centerId)).and(eqCustomerId(customerId))
                            .and(eqItemId(itemId)).and(eqMakeLotNo(makeLotNo)).and(eqMakeDate(makeDate))
                            .and(eqExpDate(expDate)).and(eqLocation(location)))
                    .fetch();
    }
    private BooleanExpression eqBizId(Long bizId){
        return bizId != null ? itemStock.biz.id.eq(bizId) : null;
    }
    private BooleanExpression eqCenterId(Long centerId){
        return centerId != null ? itemStock.center.id.eq(centerId) : null;
    }
    private BooleanExpression eqCustomerId(Long customerId){
        return customerId != null ? itemStock.customer.id.eq(customerId) : null;
    }
    private BooleanExpression eqItemId(Long itemId){
        return itemId != null ? itemStock.item.id.eq(itemId) : null;
    }
    private BooleanExpression eqMakeLotNo(String makeLotNo){
        return StringUtils.hasText(makeLotNo) ? itemStock.makeLotNo.eq(makeLotNo) : null;
    }
    private BooleanExpression eqMakeDate(String makeDate){
        return StringUtils.hasText(makeDate) ? itemStock.makeDate.eq(makeDate) : null;
    }
    private BooleanExpression eqExpDate(String expDate){
        return StringUtils.hasText(expDate) ? itemStock.expDate.eq(expDate) : null;
    }
    private BooleanExpression eqLocation(String location){
        return StringUtils.hasText(location) ? itemStock.location.eq(location) : null;
    }

}
