package kr.infonation.dto.item;

import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

public class ItemStockDto {
    @Data
    public static class Request {
        private Long bizId;
        private Long centerId;
        private Long customerId;
        private Long itemId;
        private String makeLotNo;
        private String makeDate;
        private String expDate;
        private String location;
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private Long bizId;
        private Long centerId;
        private Long customerId;
        private Long itemId;
        private String makeLotNo;
        private String makeDate;
        private String expDate;
        private String location;
        private int qty;
    }
}
