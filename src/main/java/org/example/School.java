package org.example;

import java.io.IOException;
import java.util.*;

public class School {

    private HashSet<String> names;
    List<Student> students = new ArrayList<>();
    Student highest_student=new Student();

    /**
     * 创建101个学生
     */
    public List<Student> createStudents()throws IOException{

        for(int i=0;i<101;i++)
        {
            students.add(i,StudentTool.createStudent());

        }
        /**
         * 打印学生

        for (Student student: students) {
            System.out.println(student.getName() + "\t" + student.getAge()+"\t"+student.getHeight()+"\t"+student.getWeight()+"\t"+student.getRoom());
        }
        */
        return students;
    }

    /**
     * 计算班级个数
     */
    public int ClassNum(){

        List<String> Class=new ArrayList<String>();
        int i=0;
        int n=0;
        for (Student student: students) {
            Class.add(i,student.getRoom());
            i++;
        }
        do{
            for(int m=0;m<Class.size();m++)
            {
                String j=Class.get(0);
                if(Class.removeIf(e -> e.contains(j)))
                {
                    n++;
                }

            }

        }while (Class.size()==0);
        System.out.print("班级个数为：");
        System.out.println(n+1);
        return n+1;
    }

    /**
     * 二班体重排序
     */
    public  List<Student> WeightSort(){
        List<Student> weight2 = new ArrayList<>();

        int i=0;
        for (Student student: students) {
            if(student.getRoom().equals("二"))
            {
                weight2.add(i,student);
                i++;
            }
        }
        weight2.sort(Comparator.comparingDouble(Student::getWeight));
        //二班排序后的学生
        for (Student student: weight2) {
            System.out.println(student.getName() + "\t" + student.getAge()+"\t"+student.getHeight()+"\t"+student.getWeight()+"\t"+student.getRoom());
        }
        return weight2;
    }

    /**
     * 所有体重排序
     */
    public  List<Student> AllWeightSort(){
        List<Student> weightall = new ArrayList<>();

        int i=0;
        for (Student student: students) {
             weightall.add(i,student);
                i++;
        }
        weightall.sort(Comparator.comparingDouble(Student::getWeight));

        //打印排序后的学生List

        for (Student student: weightall) {
            System.out.println(student.getName() + "\t" + student.getAge()+"\t"+student.getHeight()+"\t"+student.getWeight()+"\t"+student.getRoom());
        }


        return weightall;
    }

    /**
     * 一班身高最高学生
     */
    public  Student Highest(){
        List<Student> hightsort = new ArrayList<>();

        int i=0;
        for (Student student: students) {
            if(student.getRoom().equals("一"))
            {
                hightsort.add(i,student);
                i++;
            }
        }
        hightsort.sort(Comparator.comparingDouble(Student::getHeight));
        int q=hightsort.size()-1;
        highest_student=hightsort.get(q);

         //打印最高学生
        System.out.println(highest_student.getName() + "\t" + highest_student.getAge()+"\t"+highest_student.getHeight()+"\t"+highest_student.getWeight()+"\t"+highest_student.getRoom());

        return highest_student;
    }


    /**
     *求每个班级的平均体重
     */
    public void AverageWeight(){
        int i=0;
        double res1,res2,res3,res4,res5;
        double sum=0;
        double fullRes1 = 0;
        double fullRes2 = 0;
        double fullRes3 = 0;
        double fullRes4 = 0;
        double fullRes5 = 0;

        for (Student student: students) {
            if(student.getRoom().equals("一"))
            {
                res1=student.getWeight();
                i++;
                sum=sum+res1;
            }fullRes1=sum/i;

        }

        for (Student student: students) {
            if(student.getRoom().equals("二"))
            {
                res2=student.getWeight();
                i++;
                sum=sum+res2;
            }
            fullRes2=sum/i;
        }

        for (Student student: students) {
            if(student.getRoom().equals("三"))
            {
                res3=student.getWeight();
                i++;
                sum=sum+res3;
            }
            fullRes3=sum/i;
        }

        for (Student student: students) {
            if(student.getRoom().equals("四"))
            {
                res4=student.getWeight();
                i++;
                sum=sum+res4;
            }
            fullRes4=sum/i;
        }

        for (Student student: students) {
            if(student.getRoom().equals("五"))
            {
                res5=student.getWeight();
                i++;
                sum=sum+res5;
            }
            fullRes5=sum/i;
        }

        System.out.println(String.format("每个班级的平均体重(1-5)依次为:%.2f,%.2f,%.2f,%.2f,%.2f", fullRes1, fullRes2, fullRes3, fullRes4, fullRes5));
    }
}


