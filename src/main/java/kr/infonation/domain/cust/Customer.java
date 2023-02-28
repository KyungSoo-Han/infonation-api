package kr.infonation.domain.cust;

import kr.infonation.domain.base.Address;
import kr.infonation.domain.base.BaseEntity;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    public Customer(String name, String engName, Address address, String ownerName,
                    String bizNo, String bizType, String bizItem, String email,
                    String telNo, String faxNo, String homepage, boolean status) {
        this.name = name;
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
