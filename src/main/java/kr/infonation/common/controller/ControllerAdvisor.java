package kr.infonation.common.controller;

import kr.infonation.common.dto.ExceptionResponseDto;
import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = {RestController.class, Service.class})
public class ControllerAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);
    @ExceptionHandler(Exception.class)
    protected ExceptionResponseDto handleAll(final Exception e) {
        logger.error("error", e);

        return ExceptionResponseDto.ExceptionResponse("서버에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Controller 전범위 Exception (임시) //각 Exception 과 메시지 정의 필요
     */
    @ExceptionHandler(CustomException.class)
    protected ExceptionResponseDto customException(final Exception e) {
        logger.error("error", e);

        return ExceptionResponseDto.ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
