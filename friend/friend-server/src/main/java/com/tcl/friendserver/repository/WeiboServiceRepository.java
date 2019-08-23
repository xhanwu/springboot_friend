package com.tcl.friendserver.repository;

import com.tcl.friendserver.model.FriendWeibo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

public interface WeiboServiceRepository extends JpaRepository<FriendWeibo, Integer> {

    FriendWeibo findWeiboById(Long id);

}
