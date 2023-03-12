package kr.infonation.domain.base;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class ItemStandard {
    private Integer width;
    private Integer height;
    private Integer depth;
    private Integer weight;
    private String barcode;

}
