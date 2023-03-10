package kr.infonation.dto.biz;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.base.Address;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.biz.QBiz;
import lombok.AllArgsConstructor;
import lombok.Data;

public class BizDto {
    @Data
    @ApiModel("BizResponse")
    public static class Response {
        private Long id;
        private String name;
        private String engName;
        private String bizNo;
        private String ownerName;
        private String bizType;
        private String bizItem;
        private Address address;
        public Response(Biz biz) {
            this.id = biz.getId();
            this.name = biz.getName();
            this.engName = biz.getEngName();
            this.bizNo = biz.getBizNo();
            this.ownerName = biz.getOwnerName();
            this.bizType = biz.getBizType();
            this.bizItem = biz.getBizItem();
            this.address = biz.getAddress();

        }
    }
    @Data
    @ApiModel("BizCreateRequest")
    public static class CreateRequest{
        private String name;
        private String engName;
        private String bizNo;
        private String ownerName;
        private String bizType;
        private String bizItem;
        private Address address;

        public Biz toEntity() {
            return Biz.builder()
                    .bizNo(bizNo)
                    .ownerName(ownerName)
                    .address(address)
                    .bizItem(bizItem)
                    .bizType(bizType)
                    .engName(engName)
                    .name(name)
                    .build();
        }
    }

    @Data
    @ApiModel("BizCreateResponse")
    public static class CreateResponse {
        private Long id;
        private String name;
        private String engName;
        private String bizNo;
        private String ownerName;
        private String bizType;
        private String bizItem;
        private Address address;
        public CreateResponse(Biz biz) {
            this.id = biz.getId();
            this.name = biz.getName();
            this.engName = biz.getEngName();
            this.bizNo = biz.getBizNo();
            this.ownerName = biz.getOwnerName();
            this.bizType = biz.getBizType();
            this.bizItem = biz.getBizItem();
            this.address = biz.getAddress();

        }
    }

    @Data
    @ApiModel("BizUpdateRequest")
    public static class UpdateRequest {
        private String name;
        private String engName;
        private String bizNo;
        private String ownerName;
        private String bizType;
        private String bizItem;
        private Address address;
    }

    @Data
    @ApiModel("BizUpdateResponse")
    public static class UpdateResponse {
        private Long id;
        private String name;
        private String engName;
        private String bizNo;
        private String ownerName;
        private String bizType;
        private String bizItem;
        private Address address;
        public UpdateResponse(Biz biz) {
            this.id = biz.getId();
            this.name = biz.getName();
            this.engName = biz.getEngName();
            this.bizNo = biz.getBizNo();
            this.ownerName = biz.getOwnerName();
            this.bizType = biz.getBizType();
            this.bizItem = biz.getBizItem();
            this.address = biz.getAddress();

        }
    }


    @AllArgsConstructor
    @Data
    @ApiModel("BizDeleteResponse")
    public static class DeleteResponse {
        private Long bizId;

    }
}
