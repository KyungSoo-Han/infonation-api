package kr.infonation.dto.inbound;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.inbound.InboundGbn;
import kr.infonation.domain.inbound.Inbound;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.center.CenterDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.cust.SupplierDto;
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

        public Inbound toEntity(String inboundNo, Biz biz, Center center, Customer customer, Supplier supplier){
            return Inbound.builder()
                    .biz(biz)
                    .center(center)
                    .customer(customer)
                    .supplier(supplier)
                    .remark(remark)
                    .inBoundGbn(inboundGbn)
                    .inboundDate(inboundDate)
                    .inboundNo(inboundNo)
                    .build();
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

        public CreateResponse(Inbound inbound, BizDto.Response bizDto, CenterDto centerDto, CustomerDto.Response customerDto, SupplierDto.Response supplierDto) {
            this.bizDto = bizDto;
            this.centerDto = centerDto;
            this.customerDto = customerDto;
            this.supplierDto = supplierDto;
            this.remark = inbound.getRemark();
            this.inboundDate = inbound.getInboundDate();
            this.inboundNo = inbound.getInboundNo();
            this.inboundGbn = inbound.getInBoundGbn();
        }
    }

}
