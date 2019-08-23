package com.tcl.friendserver.serviceImpl;

import com.tcl.friendserver.dto.SupportCountDto;
import com.tcl.friendserver.mapper.FriendCommentSupportMapper;
import com.tcl.friendserver.mapper.FriendWeiboCommentMapper;
import com.tcl.friendserver.mapper.FriendWeiboMapper;
import com.tcl.friendserver.mapper.FriendWeiboSupportMapper;
import com.tcl.friendserver.model.FriendCommentSupport;
import com.tcl.friendserver.model.FriendWeiboComment;
import com.tcl.friendserver.model.FriendWeiboSupport;
import com.tcl.friendserver.model.FriendWeibo;
import com.tcl.friendserver.service.RedisService;
import com.tcl.friendserver.service.WeiboCommentService;
import com.tcl.friendserver.service.WeiboService;
import com.tcl.friendserver.service.connectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

@Service
public class connectionServiceImpl implements connectionService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private WeiboService weiboService;

    @Autowired
    private WeiboCommentService weiboCommentService;

    @Autowired
    private FriendWeiboMapper friendWeiboMapper;

    @Autowired
    private FriendWeiboCommentMapper friendWeiboCommentMapper;

    @Autowired
    private FriendWeiboSupportMapper friendWeiboSupportMapper;

    @Autowired
    private FriendCommentSupportMapper friendCommentSupportMapper;

    @Override
    public void transWeiboSupportDataFromRedisToDB() {
        List<FriendWeiboSupport> list = redisService.getAllWeiboSupportDataFromRedis();
        for (FriendWeiboSupport weiboSupportData : list) {
            if (weiboSupportData.getSupportId() == null) {
                //没有记录，直接存入
                friendWeiboSupportMapper.insert(weiboSupportData);
            } else {
                //有记录，需要更新
                friendWeiboSupportMapper.updateByPrimaryKeySelective(weiboSupportData);
            }
        }
    }

    @Override
    public void transWeiboSupportCountFromRedis2DB() {
        List<SupportCountDto> list = redisService.getAllWeiboSupportCountFromRedis();
        for(SupportCountDto dto : list) {
            FriendWeibo friendWeibo = weiboService.findById(dto.getId());
            if (friendWeibo != null) {
                Integer supportCount = friendWeibo.getSupportCount() + dto.getCount();
                friendWeibo.setSupportCount(supportCount);
                // 更新点赞数量
                friendWeiboMapper.updateByPrimaryKeySelective(friendWeibo);
            }
        }
    }

    @Override
    public void transCommentSupportDataFromRedisToDB() {
        List<FriendCommentSupport> list = redisService.getAllCommentSupportDataFromRedis();
        for (FriendCommentSupport friendCommentSupportData : list) {
            if (friendCommentSupportData.getSupportId() == null) {
                //没有记录，直接存入
                friendCommentSupportMapper.insert(friendCommentSupportData);
            } else {
                //有记录，需要更新
                friendCommentSupportMapper.updateByPrimaryKeySelective(friendCommentSupportData);
            }
        }
    }

    @Override
    public void transCommentSupportCountFromRedis2DB() {
        List<SupportCountDto> list = redisService.getAllCommentsupportCountFromRedis();
        for(SupportCountDto dto : list) {
            FriendWeiboComment friendWeiboComment = weiboCommentService.findById(dto.getId());
            if (friendWeiboComment != null) {
                Integer supportCount = friendWeiboComment.getSupportCount() + dto.getCount();
                friendWeiboComment.setSupportCount(supportCount);
                // 更新点赞数量
                friendWeiboCommentMapper.updateByPrimaryKeySelective(friendWeiboComment);
            }
        }
    }
}
