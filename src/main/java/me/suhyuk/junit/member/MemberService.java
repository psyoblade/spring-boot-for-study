package me.suhyuk.junit.member;

import me.suhyuk.junit.domain.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);
}
