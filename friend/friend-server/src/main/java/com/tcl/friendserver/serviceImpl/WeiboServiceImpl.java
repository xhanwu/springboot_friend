package com.tcl.friendserver.serviceImpl;

import com.tcl.friendserver.mapper.FriendWeiboMapper;

import com.tcl.friendserver.mapper.FriendWeiboSupportMapper;
import com.tcl.friendserver.model.FriendWeibo;
import com.tcl.friendserver.model.FriendWeiboSupport;
import com.tcl.friendserver.repository.WeiboServiceRepository;
import com.tcl.friendserver.repository.WeiboSupportRepository;
import com.tcl.friendserver.service.RedisService;
import com.tcl.friendserver.service.WeiboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

@Service
@Slf4j
public class WeiboServiceImpl implements WeiboService {

    @Autowired
    private FriendWeiboMapper friendWeiboMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private WeiboServiceRepository weiboServiceRepository;

    @Autowired
    private WeiboSupportRepository weiboSupportRepository;

    @Autowired
    private FriendWeiboSupportMapper friendWeiboSupportMapper;

    private Boolean isRedis = false;

    @Override
    public boolean createWeibo(FriendWeibo friendWeibo) {

        int result = 0;
        Long weiboId = friendWeibo.getId();
        log.info("createWeibo WeiboId = " + weiboId);
        if (weiboId == null) {
            result = friendWeiboMapper.insertSelective(friendWeibo);
        } else {
            FriendWeibo wb = weiboServiceRepository.findWeiboById(weiboId);
            log.info("getWeiboById wb = " + wb.toString());
            if (wb != null) {
                log.info("Update FriendWeibo");
                result = friendWeiboMapper.updateByPrimaryKeySelective(friendWeibo);
            } else {
                log.info("Insert FriendWeibo");
                result = friendWeiboMapper.insertSelective(friendWeibo);
            }
        }
        return result > 0;
    }

    @Override
    public boolean deleteWeibo(Long weiboId){
        if ((weiboId == null) || (weiboId == 0) || ("".equals(weiboId))) {
            log.info("deleteWeibo Invalid weiboId !!!");
            return false;
        }

        int result = friendWeiboMapper.deleteByPrimaryKey(weiboId);
        if (result > 0) {
            log.info("Delte FriendWeibo successed");
            return true;
        } else {
            log.info("Delte FriendWeibo failure");
            return false;
        }
    }

    @Override
    public boolean repostWeibo(FriendWeibo friendWeibo){
//        Long weiboId = null;
//        if ((weiboId = friendWeibo.getId()) != null) {
//            int primaryKeySelective = FriendWeiboMapper.deleteByPrimaryKey(weiboId);
//        } else {
//            friendWeibo.setUserId(SessionUtil.getUser().getId());
//            weiboId = FriendWeiboMapper.insert(weiboId);
//        }
//        Inte
        return true;
    }

    @Override
    public void weiboSupport(Long weiboId, Long supportUserId) {
        if ((weiboId == null) || (weiboId == 0) || (supportUserId == null) || (supportUserId == 0)) {
            log.info("commentSupport Invalid weiboId or Invalid supportUserId !!!");
            return;
        }

        FriendWeiboSupport ws = weiboSupportRepository.findBySupportIdAndUserId(weiboId, supportUserId);
        if (ws != null) {
            log.info("FriendWeiboSupport ws = " + ws.toString());
        } else {
            log.info("get null ws data ");
        }

        FriendWeibo wb = weiboServiceRepository.findWeiboById(weiboId);
        if (wb != null) {
            log.info("FriendWeibo wb = " + wb.toString());
        } else {
            log.info("get null wb data ");
        }
        if (wb == null) {
            return;
        }

        Integer weiboSupportCount = wb.getSupportCount();
        log.info("begin weiboSupportCount = " + weiboSupportCount);

        if (isRedis) {
            redisService.saveWeiboSupportToRedis(weiboId, supportUserId);
            redisService.incrementWeiboSupportCount(weiboId);
        } else {
            if (ws != null) {
                if (ws.getStatus()) {
                    log.info("status is true, set it as false");
                    ws.setStatus(false);
                    weiboSupportCount = weiboSupportCount - 1;
                } else {
                    log.info("status is false, set it as true");
                    ws.setStatus(true);
                    weiboSupportCount = weiboSupportCount + 1;
                }

                friendWeiboSupportMapper.updateByPrimaryKeySelective(ws);
            } else {
                ws = new FriendWeiboSupport();
                ws.setSupportId(weiboId);
                ws.setUserId(supportUserId);
                ws.setStatus(true);
                ws.setSupportTime(new Date());
                friendWeiboSupportMapper.insertSelective(ws);
                weiboSupportCount = weiboSupportCount + 1;
            }
            log.info("final weiboSupportCount = " + weiboSupportCount);
            wb.setSupportCount(weiboSupportCount);
            friendWeiboMapper.updateByPrimaryKeySelective(wb);
        }
    }

    @Override
    public void unWeiboSupport(Long weiboId, Long supportUserId) {
        redisService.unWeiboSupportFromRedis(weiboId, supportUserId);
        redisService.decrementWeiboSupportCount(weiboId);
    }

    @Override
    public void deleteWeiboSupport(Long weiboId, Long supportUserId) {
        redisService.deleteWeiboSupportFromRedis(weiboId, supportUserId);
    }

    @Override
    public FriendWeibo findById(Long id) {
        System.out.println("findById");
        return weiboServiceRepository.findWeiboById(id);
    }
}