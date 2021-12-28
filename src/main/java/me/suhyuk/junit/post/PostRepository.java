package me.suhyuk.junit.post;

import me.suhyuk.junit.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
