package staff;

import person.Person;

public abstract class MedicalStaff extends Person {
    protected int medicId;
    static int id = 0;
    protected double startingSalary;
    {
        this.medicId = ++id;
    }

    public MedicalStaff(String name, String gender, String phoneNumber, int age, double startingSalary) {
        super(name, gender, phoneNumber, age);
        this.startingSalary = startingSalary;
    }

    public int getMedicId() {
        return medicId;
    }

    public double getStartingSalary() {
        return startingSalary;
    }

    public abstract double calculateSalary();

    public abstract void displayMedicalStaff();
}
