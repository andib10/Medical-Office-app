package patient;

import java.util.Vector;

public class SpecialPatient extends Patient {
    private String diagnosis;
    private int medsNumber;
    private Vector<String> medication;

    public SpecialPatient(String name, String gender, String phoneNumber, int age, String CNP, String diagnosis, int medsNumber, Vector<String> medication) {
        super(name, gender, phoneNumber, age, CNP);
        this.diagnosis = diagnosis;
        this.medsNumber = medsNumber;
        this.medication = medication;
    }

    @Override
    public String toString() {
        return "SpecialPatient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age + '\'' +
                ", CNP='" + CNP + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", medsNumber='" + medsNumber + '\'' +
                ", medication=" + medication +
                '}' + '\n';
    }
}
