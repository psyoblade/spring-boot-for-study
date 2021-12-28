package me.suhyuk.junit.member;

import me.suhyuk.junit.domain.Member;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoTests {
    private MemberService memberServiceV1;
    private PostRepository postRepositoryV1;

    // TODO : Mock 객체를 사용하지 않고, 아래 함수의 오류를 해결하세요
    @Test
    @DisplayName("목객체를 사용하지 않고 직접 모키토가 하는 일을 굳이 내가 해보기")
    void testMimicMockito() {
        MemberService memberService = null;
        PostRepository postRepository = null;
        IPostService postService = new PostService(memberService, postRepository);
        assertNotNull(postService);
    }

    // TODO : Mockito.mock() 함수를 이용하여, 아래 함수의 오류를 해결하세요
    @Test
    @DisplayName("Mockito.mock() 함수를 통해 목객체 활용해보기")
    void testMockito() {
        MemberService memberService = null;
        PostRepository postRepository = null;
        IPostService postService = new PostService(memberService, postRepository);
        assertNotNull(postService);
    }

    // TODO : @Mock 어노테이션을 멤버변수에, 아래 함수의 오류를 해결하세요
    @Test
    @DisplayName("@Mock, @ExtendWith 어노테이션을 이용하여 멤버변수로 주입하는 예제")
    void testMockAnnotation() {
        IPostService postService = new PostService(memberServiceV1, postRepositoryV1);
        assertNotNull(postService);
    }

    // TODO : @Mock 어노테이션을 입력인자에 적용하여, 아래 함수의 오류를 해결하세요
    @Test
    @DisplayName("@Mock 인자를 통해 테스트하는 예제")
    void testMockArgument(MemberService memberService, PostRepository postRepository) {
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

    // TODO : Mockito 의 특정 함수를 활용하여 UnnecessaryStubbingException 예외가 발생하지 않도록 작성하세요
    @Test
    @DisplayName("UnnecessaryStubbingException 예외 회피 키워드 활용")
    void testLenient(@Mock Foo foo, @Mock Foo bar) {
        Mockito.when(foo.getId()).thenReturn(0);
        assertFalse(foo.getFlag());
    }

    // TODO : 단위 테스트가 통과하도록 코드를 수정하세요
    @Test
    @DisplayName("@Mock 객체의 행동을 정의하는 방법")
    void testObjectStubbing(@Mock MemberService memberService) {
        // When ... Then
        Member member = Member.builder().build();
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
        // TODO : bar.foo(intValue) 함수 호출에서 발생하는 예외를 검증하는 코드를 작성하세요
    }

    @Test
    @DisplayName("org.mockito.ArgumentMatchers 이용한 이전에 발생한 행동을 확인합니다")
    void verifyUsingArgumentMatcher() {
        LinkedList<String> mockedList = Mockito.mock(LinkedList.class);

        // Execute
        mockedList.add("12345");

        // Assert
        // TODO : mockedList 에 추가된 문자열의 수가 5이상인 것을 확인(verify)하세요
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
        // TODO : add 및 clear 함수 호출을 확인하세요
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
        // TODO : once, twice, three times 의 호출횟수를 확인하세요
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
        // TODO : 호출된 함수의 순서 ("first item", "second item")을 확인하세요
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
        // TODO : 호출된 목객체(mockList1, mockList2)를 이용하여 함수의 순서를 확인하세요
        InOrder inOrder = inOrder(mockList2, mockList1);
        inOrder.verify(mockList1).add("mockList1 first");
        inOrder.verify(mockList2).add("mockList2 first");
        inOrder.verify(mockList1).add("mockList1 second");
        inOrder.verify(mockList2).add("mockList2 second");
    }
}
