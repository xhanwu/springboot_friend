package com.tcl.friendadmin.service;

import com.tcl.friendadmin.model.Members;

import java.util.List;

public interface MembersService {
     List<Members> getMemberManage();
     int  insertMember(Members members);
     int updataMember(Members members);
     Members selectMemberbyUid(int uid);
     int deleteMembers(int uid);
     //int haveNickname(String nickname);
}
