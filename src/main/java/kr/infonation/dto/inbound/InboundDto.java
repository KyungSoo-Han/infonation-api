package kr.infonation.dto.inbound;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.inbound.InboundGbn;
import kr.infonation.domain.inbound.Inbound;
import kr.infonation.domain.inbound.InboundItem;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.center.CenterDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.cust.SupplierDto;
import kr.infonation.dto.item.ItemDto;
import lombok.Data;

public class InboundDto {

    @Data
    @ApiModel("InboundCreateRequest")
    public static class CreateRequest{

        private Long bizId;
        private Long centerId;
        private Long customerId;
        private Long supplierId;
        private String inboundDate;
        private InboundGbn inboundGbn;
        private String remark;

        private Long itemId;
        private int qty;
        private int price;
        private boolean status;
        private String subRemark;
        private String expDate;
        private String makeLotNo;
        private String makeDate;


        public Inbound toEntity(String inboundNo, Biz biz, Center center, Customer customer, Supplier supplier){
            Inbound inbound = Inbound.builder()
                    .biz(biz)
                    .center(center)
                    .customer(customer)
                    .supplier(supplier)
                    .remark(remark)
                    .inBoundGbn(inboundGbn)
                    .inboundDate(inboundDate)
                    .inboundNo(inboundNo)
                    .build();
            return inbound;
        }

        public InboundItem toItemEntity(Inbound inbound, Item item,  int qty,int price, String expDate, String makeLotNo, String makeDate, String subRemark ){
            InboundItem inboundItem = InboundItem.builder()
                    .inbound(inbound)
                    .item(item)
                    .expDate(expDate)
                    .qty(qty)
                    .price(price)
                    .makeLotNo(makeLotNo)
                    .makeDate(makeDate)
                    .subRemark(subRemark)
                    .build();
            return inboundItem;
        }
    }

    @Data
    @ApiModel("InboundCreateResponse")
    public static class CreateResponse{
        private BizDto.Response bizDto;
        private CenterDto centerDto;
        private CustomerDto.Response customerDto;
        private SupplierDto.Response supplierDto;
        private String remark;
        private String inboundDate;
        private String inboundNo;
        private InboundGbn inboundGbn;


        private ItemDto.Response itemDto ;
        private int qty;
        private int price;
        private boolean status;
        private String subRemark;
        private String expDate;
        private String makeLotNo;
        private String makeDate;


        public CreateResponse(Inbound inbound, InboundItem inboundItem, BizDto.Response bizDto, CenterDto centerDto, CustomerDto.Response customerDto,
                              SupplierDto.Response supplierDto, ItemDto.Response itemDto) {
            this.bizDto = bizDto;
            this.centerDto = centerDto;
            this.customerDto = customerDto;
            this.supplierDto = supplierDto;
            this.remark = inbound.getRemark();
            this.inboundDate = inbound.getInboundDate();
            this.inboundNo = inbound.getInboundNo();
            this.inboundGbn = inbound.getInBoundGbn();

            this.itemDto = itemDto;
            this.qty = inboundItem.getQty();
            this.price = inboundItem.getPrice();
            this.status = inboundItem.isStatus();
            this.subRemark = inboundItem.getSubRemark();
            this.expDate = inboundItem.getExpDate();
            this.makeLotNo = inboundItem.getMakeLotNo();
            this.makeDate = inboundItem.getMakeDate();


        }
    }

}
