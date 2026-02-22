package cl.speedfast.ui;

import cl.speedfast.gestor.ZonaDeCarga;
import cl.speedfast.model.Pedido;
import cl.speedfast.model.TipoPedido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaRegistroPedido extends JFrame {
    Pedido pedido = new Pedido();
    ZonaDeCarga z;
    private final JTextField id = new JTextField();
    private final JTextField direccion = new JTextField();
    private final String[] opcionesCombo = {"Comida", "Encomienda", "Express"};
    private final JComboBox<String> tipoPedido = new JComboBox<>(opcionesCombo);
    private final JButton aceptar = new JButton("Aceptar");
    private final JButton cancelar = new JButton("Cancelar");

    public VentanaRegistroPedido(ZonaDeCarga z) {
        this.z = z;
        JFrame ventana = new JFrame();
        ventana.setTitle("Registro de pedido");
        ventana.setBounds(100, 100, 700, 300);
        ventana.setSize(700, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLayout(new GridLayout());
        ventana.setResizable(false);

        ventana.add(mainPanel());
        addActions(ventana);
    }

    private void comboAction(ActionEvent e){
        JComboBox<String> combo = (JComboBox<String>) e.getSource();
        String tipo = (String) combo.getSelectedItem();
        switch (tipo){
            case "Comida":
                setTipo(TipoPedido.COMIDA);
                break;
            case "Encomienda":
                setTipo(TipoPedido.ENCOMIENDA);
                break;
            case "Express":
                setTipo(TipoPedido.EXPRESS);
                break;
            case null:
                break;
            default:
                break;
        }
    }

    private JPanel mainPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Registro de pedido"));

        panel.add(formPedido(), BorderLayout.CENTER);
        panel.add(formActions(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel formActions(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(aceptar);
        panel.add(cancelar);
        return panel;
    }

    private JPanel formPedido(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2, 8, 8));
        panel.add(new JLabel("ID"));
        id.setEditable(false);
        id.setText(String.valueOf(pedido.getId()));
        panel.add(id);
        panel.add(new JLabel("Direccion"));
        panel.add(direccion);
        panel.add(new JLabel("Tipo de pedido"));
        panel.add(tipoPedido);
        tipoPedido.setEditable(true);
        return panel;
    }

    private void setTipo(TipoPedido tipo){
        System.out.println("Tipo: " + tipo);
        pedido.setTipo(tipo);
    }

    private void submitPedido(JFrame ventana){
        String sDireccion = direccion.getText();
        if (!sDireccion.isEmpty()){
            pedido.setDireccionEntrega(direccion.getText().trim());
            z.agregarPedido(pedido);
            ventana.dispose();
        }
    }

    private void addActions(JFrame ventana){
        tipoPedido.addActionListener((ActionEvent e) -> {comboAction(e);});
        aceptar.addActionListener((ActionEvent e) -> {submitPedido(ventana);});
        cancelar.addActionListener((ActionEvent e) -> {ventana.dispose();});
    }
}
