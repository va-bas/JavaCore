package com.vasbas.chapter13;

import java.io.*;

class WriteRead{
    //ввод с консоли
    static void e1() throws IOException {
        String c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите q для выхода");
        do{
            c = br.readLine();
            System.out.println(c);
            //System.out.println("c");
        } while (!c.equals("q"));
    }
    //редактор текста
    static void e2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = new String[100];
        System.out.println("Введите строки");
        System.out.println("Введите 'стоп' для выхода");
        for(int i = 0; i < 100; i++){
            str[i] = br.readLine();
            if(str[i].equals("стоп")) break;
        }
        System.out.println("Содержимое файла:");
        for(int i = 0; i < 100; i++){
            if(str[i].equals("стоп")) break;
            System.out.println(str[i]);
        }
    }
    //чтение файла в консоль
    static void e3(File args) throws IOException {
        int i;
        FileInputStream fin = null;

        //убедимся что имя файла указанно
        if (args == null){
            System.out.println("Имя файла неочень");
            return;
        }

        //открываем файл и считываем побайтно пока не будет символ конца света
        try {
            fin = new FileInputStream(args);

            do{
                i = fin.read();
                if (i != -1 && (char) i != '\n'){
                    System.out.println((char) i);
                }
            }
            while (i != -1);
        }
        catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }
        catch (IOException e){
            System.out.println("Ошибка ввода-вавода");
        }
        finally {
            try {
                if (fin != null){
                    fin.close();
                }
            }
            catch (IOException e){
                System.out.println("Ошибка закрытия файла");
            }
        }
    }
}

public class Ch13 {
    public static void main(String[] args) throws IOException {
        File file = new File("./src/com/vasbas/chapter13/test.txt");
        //File file = new File("D:\\Users\\User\\IdeaProjects\\JavaCore\\src\\com\\vasbas\\chapter13\\test.txt")
        WriteRead.e2();
    }
}
