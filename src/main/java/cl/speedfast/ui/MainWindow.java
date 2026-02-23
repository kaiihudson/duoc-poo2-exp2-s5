package cl.speedfast.ui;

import cl.speedfast.gestor.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JButton crearPedido = new JButton("Registrar Pedido");
    private final JButton listarPedidos = new JButton("Listar Pedidos Pendientes");
    private final JButton crearRepartidor = new JButton("Registrar Repartidor");
    private final JButton listarRepartidores = new JButton("Listar Repartidores");
    private final JButton iniciarEntregas = new JButton("Iniciar Entregas");
    private final JButton cancelar = new JButton("Cancelar");

    private final ZonaDeCarga zc = new ZonaDeCarga();

    public MainWindow() {

        JFrame frame = new JFrame();
        frame.setTitle("SpeedFast");
        frame.setBounds(100, 100, 400, 300);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(4,1));
        frame.setResizable(false);

        JPanel extraActions = addExit();
        addActions();
        frame.add(addComponents0());
        frame.add(addComponents1());
        frame.add(addComponents2());
        frame.add(extraActions);
    }

    private JPanel addComponents0() {
        JPanel panel = new JPanel();

        panel.add(crearPedido);
        panel.add(listarPedidos);
        return panel;
    }

    private JPanel addComponents1(){
        JPanel panel = new JPanel();
        panel.add(crearRepartidor);
        panel.add(listarRepartidores);
        return panel;
    }
    private JPanel addComponents2(){
        JPanel panel = new JPanel();
        panel.add(iniciarEntregas);
        return panel;
    }

    private JPanel addExit(){
        JPanel panel = new JPanel();

        panel.add(cancelar);
        return panel;
    }

    private String listadoPedidos(){
        StringBuilder sb = new StringBuilder();
        zc.getPedidosPendientes().forEach(p -> sb.append(p.toString()).append("\n"));
        return sb.toString();
    }
    private String listadoRepartidores(){
        StringBuilder sb = new StringBuilder();
        zc.getRepartidores().forEach(repartidor -> sb.append(repartidor.toString()).append("\n"));
        return sb.toString();
    }

    private void executeService(){
        zc.executeAll();
    }

    private void listarPedidos(){
        if (zc.getPedidosPendientes().isEmpty()){
            JOptionPane.showOptionDialog(this, "No hay pedidos para listar", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
        } else {
            JOptionPane.showMessageDialog(this, listadoPedidos());
        }
    }

    private void listarRepartidores(){
        if (zc.getRepartidores().isEmpty()){
            JOptionPane.showOptionDialog(this, "No hay repartidores para listar", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
        } else {
            JOptionPane.showMessageDialog(this, listadoRepartidores());
        }
    }

    private void crearRepartidorJOption(){
        int returnedValue;
        System.out.println("Creando Repartidor");
        FormularioRepartidor formularioRepartidor = new FormularioRepartidor();
        returnedValue = JOptionPane.showOptionDialog(
                this,
                formularioRepartidor,
                "Agregar Repartidor",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );
        if (JOptionPane.OK_OPTION == returnedValue){
            String nombreRepartidor = formularioRepartidor.getNombreRepartidor();
            if (nombreRepartidor != null){
                zc.crearRepartidor(nombreRepartidor);
            }
        }
    }

    private void addActions() {
        crearPedido.addActionListener(e -> {new VentanaRegistroPedido(zc);});
        listarPedidos.addActionListener(e -> {listarPedidos();});
        crearRepartidor.addActionListener(e -> {crearRepartidorJOption();});
        listarRepartidores.addActionListener(e -> {listarRepartidores();});
        iniciarEntregas.addActionListener(e -> {executeService();});
        cancelar.addActionListener(e -> {System.exit(0);});
    }

}
