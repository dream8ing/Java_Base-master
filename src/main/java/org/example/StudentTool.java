package org.example;
import com.sun.org.apache.xpath.internal.objects.XString;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


public class StudentTool
{
    private HashSet<String> names;
    public List<Student> students;

    private static final Random random= new Random();

    /**
     * 创建一个学生
     */
    public static Student createStudent() throws IOException {
        Student student=new Student();
        student.setName(StudentTool.RandomName());

        student.setAge(StudentTool.RandomAge());
        student.setHeight(StudentTool.RandomHeight());
        student.setWeight(StudentTool.RandomWeight());
        student.setRoom(StudentTool.RandomClass());



        student.content();

        return student;
    }


    /**
     * 创建101个学生
     */

    /**
     * 随机生成名字
     */
    public static String RandomName() {

        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z"};
        int num = (int) (random.nextInt(2) + 2);
        String fullName="";
        for (int i = 0; i < num; i++) {
            int index = (int) (Math.random() * chars.length);
            String partName=chars[index];
            fullName=fullName+partName;
        }
        return fullName;
    }

    /**
     * 随机生成年龄
     */
    public static Integer RandomAge(){
        int age = (int) (random.nextInt(2) + 18);
        return age;
    }

    /**
     * 随机生成身高
     * @return
     */
    public static  Double RandomHeight(){
        int max=20000;
        int min=15000;
        int res = random.nextInt(max)%(max-min+1) + min;
        double height=(res*0.01) ;

        // System.out.println(height);
        BigDecimal b = new BigDecimal(Double.toString(height));


        return b.setScale(2,5).doubleValue();

    }


    /**
     * 随机生成体重
     */
    public static Double RandomWeight(){
        int max=8000;
        int min=5000;
        int res = random.nextInt(max)%(max-min+1) + min;
        double weight=( res*0.01);
        BigDecimal b = new BigDecimal(Double.toString(weight));


        return b.setScale(2,5).doubleValue();
    }


    /**
     * 随机生成班级
     */
    public static String RandomClass(){
        String[] chars = new String[]{"一","二","三","四","五"};

        int index = (int) (Math.random() * chars.length);
        String Class=chars[index];
        return Class;
    }

}

