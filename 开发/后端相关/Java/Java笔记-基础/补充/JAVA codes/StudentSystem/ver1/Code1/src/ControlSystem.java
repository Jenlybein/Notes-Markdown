import java.util.ArrayList;
import java.util.Scanner;

public class ControlSystem {
    public static void startStudentSystem(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<>();

        loop : while (true){
            System.out.println("-----欢迎来到学生管理系统-----");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出系统");
            System.out.println("请输入你的选择[数字]");

            String choose = sc.next();

            switch (choose) {
                case "1" -> addStudent(list);
                case "2" -> deleteStudent(list);
                case "3" -> updateStudent(list);
                case "4" -> queryStudent(list);
                case "5" -> {
                    System.out.println("退出系统");
                    break loop;
                }
                default -> System.out.println("没有此选项");
            }
        }
    }
    public static boolean containFlag(ArrayList<Student> list, String id) {
        for (Student stu : list) {
            if (stu.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static int getIndex(ArrayList<Student> list, String id) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    public static void addStudent(ArrayList<Student> list){
        System.out.println("-----添加学生-----");
        Student stu = new Student();
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入学生id");
        String id = sc.next();
        //判断学生是否存在
        if(containFlag(list,id)){
            System.out.println("学生id已存在!");
            return;
        }
        else{
            stu.setId(id);
        }
        System.out.println("请输入学生姓名");
        stu.setName(sc.next());
        System.out.println("请输入学生年龄");
        stu.setAge(sc.nextInt());
        System.out.println("请输入学生住址");
        stu.setAddress(sc.next());
        list.add(stu);
        System.out.println("学生信息添加成功");
    }

    public static void deleteStudent(ArrayList<Student> list){
        System.out.println("-----删除学生-----");
        System.out.println("请输入学生id");

        //判断学生是否存在
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        int index = getIndex(list,id);
        if(index!=-1){
            list.remove(index);
            System.out.println("id为:"+id+"的学生信息已删除");
        }
        else{
            System.out.println("该学生信息不存在，删除失败");
        }
    }
    public static void updateStudent(ArrayList<Student> list){
        System.out.println("-----修改学生-----");
        System.out.println("请输入学生id");

        Student stu;
        //判断学生是否存在
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        int index = getIndex(list,id);
        if(index!=-1){
            stu = list.get(index);
            System.out.println("请输入学生新id");
            String newId = sc.next();
            //判断学生id是否冲突
            while(true){
                if(containFlag(list,newId)){
                    System.out.println("学生id已存在!");
                }
                else{
                    stu.setId(newId);
                    break;
                }
            }
            System.out.println("请输入学生新姓名");
            stu.setName(sc.next());
            System.out.println("请输入学生新年龄");
            stu.setAge(sc.nextInt());
            System.out.println("请输入学生新住址");
            stu.setAddress(sc.next());
            System.out.println("id为:"+id+"的学生信息已修改[新id："+newId+"]");
        }
        else{
            System.out.println("该学生信息不存在，修改失败");
        }
    }
    public static void queryStudent(ArrayList<Student> list){
        System.out.println("-----查询学生-----");
        int size = list.size();
        if(size == 0){
            System.out.println("当前无学生信息。");
            return;
        }
        //打印表头信息
        System.out.println("id\t\t\t姓名\t\t年龄\t\t家庭住址");

        for(int i = 0; i < size; i++){
            Student stu = list.get(i);
            System.out.println(stu.getId() + "\t\t\t" + stu.getName() + "\t\t" + stu.getAge() + "\t\t" + stu.getAddress());
        }
    }

}
