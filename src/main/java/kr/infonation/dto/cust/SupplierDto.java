package kr.infonation.dto.cust;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.base.Address;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.dto.biz.BizDto;
import lombok.Data;

@Data
public class SupplierDto {
    @Data
    @ApiModel("SupplierResponse")
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

        public Response(Supplier supplier) {
            this.id = supplier.getId();
            this.name = supplier.getName();
            this.engName = supplier.getEngName();
            this.address = supplier.getAddress();
            this.ownerName = supplier.getOwnerName();
            this.bizNo = supplier.getBizNo();
            this.bizType = supplier.getBizType();
            this.bizItem = supplier.getBizItem();
            this.email = supplier.getEmail();
            this.telNo = supplier.getTelNo();
            this.faxNo = supplier.getFaxNo();
            this.homepage = supplier.getHomepage();
            this.status = supplier.isStatus();
        }
    }
    @Data
    @ApiModel("SupplierCreateRequest")
    public static class CreateRequest{

        private String name;
        private Long bizId;
        private Long customerId;
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

        public Supplier toEntity(Biz biz, Customer customer){
            return Supplier.builder()
                    .name(name)
                    .biz(biz)
                    .customer(customer)
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
    @ApiModel("SupplierCreateResponse")
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
        private BizDto.Response bizDto;
        private CustomerDto.Response customerDto;

        public CreateResponse(Supplier supplier, BizDto.Response bizDto, CustomerDto.Response customerDto) {
            this.id = supplier.getId();
            this.name = supplier.getName();
            this.engName = supplier.getEngName();
            this.address = supplier.getAddress();
            this.ownerName = supplier.getOwnerName();
            this.bizNo = supplier.getBizNo();
            this.bizType = supplier.getBizType();
            this.bizItem = supplier.getBizItem();
            this.email = supplier.getEmail();
            this.telNo = supplier.getTelNo();
            this.faxNo = supplier.getFaxNo();
            this.homepage = supplier.getHomepage();
            this.status = supplier.isStatus();
            this.bizDto = bizDto;
            this.customerDto = customerDto;
        }
    }

}
