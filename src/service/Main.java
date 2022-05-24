package service;


import dbRepository.CreateTable;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void menu(){
        System.out.println("-----------------MEDICAL OFFICE-----------------");
        System.out.println("0. Exit");
        System.out.println("1. Hire medical staff");
        System.out.println("2. Fire medical staff");
        System.out.println("3. Display the medical staff");
        System.out.println("4. Register a patient");
        System.out.println("5. Delete a patient");
        System.out.println("6. Display patients");
        System.out.println("7. Create an appointment");
        System.out.println("8. Cancel an appointment");
        System.out.println("9. Display appointments");
        System.out.println("10. Sort medical staff by salary");
        System.out.println("11. Sort patients by name");

        System.out.println("12. Hire a Head Doctor (add to DB)");
        System.out.println("13. Hire a Nurse (add to DB)");
        System.out.println("14. Hire a Cardiologist (add to DB)");
        System.out.println("15. Fire a Head Doctor (from DB)");
        System.out.println("16. Fire a Nurse (from DB)");
        System.out.println("17. Fire a Cardiologist (from DB)");
        System.out.println("18. Display the Head Doctors (from DB)");
        System.out.println("19. Display the Nurses (from DB)");
        System.out.println("20. Display the Cardiologists (from DB)");
        System.out.println("21. Register a Normal Patient (add to DB)");
        System.out.println("22. Register a Special Patient (add to DB)");
        System.out.println("23. Delete a Normal Patient (from DB)");
        System.out.println("24. Delete a Special Patient (from DB)");
        System.out.println("25. Display Normal Patients (from DB)");
        System.out.println("26. Display Special Patients (from DB)");
        System.out.println("27. Update the Head Doctor's phone number (from DB)");
        System.out.println("28. Update the Nurse phone's number (from DB)");
        System.out.println("29. Update the Cardiologist's phone number (from DB)");
        System.out.println("30. Update the Normal Patient's phone number (from DB)");
        System.out.println("31. Update the Special Patient's phone number (from DB)");
    }

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        Service service = Service.getInstance();

        CreateTable createTables = new CreateTable();
        createTables.create();

        ReadCSV read = ReadCSV.getInstance();
        read.readNurse();
        read.readCardiologist();
        read.readHeadDoctor();
        read.readNormalPatient();
        read.readSpecialPatient();
        read.readAppointment();

        WriteCSV write = WriteCSV.getInstance();

        menu();
        System.out.println("Command:");
        int option = scanner.nextInt();
        while(option != 0) {
            switch(option) {
                case 1:
                    service.addMedicalStaff();
                    break;
                case 2:
                    service.fireMedicalStaff();
                    break;
                case 3:
                    service.showStaff();
                    break;
                case 4:
                    service.addPatient();
                    break;
                case 5:
                    service.removePatient();
                    break;
                case 6:
                    service.showPatients();
                    break;
                case 7:
                    service.createAppointment();
                    break;
                case 8:
                    service.cancelAppointment();
                    break;
                case 9:
                    service.showAppointments();
                    break;
                case 10:
                    service.sortStaffBySalary();
                    service.showStaff();
                    break;
                case 11:
                    service.sortPatientsByName();
                    service.showPatients();
                    break;
                case 12:
                    service.addHeadDoctorDb();
                    break;
                case 13:
                    service.addNurseDb();
                    break;
                case 14:
                    service.addCardiologistDb();
                    break;
                case 15:
                    service.deleteHeadDoctorDb();
                    break;
                case 16:
                    service.deleteNurseDb();
                    break;
                case 17:
                    service.deleteCardiologistDb();
                    break;
                case 18:
                    System.out.println(service.selectHeadDoctorsDb());
                    break;
                case 19:
                    System.out.println(service.selectNursesDb());
                    break;
                case 20:
                    System.out.println(service.selectCardiologistsDb());
                    break;
                case 21:
                    service.addNormalPatientDb();
                    break;
                case 22:
                    service.addSpecialPatientDb();
                    break;
                case 23:
                    service.deleteNormalPatientDb();
                    break;
                case 24:
                    service.deleteSpecialPatientDb();
                    break;
                case 25:
                    System.out.println(service.selectNormalPatientsDb());
                    break;
                case 26:
                    System.out.println(service.selectSpecialPatientsDb());
                    break;
                case 27:
                    service.changeHeadDoctorPhoneNumber();
                    break;
                case 28:
                    service.changeNursePhoneNumber();
                    break;
                case 29:
                    service.changeCardiologistPhoneNumber();
                    break;
                case 30:
                    service.changeNormalPatientPhoneNumber();
                    break;
                case 31:
                    service.changeSpecialPatientPhoneNumber();
                    break;
            }
            System.out.println("Command:");
            option = scanner.nextInt();
        }

        write.writeNurse();
        write.writeCardiologist();
        write.writeHeadDoctor();
        write.writeNormalPatient();
        write.writeSpecialPatient();
        write.writeAppointment();
    }

}
