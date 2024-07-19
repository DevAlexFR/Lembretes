package util;

import control.ScheduleControl;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.sql.Connection;

public class NoteTray {
  
    private TrayIcon trayIcon;
    private PopupMenu popup;
    private MenuItem openItem;
    private MenuItem exitItem;
    private Image image;
    private JFrame JFrame;
    private SystemTray tray;
    private ScheduleControl scheduleCtr;
    
    public NoteTray(JFrame JFrame, Connection connection) {
        this.popup = new PopupMenu();
        this.openItem = new MenuItem("Abrir");
        this.exitItem = new MenuItem("Sair");
        this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/tray2.png"));
        this.trayIcon = new TrayIcon(image, "AlarmNote", popup);
        this.trayIcon.setImageAutoSize(true);
        this.JFrame = JFrame;
        this.scheduleCtr = new ScheduleControl(connection); // Passa a conexão
    }
    
    public void createNoteTray() {
        
        try {
            this.tray = SystemTray.getSystemTray();
            this.popup.add(openItem);
            this.popup.add(exitItem);
            
            this.openItem.addActionListener(getActionMaximize());
            this.trayIcon.addActionListener(getActionMaximize());
            this.exitItem.addActionListener(getActionClose());
            
            this.tray.add(trayIcon);
            this.scheduleCtr.initAlarms(trayIcon);
        } catch (AWTException ex) {
            System.out.println("Erro ao carregar NoteTray");
        }
    }
    
    private ActionListener getActionMaximize() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame.setVisible(true);
            }
        };
    }
    
    private ActionListener getActionClose() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
    }
    
    public void minimize() {
        trayIcon.displayMessage("Atenção",  "Aplicação agora em segundo plano", TrayIcon.MessageType.INFO);
    }
}
