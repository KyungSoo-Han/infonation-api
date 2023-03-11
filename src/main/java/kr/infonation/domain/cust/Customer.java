package kr.infonation.domain.cust;

import kr.infonation.domain.base.Address;
import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.biz.Biz;
import kr.infonation.dto.cust.CustomerDto;
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

    public void update(CustomerDto.UpdateRequest customer) {
       /* this.name = customer.getName();
        this.engName = customer.getEngName();
        this.address = customer.getAddress();
        this.ownerName = customer.getOwnerName();
        this.bizNo = customer.getBizNo();
        this.bizType = customer.getBizType();
        this.bizItem = customer.getBizItem();
        this.email = customer.getEmail();
        this.telNo = customer.getTelNo();
        this.faxNo = customer.getFaxNo();
        this.homepage = customer.getHomepage();
        this.status = customer.isStatus();*/

        if (customer.getName() != null) {
            this.name = customer.getName();
        }
        if (customer.getEngName() != null) {
            this.engName = customer.getEngName();
        }
        if (customer.getAddress() != null) {
            this.address = customer.getAddress();
        }
        if (customer.getOwnerName() != null) {
            this.ownerName = customer.getOwnerName();
        }
        if (customer.getBizNo() != null) {
            this.bizNo = customer.getBizNo();
        }
        if (customer.getBizType() != null) {
            this.bizType = customer.getBizType();
        }
        if (customer.getBizItem() != null) {
            this.bizItem = customer.getBizItem();
        }
        if (customer.getEmail() != null) {
            this.email = customer.getEmail();
        }
        if (customer.getTelNo() != null) {
            this.telNo = customer.getTelNo();
        }
        if (customer.getFaxNo() != null) {
            this.faxNo = customer.getFaxNo();
        }
        if (customer.getHomepage() != null) {
            this.homepage = customer.getHomepage();
        }

        this.status = customer.isStatus();
    }
}
