package kr.infonation.domain.base;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class ItemStandard {
    private int width;
    private int height;
    private int depth;
    private int weight;
    private String barcode;

}
