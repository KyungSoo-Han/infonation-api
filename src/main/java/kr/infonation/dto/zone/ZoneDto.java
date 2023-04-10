package kr.infonation.dto.zone;

import kr.infonation.domain.base.KeepType;
import kr.infonation.domain.base.ZoneType;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.zone.Zone;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ZoneDto {

    @Data
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
