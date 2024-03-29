package inflearnproject.anoncom.post.controller;

import inflearnproject.anoncom.domain.Post;
import inflearnproject.anoncom.post.dto.*;
import inflearnproject.anoncom.post.repository.PostRepository;
import inflearnproject.anoncom.post.service.PostService;
import inflearnproject.anoncom.security.jwt.util.IfLogin;
import inflearnproject.anoncom.security.jwt.util.LoginUserDto;
import inflearnproject.anoncom.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static inflearnproject.anoncom.post.dto.ResAddPostDto.buildResPostDto;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Value("${spring.data.web.pageable.default-page-size}")
    private int defaultPageSize;

    @PostMapping("/api/postWrite")
    public ResponseEntity<ResAddPostDto> addPost(@IfLogin LoginUserDto userDto, @Valid @RequestBody ReqAddPostDto postDto) {
        Post post = postService.savePost(userDto, postDto);
        ResAddPostDto resAddPostDto = buildResPostDto(userDto.getMemberId(), post);
        return ResponseEntity.ok().body(resAddPostDto);
    }

    @PatchMapping("/api/postCorrect/{postId}")
    public ResponseEntity<?> updatePost(@IfLogin LoginUserDto userDto, @PathVariable("postId") Long postId,
                                        @RequestBody ReqAddPostDto postDto) {
        postService.update(userDto, postId, postDto);
        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping("/api/postDelete/{postId}")
    public ResponseEntity<?> deletePost(@IfLogin LoginUserDto userDto, @PathVariable("postId") Long postId) {
        postService.delete(userDto, postId);
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("/api/postDetail/{postId}")
    public ResponseEntity<ResPostDetailDto> getPost(@PathVariable Long postId) {
        Post post = postService.findPostById(postId);
        ResPostDetailDto resPostDetailDto = new ResPostDetailDto(post);
        return ResponseEntity.ok().body(resPostDetailDto);
    }


    @GetMapping("/api/postList")
    public ResponseEntity<PagingPost> getPostsByLocation(
            @Valid @ModelAttribute(value = "findPostContent") PostSearchCondition cond,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, defaultPageSize);
        return getPagingPostResponseEntity(cond, pageable);
    }

    private ResponseEntity<PagingPost> getPagingPostResponseEntity(PostSearchCondition cond, Pageable pageable) {
        Page<Post> postsByCategory = postService.findPostsByCondByDSL(cond, pageable);
        int currentPage = postsByCategory.getNumber();
        int totalPage = postsByCategory.getTotalPages();
        List<ResPostDto> dtos = postsByCategory.stream().map(ResPostDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(new PagingPost(dtos, currentPage, totalPage));
    }

    @GetMapping("/myPosts")
    public ResponseEntity<?> showMyPosts(@IfLogin LoginUserDto userDto, @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, defaultPageSize);
        Page<Post> userPosts = postService.findUserPosts(userDto, pageable);
        List<ResPostDetailDto> detailPostDtos = userPosts.getContent().stream().map(ResPostDetailDto::new).toList();
        return ResponseEntity.ok().body(new UsersPostsDto(detailPostDtos, userPosts.getNumber(), userPosts.getTotalPages()));
    }

    @GetMapping("/posts/{nickname}")
    public ResponseEntity<?> showPostsByUsername(@RequestParam(value = "page", defaultValue = "0") int page
            , @PathVariable("nickname") String nickname) {
        Pageable pageable = PageRequest.of(page, defaultPageSize);
        Page<Post> userPosts = postService.findOtherUserPostsByNickname(nickname, pageable);
        List<ResPostDetailDto> detailPostDtos = userPosts.getContent().stream().map(ResPostDetailDto::new).toList();
        return ResponseEntity.ok().body(new UsersPostsDto(detailPostDtos, userPosts.getNumber(), userPosts.getTotalPages()));
    }
}
