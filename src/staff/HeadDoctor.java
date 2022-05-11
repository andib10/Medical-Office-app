package staff;

public class HeadDoctor extends MedicalStaff{
    private int bonus;

    public HeadDoctor(String name, String gender, String phoneNumber, int age, double startingSalary, int bonus) {
        super(name, gender, phoneNumber, age, startingSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return startingSalary + bonus;
    }

    @Override
    public String toString() {
        return "HeadDoctor{" +
                "medicId=" + medicId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", bonus=" + bonus +
                '}' + '\n';
    }

    @Override
    public void displayMedicalStaff() {
        System.out.println(toString());
        System.out.println("Salary: " + calculateSalary() + '\n');
    }

    public int getBonus() {
        return bonus;
    }
}
