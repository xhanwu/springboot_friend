package com.tcl.friendserver.serviceImpl;

import com.tcl.friendserver.mapper.FriendCommentSupportMapper;
import com.tcl.friendserver.mapper.FriendWeiboCommentMapper;
import com.tcl.friendserver.model.FriendCommentSupport;
import com.tcl.friendserver.model.FriendWeiboComment;
import com.tcl.friendserver.repository.CommentSupportRepository;
import com.tcl.friendserver.repository.WeiboCommentServiceRepository;
import com.tcl.friendserver.service.RedisService;
import com.tcl.friendserver.service.WeiboCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

@Service
@Slf4j
public class WeiboCommentServiceImpl implements WeiboCommentService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private FriendWeiboCommentMapper friendWeiboCommentMapper;

    @Autowired
    private FriendCommentSupportMapper friendCommentSupportMapper;

    @Autowired
    private WeiboCommentServiceRepository weiboCommentServiceRepository;

    @Autowired
    private CommentSupportRepository commentSupportRepository;

    private boolean isRedis = false;

    @Override
    public boolean createWeiboComment(FriendWeiboComment friendWeiboComment) {

        int result = 0;
        Long weiboCommentId = friendWeiboComment.getId();

        if (weiboCommentId == null) {
            result = friendWeiboCommentMapper.insertSelective(friendWeiboComment);
        } else {
            FriendWeiboComment wbc = weiboCommentServiceRepository.findWeiboCommentById(weiboCommentId);

            if (wbc != null) {
                log.info("Update FriendWeiboComment");
                result = friendWeiboCommentMapper.updateByPrimaryKeySelective(friendWeiboComment);
            } else {
                log.info("Insert FriendWeiboComment");
                result = friendWeiboCommentMapper.insertSelective(friendWeiboComment);
            }
        }
        return result > 0;
    }

    @Override
    public boolean deleteWeiboComment(Long commentId) {
        if ((commentId == null) || (commentId == 0) || ("".equals(commentId))) {
            log.info("deleteWeiboComment Invalid commentId !!!");
            return false;
        }

        int result = friendWeiboCommentMapper.deleteByPrimaryKey(commentId);
        if (result > 0) {
            log.info("Delte FriendWeiboComment successed");
            return true;
        } else {
            log.info("Delte FriendWeiboComment fail");
            return false;
        }
    }

    @Override
    public void commentSupport(Long commentId, Long supportUserId) {
        if ((commentId == null) || (commentId == 0) || (supportUserId == null) || (supportUserId == 0)) {
            log.info("commentSupport Invalid commentId or Invalid supportUserId !!!");
            return;
        }

        FriendCommentSupport cs = commentSupportRepository.findBySupportIdAndUserId(commentId, supportUserId);
        if (cs != null) {
            log.info("FriendCommentSupport cs = " + cs.toString());
        } else {
            log.info("get null cs data ");
        }

        FriendWeiboComment wbc = weiboCommentServiceRepository.findWeiboCommentById(commentId);
        if (wbc != null) {
            log.info("FriendWeiboComment wbc = " + wbc.toString());
        } else {
            log.info("get null wbc data ");
        }

        Integer weiboCommentSupportCount = wbc.getSupportCount();

        if (isRedis) {
            redisService.saveCommentSupportToRedis(commentId, supportUserId);
            redisService.incrementCommentSupportCount(commentId);
        } else {
            if (cs != null) {
                if (cs.getStatus()) {
                    cs.setStatus(false);
                    weiboCommentSupportCount = weiboCommentSupportCount - 1;
                } else {
                    cs.setStatus(true);
                    weiboCommentSupportCount = weiboCommentSupportCount + 1;
                }

                friendCommentSupportMapper.updateByPrimaryKeySelective(cs);
            } else {
                cs = new FriendCommentSupport();
                cs.setSupportId(commentId);
                cs.setUserId(supportUserId);
                cs.setStatus(true);
                cs.setSupportTime(new Date());
                friendCommentSupportMapper.insertSelective(cs);
                weiboCommentSupportCount = weiboCommentSupportCount + 1;
            }
            wbc.setSupportCount(weiboCommentSupportCount);
            friendWeiboCommentMapper.updateByPrimaryKeySelective(wbc);
        }
    }

    @Override
    public void unCommentSupport(Long commentId, Long supportUserId) {
        redisService.unCommentSupportFromRedis(commentId, supportUserId);
        redisService.decrementCommentSupportCount(commentId);
    }

    @Override
    public void deleteCommentSupport(Long commentId, Long supportUserId) {
        redisService.deleteCommentSupportFromRedis(commentId, supportUserId);
    }

    @Override
    public FriendWeiboComment findById(Long id) {
        return weiboCommentServiceRepository.findWeiboCommentById(id);
    }
}
