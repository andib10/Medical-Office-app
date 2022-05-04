package patient;

public class NormalPatient extends Patient{
    private String insuranceId;

    public NormalPatient(String name, String gender, String phoneNumber, int age, String CNP, String insuranceId) {
        super(name, gender, phoneNumber, age, CNP);
        this.insuranceId = insuranceId;
    }

    @Override
    public String toString() {
        return "NormalPatient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age + '\'' +
                ", CNP='" + CNP + '\'' +
                ", insuranceId='" + insuranceId +
                '}' + '\n';
    }

}
