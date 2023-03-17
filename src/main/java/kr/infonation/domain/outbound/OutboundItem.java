package kr.infonation.domain.outbound;

import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.outbound.Outbound;
import kr.infonation.domain.item.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OutboundItem extends BaseEntity{

   /* @Id
    @Column(name = "outbound_no")
    private String outboundNo;
    */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outbound_seq")
    private Long outboundSeq;

    @JoinColumn(name = "outbound_no")
    @ManyToOne(fetch = FetchType.LAZY)
    private Outbound outbound;

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private int qty;

    private int price;
    private String makeLotNo;
    private String makeDate;
    private String expDate;
    private String subRemark;
    private boolean status;

    public void setOutbound(Outbound outbound) {
        this.outbound = outbound;
    }

    @Builder
    public OutboundItem(Outbound outbound, Item item, int qty, int price, String expDate,
                        String makeLotNo, String makeDate, String subRemark, boolean status) {
        this.outbound = outbound;
        this.item = item;
        this.qty = qty;
        this.price = price;
        this.makeLotNo = makeLotNo;
        this.makeDate = makeDate;
        this.expDate = expDate;
        this.subRemark = subRemark;
        this.status = status;
    }

    public void update(Item item, int qty, int price,String expDate,
                       String makeLotNo, String makeDate,  String subRemark, boolean status) {
        this.item = item;
        this.qty = qty;
        this.price = price;
        this.makeLotNo = makeLotNo;
        this.makeDate = makeDate;
        this.expDate = expDate;
        this.subRemark = subRemark;
        this.status = status;
    }

}
