package com.ezezbiz.demo.generic;

public class Koo {
    public <N1 extends Integer, N2 extends Integer> Integer exampleOne(N1 t, N2 e){
        return (Integer) t + (Integer) e;
    }

    public <String> Foo<String> exampleTwo(){
        return new Foo<>();
    }

    public static void main(String[] args) {
        Koo koo = new Koo();
        // int result = koo.exampleOne(1, "2"); // 형 체크에 의한 컴파일러 에러 발생.
        int result = koo.exampleOne(1, 2);
        System.out.println(result);
    }
}
