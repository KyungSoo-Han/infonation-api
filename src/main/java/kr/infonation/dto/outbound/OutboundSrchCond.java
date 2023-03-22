package kr.infonation.dto.outbound;

import lombok.Data;

@Data
public class OutboundSrchCond {
    private Long bizId;
    private String outboundNo;
    private String fromDate;
    private String toDate;
    private Long customerId;
    private Long destinationId;

}
