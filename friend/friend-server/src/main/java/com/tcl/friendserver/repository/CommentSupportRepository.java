package com.tcl.friendserver.repository;

import com.tcl.friendserver.model.FriendCommentSupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentSupportRepository extends JpaRepository<FriendCommentSupport, Long> {

    FriendCommentSupport findBySupportIdAndUserId(Long supportId, Long userId);
}
