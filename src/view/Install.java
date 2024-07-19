package view;

import view.SplashScreen;
import view.Main;
import entity.SQLiteConnection;

public class Install {
    
    public static void main(String[] args) {
        SQLiteConnection.main(args);
        SplashScreen.load(args);
        Main.main(args);
    }
    
}
