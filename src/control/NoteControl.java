package control;

import entity.SQLiteConnection;
import java.sql.Timestamp;
import model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteControl {
    private Connection connection;

    public NoteControl() {
        SQLiteConnection sqliteConnection = new SQLiteConnection("C:/AFDeveloper/DBnote.db");
        connection = sqliteConnection.getConnection();
    }

    public List<Note> getNotes() {
        List<Note> listNotes = new ArrayList<>();
        String query = "SELECT * FROM Note ORDER BY id DESC";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int alarm = resultSet.getInt("alarm");
                Timestamp dateTimeAlarm = resultSet.getTimestamp("date_time_alarm");

                Note note = new Note();
                note.setId(id);
                note.setName(name);
                note.setDescription(description);
                note.setAlarm(alarm);
                note.setDateTimeAlarm(dateTimeAlarm);

                listNotes.add(note);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar notas: " + e.getMessage());
        }

        return listNotes;
    }


    public void save(Note note) {
        String query = "INSERT INTO Note (name, description, alarm, date_time_alarm) VALUES (?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, note.getName());
            preparedStatement.setString(2, note.getDescription());
            preparedStatement.setInt(3, note.getAlarm());
            preparedStatement.setTimestamp(4, note.getDateTimeAlarm());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar nota: " + e.getMessage());
        }
    }

    public void delete(Note note) {
        String query = "DELETE FROM Note WHERE id = ?";

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, note.getId());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar nota: " + e.getMessage());
        }
    }

    public void update(Note note) {
        String query = "UPDATE Note SET name = ?, description = ?, alarm = ?, date_time_alarm = ? WHERE id = ?";

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, note.getName());
            preparedStatement.setString(2, note.getDescription());
            preparedStatement.setInt(3, note.getAlarm());
            preparedStatement.setTimestamp(4, note.getDateTimeAlarm());
            preparedStatement.setLong(5, note.getId());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar nota: " + e.getMessage());
        }
    }
}
