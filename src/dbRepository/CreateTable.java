package dbRepository;

import config.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    private Connection connection;
    public CreateTable() throws SQLException {
        this.connection = DbConnection.getInstance();
    }

    public void create() {
        createHeadDoctorTable();
        createNurseTable();
        createCardiologistTable();
        createNormalPatientTable();
        createSpecialPatientTable();
        createMedicationTable();
        createAppointmentHeadDoctorTable();
        createAppointmentNurseTable();
        createAppointmentCardiologistTable();
    }

    public void createHeadDoctorTable() {
        String query = "CREATE TABLE IF NOT EXISTS head_doctor (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    name VARCHAR(50),\n" +
                "    gender VARCHAR(20),\n" +
                "    phone_number VARCHAR(20),\n" +
                "    age INT,\n" +
                "    starting_salary DOUBLE,\n" +
                "    bonus INT\n"+
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNurseTable() {
        String query = "CREATE TABLE IF NOT EXISTS nurse (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    name VARCHAR(50),\n" +
                "    gender VARCHAR(20),\n" +
                "    phone_number VARCHAR(20),\n" +
                "    age INT,\n" +
                "    starting_salary DOUBLE,\n" +
                "    hours INT\n"+
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCardiologistTable() {
        String query = "CREATE TABLE IF NOT EXISTS cardiologist (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    name VARCHAR(50),\n" +
                "    gender VARCHAR(20),\n" +
                "    phone_number VARCHAR(20),\n" +
                "    age INT,\n" +
                "    starting_salary DOUBLE,\n" +
                "    experience_level INT\n"+
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNormalPatientTable() {
        String query = "CREATE TABLE IF NOT EXISTS normal_patient (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    name VARCHAR(50),\n" +
                "    gender VARCHAR(20),\n" +
                "    phone_number VARCHAR(20),\n" +
                "    age INT,\n" +
                "    CNP VARCHAR(20),\n" +
                "    insurance_id VARCHAR(20)\n"+
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSpecialPatientTable() {
        String query = "CREATE TABLE IF NOT EXISTS special_patient (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    name VARCHAR(50),\n" +
                "    gender VARCHAR(20),\n" +
                "    phone_number VARCHAR(20),\n" +
                "    age INT,\n" +
                "    CNP VARCHAR(20),\n" +
                "    diagnosis VARCHAR(50)\n"+
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createMedicationTable() {
        String query = "CREATE TABLE IF NOT EXISTS medication (\n" +
                "    id_patient INT,\n" +
                "    medication_name VARCHAR(50),\n" +
                "    PRIMARY KEY (id_patient, medication_name),\n"+
                "    FOREIGN KEY (id_patient) REFERENCES special_patient(id) ON DELETE CASCADE"+
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAppointmentHeadDoctorTable() {
        String query = "CREATE TABLE IF NOT EXISTS appointment_head_doctor (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    id_medic INT NOT NULL,\n" +
                "    id_patient INT NOT NULL,\n" +
                "    data DATE,\n" +
                "    FOREIGN KEY (id_medic) REFERENCES head_doctor(id) ON DELETE CASCADE,"+
                "    FOREIGN KEY (id_patient) REFERENCES special_patient(id) ON DELETE CASCADE"+
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAppointmentNurseTable() {
        String query = "CREATE TABLE IF NOT EXISTS appointment_nurse (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    id_medic INT NOT NULL,\n" +
                "    id_patient INT NOT NULL,\n" +
                "    data DATE,\n" +
                "    FOREIGN KEY (id_medic) REFERENCES nurse(id) ON DELETE CASCADE,"+
                "    FOREIGN KEY (id_patient) REFERENCES normal_patient(id) ON DELETE CASCADE"+
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAppointmentCardiologistTable() {
        String query = "CREATE TABLE IF NOT EXISTS appointment_cardiologist (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    id_medic INT NOT NULL,\n" +
                "    id_patient INT NOT NULL,\n" +
                "    data DATE,\n" +
                "    FOREIGN KEY (id_medic) REFERENCES cardiologist(id) ON DELETE CASCADE,"+
                "    FOREIGN KEY (id_patient) REFERENCES special_patient(id) ON DELETE CASCADE"+
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
