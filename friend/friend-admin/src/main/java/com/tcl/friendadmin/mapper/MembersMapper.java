package com.tcl.friendadmin.mapper;

import com.tcl.friendadmin.model.Members;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MembersMapper {
    List<Members> getMembersManage();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    int deleteByPrimaryKey(Integer uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    int insert(Members record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    int insertSelective(Members record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    Members selectByPrimaryKey(Integer uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    int updateByPrimaryKeySelective(Members record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Tue Jul 23 11:59:52 CST 2019
     */
    int updateByPrimaryKey(Members record);
}