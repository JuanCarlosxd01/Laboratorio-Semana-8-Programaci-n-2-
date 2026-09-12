
package interfaz;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BatallaPanel extends JPanel {

    private VentanaPokemon ventana;

    private JLabel lblPokemonJugador;
    private JLabel lblPokemonRival;

    public BatallaPanel(VentanaPokemon ventana) {
        this.ventana = ventana;

        setLayout(new BorderLayout());

        crearCampoBatalla();
        crearMenu();
    }

    private void crearCampoBatalla() {
        JPanel campo = new FondoPokemonPanel();
        campo.setLayout(null);

        JPanel infoRival = crearInfoPokemon("CHARMANDER", "Lv. 15", "100 / 100 HP");
        infoRival.setBounds(80, 50, 300, 110);

        lblPokemonRival = crearSprite(4, false);
        lblPokemonRival.setBounds(720, 70, 220, 220);

        lblPokemonJugador = crearSprite(25, true);
        lblPokemonJugador.setBounds(150, 270, 250, 250);

        JPanel infoJugador = crearInfoPokemon("PIKACHU", "Lv. 15", "100 / 100 HP");
        infoJugador.setBounds(670, 340, 300, 110);

        campo.add(infoRival);
        campo.add(lblPokemonRival);
        campo.add(lblPokemonJugador);
        campo.add(infoJugador);

        add(campo, BorderLayout.CENTER);
    }

    private JPanel crearInfoPokemon(String nombre, String nivel, String hp) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(250, 250, 237));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(55, 65, 55), 4),
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel lblNombre = new JLabel(nombre + "     " + nivel);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel lblHp = new JLabel("HP");
        lblHp.setFont(new Font("Arial", Font.BOLD, 12));

        JProgressBar barra = new JProgressBar(0, 100);
        barra.setValue(100);
        barra.setStringPainted(false);

        JLabel lblVida = new JLabel(hp);
        lblVida.setFont(new Font("Arial", Font.BOLD, 13));
        lblVida.setAlignmentX(Component.RIGHT_ALIGNMENT);

        panel.add(lblNombre);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblHp);
        panel.add(barra);
        panel.add(lblVida);

        return panel;
    }

    private JLabel crearSprite(int id, boolean espalda) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        String ruta;

        if (espalda) {
            ruta = "/imagenes/pokemon/" + id + " back.png";
        } else {
            ruta = "/imagenes/pokemon/" + id + ".png";
        }

        URL recurso = getClass().getResource(ruta);

        if (recurso != null) {
            ImageIcon icono = new ImageIcon(recurso);
            Image imagen = icono.getImage().getScaledInstance(200, 200, Image.SCALE_FAST);
            label.setIcon(new ImageIcon(imagen));
        }

        return label;
    }

    private void crearMenu() {
        JPanel inferior = new JPanel(new BorderLayout());
        inferior.setPreferredSize(new Dimension(0, 150));
        inferior.setBackground(new Color(248, 248, 239));

        JTextArea mensaje = new JTextArea("¿Qué hará PIKACHU?");
        mensaje.setEditable(false);
        mensaje.setFont(new Font("Arial", Font.BOLD, 22));
        mensaje.setBorder(new EmptyBorder(30, 30, 20, 20));

        JPanel botones = new JPanel(new GridLayout(2, 2, 8, 8));
        botones.setBorder(new EmptyBorder(18, 18, 18, 18));

        JButton btnAtacar = new JButton("ATACAR");
        JButton btnCambiar = new JButton("CAMBIAR");
        JButton btnObjetos = new JButton("OBJETOS");
        JButton btnEquipo = new JButton("MI EQUIPO");

        botones.add(btnAtacar);
        botones.add(btnCambiar);
        botones.add(btnObjetos);
        botones.add(btnEquipo);

        inferior.add(mensaje, BorderLayout.CENTER);
        inferior.add(botones, BorderLayout.EAST);

        add(inferior, BorderLayout.SOUTH);
    }
}
