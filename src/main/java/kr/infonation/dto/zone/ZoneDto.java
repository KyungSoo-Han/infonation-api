package kr.infonation.dto.zone;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.base.KeepType;
import kr.infonation.domain.base.ZoneType;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.zone.Zone;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ZoneDto {

    @Data
    @ApiModel("ZoneResponse")
    public static class DataResponse{
        private Long id;
        private String code;
        private String name;
        private Long bizId;
        private Long centerId;
        private ZoneType zoneType;
        private KeepType keepType;
        private boolean stagy;
        private boolean status;

        public DataResponse(Zone zone) {
            this.id = zone.getId();
            this.code = zone.getCode();
            this.name = zone.getName();
            this.bizId = zone.getBiz().getId();
            this.centerId = zone.getCenter().getId();
            this.zoneType = zone.getZoneType();
            this.keepType = zone.getKeepType();
            this.stagy = zone.isStagy();
            this.status = zone.isStatus();
        }
    }

    @Data
    @ApiModel("ZoneCreateRequest")
    public static class CreateRequest{
        private Long bizId;
        private Long centerId;
        private String code;
        private String name;
        private ZoneType zoneType;
        private KeepType keepType;
        private boolean stagy;
        private boolean status;

        public Zone toEntity(Biz biz, Center center, String code, String name, ZoneType zoneType, KeepType keepType, boolean status, boolean stagy){
            return Zone.builder().biz(biz)
                    .stagy(stagy)
                    .code(code)
                    .zoneType(zoneType)
                    .keepType(keepType)
                    .center(center)
                    .name(name)
                    .status(status)
                    .build();
        }

    }

    @Data
    @AllArgsConstructor
    @ApiModel("ZoneCreateResponse")
    public static class CreateResponse{
        private Long id;
        private String code;
        private String name;
        private Long bizId;
        private Long centerId;
        private ZoneType zoneType;
        private KeepType keepType;
        private boolean stagy;
        private boolean status;

    }
}
