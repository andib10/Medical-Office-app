package service;

import appointment.Appointment;
import patient.NormalPatient;
import patient.SpecialPatient;
import staff.Cardiologist;
import staff.HeadDoctor;
import staff.Nurse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class WriteCSV {
    private static WriteCSV instance = null;

    public static WriteCSV getInstance() {
        if(instance == null) {
            instance = new WriteCSV();
        }
        return instance;
    }

    public void writeNurse() {
        Service medService = Service.getInstance();
        try {
            FileWriter writer = new FileWriter("src/resources/out/nurse.csv");
            ArrayList<Nurse> nurses = medService.getNurses();
            for(var nurse : nurses) {
                writer.write(nurse.getName() + "," + nurse.getGender() + "," + nurse.getPhoneNumber() + "," + nurse.getAge()
                        + "," + nurse.getStartingSalary() + "," + nurse.getHours() + '\n');
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Failed to write nurses " + e.getMessage());
        }
    }


    public void writeCardiologist() {
        Service medService = Service.getInstance();
        try {
            FileWriter writer = new FileWriter("src/resources/out/cardiologist.csv");
            ArrayList<Cardiologist> cardiologists = medService.getCardiologists();
            for(var cardiologist : cardiologists) {
                writer.write(cardiologist.getName() + "," + cardiologist.getGender() + "," + cardiologist.getPhoneNumber() + "," +
                        cardiologist.getAge() + "," + cardiologist.getStartingSalary() + "," + cardiologist.getExperienceLevel() + '\n');
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Failed to write cardiologists " + e.getMessage());
        }
    }

    public void writeHeadDoctor() {
        Service medService = Service.getInstance();
        try {
            FileWriter writer = new FileWriter("src/resources/out/headDoctor.csv");
            ArrayList<HeadDoctor> headDoctors = medService.getHeadDoctors();
            for(var headDoctor : headDoctors) {
                writer.write(headDoctor.getName() + "," + headDoctor.getGender() + "," + headDoctor.getPhoneNumber() + "," +
                        headDoctor.getAge() + "," + headDoctor.getStartingSalary() + "," + headDoctor.getBonus() + '\n');
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Failed to write head doctors " + e.getMessage());
        }
    }

    public void writeNormalPatient() {
        Service patService = Service.getInstance();
        try {
            FileWriter writer = new FileWriter("src/resources/out/normalPatient.csv");
            ArrayList<NormalPatient> normalPatients = patService.getNormalPatients();
            for(var normalPatient : normalPatients) {
                writer.write(normalPatient.getName() + "," + normalPatient.getGender() + "," + normalPatient.getPhoneNumber() + "," +
                        normalPatient.getAge() + "," + normalPatient.getCNP() + "," + normalPatient.getInsuranceId() + '\n');
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Failed to write normal patients " + e.getMessage());
        }
    }

    public void writeSpecialPatient() {
        Service patService = Service.getInstance();
        try {
            FileWriter writer = new FileWriter("src/resources/out/specialPatient.csv");
            ArrayList<SpecialPatient> specialPatients = patService.getSpecialPatients();
            for(var specialPatient : specialPatients) {
                writer.write(specialPatient.getName() + "," + specialPatient.getGender() + "," + specialPatient.getPhoneNumber() + "," +
                        specialPatient.getAge() + "," + specialPatient.getCNP() + "," + specialPatient.getDiagnosis() + "," +
                        specialPatient.getMedsNumber() + "," + specialPatient.getMedication() + '\n');
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Failed to write special patients " + e.getMessage());
        }
    }

    public void writeAppointment() {
        Service appService = Service.getInstance();
        try {
            FileWriter writer = new FileWriter("src/resources/out/appointment.csv");
            ArrayList<Appointment> appointments = appService.getAppointments();
            for(var app : appointments) {
                writer.write(app.getMedic().getName() + "," + app.getPatient().getName() + "," + app.getTimeOfAppointment() + '\n');
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Failed to write appointments " + e.getMessage());
        }
    }

    public void writeAudit(String operation) {
        try(var buffer = new BufferedWriter(new FileWriter("src/resources/out/audit.csv", true))) {
//            var buffer = new BufferedWriter(new FileWriter("src/resources/out/audit.csv", true));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            buffer.write(operation + "," + timestamp.toString() + "\n");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
