package kr.infonation.domain.zone;

import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.base.KeepType;
import kr.infonation.domain.base.ZoneType;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Zone extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private Long id;
    private String code;
    private String name;
    private boolean status;
    private boolean stagy;
    @Enumerated(EnumType.STRING)
    private ZoneType zoneType;
    @Enumerated(EnumType.STRING)
    private KeepType keepType;
    @JoinColumn(name = "biz_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Biz biz;
    @JoinColumn(name = "center_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Center center;

    @Builder
    public Zone(String code, String name, boolean status, boolean stagy, ZoneType zoneType, KeepType keepType, Biz biz, Center center) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.stagy = stagy;
        this.zoneType = zoneType;
        this.keepType = keepType;
        this.biz = biz;
        this.center = center;
    }
}
