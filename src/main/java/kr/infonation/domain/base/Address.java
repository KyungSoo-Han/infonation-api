package kr.infonation.domain.base;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String zipNo;
    private String zipAddr1;
    private String zipAddr2;

    //JPA 스펙상 만들어 놓은 것(@Entity, @Embeddable 등)
    public Address() {

    }

    public Address(String zipNo, String zipAddr1, String zipAddr2) {
        this.zipNo = zipNo;
        this.zipAddr1 = zipAddr1;
        this.zipAddr2 = zipAddr2;
    }
}
