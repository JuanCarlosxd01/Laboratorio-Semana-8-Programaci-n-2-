
package interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CrearUsuarioPanel extends FondoPokemonPanel {

    private VentanaPokemon ventana;

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmar;

    public CrearUsuarioPanel(VentanaPokemon ventana) {
        this.ventana = ventana;

        setLayout(new GridBagLayout());

        crearContenido();
    }

    private void crearContenido() {
        JPanel tarjeta = new JPanel();
        tarjeta.setPreferredSize(new Dimension(470, 520));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(new Color(248, 250, 246));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(35, 75, 62), 5),
                new EmptyBorder(35, 45, 35, 45)
        ));

        JLabel titulo = new JLabel("NUEVO ENTRENADOR");
        titulo.setFont(new Font("Arial", Font.BOLD, 27));
        titulo.setForeground(new Color(43, 91, 77));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel pokeball = new JLabel("◉");
        pokeball.setFont(new Font("Arial", Font.BOLD, 65));
        pokeball.setForeground(new Color(215, 62, 55));
        pokeball.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtUsuario = crearCampo();
        txtContrasena = crearPassword();
        txtConfirmar = crearPassword();

        JButton btnCrear = crearBoton("CREAR ENTRENADOR");
        JButton btnVolver = crearBoton("VOLVER");

        btnCrear.addActionListener(e -> crearUsuario());
        btnVolver.addActionListener(e -> ventana.mostrarLogin());

        tarjeta.add(titulo);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(pokeball);
        tarjeta.add(Box.createVerticalStrut(20));

        agregarCampo(tarjeta, "USUARIO", txtUsuario);
        agregarCampo(tarjeta, "CONTRASEÑA", txtContrasena);
        agregarCampo(tarjeta, "CONFIRMAR CONTRASEÑA", txtConfirmar);

        tarjeta.add(Box.createVerticalStrut(20));
        tarjeta.add(btnCrear);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(btnVolver);

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
        return boton;
    }

    private void crearUsuario() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String confirmar = new String(txtConfirmar.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        if (!contrasena.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Entrenador creado correctamente.");

        ventana.mostrarLogin();
    }
}
