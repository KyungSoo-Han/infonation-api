package kr.infonation.dto.inbound;

import lombok.Data;

@Data
public class InboundSrchCond {
    private Long bizId;
    private String inboundNo;
    private String fromDate;
    private String toDate;
    private Long customerId;
    private Long supplierId;

}
