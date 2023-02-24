package kr.infonation.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.infonation.domain.base.BaseEntity;
import kr.infonation.domain.users.Users;
import kr.infonation.dto.board.BoardDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String contents;
    private int viewCnt;

    //private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users users;

    @Builder
    public Board(String title, String contents, int viewCnt, Users users) {
        this.title = title;
        this.contents = contents;
        this.viewCnt = viewCnt;
        this.users = users;
    }

    public void update(BoardDto.UpdateRequest request) {
        this.title = request.getTitle();
        this.contents = request.getContents();
    }
}
