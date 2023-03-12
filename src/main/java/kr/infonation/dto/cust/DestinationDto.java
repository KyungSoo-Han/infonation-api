package kr.infonation.dto.cust;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.base.Address;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Destination;
import kr.infonation.dto.biz.BizDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DestinationDto {
    @Data
    @ApiModel("DestinationResponse")
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

        public Response(Destination destination) {
            this.id = destination.getId();
            this.name = destination.getName();
            this.engName = destination.getEngName();
            this.address = destination.getAddress();
            this.ownerName = destination.getOwnerName();
            this.bizNo = destination.getBizNo();
            this.bizType = destination.getBizType();
            this.bizItem = destination.getBizItem();
            this.email = destination.getEmail();
            this.telNo = destination.getTelNo();
            this.faxNo = destination.getFaxNo();
            this.homepage = destination.getHomepage();
            this.status = destination.isStatus();
        }
    }
    @Data
    @ApiModel("DestinationCreateRequest")
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

        public Destination toEntity(Biz biz, Customer customer){
            return Destination.builder()
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
    @ApiModel("DestinationCreateResponse")
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

        public CreateResponse(Destination destination, BizDto.Response bizDto, CustomerDto.Response customerDto) {
            this.id = destination.getId();
            this.name = destination.getName();
            this.engName = destination.getEngName();
            this.address = destination.getAddress();
            this.ownerName = destination.getOwnerName();
            this.bizNo = destination.getBizNo();
            this.bizType = destination.getBizType();
            this.bizItem = destination.getBizItem();
            this.email = destination.getEmail();
            this.telNo = destination.getTelNo();
            this.faxNo = destination.getFaxNo();
            this.homepage = destination.getHomepage();
            this.status = destination.isStatus();
            this.bizDto = bizDto;
            this.customerDto = customerDto;
        }
    }

    @Data
    @ApiModel("DestinationUpdateRequest")
    public static class UpdateRequest {
        private Long bizId;
        private Long customerId;
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
    }

    @Data
    @ApiModel("DestinationUpdateResponse")
    public static class UpdateResponse{
        private Long id;
        private Long bizId;
        private Long customerId;
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

        public UpdateResponse(Destination destination, Long bizId, Long customerId) {
            this.id = destination.getId();
            this.bizId = bizId;
            this.customerId = customerId;
            this.name = destination.getName();
            this.engName = destination.getEngName();
            this.address = destination.getAddress();
            this.ownerName = destination.getOwnerName();
            this.bizNo = destination.getBizNo();
            this.bizType = destination.getBizType();
            this.bizItem = destination.getBizItem();
            this.email = destination.getEmail();
            this.telNo = destination.getTelNo();
            this.faxNo = destination.getFaxNo();
            this.homepage = destination.getHomepage();
            this.status = destination.isStatus();
        }
    }

    @Data
    @AllArgsConstructor
    public static class DeleteResponse {
        private Long id;

    }
}
