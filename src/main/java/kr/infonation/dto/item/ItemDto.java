package kr.infonation.dto.item;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.base.ItemStandard;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.cust.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Data
public class ItemDto {

    @Data
    @ApiModel("ItemCreateRequest")
    public static class CreateRequest{
        private String name;
        private String unit;
        private Long bizId;
        private Long customerId;
        private String sname;
        private boolean status;
        private boolean isSet;
        private int caseEaQty;
        private int boxEaQty;
        private int palletEaQty;
        private int safeStockQty;
        private ItemStandard itemStandard;
        private ItemStandard caseStandard;
        private ItemStandard boxStandard;
        private ItemStandard palletStandard;
        private int nearExpDay;
        private int nonDeliverDay;
        private boolean isMakeDay;
        private int fromMakeDay;
        private String location;
        private String description;

        public Item toEntity(Biz biz, Customer customer) {
            return Item.builder()
                    .name(name)
                    .unit(unit)
                    .biz(biz)
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
        private String sname;
        private boolean status;
        private boolean isSet;
        private int caseEaQty;
        private int boxEaQty;
        private int palletEaQty;
        private int safeStockQty;
        private ItemStandard itemStandard;
        private ItemStandard caseStandard;
        private ItemStandard boxStandard;
        private ItemStandard palletStandard;
        private int nearExpDay;
        private int nonDeliverDay;
        private boolean isMakeDay;
        private int fromMakeDay;
        private String location;
        private String description;

        public CreateResponse(Item item, BizDto.Response bizDto, CustomerDto.Response customerDto) {
            this.id = item.getId();
            this.name = item.getName();
            this.unit = item.getUnit();
            this.biz = bizDto;
            this.customer = customerDto;
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
}
