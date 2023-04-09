package kr.infonation.dto.zone;

import kr.infonation.domain.base.KeepType;
import kr.infonation.domain.base.ZoneType;
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


    }

    @Data
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
