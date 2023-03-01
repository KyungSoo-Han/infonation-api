package kr.infonation.domain.cust;

import kr.infonation.domain.base.Address;
import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.biz.Biz;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.crypto.interfaces.PBEKey;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Customer extends BaseEntity {

    @Column(name = "customer_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "biz_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Biz biz;

    private String name;
    private String engName;
    @Embedded
    private Address address;
    private String ownerName;
    private String bizNo;
    private String bizType;
    private String bizItem;
    private String email;
    private String telNo;
    private String faxNo;
    private String homepage;
    private boolean status;

    @Builder
    public Customer(String name,  Biz biz, String engName, Address address, String ownerName,
                    String bizNo, String bizType, String bizItem, String email,
                    String telNo, String faxNo, String homepage, boolean status) {
        this.name = name;
        this.biz = biz;
        this.engName = engName;
        this.address = address;
        this.ownerName = ownerName;
        this.bizNo = bizNo;
        this.bizType = bizType;
        this.bizItem = bizItem;
        this.email = email;
        this.telNo = telNo;
        this.faxNo = faxNo;
        this.homepage = homepage;
        this.status = status;
    }
}