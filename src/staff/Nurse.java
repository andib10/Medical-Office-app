package staff;

public class Nurse extends MedicalStaff{
    private int hours;

    public Nurse(String name, String gender, String phoneNumber, int age, double startingSalary, int hours) {
        super(name, gender, phoneNumber, age, startingSalary);
        this.hours = hours;
    }

    @Override
    public double calculateSalary() {
        return startingSalary + 20 * hours;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "medicId=" + medicId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", hours=" + hours +
                '}' + '\n';
    }

    @Override
    public void displayMedicalStaff() {
        System.out.println(toString());
        System.out.println("Salary: " + calculateSalary() + '\n');
    }

    public int getHours() {
        return hours;
    }
}
