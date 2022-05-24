package patient;

import person.Person;

public abstract class Patient extends Person {
    protected String CNP;
    protected int patientId;
    static int id = 0;

    {
        this.patientId = ++id;
    }

    public Patient(String name, String gender, String phoneNumber, int age, String CNP) {
        super(name, gender, phoneNumber, age);
        this.CNP = CNP;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getCNP() {
        return CNP;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
