package kr.infonation.dto.outbound;


import io.swagger.annotations.ApiModel;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Destination;
import kr.infonation.domain.outbound.OutboundGbn;
import kr.infonation.domain.outbound.OutboundItem;
import kr.infonation.domain.outbound.Outbound;
import kr.infonation.domain.outbound.OutboundItem;
import kr.infonation.domain.item.Item;
import kr.infonation.domain.outbound.Outbound;
import kr.infonation.domain.outbound.OutboundGbn;
import kr.infonation.dto.outbound.OutboundDto;
import kr.infonation.dto.outbound.OutboundDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class OutboundDto {

    @Data
    @ApiModel("OutboundResponse")
    @EqualsAndHashCode(of = "outboundNo")
    public static class Response {
        private Long bizId;
        private String bizName;
        private Long centerId;
        private String centerName;
        private Long customerId;
        private String customerName;
        private Long destinationId;
        private String destinationName;
        private String remark;
        private String outboundDate;
        private String outboundNo;
        private OutboundGbn outboundGbn;
        private String outboundExpDate;
        private List<OutboundDto.ItemResponse> ItemResponse ;

        public Response(Long bizId, String bizName, Long centerId, String centerName, Long customerId, String customerName,
                        Long destinationId, String destinationName, String remark, String outboundDate, String outboundNo, OutboundGbn outboundGbn, String outboundExpDate) {
            this.bizId = bizId;
            this.bizName = bizName;
            this.centerId = centerId;
            this.centerName = centerName;
            this.customerId = customerId;
            this.customerName = customerName;
            this.destinationId = destinationId;
            this.destinationName = destinationName;
            this.remark = remark;
            this.outboundDate = outboundDate;
            this.outboundNo = outboundNo;
            this.outboundGbn = outboundGbn;
            this.outboundExpDate = outboundExpDate;
        }

        public Response(Long bizId, String bizName, Long centerId, String centerName, Long customerId, String customerName,
                        Long destinationId, String destinationName, String remark, String outboundDate, String outboundNo, OutboundGbn outboundGbn, String outboundExpDate, List<OutboundDto.ItemResponse> itemResponse) {
            this.bizId = bizId;
            this.bizName = bizName;
            this.centerId = centerId;
            this.centerName = centerName;
            this.customerId = customerId;
            this.customerName = customerName;
            this.destinationId = destinationId;
            this.destinationName = destinationName;
            this.remark = remark;
            this.outboundDate = outboundDate;
            this.outboundNo = outboundNo;
            this.outboundGbn = outboundGbn;
            this.ItemResponse = itemResponse;
            this.outboundExpDate = outboundExpDate;
        }
    }

    @Data
    @ApiModel("OutboundItemResponse")
    public static class ItemResponse {
        private String outboundNo;
        private Long outboundSeq;
        private Long itemId;
        private String itemName;
        private int qty;
        private int price;
        private boolean status;
        private String subRemark;
        private String expDate;
        private String makeLotNo;
        private String makeDate;

        public ItemResponse(String outboundNo, Long outboundSeq, Long itemId, String itemName,
                            int qty, int price, boolean status, String subRemark, String expDate, String makeLotNo, String makeDate) {
            this.outboundNo = outboundNo;
            this.outboundSeq = outboundSeq;
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
    @ApiModel("OutboundCreateRequest")
    public static class CreateRequest {

        private String outboundNo;
        private Long bizId;
        private Long centerId;
        private Long customerId;
        private Long destinationId;
        private String outboundDate;
        private OutboundGbn outboundGbn;
        private String outboundExpDate;
        private String remark;
        private List<OutboundDto.ItemCreateRequest> itemCreateRequest;

        public Outbound toEntity(String outboundNo, Biz biz, Center center, Customer customer, Destination destination) {
            Outbound outbound = Outbound.builder()
                    .biz(biz)
                    .center(center)
                    .customer(customer)
                    .destination(destination)
                    .remark(remark)
                    .outboundExpDate(outboundExpDate)
                    .outBoundGbn(outboundGbn)
                    .outboundDate(outboundDate)
                    .outboundNo(outboundNo)
                    .build();
            return outbound;
        }
    }

    @Data
    @ApiModel("OutboundCreateResponse")
    public static class CreateResponse {
        private Long bizId;
        private String bizName;
        private Long centerId;
        private String centerName;
        private Long customerId;
        private String customerName;
        private Long destinationId;
        private String destinationName;

        private String remark;
        private String outboundDate;
        private String outboundNo;
        private OutboundGbn outboundGbn;
        private String outboundExpDate;

        private List<OutboundDto.ItemCreateResponse> itemCreateResponse ;

        public CreateResponse(Outbound outbound, Long bizId, String bizName, Long centerId, String centerName,
                              Long customerId, String customerName, Long destinationId, String destinationName, List<ItemCreateResponse> itemCreateResponse
        ) {
            this.bizId = bizId;
            this.bizName = bizName;
            this.centerId = centerId;
            this.centerName = centerName;
            this.customerId = customerId;
            this.customerName = customerName;
            this.destinationId = destinationId;
            this.destinationName =destinationName;

            this.remark = outbound.getRemark();
            this.outboundDate = outbound.getOutboundDate();
            this.outboundNo = outbound.getOutboundNo();
            this.outboundGbn = outbound.getOutBoundGbn();
            this.outboundExpDate = outbound.getOutboundExpDate();
            this.itemCreateResponse = itemCreateResponse;

        }
    }
    @Data
    @ApiModel("OutboundItemCreateRequest")
    public static class ItemCreateRequest {
        private Long outboundSeq;
        private Long itemId;
        private Integer qty;
        private Integer price;
        private boolean status;
        private String subRemark;
        private String expDate;
        private String makeLotNo;
        private String makeDate;

        public OutboundItem toEntity(Outbound outbound, Item item, Integer qty, Integer price, String expDate, String makeLotNo, String makeDate, String subRemark) {
            OutboundItem outboundItem = OutboundItem.builder()
                    .outbound(outbound)
                    .item(item)
                    .item(item) 
                    .expDate(expDate)
                    .qty(qty)
                    .price(price)
                    .makeLotNo(makeLotNo)
                    .makeDate(makeDate)
                    .subRemark(subRemark)
                    .build();
            return outboundItem;
        }
    }
    @Data
    @ApiModel("OutboundItemCreateResponse")
    public static class ItemCreateResponse{
        private Long outboundSeq;
        private Long itemId;
        private String itemName;
        private int qty;
        private int price;
        private boolean status;
        private String subRemark;
        private String expDate;
        private String makeLotNo;
        private String makeDate;

        public ItemCreateResponse(OutboundItem outboundItem, Long itemId, String itemName) {
            this.outboundSeq = outboundItem.getOutboundSeq();
            this.itemId = itemId;
            this.itemName = itemName;
            this.qty = outboundItem.getQty();
            this.price = outboundItem.getPrice();
            this.status = outboundItem.isStatus();
            this.subRemark = outboundItem.getSubRemark();
            this.expDate = outboundItem.getExpDate();
            this.makeLotNo = outboundItem.getMakeLotNo();
            this.makeDate = outboundItem.getMakeDate();
        }
    }
}
