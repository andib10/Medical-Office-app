package staff;

public class Cardiologist extends MedicalStaff{
    private int experienceLevel;

    public Cardiologist(String name, String gender, String phoneNumber, int age, double startingSalary, int experienceLevel) {

        super(name, gender, phoneNumber, age, startingSalary);
        this.experienceLevel = experienceLevel;
    }

    @Override
    public double calculateSalary() {
        return startingSalary * experienceLevel;
    }

    @Override
    public String toString() {
        return "Cardiologist{" +
                "medicId=" + medicId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", experienceLevel=" + experienceLevel +
                '}' + '\n';
    }

    @Override
    public void displayMedicalStaff() {
        System.out.println(toString());
        System.out.println("Salary: " + calculateSalary() + '\n');
    }

}
