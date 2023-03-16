package kr.infonation.dto.outbound;


import io.swagger.annotations.ApiModel;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Destination;
import kr.infonation.domain.outbound.Outbound;
import kr.infonation.domain.outbound.OutboundGbn;
import lombok.Data;

public class OutboundDto {

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


        public CreateResponse(Outbound outbound, Long bizId, String bizName, Long centerId, String centerName,
                              Long customerId, String customerName, Long destinationId, String destinationName) {
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

        }
    }
}
