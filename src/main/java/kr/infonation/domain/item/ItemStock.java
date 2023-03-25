package kr.infonation.domain.item;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ItemStock {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "biz_id")
    private Biz biz;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "center_id")
    private Center center;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    private String makeLotNo;
    private String makeDate;
    private String expDate;
    private String location;
    private int stockQty;

    @Builder
    public ItemStock(Biz biz, Center center, Customer customer, Item item, String makeLotNo, String makeDate, String expDate, String location, int stockQty) {
        this.biz = biz;
        this.center = center;
        this.customer = customer;
        this.item = item;
        this.makeLotNo = makeLotNo;
        this.makeDate = makeDate;
        this.expDate = expDate;
        this.location = location;
        this.stockQty = stockQty;
    }

    public void inboundStock(int inboundQty){
        this.stockQty += inboundQty;
    }

    public void outboundStock(int outboundQty) throws CustomException {
        int restStock = this.stockQty - outboundQty;
        if(restStock < 0){
            throw new CustomException("재고가 부족합니다.");
        }
        this.stockQty = restStock;
    }


}
