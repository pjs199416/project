package com.project.util;

/**
 * 处理步长数据信息
 * @author Administrator
 *
 */
public class StepUtils {
	public static int[] getSteps(int currentPage,int pageNum,int step){
		if(pageNum<=step){
			return new int[]{1,pageNum}; //1,3
		} else if(currentPage<=step/2){
			return new int[]{1,step};
		} else if(currentPage>(pageNum-step/2)){
			return new int[]{pageNum-step+1,pageNum};
		} else {
			return new int[]{currentPage-step/2,currentPage+step/2};
		}
	}
}
