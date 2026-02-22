package cl.speedfast.app;


import cl.speedfast.ui.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            MainWindow mw = new MainWindow();
        });
    }
}
