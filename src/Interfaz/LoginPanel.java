
package interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends FondoPokemonPanel {

    private VentanaPokemon ventana;

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private JButton btnCrearUsuario;

    public LoginPanel(VentanaPokemon ventana) {
        this.ventana = ventana;

        setLayout(new GridBagLayout());

        crearLogin();
    }

    private void crearLogin() {
        JPanel tarjeta = new JPanel();
        tarjeta.setPreferredSize(new Dimension(430, 500));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(new Color(245, 250, 244));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(34, 75, 62), 5),
                new EmptyBorder(30, 45, 30, 45)
        ));

        JLabel titulo = new JLabel("POKÉMON");
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setForeground(new Color(245, 190, 30));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("BATTLE");
        subtitulo.setFont(new Font("Arial", Font.BOLD, 27));
        subtitulo.setForeground(new Color(45, 93, 85));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel icono = new JLabel("◉");
        icono.setFont(new Font("Arial", Font.BOLD, 70));
        icono.setForeground(new Color(213, 59, 55));
        icono.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUsuario = crearEtiqueta("USUARIO");

        txtUsuario = new JTextField();
        configurarCampo(txtUsuario);

        JLabel lblContrasena = crearEtiqueta("CONTRASEÑA");

        txtContrasena = new JPasswordField();
        configurarCampo(txtContrasena);

        btnIngresar = crearBoton("INICIAR SESIÓN");
        btnCrearUsuario = crearBotonSecundario("CREAR USUARIO");

        btnIngresar.addActionListener(e -> ingresar());
        btnCrearUsuario.addActionListener(e -> ventana.mostrarCrearUsuario());

        tarjeta.add(titulo);
        tarjeta.add(subtitulo);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(icono);
        tarjeta.add(Box.createVerticalStrut(15));
        tarjeta.add(lblUsuario);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(txtUsuario);
        tarjeta.add(Box.createVerticalStrut(15));
        tarjeta.add(lblContrasena);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(txtContrasena);
        tarjeta.add(Box.createVerticalStrut(25));
        tarjeta.add(btnIngresar);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(btnCrearUsuario);

        add(tarjeta);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(50, 65, 60));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void configurarCampo(JTextField campo) {
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        campo.setPreferredSize(new Dimension(320, 45));
        campo.setFont(new Font("Arial", Font.PLAIN, 17));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(71, 105, 91), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        boton.setPreferredSize(new Dimension(320, 48));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(60, 137, 98));
        boton.setForeground(Color.WHITE);
        return boton;
    }

    private JButton crearBotonSecundario(String texto) {
        JButton boton = crearBoton(texto);
        boton.setBackground(new Color(70, 91, 103));
        return boton;
    }

    private void ingresar() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese usuario y contraseña.");
            return;
        }

        ventana.mostrarSeleccion();
    }
}
