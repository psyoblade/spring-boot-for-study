package me.suhyuk.junit.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {
    private Long id;
    private PostStatus postStatus = PostStatus.CREATED;
    private String title;
    private String contents;

    void validate() {
        if (this.id == null) throw new IllegalArgumentException("작성자의 ID는 널일 수 없습니다");
        if (this.postStatus == null) this.postStatus = PostStatus.CREATED;
    }

    public void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    @Builder
    public Post(Long id, PostStatus postStatus, String title, String contents) {
        this.id = id;
        this.postStatus = postStatus;
        this.title = title;
        this.contents = contents;
        validate();
    }
}
