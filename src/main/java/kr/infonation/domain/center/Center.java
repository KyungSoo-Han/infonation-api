package kr.infonation.domain.center;

import kr.infonation.domain.base.Address;
import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.biz.Biz;
import kr.infonation.dto.center.CenterDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Center extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "center_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biz_id")
    private Biz biz;

    @Builder
    public Center(String name, Address address, Biz biz) {
        this.name = name;
        this.address = address;
        this.biz = biz;
    }

    public void update(CenterDto.UpdateRequest request, Biz biz) {
        this.name = request.getName();
        this.address= request.getAddress();
        this.biz = biz;
    }
}
