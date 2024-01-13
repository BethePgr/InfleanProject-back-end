package inflearnproject.anoncom.comment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inflearnproject.anoncom.comment.dto.QReqCommentDto;
import inflearnproject.anoncom.comment.dto.ReqCommentDto;
import inflearnproject.anoncom.domain.QComment;
import inflearnproject.anoncom.domain.QPost;
import inflearnproject.anoncom.domain.QUserEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inflearnproject.anoncom.domain.QComment.*;
import static inflearnproject.anoncom.domain.QPost.*;
import static inflearnproject.anoncom.domain.QUserEntity.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class CommentDSLRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CommentDSLRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ReqCommentDto> findCommentByPostId(Long postId) {
        return queryFactory.select(new QReqCommentDto(
                        comment.id,
                        post.id,
                        comment.user.id,
                        comment.user.nickname,
                        comment.user.rank,
                        post.userLike,
                        comment.createdAt,
                        comment.content
                ))
                .from(comment)
                .join(comment.post, post)
                .where(postIdEq(postId))
                .fetch();
    }

    private BooleanExpression postIdEq(Long postId){
        return comment.post.id.eq(postId);
    }

    private BooleanExpression titleEq(String title){
        return hasText(title) ? post.title.contains(title) : null;
    }
}
