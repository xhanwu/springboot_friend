package com.tcl.friendserver;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendServerApplication.class)
@AutoConfigureMockMvc
public class AccountTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTests.class);

    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    MockMvc mockMvc;
    MockHttpSession mockHttpSession = new MockHttpSession();

    @Test
    public void testRegisterAccount() {
        log("testRegisterAccount ++ ");
        String uri = "/api/account";
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .param("mobile", "13633335556")
                    .param("password", "a123456")).andReturn();
            log("result status = " + result.getResponse().getStatus());
            log("result content = " + result.getResponse().getContentAsString());
        } catch (Exception e) {
            log("testRegisterAccount Exception occur!");
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin() {
        log("testLogin ++");
        login();
    }

    @Test
    public void testLogout() {
        log("testLogin ++");
        login();
    }

    @Test
    public void testGetUserInfo() {
        log("testLogin ++");
        String uri = "/api/user_data";
        login();
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .session(mockHttpSession)).andReturn();
            log("result status = " + result.getResponse().getStatus());
            log("result content = " + result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * this.id = id;
     *         this.sex = sex;
     *         this.birthday = birthday;
     *         this.constellation = constellation;
     *         this.login = login;
     *         this.signature = signature;
     *         this.posProvince = posProvince;
     *         this.posCity = posCity;
     *         this.posDistrict = posDistrict;
     *         this.fans = fans;
     *         this.avatar = avatar;
     *         this.school = school;
     *         this.major = major;
     *         this.enrollmentYear = enrollmentYear;
     *         this.industry = industry;
     */
    @Test
    public void testSetUserInfo() {
        login();
        /*
        uid: "",
        nickname: "",
        sex: "0",//0:unknown;1:male;2:female
        birthday: "0000-00-00",
        constellation: "",//星座
        //qq: "",
        login: "",//登录次数
        //reg_ip: "",//登录ip地址
        //last_login_ip: "",//最后一次登录的ip地址
        signature: "",//个性签名
        pos_province: "",//所在省份
        pos_city: "", //所在城市
        pos_district: "",//所在乡镇
        //pos_community: "",
        //session_id: "",
        fans: "",
        avatar: "",//头像
        school: "", //学校
        major: "",//专业
        enrollment_year: "0000",//入学年份
        industry: "",//行业
         */
        String uri = "/api/set_user_data";
        try{
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .param("nickname", "太白")
                    .param("sex", "1")
                    .param("birthday","2010-11-11")
                    .param("signature","happy life")
                    .param("pos_province","山东")
                    .param("pos_city","菏泽")
                    .session(mockHttpSession)).andReturn();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void login() {
        String uri = "/api/authorization";
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .param("mobile", "13877778888")
                    .param("password", "Aa123456")
                    .session(mockHttpSession)).andReturn();
            log("result status = " + result.getResponse().getStatus());
            log("result content = " + result.getResponse().getContentAsString());
        } catch (Exception e) {
            log("testRegisterAccount Exception occur!");
            e.printStackTrace();
        }
    }

    private void log(String string) {
        System.out.println(this.getClass().getSimpleName() + " : " + string);
    }
}
