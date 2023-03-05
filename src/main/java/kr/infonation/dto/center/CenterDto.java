package kr.infonation.dto.center;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import kr.infonation.domain.base.Address;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.dto.biz.BizDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CenterDto {
    private Long bizId;
    private String bizName;
    private Long centerId;
    private String centerName;
    private String zipNo;
    private String zipAddr1;
    private String zipAddr2;
    @QueryProjection
    public CenterDto(Long centerId, String centerName, Address address, Long bizId, String bizName) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.zipNo = address.getZipNo();
        this.zipAddr1 = address.getZipAddr1();
        this.zipAddr2=address.getZipAddr2();
        this.bizId = bizId;
        this.bizName = bizName;
    }

    @Data
    @ApiModel("CenterCreateRequest")
    public static class CreateRequest{
        private String name;
        private Address address;
        private Long bizId;

        public Center toEntity(Biz biz){
            return Center.builder()
                    .name(name)
                    .biz(biz)
                    .address(address)
                    .build();
        }
    }

    @Data
    @ApiModel("CenterCreateResponse")
    public static class CreateResponse {
        private Long id;
        private String name;
        private Address address;
        private BizDto.Response bizDto;

        public CreateResponse(Long id, String name, Address address, BizDto.Response bizDto) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.bizDto = bizDto;
        }
    }

    @Data
    @ApiModel("CenterUpdateRequest")
    public static class UpdateRequest {
        private Long id;
        private String name;
        private Address address;
        private Long bizId;

    }
    @Data
    @ApiModel("CenterUpdateResponse")
    public static class UpdateResponse {
        private Long id;
        private String name;
        private Address address;
        private BizDto.Response bizDto;
        public UpdateResponse(Long id, String name, Address address, BizDto.Response bizDto) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.bizDto = bizDto;
        }

    }

    @Data
    @AllArgsConstructor
    @ApiModel("CenterDeleteResponse")
    public static class DeleteResponse {
        private final Long id;

    }

}
