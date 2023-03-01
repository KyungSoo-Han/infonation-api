package kr.infonation.domain.inbound;

import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class InboundReq extends BaseEntity {

    @Id
    @Column(name = "inbound_no", unique = true, nullable = false)
    private String inboundNo;

    private String inboundDt;

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


}
