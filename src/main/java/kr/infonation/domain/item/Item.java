package kr.infonation.domain.item;

import kr.infonation.domain.base.ItemStandard;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.dto.item.ItemDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Item {

    @Column(name = "item_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String unit;

    @JoinColumn(name = "biz_id")
    @ManyToOne(fetch = LAZY)
    private Biz biz;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = LAZY)
    private Customer customer;

    @JoinColumn(name = "supplier_id")
    @ManyToOne(fetch = LAZY)
    private Supplier supplier;

    @Comment("품목 약식 명칭")
    private String sname;
    @Comment("품목 활성화 상태")
    private boolean status;
    @Comment("세트 여부")
    private boolean isSet;

    @Comment("케이스 입수량")
    private Integer caseEaQty;
    @Comment("박스 입수량")
    private Integer boxEaQty;
    @Comment("파렛트 입수량")
    private Integer palletEaQty;
    @Comment("안전재고량")
    private Integer safeStockQty;

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
    private Integer nearExpDay;
    @Comment("출고 불가 기준일")
    private Integer nonDeliverDay;
    @Comment("제조일자 사용")
    private boolean isMakeDay;
    @Comment("제조일로부터 00일")
    private Integer fromMakeDay;
    @Comment("대표 로케이션")
    private String location;
    @Comment("품목 설명")
    private String description;

    @Builder
    public Item(String name, String unit, Biz biz, Customer customer, Supplier supplier, String sname, boolean status,
                boolean isSet, Integer caseEaQty, Integer boxEaQty, Integer palletEaQty, Integer safeStockQty,
                ItemStandard itemStandard, ItemStandard caseStandard, ItemStandard boxStandard, ItemStandard palletStandard,
                Integer nearExpDay, Integer nonDeliverDay, boolean isMakeDay, Integer fromMakeDay, String location, String description) {
        this.name = name;
        this.unit = unit;
        this.biz = biz;
        this.customer = customer;
        this.supplier = supplier;
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

    public void update(ItemDto.UpdateRequest request, Biz biz, Customer customer, Supplier supplier) {
        this.name = request.getName();
        this.unit = request.getUnit();
        this.biz = biz;
        this.customer = customer;
        this.supplier = supplier;
        this.sname = request.getSname();
        this.status = request.isStatus();
        this.isSet = request.isSet();
        this.caseEaQty = request.getCaseEaQty();
        this.boxEaQty = request.getBoxEaQty();
        this.palletEaQty = request.getPalletEaQty();
        this.safeStockQty = request.getSafeStockQty();
        this.itemStandard = request.getItemStandard();
        this.caseStandard = request.getCaseStandard();
        this.boxStandard = request.getBoxStandard();
        this.palletStandard = request.getPalletStandard();
        this.nearExpDay = request.getNearExpDay();
        this.nonDeliverDay = request.getNonDeliverDay();
        this.isMakeDay = request.isMakeDay();
        this.fromMakeDay = request.getFromMakeDay();
        this.location = request.getLocation();
        this.description = request.getDescription();
    }
}
