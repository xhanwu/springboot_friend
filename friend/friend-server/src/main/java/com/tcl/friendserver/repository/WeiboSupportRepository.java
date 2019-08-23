package com.tcl.friendserver.repository;

import com.tcl.friendserver.model.FriendWeiboSupport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

public interface WeiboSupportRepository extends JpaRepository<FriendWeiboSupport, Long> {

    FriendWeiboSupport findBySupportIdAndUserId(Long supportId, Long userId);
}
