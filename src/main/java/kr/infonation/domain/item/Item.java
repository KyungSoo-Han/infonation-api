package kr.infonation.domain.item;

import kr.infonation.domain.base.ItemStandard;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String unit;
    @ManyToOne(fetch = FetchType.LAZY)
    private Biz biz;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Comment("품목 약식 명칭")
    private String sname;
    @Comment("품목 활성화 상태")
    private boolean status;
    @Comment("세트 여부")
    private boolean isSet;

    @Comment("케이스 입수량")
    private int caseEaQty;
    @Comment("박스 입수량")
    private int boxEaQty;
    @Comment("파렛트 입수량")
    private int palletEaQty;
    @Comment("안전재고량")
    private int safeStockQty;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "width",
                    column = @Column(name = "item_width")),
            @AttributeOverride(name = "height",
                    column = @Column(name = "item_height")),
            @AttributeOverride(name = "depth",
                    column = @Column(name = "item_depth")),
            @AttributeOverride(name = "weight",
                    column = @Column(name = "item_weight")),
            @AttributeOverride(name = "barcode",
                    column = @Column(name = "item_barcode"))
    })
    private ItemStandard itemStandard;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "width",
                    column = @Column(name = "case_width")),
            @AttributeOverride(name = "height",
                    column = @Column(name = "case_height")),
            @AttributeOverride(name = "depth",
                    column = @Column(name = "case_depth")),
            @AttributeOverride(name = "weight",
                    column = @Column(name = "case_weight")),
            @AttributeOverride(name = "barcode",
                    column = @Column(name = "case_barcode"))
    })
    private ItemStandard caseStandard;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "width",
                    column = @Column(name = "box_width")),
            @AttributeOverride(name = "height",
                    column = @Column(name = "box_height")),
            @AttributeOverride(name = "depth",
                    column = @Column(name = "box_depth")),
            @AttributeOverride(name = "weight",
                    column = @Column(name = "box_weight")),
            @AttributeOverride(name = "barcode",
                    column = @Column(name = "box_barcode"))
    })
    private ItemStandard boxStandard;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "width",
                    column = @Column(name = "pallet_width")),
            @AttributeOverride(name = "height",
                    column = @Column(name = "pallet_height")),
            @AttributeOverride(name = "depth",
                    column = @Column(name = "pallet_depth")),
            @AttributeOverride(name = "weight",
                    column = @Column(name = "pallet_weight")),
            @AttributeOverride(name = "barcode",
                    column = @Column(name = "pallet_barcode"))
    })
    private ItemStandard palletStandard;

    @Comment("유통기한 임박 기준일")
    private int nearExpDay;
    @Comment("출고 불가 기준일")
    private int nonDeliverDay;
    @Comment("제조일자 사용")
    private boolean isMakeDay;
    @Comment("제조일로부터 00일")
    private int fromMakeDay;
    @Comment("대표 로케이션")
    private String location;
    @Comment("품목 설명")
    private String description;

    @Builder
    public Item(String name, String unit, Biz biz, Customer customer, String sname, boolean status, boolean isSet, int caseEaQty, int boxEaQty, int palletEaQty, int safeStockQty, ItemStandard itemStandard, ItemStandard caseStandard, ItemStandard boxStandard, ItemStandard palletStandard, int nearExpDay, int nonDeliverDay, boolean isMakeDay, int fromMakeDay, String location, String description) {
        this.name = name;
        this.unit = unit;
        this.biz = biz;
        this.customer = customer;
        this.sname = sname;
        this.status = status;
        this.isSet = isSet;
        this.caseEaQty = caseEaQty;
        this.boxEaQty = boxEaQty;
        this.palletEaQty = palletEaQty;
        this.safeStockQty = safeStockQty;
        this.itemStandard = itemStandard;
        this.caseStandard = caseStandard;
        this.boxStandard = boxStandard;
        this.palletStandard = palletStandard;
        this.nearExpDay = nearExpDay;
        this.nonDeliverDay = nonDeliverDay;
        this.isMakeDay = isMakeDay;
        this.fromMakeDay = fromMakeDay;
        this.location = location;
        this.description = description;
    }
}
