package lib.world;

public class Person {
    // 属性
    public static final boolean MALE = true;
    public static final boolean FEMALE = false;
    private String name;
    private int age;
    private boolean gender;

    // 构造方法
    public Person(String name, int age, boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // 方法
    public void introduce() {
        System.out.println("My name is " + name + ".");
        System.out.println("I am " + age + " years old.");
        if (this.gender) System.out.println("My gender is male.");
        else             System.out.println("My gender is female.");
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        if (this.gender) return "male";
        else             return "female";
    }

    public String toString() {
        if (this.gender) return this.name + "(male): " + this.age;
        else             return this.name + "(female): " + this.age;
    }
}

