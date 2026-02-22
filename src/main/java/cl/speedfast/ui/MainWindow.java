package cl.speedfast.ui;

import cl.speedfast.gestor.ZonaDeCarga;
import cl.speedfast.model.Repartidor;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainWindow extends JFrame {
    private final JButton crearPedido = new JButton("Registrar Pedido");
    private final JButton listarPedidos = new JButton("Listar Pedidos");
    private final JButton iniciarEntregas = new JButton("Iniciar Entregas");
    private final JButton cancelar = new JButton("Cancelar");

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

        panel.add(crearPedido);
        panel.add(listarPedidos);
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

    private void executeService(){
        // crear repartidores por defecto
        Repartidor r1 = new Repartidor("Sebastian", zc);
        Repartidor r2 = new Repartidor("ebastianS", zc);
        Repartidor r3 = new Repartidor("bastianSe", zc);

        if (!zc.getPedidosPendientes().isEmpty()){
            // usa executor service
            ExecutorService es = Executors.newFixedThreadPool(3);
            // ejecuta los repartidores como hilos aparentes
            es.execute(r1);
            es.execute(r2);
            es.execute(r3);
            // cierra la conexion una vez esta terminado el proceso.
            es.shutdown();
            try {
                if (es.awaitTermination(30, TimeUnit.SECONDS)) {
                    System.out.println("Killing");
                    es.shutdownNow();
                }
            } catch (InterruptedException e ){
                es.shutdownNow();
            }
        }
    }

    private void listarPedidos(){
        if (zc.getPedidosPendientes().isEmpty()){
            JOptionPane.showOptionDialog(this, "No hay pedidos para listar", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
        } else {
            JOptionPane.showMessageDialog(this, listadoPedidos());
        }
    }

    private void addActions() {
        crearPedido.addActionListener(e -> {new VentanaRegistroPedido(zc);});
        listarPedidos.addActionListener(e -> {listarPedidos();});
        iniciarEntregas.addActionListener(e -> {executeService();});
        cancelar.addActionListener(e -> {System.exit(0);});
    }

}
