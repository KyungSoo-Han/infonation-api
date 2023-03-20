package kr.infonation.dto.item;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.base.ItemStandard;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.cust.SupplierDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Data
public class ItemDto {

    @Data
    @ApiModel("ItemResponse")
    public static class Response {
        private Long id;
        private Long customerId;
        private Long supplierId;
        private String name;
        private String unit;
        private String sname;
        private boolean status;
        private boolean isSet;
        private Integer caseEaQty;
        private Integer boxEaQty;
        private Integer palletEaQty;
        private Integer safeStockQty;
        private ItemStandard itemStandard;
        private ItemStandard caseStandard;
        private ItemStandard boxStandard;
        private ItemStandard palletStandard;
        private Integer nearExpDay;
        private Integer nonDeliverDay;
        private boolean isMakeDay;
        private Integer fromMakeDay;
        private String location;
        private String description;

        public Response(Item item) {
            this.id = item.getId();
            this.customerId = item.getCustomer().getId();
            this.supplierId = item.getSupplier().getId();
            this.name = item.getName();
            this.unit = item.getUnit();
            this.sname = item.getSname();
            this.status = item.isStatus();
            this.isSet = item.isSet();
            this.caseEaQty = item.getCaseEaQty();
            this.boxEaQty = item.getBoxEaQty();
            this.palletEaQty = item.getPalletEaQty();
            this.safeStockQty = item.getSafeStockQty();
            this.itemStandard = item.getItemStandard();
            this.caseStandard = item.getCaseStandard();
            this.boxStandard = item.getBoxStandard();
            this.palletStandard = item.getPalletStandard();
            this.nearExpDay = item.getNearExpDay();
            this.nonDeliverDay = item.getNonDeliverDay();
            this.isMakeDay = item.isMakeDay();
            this.fromMakeDay = item.getFromMakeDay();
            this.location = item.getLocation();
            this.description = item.getDescription();
        }
    }
    @Data
    @ApiModel("ItemCreateRequest")
    public static class CreateRequest{
        private String name;
        private String unit;
        private Long bizId;
        private Long customerId;
        private Long supplierId;
        private String sname;
        private boolean status;
        private boolean isSet;
        private Integer caseEaQty;
        private Integer boxEaQty;
        private Integer palletEaQty;
        private Integer safeStockQty;
        private ItemStandard itemStandard;
        private ItemStandard caseStandard;
        private ItemStandard boxStandard;
        private ItemStandard palletStandard;
        private Integer nearExpDay;
        private Integer nonDeliverDay;
        private boolean isMakeDay;
        private Integer fromMakeDay;
        private String location;
        private String description;

        public Item toEntity(Biz biz, Customer customer, Supplier supplier) {
            return Item.builder()
                    .name(name)
                    .unit(unit)
                    .biz(biz)
                    .supplier(supplier)
                    .customer(customer)
                    .sname(sname)
                    .status(status)
                    .isSet(isSet)
                    .caseEaQty(caseEaQty)
                    .boxEaQty(boxEaQty)
                    .palletEaQty(palletEaQty)
                    .safeStockQty(safeStockQty)
                    .itemStandard(itemStandard)
                    .caseStandard(caseStandard)
                    .boxStandard(boxStandard)
                    .palletStandard(palletStandard)
                    .nearExpDay(nearExpDay)
                    .nonDeliverDay(nonDeliverDay)
                    .isMakeDay(isMakeDay)
                    .fromMakeDay(fromMakeDay)
                    .location(location)
                    .description(description)
                    .build();
        }

    }

    @Data
    @ApiModel("ItemCreateResponse")
    public static class CreateResponse {
        private Long id;
        private String name;
        private String unit;
        private BizDto.Response biz;
        private CustomerDto.Response customer;
        private SupplierDto.Response supplier;
        private String sname;
        private boolean status;
        private boolean isSet;
        private Integer caseEaQty;
        private Integer boxEaQty;
        private Integer palletEaQty;
        private Integer safeStockQty;
        private ItemStandard itemStandard;
        private ItemStandard caseStandard;
        private ItemStandard boxStandard;
        private ItemStandard palletStandard;
        private Integer nearExpDay;
        private Integer nonDeliverDay;
        private boolean isMakeDay;
        private Integer fromMakeDay;
        private String location;
        private String description;

        public CreateResponse(Item item, BizDto.Response bizDto,
                              CustomerDto.Response customerDto, SupplierDto.Response supplierDto ) {
            this.id = item.getId();
            this.name = item.getName();
            this.unit = item.getUnit();
            this.biz = bizDto;
            this.customer = customerDto;
            this.supplier = supplierDto;
            this.sname = item.getSname();
            this.status = item.isStatus();
            this.isSet = item.isSet();
            this.caseEaQty = item.getCaseEaQty();
            this.boxEaQty = item.getBoxEaQty();
            this.palletEaQty = item.getPalletEaQty();
            this.safeStockQty = item.getSafeStockQty();
            this.itemStandard = item.getItemStandard();
            this.caseStandard = item.getCaseStandard();
            this.boxStandard = item.getBoxStandard();
            this.palletStandard = item.getPalletStandard();
            this.nearExpDay = item.getNearExpDay();
            this.nonDeliverDay = item.getNonDeliverDay();
            this.isMakeDay = item.isMakeDay();
            this.fromMakeDay = item.getFromMakeDay();
            this.location = item.getLocation();
            this.description = item.getDescription();
        }
    }

    @Data
    public static class UpdateRequest {
        private Long id;
        private String name;
        private String unit;
        private Long bizId;
        private Long customerId;
        private Long supplierId;
        private String sname;
        private boolean status;
        private boolean isSet;
        private Integer caseEaQty;
        private Integer boxEaQty;
        private Integer palletEaQty;
        private Integer safeStockQty;
        private ItemStandard itemStandard;
        private ItemStandard caseStandard;
        private ItemStandard boxStandard;
        private ItemStandard palletStandard;
        private Integer nearExpDay;
        private Integer nonDeliverDay;
        private boolean isMakeDay;
        private Integer fromMakeDay;
        private String location;
        private String description;

    }

    @Data
    public static class UpdateResponse {
        private Long id;
        private String name;
        private String unit;
        private Long bizId;
        private Long customerId;
        private Long supplierId;
        private String sname;
        private boolean status;
        private boolean isSet;
        private Integer caseEaQty;
        private Integer boxEaQty;
        private Integer palletEaQty;
        private Integer safeStockQty;
        private ItemStandard itemStandard;
        private ItemStandard caseStandard;
        private ItemStandard boxStandard;
        private ItemStandard palletStandard;
        private Integer nearExpDay;
        private Integer nonDeliverDay;
        private boolean isMakeDay;
        private Integer fromMakeDay;
        private String location;
        private String description;

        public UpdateResponse(Item item, Long bizId,Long customerId, Long supplierId ) {
            this.id = item.getId();
            this.name = item.getName();
            this.unit = item.getUnit();
            this.bizId = bizId;
            this.customerId = customerId;
            this.supplierId = supplierId;
            this.sname = item.getSname();
            this.status = item.isStatus();
            this.isSet = item.isSet();
            this.caseEaQty = item.getCaseEaQty();
            this.boxEaQty = item.getBoxEaQty();
            this.palletEaQty = item.getPalletEaQty();
            this.safeStockQty = item.getSafeStockQty();
            this.itemStandard = item.getItemStandard();
            this.caseStandard = item.getCaseStandard();
            this.boxStandard = item.getBoxStandard();
            this.palletStandard = item.getPalletStandard();
            this.nearExpDay = item.getNearExpDay();
            this.nonDeliverDay = item.getNonDeliverDay();
            this.isMakeDay = item.isMakeDay();
            this.fromMakeDay = item.getFromMakeDay();
            this.location = item.getLocation();
            this.description = item.getDescription();
        }
    }

    @Data
    @AllArgsConstructor
    public static class DeleteResponse {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    public static class ExcelUpload{
        private String name;
        private String sname;
        private boolean status;
        private boolean isSet;
        private boolean isMakeDay;
        private Integer fromMakeDay;
        private String description;


        public Item toEntity(Biz biz, Customer customer, Supplier supplier) {
            return Item.builder()
                    .name(name)
                    .biz(biz)
                    .supplier(supplier)
                    .customer(customer)
                    .sname(sname)
                    .status(status)
                    .isSet(isSet)
                    .isMakeDay(isMakeDay)
                    .fromMakeDay(fromMakeDay)
                    .description(description)
                    .build();
        }
    }

}
