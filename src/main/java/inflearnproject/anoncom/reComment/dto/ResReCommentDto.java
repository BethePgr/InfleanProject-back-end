package inflearnproject.anoncom.reComment.dto;

import com.querydsl.core.annotations.QueryProjection;
import inflearnproject.anoncom.domain.ReComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResReCommentDto {

    private Long id; //대댓글 id

    private Long parentCommentId; //부모 댓글 id

    private String parentCommentNickname; //부모 댓글 닉네임

    private Long writerId; //대댓글 작성자 id
    private String writerNickname; //대댓글 작성자 닉네임
    private boolean writerActive;
    private int rank; //대댓글 작성자 랭크
    private LocalDateTime createdAt; //대댓글 작성 날짜
    private String content; //대댓글 내용
    private boolean isDelete;

    @QueryProjection
    public ResReCommentDto(Long id, Long parentCommentId, String parentCommentNickname, boolean writerActive, Long writerId,
                           String writerNickname, int rank, LocalDateTime createdAt, String content, boolean isDelete) {
        this.id = id;
        this.parentCommentId = parentCommentId;
        this.parentCommentNickname = parentCommentNickname;
        this.writerActive = writerActive;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.rank = rank;
        this.createdAt = createdAt;
        this.content = content;
        this.isDelete = isDelete;
    }

    public ResReCommentDto(ReComment reComment) {
        this.id = reComment.getId();
        this.parentCommentId = reComment.getComment().getId();
        this.parentCommentNickname = reComment.getComment().getUser().getNickname();
        this.writerActive = reComment.getUser().isActive();
        this.writerId = reComment.getUser().getId();
        this.writerNickname = reComment.getUser().getNickname();
        this.rank = reComment.getUser().getRank();
        this.createdAt = reComment.getCreatedAt();
        this.content = reComment.getContent();
        this.isDelete = reComment.isDeleted();
    }
}
