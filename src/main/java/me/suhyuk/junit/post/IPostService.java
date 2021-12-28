package me.suhyuk.junit.post;

import me.suhyuk.junit.domain.Post;
import me.suhyuk.junit.member.NotExistsException;

public interface IPostService {
    Post createNewPost(Long memberId, Post post) throws NotExistsException;
    void validate();
    void notify(Long memberId);
}
