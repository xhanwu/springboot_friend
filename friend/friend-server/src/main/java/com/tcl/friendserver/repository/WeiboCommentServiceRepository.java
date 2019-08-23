package com.tcl.friendserver.repository;

import com.tcl.friendserver.model.FriendWeiboComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

public interface WeiboCommentServiceRepository extends JpaRepository<FriendWeiboComment, Integer> {

    FriendWeiboComment findWeiboCommentById(Long id);

}
