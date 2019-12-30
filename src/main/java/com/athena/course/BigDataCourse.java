package com.athena.course;

/**
 * @Author: xiaoxiang.zhang
 * @Description:大数据课程
 * @Date: Create in 5:32 PM 2019/12/2
 */
public class BigDataCourse extends NetWorkCourse {
    private boolean needHomeWork = false;

    public BigDataCourse(boolean needHomeWork) {
        this.needHomeWork = needHomeWork;
    }

    @Override
    protected void checkHomeWork() {
        System.out.println("检查大数据作业");
    }

    @Override
    protected boolean needHomeWork() {
        return this.needHomeWork;
    }
}
