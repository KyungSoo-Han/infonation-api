package kr.infonation.dto.center;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.base.Address;
import kr.infonation.domain.biz.Biz;
import lombok.Data;

public class CenterDto {
    @Data
    @ApiModel("CreateRequest")
    public static class CreateRequest{
        private String name;
        private Address address;
        private Biz biz;
    }

}
