package kr.infonation.domain.inbound;

import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Inbound extends BaseEntity {

    @Id
    @Column(name = "inbound_no", unique = true, nullable = false)
    private String inboundNo;

    private String inboundDate;

    @JoinColumn(name = "biz_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Biz biz;

    @JoinColumn(name = "center_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Center center;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @JoinColumn(name = "supplier_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @Enumerated(EnumType.STRING)
    private InboundGbn inBoundGbn;

    private String remark;

    @OneToMany(mappedBy = "inbound", cascade = CascadeType.ALL)
    private List<InboundItem> inboundItemList= new ArrayList<>();

    public void addInboundItem(InboundItem inboundItem) {
        inboundItemList.add(inboundItem);
        inboundItem.setInbound(this);
    }

    @Builder
    public Inbound(String inboundNo, String inboundDate, Biz biz, Center center,
                                Customer customer, Supplier supplier, InboundGbn inBoundGbn, String remark) {
        this.inboundNo = inboundNo;
        this.inboundDate = inboundDate;
        this.biz = biz;
        this.center = center;
        this.customer = customer;
        this.supplier = supplier;
        this.inBoundGbn = inBoundGbn;
        this.remark = remark;
    }
}
