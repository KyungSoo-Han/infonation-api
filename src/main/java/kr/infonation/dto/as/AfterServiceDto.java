package kr.infonation.dto.as;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.as.AfterService;
import kr.infonation.domain.as.RequestGbn;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AfterServiceDto {
    @Data
    @ApiModel("AfterServiceCreateRequest")
    public static class CreateRequest {
        private String title;
        private String content;
        private String status;
        private String writer;
        private RequestGbn requestGbn;

        public AfterService toEntity() {
            return AfterService.builder()
                    .title(title)
                    .content(content)
                    .status("N")
                    .requestGbn(requestGbn)
                    .writer(writer)
                    .build();
        }
    }

    @Data
    @ApiModel("AfterServiceResponse")
    public static class Response {
        private Long id;
        private String title;
        private String content;
        private String status;
        private String writer;
        private RequestGbn requestGbn;

        public Response(AfterService entity) {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.status = entity.getStatus();
            this.writer = entity.getWriter();
            this.requestGbn = entity.getRequestGbn();
        }
    }
}
