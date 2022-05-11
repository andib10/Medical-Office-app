package appointment;

import patient.Patient;
import staff.MedicalStaff;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {
    private int AppointmentId;
    private MedicalStaff medic;
    private Patient patient;
    private LocalDateTime timeOfAppointment;
    static int id = 0;

    {
        this.AppointmentId = ++id;
    }

    public Appointment(MedicalStaff medic, Patient patient, LocalDateTime timeOfAppointment) {
        this.medic = medic;
        this.patient = patient;
        this.timeOfAppointment = timeOfAppointment;
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
                ", dateOfAppointment=" + timeOfAppointment +
                '}' + '\n';
    }

    public MedicalStaff getMedic() {
        return medic;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getTimeOfAppointment() {
        return timeOfAppointment;
    }
}
