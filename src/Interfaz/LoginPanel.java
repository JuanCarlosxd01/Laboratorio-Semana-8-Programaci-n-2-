
package interfaz;

import Preparacion.Sesion;
import Preparacion.User;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends FondoPokemonPanel {

    private VentanaPokemon ventana;

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    public LoginPanel(VentanaPokemon ventana) {
        this.ventana = ventana;

        setLayout(new GridBagLayout());

        crearContenido();
    }

    private void crearContenido() {
        JPanel tarjeta = new JPanel();

        tarjeta.setPreferredSize(new Dimension(450, 430));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(new Color(248, 250, 246));

        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(35, 75, 62), 5),
                new EmptyBorder(35, 45, 35, 45)
        ));

        JLabel titulo = new JLabel("POKÉMON BATTLE");

        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(43, 91, 77));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel pokeball = new JLabel("◉");

        pokeball.setFont(new Font("Arial", Font.BOLD, 65));
        pokeball.setForeground(new Color(215, 62, 55));
        pokeball.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtUsuario = crearCampo();
        txtContrasena = crearPassword();

        JButton btnIngresar = crearBoton("INICIAR SESIÓN");
        JButton btnCrear = crearBoton("CREAR USUARIO");

        btnIngresar.addActionListener(e -> iniciarSesion());

        btnCrear.addActionListener(e -> {
            limpiarCampos();
            ventana.mostrarCrearUsuario();
        });

        tarjeta.add(titulo);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(pokeball);
        tarjeta.add(Box.createVerticalStrut(20));

        agregarCampo(tarjeta, "USUARIO", txtUsuario);
        agregarCampo(tarjeta, "CONTRASEÑA", txtContrasena);

        tarjeta.add(Box.createVerticalStrut(20));
        tarjeta.add(btnIngresar);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(btnCrear);

        add(tarjeta);
    }

    private JTextField crearCampo() {
        JTextField campo = new JTextField();

        configurarCampo(campo);

        return campo;
    }

    private JPasswordField crearPassword() {
        JPasswordField campo = new JPasswordField();

        configurarCampo(campo);

        return campo;
    }

    private void configurarCampo(JTextField campo) {
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        campo.setFont(new Font("Arial", Font.PLAIN, 16));

        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 103, 91), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void agregarCampo(JPanel panel, String texto, JTextField campo) {
        JLabel label = new JLabel(texto);

        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(campo);
        panel.add(Box.createVerticalStrut(15));
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);

        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setFocusPainted(false);
        boton.setBackground(new Color(55, 129, 93));
        boton.setForeground(Color.WHITE);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }

    private void iniciarSesion() {
        String nombreUsuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        if (nombreUsuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese su usuario y contraseña.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        User usuario = Sesion.getInstancia().getUsuarios().autenticar(nombreUsuario, contrasena);

        if (usuario == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contraseña incorrectos.",
                    "Inicio de sesión",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Sesion.getInstancia().iniciarSesion(usuario);

        JOptionPane.showMessageDialog(
                this,
                "Bienvenido, " + usuario.getUsuario() + ".",
                "Sesión iniciada",
                JOptionPane.INFORMATION_MESSAGE
        );

        limpiarCampos();

        ventana.mostrarSeleccion();
    }

    private void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasena.setText("");
    }
}