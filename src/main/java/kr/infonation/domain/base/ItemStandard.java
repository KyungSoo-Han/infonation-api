package kr.infonation.domain.base;

import javax.persistence.Embeddable;

@Embeddable
public class ItemStandard {
    private int width;
    private int height;
    private int depth;
    private int weight;
    private String barcode;

}
