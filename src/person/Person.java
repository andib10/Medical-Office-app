package person;

public abstract class Person {
    protected String name;
    protected String gender;
    protected String phoneNumber;
    protected int age;

    public Person(String name, String gender, String phoneNumber, int age) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
