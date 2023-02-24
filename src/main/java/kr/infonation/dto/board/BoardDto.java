package kr.infonation.dto.board;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.board.Board;
import kr.infonation.domain.users.Users;
import lombok.Data;

public class BoardDto {

    @Data
    @ApiModel("BoardResponse")
    public static class Response{
        private Long id;
        private String title;
        private String contents;
        private int viewCnt;
        private Long userId;
        private String userName;

        public Response(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.contents = board.getContents();
            this.viewCnt = board.getViewCnt();
            this.userId = board.getUsers().getId();
            this.userName = board.getUsers().getName();
        }
    }
    @Data
    @ApiModel("BoardCreateRequest")
    public static class CreateRequest {
        private String title;
        private String contents;
        private int viewCnt;
        private Long userId;
        public Board toEntity(Users users){
            return Board.builder()
                    .contents(contents).title(title).viewCnt(0).users(users).build();
        }
    }
    @Data
    @ApiModel("BoardCreateResponse")
    public static class CreateResponse {
        private Long id;
        private String title;
        private String contents;
        private int viewCnt;
        private Long userId;
        private String userName;

        public CreateResponse(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.contents = board.getContents();
            this.viewCnt = board.getViewCnt();
            this.userId = board.getUsers().getId();
            this.userName = board.getUsers().getName();
        }
    }


    @Data
    @ApiModel("BoardUpdateRequest")
    public static class UpdateRequest {
        private String title;
        private String contents;
    }


    @Data
    @ApiModel("BoardUpdateResponse")
    public static class UpdateResponse{
        private Long id;
        private String title;
        private String contents;
        private int viewCnt;
        private Long userId;
        private String userName;

        public UpdateResponse(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.contents = board.getContents();
            this.viewCnt = board.getViewCnt();
            this.userId = board.getUsers().getId();
            this.userName = board.getUsers().getName();
        }
    }
}
