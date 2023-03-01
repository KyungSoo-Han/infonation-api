package kr.infonation.domain.biz;

import kr.infonation.domain.base.Address;
import kr.infonation.domain.base.BaseEntity;
import kr.infonation.dto.biz.BizDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@DynamicUpdate
public class Biz extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "biz_id")
    private Long id;
    private String name;
    private String engName;
    private String bizNo;
    private String ownerName;
    private String bizType;
    private String bizItem;
    @Embedded
    private Address address;

    @Builder
    public Biz(String name, String engName, String bizNo, String ownerName, String bizType, String bizItem, Address address) {
        this.name = name;
        this.engName = engName;
        this.bizNo = bizNo;
        this.ownerName = ownerName;
        this.bizType = bizType;
        this.bizItem = bizItem;
        this.address = address;
    }

    public void update(BizDto.UpdateRequest request){
        if(StringUtils.hasText(request.getName()))
            this.name = request.getName();
        if(StringUtils.hasText(request.getEngName()))
            this.engName = request.getEngName();
        if(StringUtils.hasText(request.getBizNo()))
            this.bizNo = request.getBizNo();
        if(StringUtils.hasText(request.getOwnerName()))
            this.ownerName = request.getOwnerName();
        if(StringUtils.hasText(request.getBizType()))
            this.bizType = request.getBizType();
        if(StringUtils.hasText(request.getBizItem()))
            this.bizItem = request.getBizItem();
        if(!ObjectUtils.isEmpty(request.getAddress()))
            this.address = request.getAddress();
    }
}
