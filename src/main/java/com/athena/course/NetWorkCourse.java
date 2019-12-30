package com.athena.course;

/**
 * @Author: xiaoxiang.zhang
 * @Description:网络课程发布
 * @Date: Create in 4:14 PM 2019/12/2
 */
public abstract class NetWorkCourse {

    public final void createWork() {
        //1.发布预习资料
        this.postPreResource();
        //2.制作PPT
        this.createPPT();
        //3.在线直播
        this.liveViedo();
        //4.提交课件，课堂笔记
        this.postNote();
        //5.提交源码
        this.postSource();
        //6.检查作业
        //如果有作业，则检查作业，如果没有作业，则无需检查作业
        if (needHomeWork()) {
            //检查作业
            checkHomeWork();
        }
    }

    protected abstract void checkHomeWork();

    //钩子方法，勾住以后可以对流程做一个微调！
    protected boolean needHomeWork() {
        return false;
    }

    private final void postSource() {
        System.out.println("提交源码至Gper!");
    }

    private final void postNote() {
        System.out.println("提交课件，笔记至百度云盘！");
    }

    private final void liveViedo() {
        System.out.println("在线直播课程上课！");
    }

    private final void createPPT() {
        System.out.println("创建备课PPT");
    }

    private final void postPreResource() {
        System.out.println("分发预习资料");
    }
}
