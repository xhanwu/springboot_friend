package com.tcl.friendadmin.serviceImpl;

import com.tcl.friendadmin.mapper.MembersMapper;
import com.tcl.friendadmin.model.Members;
import com.tcl.friendadmin.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembersServiceImpl implements MembersService {
    @Autowired
    private MembersMapper membersMapper;

    @Override
    public List<Members> getMemberManage() {
        List<Members> membersList = membersMapper.getMembersManage() ;
        System.out.println(membersMapper.getMembersManage());
        return membersList;
    }

    @Override
    public int insertMember(Members members) {
        return membersMapper.insert(members);
    }

    @Override
    public int updataMember(Members members) {
        return membersMapper.updateByPrimaryKey(members);
    }

    @Override
    public Members selectMemberbyUid(int uid) {
        return membersMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int deleteMembers(int uid) {
        return membersMapper.deleteByPrimaryKey(uid);
    }

}
