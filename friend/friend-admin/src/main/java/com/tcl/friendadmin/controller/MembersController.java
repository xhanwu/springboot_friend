package com.tcl.friendadmin.controller;


import com.sun.xml.internal.bind.v2.model.core.ID;
import com.tcl.friendadmin.config.BizExceptionEnum;
import com.tcl.friendadmin.config.exception.BussinessException;
import com.tcl.friendadmin.config.tips.ErrorTip;
import com.tcl.friendadmin.config.tips.SuccessTip;
import com.tcl.friendadmin.config.tips.Tip;
import com.tcl.friendadmin.model.Members;
import com.tcl.friendadmin.service.MembersService;
import com.tcl.friendadmin.utils.BeanKit;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@MapperScan("com.tcl.friendadmin.mapper")
@RequestMapping("/admin")
public class MembersController extends BaseController {
    @Resource
    private MembersService membersService;

    @GetMapping("/members")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "members");
        return "admin/members";
    }
    /*
    * 查询所有用户
    * */
    @GetMapping("/members/list")
    @ResponseBody
    public Tip getMembersList(@RequestParam Map<String, Object> params){
       if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            System.out.println("参数异常");
        }
        return new SuccessTip(membersService.getMemberManage());
    }
    /*
     * 搜索所有用户
     * */
    @GetMapping("/members/search")
    @ResponseBody
    public Tip getMemberById(@RequestParam Map<String,Object> params){
        String number = String.valueOf(params.get("uid"));
        if ( "".equals(number) || number==null){
            return getMembersList(params);
        }
        int ID = Integer.parseInt(number);
        List<Members> member = new ArrayList<>();
        try{
            member.add(membersService.selectMemberbyUid(ID));
            if (member.get(0)==null){
                return new ErrorTip(membersService.getMemberManage());
            }
        }catch (Exception e){
            return new ErrorTip();
        }
        return new SuccessTip(member);
    }

/*    * 添加用户
    * */
    @ResponseBody
    @PostMapping("/members/insert")
    public Tip insertMember(@RequestParam Map<String,Object> params){
       try{
           Members members = BeanKit.fillBeanWithMap(params,new Members());
           System.out.println(params.toString());
           System.out.println("members = "+members.toString());
           Boolean flag = String.valueOf(members.getPassword())!=null &&
                          String.valueOf(members.getMobile())!=null &&
                          String.valueOf(members.getPerms())!=null;
           if (!flag){
               throw new BussinessException(BizExceptionEnum.NAME_REAPEAT);
           }
           membersService.insertMember(members);
       }catch (Exception e ){
           return new ErrorTip(0,"添加用户失败");
       }
        return new SuccessTip();
    }

   /*
   * 删除选定用户
   * */
   @ResponseBody
    @PostMapping("/members/delete")
    public Tip deleteMember(@RequestParam Map<String,Object> params ){
        System.out.println(params);
        try{
            int ID = Integer.parseInt(String.valueOf(params.get("uid")))+10;
            System.out.println("delete id = "+ ID);
            if (membersService.deleteMembers(ID) != 1){
                throw new BussinessException(BizExceptionEnum.NO_THIS_USER);
            }
            membersService.deleteMembers(ID);
        }catch (Exception e){
            System.out.println("删除失败"+e);
        }
        return new SuccessTip();
    }
    /*
    * 更新选定用户信息
    * */
    @ResponseBody
    @PostMapping("/members/edit")
    public Tip editMember(@RequestParam Map<String,Object> params){
        try{
            int ID = Integer.parseInt(String.valueOf(params.get("uid")));
            System.out.println(" update id = "+ID );
            if (membersService.selectMemberbyUid(ID)==null){
                throw new BussinessException(BizExceptionEnum.NO_THIS_USER);
            }
            Members members = membersService.selectMemberbyUid(ID);
            members.setMobile(String.valueOf(params.get("mobile")));
            members.setPassword(String.valueOf(params.get("password")));
            members.setPerms(String.valueOf(params.get("perms")));
            if (membersService.updataMember(members)!=1){
                throw new BussinessException(BizExceptionEnum.NO_THIS_USER);
            }
            membersService.updataMember(members);
        }catch (Exception e){
            System.out.println("抛出");
            return new ErrorTip(0,"更新用户信息失败");
        }
        System.out.println("success change ");
        return new SuccessTip();

    }
    @Transactional
    public int test(int uid){
        int flag = membersService.deleteMembers(uid);
        if (flag!=1){
            System.out.println("删除失败");
           // throw new BussinessException(BizExceptionEnum.NO_THIS_USER);
        }
        return membersService.deleteMembers(uid);
    }

}
