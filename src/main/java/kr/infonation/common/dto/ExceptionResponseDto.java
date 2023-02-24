package kr.infonation.common.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionResponseDto<T> {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private String message;
    private final T data = null;

    public static <T> ExceptionResponseDto<T> ExceptionResponse(String message, HttpStatus status) {
        return new ExceptionResponseDto<>(message, status );
    }
    @Builder
    public ExceptionResponseDto(String message, HttpStatus status ) {
        this.status = status;
        this.message = message;
    }
}
