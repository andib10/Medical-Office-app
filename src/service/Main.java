package service;


import java.util.Scanner;

public class Main {
    public static void menu(){
        System.out.println("-----------------MEDICAL CLINIC-----------------");
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
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        Service service = Service.getInstance();
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

            }
            System.out.println("Command:");
            option = scanner.nextInt();
        }

    }
}
