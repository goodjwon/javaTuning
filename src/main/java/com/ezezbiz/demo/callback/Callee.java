package com.ezezbiz.demo.callback;

import java.util.Scanner;

public class Callee {
    private String msg;
    private CallBack callBack;

    @FunctionalInterface
    public interface CallBack{
        void onGetMessage(Callee callee);
    }

    public Callee(){
        this.msg = "";
        this.callBack = null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void onInputMessage(){
        Scanner scanner = new Scanner(System.in);
        this.msg = "";
        System.out.println("Enter Message : ");
        this.msg = scanner.nextLine();

        if(this.callBack != null){
            this.callBack.onGetMessage(Callee.this);
        }
    }
}
