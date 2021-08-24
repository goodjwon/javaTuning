package com.ezezbiz.demo.callback;

public class CallBackMain {
    public static void main(String[] args) {
        Callee callee = new Callee();
        callee.setCallBack(new Callee.CallBack() {
            @Override
            public void onGetMessage(Callee callee) {
                System.out.println("Input message is > " + callee.getMsg());
            }
        });

        for(int i = 0; i<5; i++){
            callee.onInputMessage();
        }
    }
}
