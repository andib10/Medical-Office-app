package dbRepository;

import config.DbConnection;
import patient.NormalPatient;
import patient.SpecialPatient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PatientRepository {

    private Connection connection;
    public PatientRepository() throws SQLException {
        this.connection = DbConnection.getInstance();
    }

    public NormalPatient saveNormalPatient(NormalPatient patient) {
        String query = "INSERT into normal_patient" +
                "(name, gender, phone_number, age," +
                " CNP, insurance_id) " +
                "VALUES(?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getGender());
            preparedStatement.setString(3, patient.getPhoneNumber());
            preparedStatement.setInt(4, patient.getAge());
            preparedStatement.setString(5, patient.getCNP());
            preparedStatement.setString(6, patient.getInsuranceId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                patient.setPatientId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return patient;
    }

    public SpecialPatient saveSpecialPatient(SpecialPatient patient) {
        String query = "INSERT into special_patient" +
                "(name, gender, phone_number, age," +
                " CNP, diagnosis) " +
                "VALUES(?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getGender());
            preparedStatement.setString(3, patient.getPhoneNumber());
            preparedStatement.setInt(4, patient.getAge());
            preparedStatement.setString(5, patient.getCNP());
            preparedStatement.setString(6, patient.getDiagnosis());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                patient.setPatientId(resultSet.getInt(1));
            }

            String query1 = "INSERT into medication" +
                    "(id_patient, medication_name) " +
                    "VALUES(?,?)";

            PreparedStatement preparedStatement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            for(String meds : patient.getMedication()) {
                preparedStatement1.setInt(1, patient.getPatientId());
                preparedStatement1.setString(2, meds);
                preparedStatement1.execute();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return patient;
    }

    public NormalPatient selectNormalPatient(int id) {
        NormalPatient pat = null;
        String query = "SELECT * FROM normal_patient WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                pat = new NormalPatient(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
                pat.setPatientId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pat;
    }

    public SpecialPatient selectSpecialPatient(int id) {
        SpecialPatient pat = null;
        String query = "SELECT * FROM special_patient WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            String query1 = "SELECT * FROM medication WHERE id_patient=?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            Vector<String> meds = new Vector<>();
            while(resultSet1.next()) {
                meds.add(resultSet1.getString(2));
            }

            if(resultSet.next()) {
                pat = new SpecialPatient(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7), meds.size(), meds);
                pat.setPatientId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pat;
    }

    public List<NormalPatient> selectAllNormalPatients() {
        List<NormalPatient> pat = new ArrayList<>();
        String query = "SELECT * FROM normal_patient";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                NormalPatient patient = new NormalPatient(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
                patient.setPatientId(resultSet.getInt(1));
                System.out.println(patient);
                pat.add(patient);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pat;
    }

    public List<SpecialPatient> selectAllSpecialPatients() {
        List<SpecialPatient> pat = new ArrayList<>();
        String query = "SELECT * FROM special_patient";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                String query1 = "SELECT * FROM medication WHERE id_patient=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, resultSet.getInt(1));
                ResultSet resultSet2 = preparedStatement.executeQuery();

                Vector<String> meds = new Vector<>();
                while(resultSet2.next()) {
                    meds.add(resultSet2.getString(2));
                }

                SpecialPatient patient = new SpecialPatient(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7), meds.size(), meds);
                patient.setPatientId(resultSet.getInt(1));
                System.out.println(patient);
                pat.add(patient);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pat;
    }

    public boolean updateNormalPatient(int id, String phoneNumber) {
        String query = "UPDATE normal_patient SET phone_number = ? \n" +
                "WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean updateSpecialPatient(int id, String phoneNumber) {
        String query = "UPDATE special_patient SET phone_number = ? \n" +
                "WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean deleteNormalPatient(int id) {
        String query = "DELETE FROM normal_patient WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean deleteSpecialPatient(int id) {
        String query = "DELETE FROM special_patient WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
