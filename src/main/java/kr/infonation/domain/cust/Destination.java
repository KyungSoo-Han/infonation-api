package kr.infonation.domain.cust;

import kr.infonation.domain.base.Address;
import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.biz.Biz;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.cust.DestinationDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Destination extends BaseEntity {

    @Column(name = "destination_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JoinColumn(name = "biz_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Biz biz;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

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
    public Destination(String name, Biz biz, Customer customer, String engName, Address address, String ownerName, String bizNo, String bizType, String bizItem, String email, String telNo, String faxNo, String homepage, boolean status) {
        this.name = name;
        this.biz = biz;
        this.customer = customer;
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


    public void update(DestinationDto.UpdateRequest request) {

        if (request.getName() != null) {
            this.name = request.getName();
        }
        if (request.getEngName() != null) {
            this.engName = request.getEngName();
        }
        if (request.getAddress() != null) {
            this.address = request.getAddress();
        }
        if (request.getOwnerName() != null) {
            this.ownerName = request.getOwnerName();
        }
        if (request.getBizNo() != null) {
            this.bizNo = request.getBizNo();
        }
        if (request.getBizType() != null) {
            this.bizType = request.getBizType();
        }
        if (request.getBizItem() != null) {
            this.bizItem = request.getBizItem();
        }
        if (request.getEmail() != null) {
            this.email = request.getEmail();
        }
        if (request.getTelNo() != null) {
            this.telNo = request.getTelNo();
        }
        if (request.getFaxNo() != null) {
            this.faxNo = request.getFaxNo();
        }
        if (request.getHomepage() != null) {
            this.homepage = request.getHomepage();
        }

        this.status = request.isStatus();
    }
}
