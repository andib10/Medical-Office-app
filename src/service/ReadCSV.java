package service;

import appointment.Appointment;
import patient.NormalPatient;
import patient.SpecialPatient;
import staff.Cardiologist;
import staff.HeadDoctor;
import staff.Nurse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Vector;

public class ReadCSV {
    private static ReadCSV instance = null;

    public static ReadCSV getInstance() {
        if(instance == null) {
            instance = new ReadCSV();
        }
        return instance;
    }

    public void readNurse() {
        Service med = Service.getInstance();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/in/nurse.csv"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                med.addMedicalStaff(new Nurse(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), Integer.parseInt(data[5])));
            }
        } catch (IOException e) {
            System.out.println("Failed to read nurses from CSV file. " + e.getMessage());
        }
    }

    public void readCardiologist() {
        Service med = Service.getInstance();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/in/cardiologist.csv"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                med.addMedicalStaff(new Cardiologist(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), Integer.parseInt(data[5])));
            }
        } catch (IOException e) {
            System.out.println("Failed to read cardiologists from CSV file. " + e.getMessage());
        }
    }

    public void readHeadDoctor() {
        Service med = Service.getInstance();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/in/headDoctor.csv"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                med.addMedicalStaff(new HeadDoctor(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), Integer.parseInt(data[5])));
            }
        } catch (IOException e) {
            System.out.println("Failed to read head doctors from CSV file. " + e.getMessage());
        }
    }

    public void readNormalPatient() {
        Service pat = Service.getInstance();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/in/normalPatient.csv"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                pat.addPatient(new NormalPatient(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], data[5]));
            }
        } catch (IOException e) {
            System.out.println("Failed to read normal patients from CSV file. " + e.getMessage());
        }
    }

    public void readSpecialPatient() {
        Service pat = Service.getInstance();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/in/specialPatient.csv"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                pat.addPatient(new SpecialPatient(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4],
                        data[5], Integer.parseInt(data[6]), new Vector<String>(Arrays.asList(data[7].split(";")))));
            }
        } catch (IOException e) {
            System.out.println("Failed to read special patients from CSV file. " + e.getMessage());
        }
    }

    public void readAppointment() {
        Service ap = Service.getInstance();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/in/appointment.csv"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int day = Integer.parseInt(data[2].substring(0,2));
                int month = Integer.parseInt(data[2].substring(3, 5));
                int year = Integer.parseInt(data[2].substring(6, 10));
                int hour = Integer.parseInt(data[2].substring(12, 14));
                int minute = Integer.parseInt(data[2].substring(15, 17));
                LocalDateTime time = LocalDateTime.of(year, month, day, hour, minute);
                ap.addAppointment(new Appointment(ap.searchMedicalStaff(Integer.parseInt(data[0])), ap.searchPatient(Integer.parseInt(data[1])), time));
            }
        } catch (IOException e) {
            System.out.println("Failed to read appointments from CSV file. " + e.getMessage());
        }
    }

}
