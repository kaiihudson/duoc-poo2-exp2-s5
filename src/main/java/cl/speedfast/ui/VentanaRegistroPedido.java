package cl.speedfast.ui;

import cl.speedfast.model.Pedido;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistroPedido extends JFrame {
    private Pedido objPedido = new Pedido();
    private final JTextField id = new JTextField(String.valueOf(objPedido.getId()));
    private JTextField direccion = new JTextField();
    private final String[] opcionesCombo = {"Comida", "Encomienda", "Express"};
    private final JComboBox<String> tipoPedido = new JComboBox<>(opcionesCombo);

    public VentanaRegistroPedido(){
        JFrame ventana = new JFrame();
        ventana.setTitle("Registro de pedido");
        ventana.setBounds(100, 100, 700, 300);
        ventana.setSize(700, 300);

        ventana.add(new JLabel("Tipo de pedido"));
        ventana.add(tipoPedido);
        ventana.setLocationRelativeTo(null);
        tipoPedido.setEditable(true);
        tipoPedido.addActionListener((ActionEvent e) -> {comboAction(e, ventana);});
    }

    public void comboAction(ActionEvent e, JFrame ventana){
        JComboBox<String> combo = (JComboBox<String>) e.getSource();
        String tipo = (String) combo.getSelectedItem();
        switch (tipo){
            case "Comida":
                ventana.add(new JLabel("Comida"));
                ventana.add(id);
                break;
            case "Encomienda":
                ventana.add(new JLabel("Encomienda"));
                ventana.add(id);
                break;
            case "Express":
                ventana.add(new JLabel("Express"));
                ventana.add(id);
                break;
            case null:
                break;
            default:
                break;
        }
    }

    public JTextField getDireccion() {
        return direccion;
    }

    public void setDireccion(JTextField direccion) {
        this.direccion = direccion;
    }
}
