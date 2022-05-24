package service;

import appointment.Appointment;
import dbRepository.MedRepository;
import dbRepository.PatientRepository;
import patient.NormalPatient;
import patient.Patient;
import patient.SpecialPatient;
import staff.Cardiologist;
import staff.HeadDoctor;
import staff.MedicalStaff;
import staff.Nurse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class Service {
    public static Service serviceInstance = null;
    private ArrayList<MedicalStaff> staff = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    static MedRepository medRepository;

    static {
        try {
            medRepository = new MedRepository();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static PatientRepository patRepository;

    static {
        try {
            patRepository = new PatientRepository();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Service() {}

    public static Service getInstance() {
        if(serviceInstance == null) {
            serviceInstance = new Service();
        }
        return serviceInstance;
    }



    public void addMedicalStaff() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Medical Staff data");
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Gender:");
        String gender = scanner.next();
        System.out.println("Phone number:");
        String phone = scanner.next();
        System.out.println("Age:");
        int age = scanner.nextInt();
        System.out.println("Starting salary:");
        double startingSalary = scanner.nextDouble();
        System.out.println("Is he/she a Nurse (1), a Head Doctor (2) or a Cardiologist (3)?");
        int type = scanner.nextInt();
        switch (type) {
            case 1 -> {
                System.out.println("How many hours does he/she work in a week?");
                int nrHours = scanner.nextInt();
                MedicalStaff nurse = new Nurse(name, gender, phone, age, startingSalary, nrHours);
                staff.add(nurse);
            }
            case 2 -> {
                System.out.println("How high is his/her bonus?");
                int bonus = scanner.nextInt();
                MedicalStaff headDoc = new HeadDoctor(name, gender, phone, age, startingSalary, bonus);
                staff.add(headDoc);
            }
            case 3 -> {
                System.out.println("What is their experience level?");
                int exp = scanner.nextInt();
                MedicalStaff cardiologist = new Cardiologist(name, gender, phone, age, startingSalary, exp);
                staff.add(cardiologist);
            }
            default -> System.out.println("Invalid type of medical staff. Try again!");
        }
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Hire medical staff");
    }

    public void addMedicalStaff(MedicalStaff m) {
        staff.add(m);
    }

    public void addHeadDoctorDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Medical Staff data");
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Gender:");
        String gender = scanner.next();
        System.out.println("Phone number:");
        String phone = scanner.next();
        System.out.println("Age:");
        int age = scanner.nextInt();
        System.out.println("Starting salary:");
        double startingSalary = scanner.nextDouble();
        System.out.println("How high is their bonus?");
        int bonus = scanner.nextInt();
        HeadDoctor headDoc = new HeadDoctor(name, gender, phone, age, startingSalary, bonus);
        medRepository.saveHeadDoctor(headDoc);
        staff.add(headDoc);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Add Head Doctor to DB");
    }

    public void addNurseDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Medical Staff data");
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Gender:");
        String gender = scanner.next();
        System.out.println("Phone number:");
        String phone = scanner.next();
        System.out.println("Age:");
        int age = scanner.nextInt();
        System.out.println("Starting salary:");
        double startingSalary = scanner.nextDouble();
        System.out.println("How many hours does he/she work in a week?");
        int hours = scanner.nextInt();
        Nurse nurse = new Nurse(name, gender, phone, age, startingSalary, hours);
        medRepository.saveNurse(nurse);
        staff.add(nurse);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Add Nurse to DB");
    }

    public void addCardiologistDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Medical Staff data");
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Gender:");
        String gender = scanner.next();
        System.out.println("Phone number:");
        String phone = scanner.next();
        System.out.println("Age:");
        int age = scanner.nextInt();
        System.out.println("Starting salary:");
        double startingSalary = scanner.nextDouble();
        System.out.println("What is their experience level?");
        int exp = scanner.nextInt();
        Cardiologist cardiologist = new Cardiologist(name, gender, phone, age, startingSalary, exp);
        medRepository.saveCardiologist(cardiologist);
        staff.add(cardiologist);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Add Cardiologist to DB");
    }


    public void showStaff() {
        System.out.println("Current Medical Staff:");
        for(var i : staff)
            i.displayMedicalStaff();

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display the medical staff");
    }

    public MedicalStaff searchMedicalStaff(int id) {
        MedicalStaff found = null;
        for (MedicalStaff i : staff) {
            if(i.getMedicId() == id) {
                found = i;
                break;
            }
        }
        return found;
    }

    public void fireMedicalStaff() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID for the medical staff you want to fire:");
        int fire = scanner.nextInt();
        MedicalStaff medStaff = searchMedicalStaff(fire);
        if(medStaff == null)
            return;
        staff.remove(medStaff);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Fire medical staff");

    }

    public ArrayList<Nurse> getNurses() {
        ArrayList<Nurse> nurses = new ArrayList<>();
        for(var i : staff) {
            if(i instanceof Nurse) {
                nurses.add((Nurse)i);
            }
        }
        return nurses;
    }

    public ArrayList<Cardiologist> getCardiologists() {
        ArrayList<Cardiologist> cardiologists = new ArrayList<>();
        for(var i : staff) {
            if(i instanceof Cardiologist) {
                cardiologists.add((Cardiologist) i);
            }
        }
        return cardiologists;
    }

    public ArrayList<HeadDoctor> getHeadDoctors() {
        ArrayList<HeadDoctor> headDoctors = new ArrayList<>();
        for(var i : staff) {
            if(i instanceof HeadDoctor) {
                headDoctors.add((HeadDoctor) i);
            }
        }
        return headDoctors;
    }

    public List<HeadDoctor> selectHeadDoctorsDb() {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Head Doctors from DB");
        return medRepository.selectAllHeadDoctors();
    }

    public List<Nurse> selectNursesDb() {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Nurses from DB");
        return medRepository.selectAllNurses();
    }

    public List<Cardiologist> selectCardiologistsDb() {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Cardiologists from DB");
        return medRepository.selectAllCardiologists();
    }

    public HeadDoctor selectHeadDoctorDb(int id) {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Head Doctor from DB");
        return medRepository.selectHeadDoctor(id);
    }

    public Nurse selectNurseDb(int id) {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Nurse from DB");
        return medRepository.selectNurse(id);
    }

    public Cardiologist selectCardiologistDb(int id) {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Cardiologist from DB");
        return medRepository.selectCardiologist(id);
    }

    public void changeHeadDoctorPhoneNumber() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID of the Head Doctor whose phone number you want to change:");
        int id = scanner.nextInt();
        System.out.println("TYpe the new phone number");
        String newNumber = scanner.next();
        if(medRepository.uppdateHeadDoctor(id, newNumber))
            System.out.println("Phone number changed!");
        else
            System.out.println("Couldn't change the number");

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Update Head Doctor from DB");
    }

    public void changeNursePhoneNumber() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID of the Nurse whose phone number you want to change:");
        int id = scanner.nextInt();
        System.out.println("TYpe the new phone number");
        String newNumber = scanner.next();
        if(medRepository.uppdateNurse(id, newNumber))
            System.out.println("Phone number changed!");
        else
            System.out.println("Couldn't change the number");

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Update Nurse from DB");
    }

    public void changeCardiologistPhoneNumber() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID of the Cardiologist whose phone number you want to change:");
        int id = scanner.nextInt();
        System.out.println("TYpe the new phone number");
        String newNumber = scanner.next();
        if(medRepository.uppdateCardiologist(id, newNumber))
            System.out.println("Phone number changed!");
        else
            System.out.println("Couldn't change the number");

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Update Cardiologist from DB");
    }


    public void deleteHeadDoctorDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID for the Head Doctor you want to remove:");
        int id = scanner.nextInt();
        HeadDoctor doc = medRepository.selectHeadDoctor(id);
        if(doc == null) {
            System.out.println("Invalid ID");
            return;
        }
        medRepository.deleteHeadDoctor(id);
        staff.remove(doc);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Delete Head Doctor from DB");
    }

    public void deleteNurseDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID for the Nurse you want to remove:");
        int id = scanner.nextInt();
        Nurse nurse = medRepository.selectNurse(id);
        if(nurse == null) {
            System.out.println("Invalid ID");
            return;
        }
        medRepository.deleteNurse(id);
        staff.remove(nurse);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Delete Nurse from DB");
    }

    public void deleteCardiologistDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID for the Cardiologist you want to remove:");
        int id = scanner.nextInt();
        Cardiologist card = medRepository.selectCardiologist(id);
        if(card == null) {
            System.out.println("Invalid ID");
            return;
        }
        medRepository.deleteCardiologist(id);
        staff.remove(card);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Delete Cardiologist from DB");
    }



    public void addPatient() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Patients data");
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Gender:");
        String gender = scanner.next();
        System.out.println("Phone number:");
        String phone = scanner.next();
        System.out.println("Age:");
        int age = scanner.nextInt();
        System.out.println("CNP:");
        String cnp = scanner.next();
        System.out.println("What type of patient is? Type 1 fot Normal patient or 2 for Special Patient");
        int type = scanner.nextInt();
        switch (type) {
            case 1 -> {
                System.out.println("Insurance Number:");
                String insurance = scanner.next();
                Patient normalPatient = new NormalPatient(name, gender, phone, age, cnp, insurance);
                patients.add(normalPatient);
            }
            case 2 -> {
                System.out.println("Diagnosis:");
                String diagnosis = scanner.next();
                System.out.println("How many medication do they take? If none, type 0.");
                int medsnr = scanner.nextInt();
                Vector<String> medications;
                if (medsnr != 0) {
                    System.out.println("Type the names of the medications one by one:");
                    medications = new Vector<String>();
                    for (int i = 0; i < medsnr; i++) {
                        medications.add(scanner.next());
                    }
                } else {
                    medications = new Vector<String>();
                }
                Patient specialPatient = new SpecialPatient(name, gender, phone, age, cnp, diagnosis, medsnr, medications);
                patients.add(specialPatient);
            }
            default -> System.out.println("Invalid type of patient. Try again!");
        }

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Register a patient");
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public void addNormalPatientDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Patients data");
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Gender:");
        String gender = scanner.next();
        System.out.println("Phone number:");
        String phone = scanner.next();
        System.out.println("Age:");
        int age = scanner.nextInt();
        System.out.println("CNP:");
        String cnp = scanner.next();
        System.out.println("Insurance Number:");
        String insurance = scanner.next();
        NormalPatient normalPatient = new NormalPatient(name, gender, phone, age, cnp, insurance);
        patients.add(normalPatient);
        patRepository.saveNormalPatient(normalPatient);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Add normal patient to DB");
    }

    public void addSpecialPatientDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Patients data");
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Gender:");
        String gender = scanner.next();
        System.out.println("Phone number:");
        String phone = scanner.next();
        System.out.println("Age:");
        int age = scanner.nextInt();
        System.out.println("CNP:");
        String cnp = scanner.next();
        System.out.println("Diagnosis:");
        String diagnosis = scanner.next();
        System.out.println("How many medication do they take? If none, type 0.");
        int medsnr = scanner.nextInt();
        Vector<String> medications;
        if (medsnr != 0) {
            System.out.println("Type the names of the medications one by one:");
            medications = new Vector<String>();
            for (int i = 0; i < medsnr; i++) {
                medications.add(scanner.next());
            }
        } else {
            medications = new Vector<String>();
        }
        SpecialPatient specialPatient = new SpecialPatient(name, gender, phone, age, cnp, diagnosis, medsnr, medications);
        patients.add(specialPatient);
        patRepository.saveSpecialPatient(specialPatient);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Add special patient to DB");
    }

    public List<NormalPatient> selectNormalPatientsDb() {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Normal Patients from DB");
        return patRepository.selectAllNormalPatients();
    }

    public List<SpecialPatient> selectSpecialPatientsDb() {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Special Patients from DB");
        return patRepository.selectAllSpecialPatients();
    }

    public NormalPatient selectNormalPatientDb(int id) {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Normal Patient from DB");
        return patRepository.selectNormalPatient(id);
    }

    public SpecialPatient selectSpecialPatientDb(int id) {
        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display Special Patient from DB");
        return patRepository.selectSpecialPatient(id);
    }

    public void changeNormalPatientPhoneNumber() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID of the Normal Patient whose phone number you want to change:");
        int id = scanner.nextInt();
        System.out.println("Type the new number");
        String number = scanner.next();
        if(patRepository.updateNormalPatient(id, number))
            System.out.println("Number changed!");
        else
            System.out.println("Invalid ID");

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Update Normal Patient from DB");
    }

    public void changeSpecialPatientPhoneNumber() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID of the Special Patient whose phone number you want to change:");
        int id = scanner.nextInt();
        System.out.println("Type the new number");
        String number = scanner.next();
        if (patRepository.updateSpecialPatient(id, number))
            System.out.println("Number changed!");
        else
            System.out.println("Invalid ID");

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Update Special Patient from DB");
    }

    public void deleteNormalPatientDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID for the Normal Patient you want to remove:");
        int id = scanner.nextInt();
        NormalPatient pat = patRepository.selectNormalPatient(id);
        if(pat == null) {
            System.out.println("Invalid ID");
            return;
        }
        patRepository.deleteNormalPatient(id);
        patients.remove(pat);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Delete Normal Patient from DB");
    }

    public void deleteSpecialPatientDb() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID for the Special Patient you want to remove:");
        int id = scanner.nextInt();
        SpecialPatient pat = patRepository.selectSpecialPatient(id);
        if(pat == null) {
            System.out.println("Invalid ID");
            return;
        }
        patRepository.deleteSpecialPatient(id);
        patients.remove(pat);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Delete Special Patient from DB");
    }


        public Patient searchPatient(int id) {
        Patient found = null;
        for(Patient i : patients) {
            if(i.getPatientId() == id) {
                found = i;
                break;
            }
        }
        return found;
    }

    public void showPatients() {
        System.out.println("Current Patients:");
        for(var i : patients)
            System.out.println(i);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display patients");
    }

    public void removePatient() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID for the patient you want to remove:");
        int remove = scanner.nextInt();
        Patient p = searchPatient(remove);
        if(p == null)
            return;
        patients.remove(p);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Delete a patient");
    }

    public ArrayList<NormalPatient> getNormalPatients() {
        ArrayList<NormalPatient> normalPatients = new ArrayList<>();
        for(var i : patients) {
            if(i instanceof NormalPatient) {
                normalPatients.add((NormalPatient) i);
            }
        }
        return normalPatients;
    }

    public ArrayList<SpecialPatient> getSpecialPatients() {
        ArrayList<SpecialPatient> specialPatients = new ArrayList<>();
        for(var i : patients) {
            if(i instanceof SpecialPatient) {
                specialPatients.add((SpecialPatient) i);
            }
        }
        return specialPatients;
    }

    public void createAppointment() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Appointment data:");
        System.out.println("Patient's ID:");
        int id = scanner.nextInt();
        Patient patient = searchPatient(id);
        MedicalStaff med;
        System.out.println("Does the patient needs a routine consultation (1), cardiologic consult (2) or a complex consultation (3)?");
        int type = scanner.nextInt();
        switch(type) {
            case 1:
                System.out.println("Type the ID of the Nurse you would like.");
                showStaff();
                int nurseID = scanner.nextInt();
                while(!(searchMedicalStaff(nurseID) instanceof Nurse)) {
                    System.out.println("Invalid ID!");
                    nurseID = scanner.nextInt();
                }
                med = searchMedicalStaff(nurseID);
                break;
            case 2:
                System.out.println("Type the ID of the Cardiologist you would like.");
                showStaff();
                int cardiologistID = scanner.nextInt();
                while(!(searchMedicalStaff(cardiologistID) instanceof Cardiologist)) {
                    System.out.println("Invalid ID!");
                    cardiologistID = scanner.nextInt();
                }
                med = searchMedicalStaff(cardiologistID);
                break;
            case 3:
                System.out.println("Type the ID of the Head Doctor you would like.");
                showStaff();
                int headDoctorID = scanner.nextInt();
                while(!(searchMedicalStaff(headDoctorID) instanceof HeadDoctor)) {
                    System.out.println("Invalid ID!");
                    headDoctorID = scanner.nextInt();
                }
                med = searchMedicalStaff(headDoctorID);
                break;
            default:
                med = null;
                System.out.println("Invalid type of consultation. Try again!");
        }
        System.out.println("Date (dd-mm-yyyy--hh-mm):");
        String date = scanner.next();
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        int hour = Integer.parseInt(date.substring(12, 14));
        int minute = Integer.parseInt(date.substring(15, 17));
        LocalDateTime time = LocalDateTime.of(year, month, day, hour, minute);
        Appointment appointment = new Appointment(med, patient, time);
        appointments.add(appointment);
        System.out.println("Appointment created!");

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Create an appointment");

    }
    public void addAppointment(Appointment app) {
        appointments.add(app);
    }

    public void showAppointments() {
        System.out.println("Current appointments:");
        for(var i : appointments)
            System.out.println(i);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Display appointments");
    }

    public Appointment findAppointment(int id) {
        Appointment found = null;
        for(Appointment i : appointments) {
            if(i.getAppointmentId() == id) {
                found = i;
                break;
            }
        }
        return found;
    }

    public void cancelAppointment() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Type the ID for the appointment you want to cancel:");
        int delete = scanner.nextInt();
        Appointment app = findAppointment(delete);
        if(app == null) {
            return;
        }
        appointments.remove(app);

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Cancel an appointment");
    }

    public ArrayList<Appointment> getAppointments() {
        ArrayList<Appointment> app = new ArrayList<>();
        for(var i : appointments) {
            app.add(i);
            }
        return app;
    }

    public void sortStaffBySalary() {
        Collections.sort(staff, (o1, o2) -> (int) (o1.calculateSalary() - o2.calculateSalary()));

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Sort medical staff by salary");
    }

    public void sortPatientsByName() {
        Collections.sort(patients, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        WriteCSV audit = WriteCSV.getInstance();
        audit.writeAudit("Sort patients by name");
    }
}
