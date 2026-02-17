package cl.speedfast.ui;

import cl.speedfast.gestor.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JButton button1 = new JButton("Registrar Pedido");
    private final JButton button2 = new JButton("Listar Pedidos");
    private final JButton button3 = new JButton("Asignar Repartidor");
    private final JButton button4 = new JButton("Iniciar Entregas");
    private final JButton button5 = new JButton("Cancelar");

    private final ZonaDeCarga zc = new ZonaDeCarga();

    public MainWindow() {
        JFrame frame = new JFrame();
        frame.setTitle("SpeedFast");
        frame.setBounds(100, 100, 700, 300);
        frame.setSize(700, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridLayout());
        frame.setResizable(false);

        JPanel panel = addComponents();
        JPanel extraActions = addExit();
        addActions();
        frame.add(panel);
        frame.add(extraActions);
    }

    private JPanel addComponents() {
        JPanel panel = new JPanel();

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        return panel;
    }

    private JPanel addExit(){
        JPanel panel = new JPanel();

        panel.add(button5);
        return panel;
    }

    private void addActions() {
        button1.addActionListener(e -> {changeWindow(new VentanaRegistroPedido());});
        button2.addActionListener(e -> {System.out.println("b");});
        button3.addActionListener(e -> {System.out.println("c");});
        button4.addActionListener(e -> {System.out.println("d");});
        button5.addActionListener(e -> {System.exit(0);});
    }

    private void changeWindow(JFrame frame) {
        setVisible(false);
        if(frame.getComponentCount()>0){
            System.out.println("a");
            frame.setVisible(true);
        }
    }
}
