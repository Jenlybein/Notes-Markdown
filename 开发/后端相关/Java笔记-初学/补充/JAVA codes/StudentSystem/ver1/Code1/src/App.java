import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        loop:
        while (true) {
            System.out.println("-----学生管理系统登录-----");
            System.out.println("请选择你的操作：");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("3.忘记密码");
            System.out.println("4.退出");

            switch (sc.next()) {
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgetPassword(list);
                case "4" -> {
                    System.out.println("感谢使用，再见！");
                    break loop;
                }
                default -> System.out.println("没有此选项");
            }
        }
    }

    public static void login(ArrayList<User> list) {
        loop:while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入账号：");
            String username = sc.next();
            System.out.print("请输入密码：");
            String password = sc.next();
            // 生成验证码
            ArrayList<Character> chs = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                chs.add((char) ('a' + i));
                chs.add((char) ('A' + i));
                if (i < 9) {
                    chs.add((char) ('0' + i));
                }
            }
            StringBuilder sb = new StringBuilder();
            Random r = new Random();
            for (int i = 0; i < 5; i++) {
                sb.append(chs.get(r.nextInt(chs.size())));
            }
            System.out.println("(输入0退出登录)请输入验证码：" + sb);
            String input = sc.next();
            if(input.equals("0")){
                System.out.println("退出登录");
                break;
            }
            if (input.contentEquals(sb)) {
                for (User u : list) {
                    if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                        System.out.println("登录成功");
                        ControlSystem.startStudentSystem();
                        break loop;
                    }
                }
                System.out.println("用户名不存在或密码错误");
            } else {
                System.out.println("验证码错误");
            }
        }
    }

    public static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        String username,password,personID,phoneNumber;
        boolean flag;
        int len;
        // 用户名注册
        while(true) {
            System.out.print("请输入用户名：");
            username = sc.next();
            // 用户名检测：不短于3个字符，不长于20个字符，仅限大小写字母与数字
            flag = true;
            len = username.length();
            if (len >= 3 && len <= 20) {
                for (int i = 0; i < len; i++) {
                    char c = username.charAt(i);
                    if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')||(c >= '0' && c <= '9'))) {
                        flag = false;
                        break;
                    }
                }
            }else {
                flag = false;
            }

            if (flag){
                // 判断是否唯一
                for(User u : list){
                    if(u.getUsername().equals(username)){
                        System.out.println("用户名已存在，请重新输入");
                        break;
                    }
                }
                System.out.println("用户名设置成功");
                break;
            }else{
                System.out.println("用户名格式错误，请重新输入");
            }
        }

        //密码注册
        // 不短于8个字符，不长于20个字符，必须至少含有大写字母，小写字母，数字，符号其中两项。
        while (true) {
            System.out.print("请输入密码：");
            password = sc.next();
            if(passwordCheck(password)){
                System.out.print("请重复输入密码：");
                String assignPassword = sc.next();
                if (assignPassword.equals(password)) {
                    System.out.println("密码设置成功");
                    break;
                }else{
                    System.out.println("与第一次输入的密码不符，请重新设置密码");
                }
            }else{
                System.out.println("格式不符，请重新输入密码");
            }
        }

        //身份证
        //18位，前17位为数字，第十八位可以为数字,X或x，开头不为0
        while(true) {
            System.out.print("请输入身份证：");
            personID = sc.next();
            len = personID.length();
            flag = true;
            if (len == 18 && !personID.startsWith("0") && ((personID.charAt(17) >= '0' && personID.charAt(17) <= '9') || personID.charAt(17) == 'x' || personID.charAt(17) == 'X')) {
                for (int i = 0; i < len - 1; i++) {
                    if (!(personID.charAt(i) >= '0' && personID.charAt(i) <= '9')) {
                        flag = false;
                        break;
                    }
                }
            }else{
                flag = false;
            }
            if(flag) {
                for (User u : list) {
                    if (u.getPersonID().equals(personID)) {
                        System.out.println("该身份证已存在，请重新输入");
                        break;
                    }
                }
                System.out.println("身份证输入成功");
                break;
            }
            else {
                System.out.println("身份证输入格式错误，请重新输入");
            }
        }

        //手机号
        //中国手机号11位，纯数字
        while(true) {
            System.out.print("请输入手机号：");
            phoneNumber = sc.next();
            len = phoneNumber.length();
            flag = true;
            if (len == 11 && !phoneNumber.startsWith("0")) {
                for (int i = 0; i < len; i++) {
                    if (!(phoneNumber.charAt(i) >= '0' && phoneNumber.charAt(i) <= '9')) {
                        flag = false;
                        break;
                    }
                }
            }else{
                flag = false;
            }
            if(flag) {
                for (User u : list) {
                    if (u.getPhoneNumber().equals(phoneNumber)) {
                        System.out.println("该手机号已存在，请重新输入");
                        break;
                    }
                }
                System.out.println("手机号输入成功");
                break;
            }
            else {
                System.out.println("手机号输入格式错误，请重新输入");
            }
        }

        list.add(new User(username,password,personID,phoneNumber));
        System.out.println("注册成功");
    }

    public static void forgetPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("(输入0退出找回密码)请输入账号：");
            String username = sc.next();
            User user = null;
            boolean flag = false;
            if (username.equals("0")) {
                System.out.println("退出找回密码");
                break;
            }
            for (User u : list) {
                if (u.getUsername().equals(username)) {
                    flag = true;
                    user = u;
                    break;
                }
            }
            if (!flag) {
                System.out.println("用户名不存在");
                continue;
            }
            System.out.print("请输入身份证：");
            String personID = sc.next();
            System.out.print("请输入手机号：");
            String phoneNumbers = sc.next();
            if(user.getPhoneNumber().equals(phoneNumbers)&&user.getPersonID().equals(personID)){
                System.out.println("信息核对正确");
                while(true) {
                    System.out.print("请输入新密码：");
                    String password = sc.next();
                    if (passwordCheck(password)) {
                        System.out.print("请重复输入密码：");
                        String assignPassword = sc.next();
                        if (assignPassword.equals(password)) {
                            user.setPassword(password);
                            System.out.println("密码设置成功");
                            break;
                        } else {
                            System.out.println("与第一次输入的密码不符，请重新设置密码");
                        }
                    }else{
                        System.out.println("格式不符，请重新输入密码");
                    }
                }
            }else{
                System.out.println("信息不匹配");
            }
        }
    }

    public static boolean passwordCheck(String password) {
        boolean flag = true;
        int len = password.length();
        int countUpper = 0;
        int countLower = 0;
        int countNum = 0;
        int countSym = 0;
        if (len >= 8 && len <= 20) {
            for (int i = 0; i < len; i++) {
                char c = password.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    countLower++;
                } else if (c >= 'A' && c <= 'Z') {
                    countUpper++;
                } else if (c >= '0' && c <= '9') {
                    countNum++;
                } else {
                    countSym++;
                }
            }
        } else {
            flag = false;
        }
        int Sum = countUpper + countNum + countLower + countSym;
        if (!(Sum != countNum && Sum != countLower && Sum != countUpper && Sum != countSym)) {
            flag = false;
        }
        return flag;
    }
}
