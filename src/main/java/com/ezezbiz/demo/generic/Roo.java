
package com.ezezbiz.demo.generic;


interface Roo <T1, T2, T3> {
    T1 implementThis(T1 t1);
    T2 implementThis();
    T3 maintainGeneric(T3 t3);
}

// 제네릭에 어떤 자료형의 정의하느냐에 따라 유연하게 구현 가능.
class roo <String, Integer, T3> implements Roo<String,Integer,T3> {

    @Override
    public String implementThis(String s) {
        return s;
    }

    @Override
    public Integer implementThis() {
        return null;
    }

    // 이렇게 제네릭을 유지하는 방법도 있음.
    @Override
    public T3 maintainGeneric(T3 t3) {
        return null;
    }
}


