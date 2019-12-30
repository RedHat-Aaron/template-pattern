package com.athena.course;

/**
 * @Author: xiaoxiang.zhang
 * @Description:课程测试类
 * @Date: Create in 5:35 PM 2019/12/2
 */
public class CourseTest {
    public static void main(String[] args) {
        //创建Java课程
        NetWorkCourse course = new JavaCourse();
        course.createWork();
        System.out.println("==================================");
        //创建大数据课程
        NetWorkCourse bigData = new BigDataCourse(true);
        bigData.createWork();
    }
}
