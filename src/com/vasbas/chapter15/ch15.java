package com.vasbas.chapter15;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

interface MyNumber{
    double getValue();
}

interface NumTest{
     boolean test(int n);
}

interface SomeTest<T>{
    T test(T n);
}

interface SoLam{
    String func(String n);
}

interface TemTest<T>{
    boolean func(T v1, T v2);
}


class ExLambd{
    static <T> void e1(T w){
        System.out.println(w);
        MyNumber myNumb;
        myNumb = () -> 123.45 + 25;
        System.out.println(myNumb.getValue());
    }
    static void e2(){
        NumTest numTest = (n) -> (n % 2 == 0);

        if(numTest.test(10)) System.out.println("10 делится на 2");
        if(!numTest.test(9)) System.out.println("9 не делится на 2");
    }
    static void e3(){
        SomeTest<String> reverse = (str) ->{
            String result = "";
            int i;

            for (i = str.length() - 1; i>=0; i--){
                result += str.charAt(i);
            }
            return result;
        };
        System.out.println("Лямбда меняется на " + reverse.test("Лямбда"));
        System.out.println("Вовка сука спит меняется на " + reverse.test("Вовка сука спит"));
    }
    //------------- Lambda vida :: ---------------------
    static String strRev(String str){
        String result = "";
        int i;
        for(i = str.length() - 1; i >= 0; i--){
            result += str.charAt(i);
        }
        return result;
    }
    static String strOp(SomeTest<String> lam, String s){
        return lam.test(s);
    }
    //-------------------------------------------------
    static void e4(){
        String strIn = "Лямбда захватывает весь мир";
        String strOut;

        strOut = strOp(ExLambd::strRev, strIn);

        System.out.println(strIn);
        System.out.println(strOut);
    }
    //---------------------Temperature-----------------
    static <T> int counter(T[] vals, TemTest<T> f, T v){
        int count = 0;

        for (T val : vals) {
            if (f.func(val, v)) {
                count++;
            }
        }
        return count;
    }

    static void e5(){
        int count;

        HiTemp[] weekDayH = {
                new HiTemp(89), new HiTemp(90), new HiTemp(82),
                new HiTemp(83), new HiTemp(89), new HiTemp(90),
                new HiTemp(91), new HiTemp(83), new HiTemp(91)
        };

        count = counter(weekDayH, HiTemp::sameTemp, new HiTemp(89));

        System.out.println("Дней когда макс темп была 89 было: " + count);

        count = counter(weekDayH, HiTemp::lessThetTemp, new HiTemp(90));

        System.out.println("Дней когда макс темп была меньше 90 было: " + count);
    }

    static void e6(){
        ArrayList<Compar> arr = new ArrayList<Compar>();

        arr.add(new Compar(1));
        arr.add(new Compar(5));
        arr.add(new Compar(4));
        arr.add(new Compar(9));
        arr.add(new Compar(12));
        arr.add(new Compar(1));

        Compar maxV = Collections.max(arr, UseMetodX::compMC);

        System.out.println("Max number is: " + maxV.getVal());
    }
}
class HiTemp{
private int hTemp;

    HiTemp(int ht){
        hTemp = ht;
    }

    boolean sameTemp(HiTemp ht2){
        return hTemp == ht2.hTemp;
    }

    boolean lessThetTemp(HiTemp ht2){
        return hTemp < ht2.hTemp;
    }
}

class UseMetodX{
    //static T getByID(long id)
    static int compMC(Compar a, Compar b){
        System.out.println(a.getVal());
        System.out.println(b.getVal());
        System.out.println(a.getVal() - b.getVal());
        return a.getVal() - b.getVal();
    }
}

class Compar {
    private int val;

    Compar(int v){
        val = v;
    }

    public int getVal() {
        return val;
    }
}


public class ch15 {
    public static void main(String[] args) throws IOException {
        //ExLambd.e1(1.0);
        ExLambd.e6();
        //717


    }
}
