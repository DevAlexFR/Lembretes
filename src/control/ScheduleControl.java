package control;

import entity.SQLiteConnection;
import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Note;
import util.DateUtil;

public class ScheduleControl {
    SQLiteConnection sqliteConnection = new SQLiteConnection("C:/AFDeveloper/DBnote.db");
    Connection connection = sqliteConnection.getConnection();
    
    private final NoteControl noteCtr;
    private final DateUtil dateUtil;

    public ScheduleControl(Connection connection) {
        this.noteCtr = new NoteControl();
        this.dateUtil = new DateUtil();
    }

    private List<Note> itsTime() {
        List<Note> alarmNotes = new ArrayList<>();

        List<Note> allNotes = noteCtr.getNotes();

        Timestamp currentDateTime = new Timestamp(new Date().getTime());

        for (Note note : allNotes) {
            if (note.getAlarm() == 1) {
                Timestamp noteDateTime = note.getDateTimeAlarm();
                long timeDifference = currentDateTime.getTime() - noteDateTime.getTime();
                long sixtySecondsInMillis = 60 * 1000; // 60 segundos em milissegundos

                if (timeDifference >= 0 && timeDifference <= sixtySecondsInMillis) {
                    alarmNotes.add(note);
                }
            }
        }

        return alarmNotes;
    }

    private void displayNotes(List<Note> alarms, TrayIcon trayIcon) {
        for (Note alarm : alarms) {
            trayIcon.displayMessage(alarm.getName(), alarm.getDescription(), TrayIcon.MessageType.INFO);
        }
    }

    public void initAlarms(TrayIcon trayIcon) {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(60000);
                        List<Note> notes = itsTime();
                        if (!notes.isEmpty()) {
                            displayNotes(notes, trayIcon);
                        }
                    } catch (InterruptedException ex) {
                        System.out.println("Erro no monitoramento de alarmes: " + ex.getMessage());
                    }
                }
            }
        }.start();
    }
}
