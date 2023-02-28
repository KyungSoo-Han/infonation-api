package kr.infonation.dto.cust;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.base.Address;
import kr.infonation.domain.cust.Customer;
import lombok.Data;

import javax.persistence.Embedded;

@Data
public class CustomerDto {
    @Data
    @ApiModel("CustomerResponse")
    public static class Response{
        private Long id;
        private String name;
        private String engName;
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

        public Response(Customer customer) {
            this.id = customer.getId();
            this.name = customer.getName();
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
            this.status = customer.isStatus();
        }
    }
    @Data
    @ApiModel("CustomerCreateRequest")
    public static class CreateRequest{

        private String name;
        private String engName;
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

        public Customer toEntity(){
            return Customer.builder()
                    .name(name)
                    .engName(engName)
                    .address(address)
                    .ownerName(ownerName)
                    .bizNo(bizNo)
                    .bizType(bizType)
                    .bizItem(bizItem)
                    .email(email)
                    .telNo(telNo)
                    .faxNo(faxNo)
                    .homepage(homepage)
                    .status(status)
                    .build();
        }
    }

    @Data
    @ApiModel("CustomerCreateResponse")
    public static class CreateResponse{
        private Long id;
        private String name;
        private String engName;
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

        public CreateResponse(Customer customer) {
            this.id = customer.getId();
            this.name = customer.getName();
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
            this.status = customer.isStatus();
        }
    }

}
