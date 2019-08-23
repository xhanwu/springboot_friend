package com.tcl.friendserver.utils;

import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class HttpRequestResponseUtils {

    /**
     *
     * 通过反射从request参数中构造对应对象
     * 目前只能处理Integer和String类型数据
     *
     * @param request
     * @return
     */
    public static Object extraObjectFromRequest(HttpServletRequest request, Class clazz) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> strings = parameterMap.keySet();
        Object result = null;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (result != null) {
            try {
                for (String paramKey : strings) {
                    String[] values = parameterMap.get(paramKey);
                    if (values.length == 0) continue;

                    //类似pos_province字段要转为转为posProvince
                    paramKey = TextUtils.lineToHump(paramKey);
                    Field field = result.getClass().getDeclaredField(paramKey);
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    if (fieldType == Integer.class) {
                        if (values.length == 1) {
                            field.set(result, Integer.valueOf(values[0]));
                        }//TODO 数组类型的值后续再处理

                    } else if (fieldType == String.class) {
                        if (values.length == 1) {
                            field.set(result, values[0]);
                        } else {
                            field.set(result, values);
                        }
                    }//TODO 其他数据类型目前还没有,先不做处理
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * description 封装response  统一json返回
     *
     * @param outStr 1
     * @param response 2
     */
    public static void responseWrite(String outStr, ServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter;
        try {
            printWriter = WebUtils.toHttp(response).getWriter();
            printWriter.write(outStr);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
