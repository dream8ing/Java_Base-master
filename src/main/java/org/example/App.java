package org.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {

        School school=new School();
        school.createStudents();
        System.out.println("创建的学生信息见文件：D://Student.txt\n");
        school.ClassNum();

        school.AverageWeight();

        System.out.println("一班身高最高的学生为：");
        school.Highest();

        System.out.println("二班体重排序为");
        school.WeightSort();

        System.out.println("所有学生体重排序后：");
        school.AllWeightSort();


    }
}
