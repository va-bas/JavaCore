package com.vasbas.chapter10;

import java.util.Random;

class MyException extends Exception{
    private int det;

    MyException (int a){
        det = a;
    }

    public String toString(){
        return  "Ошибка a>10 a=" + det ;
    }
}

class Exc {
    static void e1(){
        int d = 0;
        int a = 10/d;
    }

    static void e2(){
        int d, a;
        try{
            d = 0;
            a = 10/d;
            System.out.println("Не выводится");
        }
        catch (ArithmeticException e){
            System.out.println("Деление на 0");
        }
        System.out.println("После блока try");
    }

    static void e3(){
        int a = 0, b = 0, c = 0;
        Random r = new Random();
        for(int i = 0; i < 32; i++ ){
            try{
                b = r.nextInt();
                c = r.nextInt();
                a = 12345 / (b/c);
                System.out.println("a" + i + " :" + a);
            }
            catch (ArithmeticException e){
                System.out.println("Деление на 0");
                //a = 0;
            }

        }
    }
    static void e4(){
        try {
            throw new NullPointerException("Демонстрация");
        }
        catch (NullPointerException e){
            System.out.println("Перехват внутри метода " + e);
            throw e;
        }
    }
    static void e5(int a) throws MyException{
        System.out.println("metod " + a);
        if(a > 10){
            throw new MyException(a);
        }
        System.out.println("без исключений");
    }
}

public class Ch10 {

    public static void main(String[] args) {
	// write your code here
        Exc exc = new Exc();
        try{
            Exc.e4();
        }
        catch (NullPointerException e){
            System.out.println("Повторный перехват " + e);
        }
        try{
            exc.e5(1);
            exc.e5(20);
        }
        catch (MyException e){
            System.out.println("Перехват исключения " + e);
        }
        //exc.e3();




    }
}
