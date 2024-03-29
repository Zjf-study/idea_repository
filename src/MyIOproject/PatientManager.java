package MyIOproject;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientManager {
    public static void main(String[] args) throws IOException {
        String fileName = "data.txt";//将病人相关信息的数据存储在data.txt中
        FileOutputStream f = new FileOutputStream("user.txt", true);
        Scanner scan = new Scanner(System.in);
        System.out.println("-----------请进行登录-------------");
        System.out.println("请输入账号：");
        String id = scan.nextLine();
        System.out.println("请输入密码：");
        String keyword = scan.nextLine();
        byte[] bytes1 = id.getBytes();
        f.write(bytes1);
        String str = "\r\n";//Windows系统换行操作\r\n
        byte[] bytes2 = str.getBytes();
        f.write(bytes2);
        byte[] bytes3 = keyword.getBytes();
        f.write(bytes3);
        f.close();

        if (id.equals("147852369") && keyword.equals("Yiyuan785")) {
            System.out.println("登录成功！");
            while (true) {
                System.out.println("--------欢迎使用医院疫情感染人员管理系统--------");
                System.out.println("1.查看病人信息");
                System.out.println("2.添加病人信息");
                System.out.println("3.删除病人信息");
                System.out.println("4.修改病人信息");
                System.out.println("5.退出系统");
                System.out.println("请输入您的选择：");
                Scanner sc = new Scanner(System.in);
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        findAllpatient(fileName);
                        break;
                    case "2":
                        addpatient(fileName);
                        break;
                    case "3":
                        deletepatient(fileName);
                        break;
                    case "4":
                        updatepatient(fileName);
                        break;
                    case "5":
                    default:
                        System.out.println("感谢您的使用！");
                        System.exit(0);//使JVM退出，程序结束
                        break;
                }

            }
        }else{
            System.out.println("您输入的账号或密码错误，请退出重新登录。");
            System.exit(0);//使JVM退出，程序结束
        }
    }

    //查看病人信息：
    public static void findAllpatient(String fileName) throws IOException {
        ArrayList<Patient> array = new ArrayList<>();
        readDate(fileName, array);//从文件中读取数据到集合中
        if (array.size() == 0) {
            System.out.println("目前病人都已康复，没有信息可供查看，请重新进行您的选择 ");
            return;
        }
        System.out.println("病人信息按照此格式进行呈现：编号-姓名-年龄-住址 ");
        for (int i = 0; i < array.size(); i++) {
            Patient p = array.get(i);
            System.out.println(p.getNumber() + "  " + p.getName() + "  " + p.getAge() + "  " + p.getAddress());
        }
    }

    //添加病人信息：
    public static void addpatient(String fileName) throws IOException {
        ArrayList<Patient> array = new ArrayList<>();
        readDate(fileName, array);//从文件中读取数据到集合中
        Scanner sc = new Scanner(System.in);
        String number;
        while (true) {
            System.out.println("请输入病人的编号：");
            number = sc.nextLine();
            //避免重复编号
            boolean flag = false;
            for (int i = 0; i < array.size(); i++) {
                Patient p = array.get(i);
                if (p.getNumber().equals(number)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("该编号已经存在，请重新输入");
            } else {
                break;
            }
        }
        System.out.println("请输入病人的姓名：");
        String name = sc.nextLine();
        System.out.println("请输入病人的年龄：");
        String age = sc.nextLine();
        System.out.println("请输入病人的住址：");
        String address = sc.nextLine();
        Patient p = new Patient();
        p.setNumber(number);
        p.setName(name);
        p.setAge(age);
        p.setAddress(address);
        array.add(p);
        writeDate(fileName, array);//将集合中的数据重新写回到文件中
        System.out.println("添加病人信息成功！");
    }

    //删除病人信息
    public static void deletepatient(String fileName) throws IOException {
        ArrayList<Patient> array = new ArrayList<>();
        readDate(fileName, array);//从文件中读取数据到集合中
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要删除的病人的编号：");
        String number = sc.nextLine();
        int index = -1;
        for (int i = 0; i < array.size(); i++) {
            Patient p = array.get(i);
            if (p.getNumber().equals(number)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("您删除的编号所对应病人的信息不存在，请重新选择");
        } else {
            array.remove(index);
            writeDate(fileName, array);//将集合中的数据重新写回到文件中
            System.out.println("删除病人信息成功!");
        }
    }

    //修改病人信息
    public static void updatepatient(String fileName) throws IOException {
        ArrayList<Patient> array = new ArrayList<>();
        readDate(fileName, array);//从文件中读取数据到集合中
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要修改的病人的编号");
        String number = sc.nextLine();
        int index = -1;
        for (int i = 0; i < array.size(); i++) {
            Patient p = new Patient();
            if (p.getNumber().equals(number)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("您修改的编号所对应病人的信息不存在，请重新选择");
        } else {
            System.out.println("请输入新病人的姓名：");
            String name = sc.nextLine();
            System.out.println("请输入新病人的年龄：");
            String age = sc.nextLine();
            System.out.println("请输入新病人的住址：");
            String address = sc.nextLine();
            Patient p = new Patient();
            p.setNumber(number);
            p.setName(name);
            p.setAge(age);
            p.setAddress(address);
            array.set(index, p);
            writeDate(fileName, array);//将集合中的数据重新写回到文件中
            System.out.println("修改病人信息成功！");
        }
    }

    //定义一个方法实现将文件中的数据读取到集合中
    public static void readDate(String fileName, ArrayList<Patient> array) throws IOException {
        //创建一个输入缓冲流对象
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String l;
        while (true) {
            try {
                if (!((l = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] dates = l.split(",");//定义一个数组按照逗号进行分割
            Patient p = new Patient();
            p.setNumber(dates[0]);
            p.setName(dates[1]);
            p.setAge(dates[2]);
            p.setAddress(dates[3]);
            array.add(p);
        }
        br.close();//释放资源
    }

    //定义一个方法实现把集合中的数据写入文件中

    public static void writeDate(String fileName, ArrayList<Patient> array) throws IOException {
        //创建输出缓冲流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < array.size(); i++) {
            Patient p = array.get(i);
            StringBuffer sb = new StringBuffer();//创建一个字符串缓冲对象
            sb.append(p.getNumber()).append(",").append(p.getName()).append(",").append(p.getAge()).append(",").append(p.getAddress());
            bw.write(sb.toString());//以字符串的形式写入文件中
            bw.newLine();//写入一个行分隔符
            bw.flush();//使缓冲区的数据立即被输出
        }
        bw.close();//释放资源
    }
}
