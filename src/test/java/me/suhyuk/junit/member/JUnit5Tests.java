package me.suhyuk.junit.member;

import me.suhyuk.junit.domain.Member;
import me.suhyuk.junit.domain.Post;
import me.suhyuk.junit.domain.PostStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class JUnit5Tests {

    @Test
    @DisplayName("JUnit5 기본 검증")
    void testBasicAssert() {
        // Execute
        Post newPost = Post.builder().id(1L).build();

        // Assert
        assertEquals(PostStatus.CREATED, newPost.getPostStatus());
    }

    @Test
    @DisplayName("Java8 Supplier 통한 Assertion")
    void testBasicAssertWithSupplier() {
        // Execute
        Post newPost = Post.builder().id(1L).build();

        // Assert
        assertEquals(PostStatus.UPDATED, newPost.getPostStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "처음 생성된 Post 의 상태는 CREATED 입니다";
            }
        });
    }

    @Test
    @DisplayName("Java8 Lambda 통한 Assertion")
    void testBasicAssertWithLambda() {
        // Execute
        Post newPost = Post.builder().id(1L).build();

        // Assert
        assertEquals(PostStatus.UPDATED, newPost.getPostStatus(), () -> "처음 생성된 Post 의 상태는 CREATED 입니다");
    }

    @Test
    @DisplayName("assertAll 구문을 통한 Assertion")
    void testBasicAssertWithAssertAll() {
        // Execute
        Post newPost = Post.builder().id(1L).build();

        // Assert
        assertAll(
                () -> assertNotNull(newPost),
                () -> assertEquals(1L, newPost.getId()),
                () -> assertEquals(PostStatus.CREATED, newPost.getPostStatus(), () -> "처음 생성된 Post 의 상태는 CREATED 입니다")
        );
    }

    @Test
    @DisplayName("assetThrows 테스트")
    void testAssertThrows() {
        // Execute
        Post.PostBuilder postBuilder = Post.builder();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> postBuilder.build());
    }

    @Test
    @DisplayName("assetTimeout 테스트")
    void testAssertTimeout() {
        // Execute
        Post post = Post.builder().id(1L).build();

        // Assert
        assertTimeout(Duration.ofMillis(1000), () -> post.sleep(5000));
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> post.sleep(5000));
    }

    @Test
    @DisplayName("assetTimeoutPreemptively 테스트")
    void testAssertTimeoutPreemptively() {
        // Execute
        Post post = Post.builder().id(1L).build();

        // Assert
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> post.sleep(5000));
    }

    @Test
    @DisplayName("org.assertj.core.api.AssertionsForInterfaceTypes.assertThat 테스트")
    void testAssertMatcher() {
        // Execute
        Post post = Post.builder().id(1L).title("제목").build();

        // Assert
        assertThat(post.getTitle().length()).isGreaterThan(2);
    }

    @Test
    @DisplayName("assumeTrue 조건에 따른 테스트")
    void testAssumeTrue() {
        // Execute
        String debug = System.getenv("DEBUG");

        // Assert
        assumeTrue("True".equalsIgnoreCase(debug));
        Post.builder().build(); // throw illegal-argument-exception
    }

    @Test
    @Tag("fast")
    @DisplayName("@Tag('fast') 조건에 따른 테스트")
    void testTagFast() throws InterruptedException {
        Post fastPost = Post.builder().id(1L).build();
        assertTimeout(Duration.ofMillis(50), () -> fastPost.sleep(10));
    }

    @Test
    @Tag("slow")
    @DisplayName("@Tag('slow') 조건에 따른 테스트")
    void testTagSlow() throws InterruptedException {
        Post slowPost = Post.builder().id(1L).build();
        assertTimeout(Duration.ofMillis(10000), () -> slowPost.sleep(5000));
    }

    @FastTest
    @DisplayName("커스텀 어노테이션 태그를 통한 테스트")
    void testFastTag() {
        Post fastPost = Post.builder().id(1L).build();
        assertTimeout(Duration.ofMillis(50), () -> fastPost.sleep(10));
    }

    @ParameterizedTest
    @DisplayName("Parameterized 테스트")
    @ValueSource(strings = {
            "파라메터", "직접입력", "테스트"
    })
    void testParameterizedTest(String word) {
        assertThat(word.length() > 0);
    }

    @ParameterizedTest
    @DisplayName("다수의 파라메터와 인자를 가진 테스트")
    @CsvSource(value = {"1:'박수혁':CREATED", "2:'박송희':UPDATED", "3:'백건호':DELETED"}, delimiter = ':')
    void testMultipleParameterized(Long id, PostStatus postStatus, String name) {
        Member member = Member.builder().id(id).name(name).build();
        assertNotNull(member);
        assertThat(member.getName().length()).isGreaterThan(1);
        Post post = Post.builder().postStatus(postStatus).id(id).build();
        assertNotNull(post);
    }

    @ParameterizedTest
    @DisplayName("널과 빈값 인자 테스트")
    @ValueSource(strings = {"박수혁", "박송희", "백건호"})
    @NullAndEmptySource
    void testNullAndEmptySource(String name) {
        System.out.println(String.format("[%s]", name));
    }
}