package kr.infonation.domain.location;

import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.zone.Zone;
import lombok.AccessLevel;
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
    private String type;
    @JoinColumn(name = "zone_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;

}
