package kr.infonation.dto.inbound;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.Api;
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
import lombok.EqualsAndHashCode;

import java.util.List;

public class InboundDto {

    @Data
    @ApiModel("InboundResponse")
    @EqualsAndHashCode(of = "inboundNo")
    public static class Response {
        private Long bizId;
        private String bizName;
        private Long centerId;
        private String centerName;
        private Long customerId;
        private String customerName;
        private Long supplierId;
        private String supplierName;

        private String remark;
        private String inboundDate;
        private String inboundNo;
        private InboundGbn inboundGbn;

        private List<InboundDto.ItemResponse> ItemResponse ;

        public Response(Long bizId, String bizName, Long centerId, String centerName, Long customerId, String customerName,
                        Long supplierId, String supplierName, String remark, String inboundDate, String inboundNo, InboundGbn inboundGbn) {
            this.bizId = bizId;
            this.bizName = bizName;
            this.centerId = centerId;
            this.centerName = centerName;
            this.customerId = customerId;
            this.customerName = customerName;
            this.supplierId = supplierId;
            this.supplierName = supplierName;
            this.remark = remark;
            this.inboundDate = inboundDate;
            this.inboundNo = inboundNo;
            this.inboundGbn = inboundGbn;
        }

        public Response(Long bizId, String bizName, Long centerId, String centerName, Long customerId, String customerName,
                        Long supplierId, String supplierName, String remark, String inboundDate, String inboundNo, InboundGbn inboundGbn, List<InboundDto.ItemResponse> itemResponse) {
            this.bizId = bizId;
            this.bizName = bizName;
            this.centerId = centerId;
            this.centerName = centerName;
            this.customerId = customerId;
            this.customerName = customerName;
            this.supplierId = supplierId;
            this.supplierName = supplierName;
            this.remark = remark;
            this.inboundDate = inboundDate;
            this.inboundNo = inboundNo;
            this.inboundGbn = inboundGbn;
            this.ItemResponse = itemResponse;
        }
    }

    @Data
    @ApiModel("InboundItemResponse")
    public static class ItemResponse {
        private String inboundNo;
        private Long inboundSeq;
        private Long itemId;
        private String itemName;
        private int qty;
        private int price;
        private boolean status;
        private String subRemark;
        private String expDate;
        private String makeLotNo;
        private String makeDate;

        public ItemResponse(String inboundNo, Long inboundSeq, Long itemId, String itemName,
                            int qty, int price, boolean status, String subRemark, String expDate, String makeLotNo, String makeDate) {
            this.inboundNo = inboundNo;
            this.inboundSeq = inboundSeq;
            this.itemId = itemId;
            this.itemName = itemName;
            this.qty = qty;
            this.price = price;
            this.status = status;
            this.subRemark = subRemark;
            this.expDate = expDate;
            this.makeLotNo = makeLotNo;
            this.makeDate = makeDate;
        }
    }

    @Data
    @ApiModel("InboundCreateRequest")
    public static class CreateRequest {

        private Long bizId;
        private Long centerId;
        private Long customerId;
        private Long supplierId;
        private String inboundDate;
        private InboundGbn inboundGbn;
        private String remark;
        private List<ItemCreateRequest> itemCreateRequest;

        public Inbound toEntity(String inboundNo, Biz biz, Center center, Customer customer, Supplier supplier) {
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
    }

    @Data
    @ApiModel("InboundItemCreateRequest")
    public static class ItemCreateRequest {

        private Long itemId;
        private int qty;
        private int price;
        private boolean status;
        private String subRemark;
        private String expDate;
        private String makeLotNo;
        private String makeDate;

        public InboundItem toEntity(Inbound inbound, Item item, int qty, int price, String expDate, String makeLotNo, String makeDate, String subRemark) {
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
    public static class CreateResponse {
        private Long bizId;
        private String bizName;
        private Long centerId;
        private String centerName;
        private Long customerId;
        private String customerName;
        private Long supplierId;
        private String supplierName;

        private String remark;
        private String inboundDate;
        private String inboundNo;
        private InboundGbn inboundGbn;
        private List<ItemCreateResponse> itemCreateResponses ;

        public CreateResponse(Inbound inbound, Long bizId, String bizName, Long centerId, String centerName,
                              Long customerId, String customerName, Long supplierId, String supplierName, List<ItemCreateResponse> itemCreateResponses) {
            this.bizId = bizId;
            this.bizName = bizName;
            this.centerId = centerId;
            this.centerName = centerName;
            this.customerId = customerId;
            this.customerName = customerName;
            this.supplierId = supplierId;
            this.supplierName =supplierName;

            this.remark = inbound.getRemark();
            this.inboundDate = inbound.getInboundDate();
            this.inboundNo = inbound.getInboundNo();
            this.inboundGbn = inbound.getInBoundGbn();
            this.itemCreateResponses = itemCreateResponses;

        }
    }

    @Data
    @ApiModel("InboundItemCreateResponse")
    public static class ItemCreateResponse{

        private Long itemId;
        private String itemName;
        private int qty;
        private int price;
        private boolean status;
        private String subRemark;
        private String expDate;
        private String makeLotNo;
        private String makeDate;

        public ItemCreateResponse(InboundItem inboundItem, Long itemId, String itemName) {
            this.itemId = itemId;
            this.itemName = itemName;
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
