package kr.infonation.dto.inbound;

import kr.infonation.domain.inbound.InboundGbn;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class InboundQueryDto {

    private String inboundNo;
    private String inboundDate;
    private Long bizId;
    private String bizName;
    private Long centerId;
    private String centerName;
    private Long customerId;
    private String customerName;
    private Long supplierId;
    private String supplierName;
    private String remark;
    private InboundGbn inboundGbn;
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

    public InboundQueryDto(String inboundNo, String inboundDate, Long bizId, String bizName, Long centerId, String centerName, Long customerId, String customerName, Long supplierId, String supplierName, String remark, InboundGbn inboundGbn, Long inboundSeq, Long itemId, String itemName, int qty, int price,
                           boolean status, String subRemark, String expDate, String makeLotNo, String makeDate) {
        this.inboundNo = inboundNo;
        this.inboundDate = inboundDate;
        this.bizId = bizId;
        this.bizName = bizName;
        this.centerId = centerId;
        this.centerName = centerName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.remark = remark;
        this.inboundGbn = inboundGbn;
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
