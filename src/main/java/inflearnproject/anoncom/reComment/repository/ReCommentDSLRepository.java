package inflearnproject.anoncom.reComment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inflearnproject.anoncom.domain.QComment;
import inflearnproject.anoncom.domain.QReComment;
import inflearnproject.anoncom.reComment.dto.QResReCommentDto;
import inflearnproject.anoncom.reComment.dto.ResReCommentDto;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inflearnproject.anoncom.domain.QComment.*;
import static inflearnproject.anoncom.domain.QReComment.*;

@Repository
public class ReCommentDSLRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ReCommentDSLRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ResReCommentDto> findReCommentsByPost(Long postId){
        return queryFactory.
                select(new QResReCommentDto(
                        reComment.id,
                        reComment.comment.id,
                        reComment.comment.user.nickname,
                        reComment.user.id,
                        reComment.user.nickname,
                        reComment.user.rank,
                        reComment.createdAt,
                        reComment.content
                )).from(reComment)
                .join(reComment.comment, comment)
                .where(reComment.post.id.eq(postId))
                .fetch();
    }
}
