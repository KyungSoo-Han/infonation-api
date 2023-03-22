package kr.infonation.dto.outbound;

import kr.infonation.domain.outbound.OutboundGbn;
import lombok.Data;

@Data
public class OutboundQueryDto {

    private String outboundNo;
    private String outboundDate;
    private Long bizId;
    private String bizName;
    private Long centerId;
    private String centerName;
    private Long customerId;
    private String customerName;
    private Long destinationId;
    private String destinationName;
    private String remark;
    private OutboundGbn outboundGbn;
    private String outboundExpDate;
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

    public OutboundQueryDto(String outboundNo, String outboundDate, Long bizId, String bizName, Long centerId, String centerName, Long customerId, String customerName,
                            Long destinationId, String destinationName, String remark, OutboundGbn outboundGbn, String outboundExpDate, Long outboundSeq, Long itemId, String itemName, int qty, int price,
                            boolean status, String subRemark, String expDate, String makeLotNo, String makeDate) {
        this.outboundNo = outboundNo;
        this.outboundDate = outboundDate;
        this.bizId = bizId;
        this.bizName = bizName;
        this.centerId = centerId;
        this.centerName = centerName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.destinationId = destinationId;
        this.destinationName = destinationName;
        this.remark = remark;
        this.outboundGbn = outboundGbn;
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
        this.outboundExpDate =outboundExpDate;
    }
}
