package com.vasbas.chapter29;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Str{
    static void e1(){
        ArrayList<Integer> myList = new ArrayList<Integer>();
        myList.add(3);
        myList.add(17);
        myList.add(24);
        myList.add(9);
        myList.add(15);
        myList.add(666);

        System.out.println("Исходный список: " + myList);

        //получим поток элементов списочного массива
        Stream<Integer> myStream = myList.stream();

        //получить мин и мах значения вызвав методы: min(), max(), isPresent(), get()
        Optional<Integer> minVal = myStream.min(Integer::compare);
        if(minVal.isPresent()){
            System.out.println("Минимальное значение: " + minVal.get());
        }

        myStream = myList.stream();
        Optional<Integer> maxVal = myStream.max(Integer::compare);
        maxVal.ifPresent(integer -> System.out.println("Максимальное значение: " + integer));

        //сортировка метод sorted()
        Stream<Integer> sortStr = myList.stream().sorted();

        System.out.println("Отсортированный поток данных ");
        sortStr.forEach((n) -> System.out.println(n + " "));
        System.out.println();

        //метод filter()
        Stream<Integer> oddStr = myList.stream().sorted().filter((n) -> n % 2 == 1);
        System.out.println("Нечетные значения ");
        oddStr.forEach((n) -> System.out.println(n + " "));
        System.out.println();

        //Нечетные и больше 10
        Stream<Integer> oddStr1 = myList.stream().sorted().filter((n) -> n % 2 == 1).filter((n) -> n > 10);
        System.out.println("Нечетные значения больше 10 ");
        oddStr1.forEach((n) -> System.out.println(n + " "));
        System.out.println();
    }

    static void e2(){
        ArrayList<Integer> myList = new ArrayList<Integer>();

        myList.add(3);
        myList.add(17);
        myList.add(24);
        myList.add(9);
        myList.add(15);
        myList.add(777);

        //Два способа применения reduce()
        //1-й способ
        Optional<Integer> prObj = myList.stream().reduce((a, b) -> a * b);
        if(prObj.isPresent()){
            System.out.println("Произвежение в виде объекта типа Optional " + prObj.get());
        }

        //2-й способ
        int prod = myList.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Произвежение в виде значение типа int " + prod);
    }

    static void e3(){
        ArrayList<Double> myList = new ArrayList<>();

        myList.add(1.0);
        myList.add(7.0);
        myList.add(9.0);
        myList.add(25.0);
        myList.add(7.0);
        myList.add(5.0);
        myList.add(13.0);
        myList.add(22.0);

        long ti = System.currentTimeMillis();

        double prOfSqrt = myList.parallelStream().reduce(1.0, (a, b) -> a * Math.sqrt(b),
                (a, b) -> a * b);

        System.out.println("Произведение корней " + prOfSqrt);

        ti = System.currentTimeMillis() - ti;
        System.out.println("time 1 " + ti);

        ti = System.currentTimeMillis();

        double prOfSqrt1 = myList.stream().reduce(1.0, (a, b) -> a * Math.sqrt(b));

        System.out.println("Произведение корней " + prOfSqrt1);

        ti = System.currentTimeMillis() - ti;
        System.out.println("time 2 " + ti);
    }

    static void e4(){
        ArrayList<Double> myList = new ArrayList<>();

        myList.add(1.0);
        //myList.add(7.0);
        myList.add(9.0);
        myList.add(25.0);
        //myList.add(7.0);
        //myList.add(3.0);

        Stream<Double> sqrtStr = myList.stream().map(Math::sqrt);

        double res = sqrtStr.reduce(1.0, (a, b) -> a * b);
        System.out.println("Sqrt = " + res);
    }

    static void e5(){
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();
        myList.add(new NamePhoneEmail("Вовка", "666-666", "qqq@eee.tt"));
        myList.add(new NamePhoneEmail("Петька", "123-456", "asdg@eee.tddt"));
        myList.add(new NamePhoneEmail("Светка", "777-777", "eewc@ghj.tt"));

        System.out.println("Исходные эл-ты списка ");
        myList.stream().forEach( (a) -> System.out.println(a.name + " " + a.phone + " " + a.email ));
        System.out.println();

        Stream<NamePhone> nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phone));
        System.out.println("Список имен и телефонов ");
        nameAndPhone.forEach( (a) -> System.out.println(a.name + " " + a.phone ));
        System.out.println();
        //С помощью списка
        Stream<NamePhone> nameAndPhone11 = myList.stream().map((a) -> new NamePhone(a.name, a.phone));
        List<NamePhone> npList1 = nameAndPhone11.collect(Collectors.toList());
        System.out.println("Список имен и телефонов from list ");
        for (NamePhone e : npList1) {
            System.out.println(e.name + " " + e.phone);
        }
        //С помощью Set
        Stream<NamePhone> nameAndPhone12 = myList.stream().map((a) -> new NamePhone(a.name, a.phone));
        Set<NamePhone> npSet = nameAndPhone12.collect(Collectors.toSet());
        System.out.println("Список имен и телефонов from set ");
        for (NamePhone e : npSet) {
            System.out.println(e.name + " " + e.phone);
        }

        Stream<NamePhone> nameAndPhone1 =
                myList.stream().filter((a) -> a.name.equals("Светка")).map((a) -> new NamePhone(a.name, a.phone));
        System.out.println("Список имен и телефонов 1 ");
        nameAndPhone1.forEach( (a) -> System.out.println(a.name + " " + a.phone ));
        System.out.println();
    }
    static void e6(){
        ArrayList<String> myList = new ArrayList<>();

        myList.add("Альфа");
        myList.add("Бетта");
        myList.add("Гамма");
        myList.add("Дельта");
        myList.add("Кси");
        myList.add("Омега");

        //----------------Iterator------------------------
        System.out.println("Iterator:");

        Stream<String> myStr = myList.stream();

        Iterator<String> itr = myStr.iterator();

        while (itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println();
        System.out.println("Spliterator:");

        //------------------------Spliterator----------------------------------
        Stream<String> myStr1 = myList.stream();

        Spliterator<String> splitItr = myStr1.spliterator();

        while (splitItr.tryAdvance((a) -> System.out.println(a)));

        //-------------------------trySplit-----------------------------------
        System.out.println();
        System.out.println("trySplit  O_O");

        Stream<String> myStr2 = myList.stream();

        Spliterator<String> splitItr1 = myStr2.spliterator();

        Spliterator<String> splitItr2 = splitItr1.trySplit();

        if(splitItr2 != null){
            System.out.println("splitItr2: ");
            splitItr2.forEachRemaining((n) -> System.out.println(n));
        }

        System.out.println();
        System.out.println("splitItr1: ");
        splitItr1.forEachRemaining(System.out::println);
    }
}

class NamePhoneEmail {
    String name;
    String phone;
    String email;

    NamePhoneEmail(String n, String p, String e){
        name = n;
        phone = p;
        email = e;
    }
}

class NamePhone {
    String name;
    String phone;

    NamePhone(String n, String p){
        name = n;
        phone = p;
    }
}



public class Ch29 {
    public static void main(String[] args) throws IOException {
        Str.e6();


    }
}
