package me.suhyuk.junit.member;

import me.suhyuk.junit.domain.Member;
import me.suhyuk.junit.domain.Post;
import me.suhyuk.junit.post.IPostService;
import me.suhyuk.junit.post.PostRepository;
import me.suhyuk.junit.post.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTests {

    @Test
    @DisplayName("목객체를 사용하지 않고 직접 모키토가 하는 일을 굳이 내가 해보기")
    void testMimicMockito() {
        MemberService memberService = new MemberService() {
            @Override
            public Optional<Member> findById(Long memberId) {
                return Optional.of(Member.builder().id(1L).build());
            }
        };
        PostRepository postRepository = new PostRepository() {
            @Override
            public List<Post> findAll() {
                return null;
            }

            @Override
            public List<Post> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Post> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends Post> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Post> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Post> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Post> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Post getOne(Long aLong) {
                return null;
            }

            @Override
            public Post getById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Post> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Post> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Post> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Post> S save(S entity) {
                return (S) Post.builder().id(1L).build();
            }

            @Override
            public Optional<Post> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Post entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Post> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Post> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Post> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Post> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Post> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Post, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        IPostService postService = new PostService(memberService, postRepository);
        assertNotNull(postService);
    }

    @Test
    @DisplayName("Mockito.mock() 함수를 통해 목객체 활용해보기")
    void testMockito() {
        MemberService memberService = Mockito.mock(MemberService.class);
        PostRepository postRepository = Mockito.mock(PostRepository.class);
        IPostService postService = new PostService(memberService, postRepository);
        assertNotNull(postService);
    }

    @Mock private MemberService memberServiceV1;
    @Mock private PostRepository postRepositoryV1;
    @Test
    @DisplayName("@Mock, @ExtendWith 어노테이션을 이용하여 멤버변수로 주입하는 예제")
    void testMockAnnotation() {
        IPostService postService = new PostService(memberServiceV1, postRepositoryV1);
        assertNotNull(postService);
    }

    @Test
    @DisplayName("@Mock 인자를 통해 테스트하는 예제")
    void testMockArgument(@Mock MemberService memberService, @Mock PostRepository postRepository) {
        IPostService postService = new PostService(memberService, postRepository);
        assertNotNull(postService);
    }

    class Foo {
        private int id;
        private boolean flag;
        public Foo() {}
        public int getId() { return id; }
        public boolean getFlag() { return flag; }
        @Override public String toString() { return "Foo{" + "id=" + id + ", flag=" + flag + '}'; }
    }

    @Test
    @DisplayName("Mockito.lenient 키워드 활용")
    void testLenient(@Mock Foo foo, @Mock Foo bar) {
        Mockito.lenient()
                .when(foo.getId())
                .thenReturn(0);
        assertFalse(foo.getFlag());
        Mockito.when(bar.getId())
                .thenReturn(0);
        assertFalse(foo.getFlag());
    }

    @Test
    @DisplayName("@Mock 객체의 행동을 정의하는 방법")
    void testObjectStubbing(@Mock MemberService memberService) {
        // When ... Then
        Member member = Member.builder().id(1L).name("박수혁").email("psyoblade@ncsoft.com").build();
        when(memberService.findById(anyLong())).thenReturn(Optional.of(member));

        // Execute
        Member foundMember = memberService.findById(1L).orElseGet(null);

        // Assert
        assertNotNull(foundMember);
        assertEquals("박수혁", foundMember.getName());
        assertTrue(foundMember.getEmail().startsWith("psyoblade"));
    }

    class Bar {
        public void foo(int i) {
            if (i < 0)
                throw new IllegalArgumentException();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    @DisplayName("assertThrows 통한 예외처리 하기")
    void assertExceptions(Integer intValue) {
        // When ... Then
        Bar bar = new Bar();
        // Assert
        assertThrows(IllegalArgumentException.class, () -> bar.foo(intValue));
    }

    @Test
    @DisplayName("org.mockito.ArgumentMatchers 이용한 이전에 발생한 행동을 확인합니다")
    void verifyUsingArgumentMatcher() {
        LinkedList<String> mockedList = Mockito.mock(LinkedList.class);

        // Execute
        mockedList.add("12345");

        // Assert
        verify(mockedList).add(argThat(someString -> someString.length() >= 5));
    }

    @Test
    @DisplayName("수행한 명령어 호출 여부를 확인합니다")
    void verifyOperationInvocations() {
        // When ... Then
        List<String> mockedList = Mockito.mock(List.class);

        // Execute
        mockedList.add("one");
        mockedList.clear();

        // Assert -- 순서와 무관하게 호출만을 확인합니다
        verify(mockedList).clear();
        verify(mockedList).add("one");
    }

    @Test
    @DisplayName("호출 횟수를 확인합니다")
    void verifyNumberOfInvocations() {
        // When ... Then
        LinkedList<String> mockedList = Mockito.mock(LinkedList.class);

        // Execute
        mockedList.add("once");
        mockedList.add("twice");
        mockedList.add("twice");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        // Assert
        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, atLeast(3)).add("three times");
        verify(mockedList, atMost(3)).add("three times");
    }

    @Test
    @DisplayName("호출된 순서를 확인합니다")
    void verifySequenceOfInvocations() {
        // When ... Then
        List<String> mockedList = Mockito.mock(List.class);

        // Execute
        mockedList.add("first item");
        mockedList.add("second item");

        // Assert
        InOrder inOrder = inOrder(mockedList);
        inOrder.verify(mockedList).add("first item");
        inOrder.verify(mockedList).add("second item");
    }

    @Test
    @DisplayName("여러개의 목객체의 함수 호출 순서 확인")
    void verifySequenceOfMultipleMockInvocations() {
        // When ... Then
        List<String> mockList1 = Mockito.mock(List.class);
        List<String> mockList2 = Mockito.mock(List.class);

        // Execute
        mockList1.add("mockList1 first");
        mockList2.add("mockList2 first");
        mockList1.add("mockList1 second");
        mockList2.add("mockList2 second");

        // Assert
        InOrder inOrder = inOrder(mockList2, mockList1);
        inOrder.verify(mockList1).add("mockList1 first");
        inOrder.verify(mockList2).add("mockList2 first");
        inOrder.verify(mockList1).add("mockList1 second");
        inOrder.verify(mockList2).add("mockList2 second");
    }
}
