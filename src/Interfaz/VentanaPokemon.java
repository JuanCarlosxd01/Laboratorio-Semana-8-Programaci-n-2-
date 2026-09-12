
package interfaz;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPokemon extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    private LoginPanel loginPanel;
    private CrearUsuarioPanel crearUsuarioPanel;
    private SeleccionEquipoPanel seleccionEquipoPanel;
    private BatallaPanel batallaPanel;

    public VentanaPokemon() {
        configurarVentana();
        crearPaneles();
        mostrarLogin();
    }

    private void configurarVentana() {
        setTitle("Pokémon Battle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void crearPaneles() {
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
        crearUsuarioPanel = new CrearUsuarioPanel(this);
        seleccionEquipoPanel = new SeleccionEquipoPanel(this);
        batallaPanel = new BatallaPanel(this);

        panelPrincipal.add(loginPanel, "LOGIN");
        panelPrincipal.add(crearUsuarioPanel, "CREAR_USUARIO");
        panelPrincipal.add(seleccionEquipoPanel, "SELECCION");
        panelPrincipal.add(batallaPanel, "BATALLA");

        setContentPane(panelPrincipal);
    }

    public void mostrarLogin() {
        cardLayout.show(panelPrincipal, "LOGIN");
    }

    public void mostrarCrearUsuario() {
        cardLayout.show(panelPrincipal, "CREAR_USUARIO");
    }

    public void mostrarSeleccion() {
        seleccionEquipoPanel.reiniciarSeleccion();
        cardLayout.show(panelPrincipal, "SELECCION");
    }

    public void mostrarBatalla() {
        batallaPanel.prepararBatalla();
        cardLayout.show(panelPrincipal, "BATALLA");
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public CrearUsuarioPanel getCrearUsuarioPanel() {
        return crearUsuarioPanel;
    }

    public SeleccionEquipoPanel getSeleccionEquipoPanel() {
        return seleccionEquipoPanel;
    }

    public BatallaPanel getBatallaPanel() {
        return batallaPanel;
    }
}