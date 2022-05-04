package appointment;

import patient.Patient;
import staff.MedicalStaff;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {
    private int AppointmentId;
    private MedicalStaff medic;
    private Patient patient;
    private String dateOfAppointment;
    static int id = 0;

    {
        this.AppointmentId = ++id;
    }

    public Appointment(MedicalStaff medic, Patient patient, String dateOfAppointment) {
        this.medic = medic;
        this.patient = patient;
        this.dateOfAppointment = dateOfAppointment;
    }

    public int getAppointmentId() {
        return AppointmentId;
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "AppointmentId=" + AppointmentId +
                ", medic=" + medic +
                ", patient=" + patient +
                ", dateOfAppointment=" + dateOfAppointment +
                '}' + '\n';
    }
}
