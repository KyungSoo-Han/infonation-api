package kr.infonation.domain.as;

import kr.infonation.domain.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class AfterService extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String status;
    private String writer;
    @Enumerated(EnumType.STRING)
    private RequestGbn requestGbn;

    @Builder
    public AfterService(String title, String content, String status, String writer, RequestGbn requestGbn) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.writer = writer;
        this.requestGbn = requestGbn;
    }
}
