package kr.infonation.domain.outbound;

import kr.infonation.domain.base.OutboundGbn;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Destination;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Outbound {

    @Id
    @Column(name = "outbound_no", unique = true, nullable = false)
    private String outboundNo;

    @ManyToOne(fetch = LAZY)
    private Biz biz;

    @ManyToOne(fetch = LAZY)
    private Center center;

    @ManyToOne(fetch = LAZY)
    private Customer customer;

    @ManyToOne(fetch = LAZY)
    private Destination destination;

    private String outboundDate;

    private String outboundExpDate;

    @Enumerated(EnumType.STRING)
    private OutboundGbn outBoundGbn;

    private String remark;

    @OneToMany(mappedBy = "outbound", cascade = CascadeType.ALL)
    private List<OutboundItem> outboundItemList= new ArrayList<>();

    public void addOutboundItem(OutboundItem outboundItem) {
        outboundItemList.add(outboundItem);
        outboundItem.setOutbound(this);
    }

    @Builder
    public Outbound(String outboundNo, String outboundDate, Biz biz, Center center,
                   Customer customer, Destination destination, OutboundGbn outBoundGbn, String remark, String outboundExpDate) {
        this.outboundNo = outboundNo;
        this.outboundDate = outboundDate;
        this.biz = biz;
        this.center = center;
        this.customer = customer;
        this.destination = destination;
        this.outBoundGbn = outBoundGbn;
        this.remark = remark;
        this.outboundExpDate = outboundExpDate;
    }

}
