
package interfaz;

import java.awt.*;
import javax.swing.*;

public class VentanaPokemon extends JFrame {

    private CardLayout cardLayout;
    private JPanel contenedor;

    private LoginPanel loginPanel;
    private CrearUsuarioPanel crearUsuarioPanel;
    private SeleccionEquipoPanel seleccionEquipoPanel;
    private BatallaPanel batallaPanel;

    public VentanaPokemon() {
        configurarVentana();
        crearContenido();
    }

    private void configurarVentana() {
        setTitle("Pokémon Battle");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void crearContenido() {
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
        crearUsuarioPanel = new CrearUsuarioPanel(this);
        seleccionEquipoPanel = new SeleccionEquipoPanel(this);
        batallaPanel = new BatallaPanel(this);

        contenedor.add(loginPanel, "LOGIN");
        contenedor.add(crearUsuarioPanel, "CREAR_USUARIO");
        contenedor.add(seleccionEquipoPanel, "SELECCION");
        contenedor.add(batallaPanel, "BATALLA");

        add(contenedor);

        mostrarLogin();
    }

    public void mostrarLogin() {
        cardLayout.show(contenedor, "LOGIN");
    }

    public void mostrarCrearUsuario() {
        cardLayout.show(contenedor, "CREAR_USUARIO");
    }

    public void mostrarSeleccion() {
        cardLayout.show(contenedor, "SELECCION");
    }

    public void mostrarBatalla() {
        cardLayout.show(contenedor, "BATALLA");
    }
}
