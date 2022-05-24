package dbRepository;

import config.DbConnection;
import staff.Cardiologist;
import staff.HeadDoctor;
import staff.Nurse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedRepository {

    private Connection connection;
    public MedRepository() throws SQLException {
        this.connection = DbConnection.getInstance();
    }


    public HeadDoctor saveHeadDoctor(HeadDoctor doc) {
        String query = "INSERT into head_doctor" +
                "(name, gender, phone_number, age," +
                " starting_salary, bonus) " +
                "VALUES(?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, doc.getName());
            preparedStatement.setString(2, doc.getGender());
            preparedStatement.setString(3, doc.getPhoneNumber());
            preparedStatement.setInt(4, doc.getAge());
            preparedStatement.setDouble(5, doc.getStartingSalary());
            preparedStatement.setInt(6, doc.getBonus());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                doc.setMedicId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return doc;
    }

    public Nurse saveNurse(Nurse nurse) {
        String query = "INSERT into nurse" +
                "(name, gender, phone_number, age," +
                " starting_salary, hours) " +
                "VALUES(?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nurse.getName());
            preparedStatement.setString(2, nurse.getGender());
            preparedStatement.setString(3, nurse.getPhoneNumber());
            preparedStatement.setInt(4, nurse.getAge());
            preparedStatement.setDouble(5, nurse.getStartingSalary());
            preparedStatement.setInt(6, nurse.getHours());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                nurse.setMedicId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nurse;
    }

    public Cardiologist saveCardiologist(Cardiologist card) {
        String query = "INSERT into cardiologist" +
                "(name, gender, phone_number, age," +
                " starting_salary, experience_level) " +
                "VALUES(?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, card.getName());
            preparedStatement.setString(2, card.getGender());
            preparedStatement.setString(3, card.getPhoneNumber());
            preparedStatement.setInt(4, card.getAge());
            preparedStatement.setDouble(5, card.getStartingSalary());
            preparedStatement.setInt(6, card.getExperienceLevel());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                card.setMedicId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return card;
    }

    public HeadDoctor selectHeadDoctor(int id) {
        HeadDoctor doc = null;
        String query = "SELECT * FROM head_doctor WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                doc = new HeadDoctor(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7));
                doc.setMedicId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return doc;
    }


    public Nurse selectNurse(int id) {
        Nurse nurse = null;
        String query = "SELECT * FROM nurse WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                nurse = new Nurse(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7));
                nurse.setMedicId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nurse;
    }

    public Cardiologist selectCardiologist(int id) {
        Cardiologist card = null;
        String query = "SELECT * FROM cardiologist WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                card = new Cardiologist(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7));
                card.setMedicId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return card;
    }

    public List<HeadDoctor> selectAllHeadDoctors() {
        List<HeadDoctor> doc = new ArrayList<>();
        String query = "SELECT * FROM head_doctor";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                HeadDoctor doctor = new HeadDoctor(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7));
                doctor.setMedicId(resultSet.getInt(1));
                System.out.println(doctor);
                doc.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return doc;
    }

    public List<Nurse> selectAllNurses() {
        List<Nurse> nurses = new ArrayList<>();
        String query = "SELECT * FROM nurse";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Nurse nurse = new Nurse(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7));
                nurse.setMedicId(resultSet.getInt(1));
                System.out.println(nurse);
                nurses.add(nurse);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nurses;
    }

    public List<Cardiologist> selectAllCardiologists() {
        List<Cardiologist> cardiologists = new ArrayList<>();
        String query = "SELECT * FROM cardiologist";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Cardiologist cardiologist = new Cardiologist(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7));
                cardiologist.setMedicId(resultSet.getInt(1));
                System.out.println(cardiologist);
                cardiologists.add(cardiologist);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cardiologists;
    }


    public boolean uppdateHeadDoctor(int id, String phoneNumber) {
        String query = "UPDATE head_doctor SET phone_number = ? \n" +
                "WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean uppdateNurse(int id, String phoneNumber) {
        String query = "UPDATE nurse SET phone_number = ? \n" +
                "WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean uppdateCardiologist(int id, String phoneNumber) {
        String query = "UPDATE cardiologist SET phone_number = ? \n" +
                "WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean deleteHeadDoctor(int id) {
        String query = "DELETE FROM head_doctor WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean deleteNurse(int id) {
        String query = "DELETE FROM nurse WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public boolean deleteCardiologist(int id) {
        String query = "DELETE FROM cardiologist WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
