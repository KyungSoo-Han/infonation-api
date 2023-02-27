package kr.infonation.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import springfox.documentation.annotations.ApiIgnore;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiIgnore
public class ResponseDto<T> {

    private HttpStatus status = HttpStatus.OK;
    private String message;
    private T data;

    @ApiIgnore
    public static <T> ResponseDto<T> SuccessResponse(T data, HttpStatus status) {
        return new ResponseDto<>(data, status );
    }


    @Builder
    public ResponseDto(T data, HttpStatus status ) {
        this.status = status;
        this.data = data;
    }


}
