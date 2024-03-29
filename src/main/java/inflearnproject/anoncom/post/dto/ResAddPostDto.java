package inflearnproject.anoncom.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import inflearnproject.anoncom.domain.Post;
import inflearnproject.anoncom.enumType.LocationType;
import inflearnproject.anoncom.enumType.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResAddPostDto {

    private long id;
    private String title;
    private PostCategory category;
    private LocalDateTime createdAt;
    private Long userId;
    private int finalLike;
    private String content;
    private LocationType location;

    public static ResAddPostDto buildResPostDto(Long userId, Post post) {
        ResAddPostDto dto = ResAddPostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .category(post.getCategory())
                .createdAt(post.getCreatedAt())
                .userId(userId)
                .finalLike(post.getUserLike() - post.getUserDisLike())
                .content(post.getContent())
                .location(post.getLocation())
                .build();
        return dto;
    }

    @QueryProjection
    public ResAddPostDto(Long id, String title, PostCategory category, LocalDateTime createdAt, Long userId, int finalLike, String content) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.createdAt = createdAt;
        this.userId = userId;
        this.finalLike = finalLike;
        this.content = content;
    }
}
