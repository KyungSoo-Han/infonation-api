package kr.infonation.domain.inbound;

import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.item.Item;
import lombok.*;

import javax.crypto.interfaces.PBEKey;
import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class InboundItem extends BaseEntity{

   /* @Id
    @Column(name = "inbound_no")
    private String inboundNo;
    */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inbound_seq")
    private Long inboundSeq;

    @JoinColumn(name = "inbound_no")
    @ManyToOne(fetch = FetchType.LAZY)
    private Inbound inbound;

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

    public void setInbound(Inbound inbound) {
        this.inbound = inbound;
    }

    @Builder
    public InboundItem(Inbound inbound, Item item, int qty, int price,String expDate,
                       String makeLotNo, String makeDate, String subRemark, boolean status) {
        this.inbound = inbound;
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
