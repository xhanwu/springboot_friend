package com.tcl.friendadmin.config.tips;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回给前台的成功提示
 *
 * @author added by xiangtian.meng
 */
public class SuccessTip extends Tip implements Serializable{
	
	public SuccessTip(List list){
		super.code = 0; //success code is 0
		super.message = "操作成功";
		super.count = "1";
		super.data = list;
	}
	public SuccessTip(){
		super.code = 0; //success code is 0
		super.message = "操作成功";
		super.count = "1";
		List list = new ArrayList();
		super.data = list;
	}

}
