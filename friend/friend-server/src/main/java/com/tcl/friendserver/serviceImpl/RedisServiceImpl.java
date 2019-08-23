package com.tcl.friendserver.serviceImpl;

import com.tcl.friendserver.dto.SupportCountDto;
import com.tcl.friendserver.enums.supportStatusEnum;
import com.tcl.friendserver.model.FriendCommentSupport;
import com.tcl.friendserver.model.FriendWeiboSupport;
import com.tcl.friendserver.service.RedisService;
import com.tcl.friendserver.utils.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void saveWeiboSupportToRedis(Long weiboId, Long supportUserId) {
        String key = RedisKeyUtils.getSupportKey(weiboId, supportUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.KEY_WEIBO_SUPPORT_STATUS, key, supportStatusEnum.SUPPORT.getCode());
    }

    @Override
    public void unWeiboSupportFromRedis(Long weiboId, Long supportUserId) {
        String key = RedisKeyUtils.getSupportKey(weiboId, supportUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.KEY_WEIBO_SUPPORT_STATUS, key, supportStatusEnum.UNSUPPORT.getCode());
    }

    @Override
    public void deleteWeiboSupportFromRedis(Long weiboId, Long supportUserId) {
        String key = RedisKeyUtils.getSupportKey(weiboId, supportUserId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.KEY_WEIBO_SUPPORT_STATUS, key);
    }

    @Override
    public void saveCommentSupportToRedis(Long commentId, Long supportUserId) {
        String key = RedisKeyUtils.getSupportKey(commentId, supportUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.KEY_COMMENT_SUPPORT_STATUS, key, supportStatusEnum.SUPPORT.getCode());
    }

    @Override
    public void unCommentSupportFromRedis(Long commentId, Long supportUserId) {
        String key = RedisKeyUtils.getSupportKey(commentId, supportUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.KEY_COMMENT_SUPPORT_STATUS, key, supportStatusEnum.UNSUPPORT.getCode());
    }

    @Override
    public void deleteCommentSupportFromRedis(Long commentId, Long supportUserId) {
        String key = RedisKeyUtils.getSupportKey(commentId, supportUserId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.KEY_COMMENT_SUPPORT_STATUS, key);
    }

    @Override
    public void incrementWeiboSupportCount(Long weiboId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.KEY_WEIBO_SUPPORT_COUNT, weiboId, 1);
    }

    @Override
    public void decrementWeiboSupportCount(Long weiboId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.KEY_WEIBO_SUPPORT_COUNT, weiboId, -1);
    }

    @Override
    public void incrementCommentSupportCount(Long commentId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.KEY_COMMENT_SUPPORT_COUNT, commentId, 1);
    }

    @Override
    public void decrementCommentSupportCount(Long commentId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.KEY_COMMENT_SUPPORT_COUNT, commentId, -1);
    }

    @Override
    public List<FriendWeiboSupport> getAllWeiboSupportDataFromRedis() {
        List<FriendWeiboSupport> List = new ArrayList<>();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.KEY_WEIBO_SUPPORT_STATUS, ScanOptions.NONE);
        while (cursor.hasNext()){
            Map.Entry<Object, Object> Entry = cursor.next();
            String weiboKey = (String) Entry.getKey();
            //分离出 weiboSupportId，weiboSupportUserId
            String[] weiboSplit = weiboKey.split(":");
            Long weiboSupportId = Long.valueOf(weiboSplit[0]);
            Long weiboSupportUserId = Long.valueOf(weiboSplit[1]);
            Integer value = (Integer) Entry.getValue();
            Boolean supportStatus = (value == 1) ? true : false;

            //存储微博supportData 对象
            FriendWeiboSupport weiboSupportData = new FriendWeiboSupport();
            weiboSupportData.setSupportId(weiboSupportId);
            weiboSupportData.setUserId(weiboSupportUserId);
            weiboSupportData.setStatus(supportStatus);
            weiboSupportData.setSupportTime(new Date());
            List.add(weiboSupportData);

            //存到 list 后从 Redis 中删除
            redisTemplate.opsForHash().delete(RedisKeyUtils.KEY_WEIBO_SUPPORT_STATUS, weiboKey);
        }

        return List;
    }

    @Override
    public Long getWeiboSupportCountFromRedis (Long weiboId) {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.KEY_WEIBO_SUPPORT_COUNT, ScanOptions.NONE);
        List<SupportCountDto> list = new ArrayList<>();
        Long count = 0L;
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            Long key = (Long) map.getKey();
            //获取Id为supportId的点赞数量
            if (key == weiboId) {
                count = (Long) map.getValue();
                //从Redis中删除这条记录
                redisTemplate.opsForHash().delete(RedisKeyUtils.KEY_WEIBO_SUPPORT_COUNT, key);
                break;
            }
        }
        return count;
    }

    @Override
    public List<SupportCountDto> getAllWeiboSupportCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.KEY_WEIBO_SUPPORT_COUNT, ScanOptions.NONE);
        List<SupportCountDto> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 SupportCountDto
            Long key = (Long) map.getKey();
            SupportCountDto dto = new SupportCountDto(key, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisTemplate.opsForHash().delete(RedisKeyUtils.KEY_WEIBO_SUPPORT_COUNT, key);
        }
        return list;
    }

    @Override
    public List<FriendCommentSupport> getAllCommentSupportDataFromRedis() {
        List<FriendCommentSupport> List = new ArrayList<>();
        Cursor<Map.Entry<Object, Object>> commentCursor = redisTemplate.opsForHash().scan(RedisKeyUtils.KEY_COMMENT_SUPPORT_STATUS, ScanOptions.NONE);
        while (commentCursor.hasNext()){
            Map.Entry<Object, Object> commentEntry = commentCursor.next();
            String commentKey = (String) commentEntry.getKey();
            //分离出 commentSupportId，commentSupportUserId
            String[] commentSplit = commentKey.split(":");
            Long commentSupportId = Long.valueOf(commentSplit[0]);
            Long commentSupportUserId = Long.valueOf(commentSplit[1]);
            Integer value = (Integer) commentEntry.getValue();
            Boolean supportStatus = (value == 1) ? true : false;

            //存储评论supportData 对象
            FriendCommentSupport friendCommentSupportData = new FriendCommentSupport();
            friendCommentSupportData.setSupportId(commentSupportId);
            friendCommentSupportData.setUserId(commentSupportUserId);
            friendCommentSupportData.setStatus(supportStatus);
            friendCommentSupportData.setSupportTime(new Date());
            List.add(friendCommentSupportData);

            //存到 list 后从 Redis 中删除
            redisTemplate.opsForHash().delete(RedisKeyUtils.KEY_COMMENT_SUPPORT_STATUS, commentKey);
        }

        return List;
    }

    @Override
    public Long getCommentSupportCountFromRedis (Long commentId) {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.KEY_COMMENT_SUPPORT_COUNT, ScanOptions.NONE);
        List<SupportCountDto> list = new ArrayList<>();
        Long count = 0L;
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //获取Id为supportId的点赞数量
            Long key = (Long) map.getKey();
            if (key == commentId) {
                count = (Long) map.getValue();
                //从Redis中删除这条记录
                redisTemplate.opsForHash().delete(RedisKeyUtils.KEY_COMMENT_SUPPORT_COUNT, key);
                break;
            }
        }
        return count;
    }

    @Override
    public List<SupportCountDto> getAllCommentsupportCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.KEY_COMMENT_SUPPORT_COUNT, ScanOptions.NONE);
        List<SupportCountDto> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 LikedCountDT
            Long key = (Long) map.getKey();
            SupportCountDto dto = new SupportCountDto(key, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisTemplate.opsForHash().delete(RedisKeyUtils.KEY_COMMENT_SUPPORT_COUNT, key);
        }
        return list;
    }
}
