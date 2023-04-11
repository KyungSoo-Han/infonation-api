package kr.infonation.domain.location;

import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.base.LocationType;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.zone.Zone;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Getter
@Entity
public class Location extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private LocationType locationType;
    private Integer axisX;
    private Integer axisY;
    private Integer axisZ;
    private boolean status;
    @JoinColumn(name = "biz_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Biz biz;

    @JoinColumn(name = "center_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Center center;

    @JoinColumn(name = "zone_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;

    @Builder
    public Location(String code, String name, LocationType locationType, Integer axisX, Integer axisY, Integer axisZ,
                    boolean status, Biz biz, Center center, Zone zone) {
        this.code = code;
        this.name = name;
        this.locationType = locationType;
        this.axisX = axisX;
        this.axisY = axisY;
        this.axisZ = axisZ;
        this.status = status;
        this.biz = biz;
        this.center = center;
        this.zone = zone;
    }
}
