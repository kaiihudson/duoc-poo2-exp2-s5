package cl.speedfast.ui;

import javax.swing.*;

public class FormularioRepartidor extends JPanel {
    private final JTextField nombreRepartidor = new JTextField(40);

    protected FormularioRepartidor(){
        add(new JLabel("Nombre de Repartidor:"));
        add(nombreRepartidor);
    }
    public String getNombreRepartidor(){
        return nombreRepartidor.getText();
    }

}
