package org.example;

import sun.nio.cs.ext.GBK;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class Student {

    private String name;

    private Integer age;

    private Double height;

    private Double weight;

    private String room;

    public List<Student> students;
    /**
     * 默认构造函数，只在没有自定义构造函数时存在！
     */
    public Student() {
    }

    public Student(String name, Integer age, Double height, Double weight, String room) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.room = room;
    }

    public String getName(){ return name; }

    public void setName(String name) {
        this.name = name;

    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * 一个学生信息占一行，英文逗号分隔字段信息
     * @return
     */
    public String content() throws IOException {
        String res = name + "," + age;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(",")
                .append(age).append(",")
                .append(height).append(",")
                .append(weight).append(",")
                .append(room+"\r\n");
        ;

        FileTooll.writeStringToFile(FileTooll.create("D:\\Student.txt",false),stringBuilder.toString(), Charset.forName("utf8"),true);
        //System.out.println(string);
        return stringBuilder.toString();
    }

}