package kr.infonation.dto.location;

import kr.infonation.domain.base.LocationType;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.location.Location;
import kr.infonation.domain.zone.Zone;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

public class LocationDto {

    @Data
    public static class CreateRequest{

        private String code;
        private String name;
        private LocationType locationType;
        private Integer axisX;
        private Integer axisY;
        private Integer axisZ;
        private boolean status;
        private Long bizId;
        private Long centerId;
        private Long zoneId;

        public Location toEntity(Zone zone, Center center, Biz biz){
            return Location.builder()
                    .axisY(axisY)
                    .code(code)
                    .axisZ(axisZ)
                    .axisX(axisX)
                    .locationType(locationType)
                    .zone(zone)
                    .center(center)
                    .biz(biz)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    public static class CreateResponse{
        private Long id;
        private String code;
        private String name;
        private LocationType locationType;
        private Integer axisX;
        private Integer axisY;
        private Integer axisZ;
        private boolean status;
        private Long bizId;
        private Long centerId;
        private Long zoneId;
    }
}
