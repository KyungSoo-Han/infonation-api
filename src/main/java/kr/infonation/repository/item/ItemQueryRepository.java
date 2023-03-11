package kr.infonation.repository.item;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.infonation.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static kr.infonation.domain.biz.QBiz.biz;
import static kr.infonation.domain.cust.QCustomer.customer;
import static kr.infonation.domain.item.QItem.item;

@Repository
@RequiredArgsConstructor
public class ItemQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Item> findItemList (Long bizId, Long customerId, String itemName){

        return queryFactory.selectFrom(item)
                .leftJoin(item.biz, biz)
                .leftJoin(item.customer, customer)
                .where(customer.id.eq(customerId).and(biz.id.eq(bizId)), containItemName(itemName)).fetch();
    }

    private BooleanExpression containItemName(String itemName){
        return StringUtils.hasText(itemName) ? item.name.contains(itemName) : null;
    }
}
