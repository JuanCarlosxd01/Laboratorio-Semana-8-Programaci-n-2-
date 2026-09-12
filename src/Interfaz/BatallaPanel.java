
package interfaz;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;

public class BatallaPanel extends JPanel {

    private VentanaPokemon ventana;

    private JPanel campo;

    private JLabel lblPokemonJugador;
    private JLabel lblPokemonRival;

    private JLabel texto1;
    private JLabel texto2;

    private JLabel lblNombreJugador;
    private JLabel lblVidaJugador;

    private JLabel lblNombreRival;
    private JLabel lblVidaRival;

    private JProgressBar barraJugador;
    private JProgressBar barraRival;

    private Font fuentePokemon;

    public BatallaPanel(VentanaPokemon ventana) {
        this.ventana = ventana;

        cargarFuente();

        setLayout(new BorderLayout());

        crearCampoBatalla();
    }

    private void cargarFuente() {
        try {
            InputStream archivo = BatallaPanel.class.getResourceAsStream("/Imagenes/pokemon-emerald.otf");

            if (archivo == null) {
                System.out.println("No se encontró /Imagenes/pokemon-emerald.otf");
                fuentePokemon = new Font("Arial", Font.BOLD, 16);
                return;
            }

            fuentePokemon = Font.createFont(Font.TRUETYPE_FONT, archivo);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuentePokemon);

            archivo.close();

            System.out.println("Fuente Pokémon cargada: " + fuentePokemon.getFontName());

        } catch (Exception e) {
            System.out.println("Error cargando fuente Pokémon: " + e.getMessage());
            fuentePokemon = new Font("Arial", Font.BOLD, 16);
        }
    }

    private Font fuente(float tamaño) {
        return fuentePokemon.deriveFont(Font.PLAIN, tamaño);
    }

    private void crearCampoBatalla() {
        campo = new FondoPokemonPanel();
        campo.setLayout(null);

        crearCajasInformacion();
        crearPokemon();
        crearInformacionPokemon();
        crearBotonHistorial();
        crearMenuInferior();

        add(campo, BorderLayout.CENTER);
    }

    private void crearCajasInformacion() {
        texto1 = cargarImagen("/Imagenes/texto1.png", 360, 115);
        texto2 = cargarImagen("/Imagenes/texto2.png", 360, 115);

        texto1.setBounds(25, 30, 360, 115);
        texto2.setBounds(650, 330, 360, 115);

        campo.add(texto1);
        campo.add(texto2);
    }

    private void crearPokemon() {
        lblPokemonRival = crearSprite(4, false);
        lblPokemonRival.setBounds(680, 75, 280, 250);

        lblPokemonJugador = crearSprite(25, true);
        lblPokemonJugador.setBounds(75, 285, 330, 280);

        campo.add(lblPokemonRival);
        campo.add(lblPokemonJugador);
    }

    private void crearInformacionPokemon() {
        lblNombreRival = new JLabel("CHARMANDER");
        lblNombreRival.setFont(fuente(18f));
        lblNombreRival.setForeground(new Color(45, 45, 45));
        lblNombreRival.setBounds(80, 60, 200, 25);

        JLabel nivelRival = new JLabel("Lv. 15");
        nivelRival.setFont(fuente(14f));
        nivelRival.setForeground(new Color(45, 45, 45));
        nivelRival.setBounds(315, 60, 70, 25);

        JLabel hpRival = new JLabel("HP");
        hpRival.setFont(fuente(11f));
        hpRival.setForeground(new Color(45, 45, 45));
        hpRival.setBounds(100, 100, 40, 20);

        barraRival = crearBarraVida();
        barraRival.setBounds(145, 102, 215, 14);

        lblVidaRival = new JLabel("100 / 100");
        lblVidaRival.setFont(fuente(11f));
        lblVidaRival.setForeground(new Color(45, 45, 45));
        lblVidaRival.setBounds(275, 124, 90, 20);

        lblNombreJugador = new JLabel("PIKACHU");
        lblNombreJugador.setFont(fuente(18f));
        lblNombreJugador.setForeground(new Color(45, 45, 45));
        lblNombreJugador.setBounds(685, 365, 200, 25);

        JLabel nivelJugador = new JLabel("Lv. 15");
        nivelJugador.setFont(fuente(14f));
        nivelJugador.setForeground(new Color(45, 45, 45));
        nivelJugador.setBounds(920, 365, 70, 25);

        JLabel hpJugador = new JLabel("HP");
        hpJugador.setFont(fuente(11f));
        hpJugador.setForeground(new Color(45, 45, 45));
        hpJugador.setBounds(705, 405, 40, 20);

        barraJugador = crearBarraVida();
        barraJugador.setBounds(750, 407, 215, 14);

        lblVidaJugador = new JLabel("100 / 100");
        lblVidaJugador.setFont(fuente(11f));
        lblVidaJugador.setForeground(new Color(45, 45, 45));
        lblVidaJugador.setBounds(875, 428, 100, 20);

        campo.add(lblNombreRival);
        campo.add(nivelRival);
        campo.add(hpRival);
        campo.add(barraRival);
        campo.add(lblVidaRival);

        campo.add(lblNombreJugador);
        campo.add(nivelJugador);
        campo.add(hpJugador);
        campo.add(barraJugador);
        campo.add(lblVidaJugador);

        campo.setComponentZOrder(lblNombreRival, 0);
        campo.setComponentZOrder(nivelRival, 0);
        campo.setComponentZOrder(hpRival, 0);
        campo.setComponentZOrder(barraRival, 0);
        campo.setComponentZOrder(lblVidaRival, 0);

        campo.setComponentZOrder(lblNombreJugador, 0);
        campo.setComponentZOrder(nivelJugador, 0);
        campo.setComponentZOrder(hpJugador, 0);
        campo.setComponentZOrder(barraJugador, 0);
        campo.setComponentZOrder(lblVidaJugador, 0);
    }

    private JProgressBar crearBarraVida() {
        JProgressBar barra = new JProgressBar(0, 100);

        barra.setValue(100);
        barra.setBorderPainted(false);
        barra.setStringPainted(false);

        barra.setBackground(new Color(65, 75, 70));
        barra.setForeground(new Color(55, 190, 90));

        return barra;
    }

    private void crearBotonHistorial() {
        JButton btnHistorial = new JButton("HISTORIAL");

        btnHistorial.setFont(fuente(13f));
        btnHistorial.setFocusPainted(false);
        btnHistorial.setBackground(new Color(248, 246, 232));
        btnHistorial.setForeground(new Color(45, 45, 45));
        btnHistorial.setBounds(890, 20, 150, 35);
        btnHistorial.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnHistorial.addActionListener(e -> mostrarHistorial());

        campo.add(btnHistorial);

        campo.setComponentZOrder(btnHistorial, 0);
    }

    private void crearMenuInferior() {
        JPanel panelInferior = new JPanel(null);
        panelInferior.setBounds(0, 515, 1085, 165);
        panelInferior.setBackground(new Color(65, 67, 78));

        JPanel panelMensaje = new JPanel(new BorderLayout());
        panelMensaje.setBounds(12, 10, 600, 145);
        panelMensaje.setBackground(new Color(105, 175, 180));

        Border bordeMensaje = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(195, 70, 65), 8),
                BorderFactory.createLineBorder(new Color(225, 225, 225), 4)
        );

        panelMensaje.setBorder(bordeMensaje);

        JLabel mensaje = new JLabel("<html>¿QUÉ HARÁ<br>PIKACHU?</html>");
        mensaje.setFont(fuente(21f));
        mensaje.setForeground(Color.WHITE);
        mensaje.setBorder(BorderFactory.createEmptyBorder(15, 25, 10, 10));

        panelMensaje.add(mensaje, BorderLayout.CENTER);

        JPanel panelOpciones = new JPanel(null);
        panelOpciones.setBounds(620, 10, 450, 145);
        panelOpciones.setBackground(new Color(248, 246, 232));

        Border bordeOpciones = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(75, 70, 95), 7),
                BorderFactory.createLineBorder(new Color(220, 220, 220), 4)
        );

        panelOpciones.setBorder(bordeOpciones);

        JButton btnAtaque = crearBotonMenu("ATAQUE");
        JButton btnObjetos = crearBotonMenu("OBJETOS");
        JButton btnCambiar = crearBotonMenu("CAMBIAR");
        JButton btnEquipo = crearBotonMenu("MI EQUIPO");

        btnAtaque.setBounds(25, 20, 185, 45);
        btnObjetos.setBounds(235, 20, 185, 45);

        btnCambiar.setBounds(25, 80, 185, 45);
        btnEquipo.setBounds(235, 80, 185, 45);

        btnAtaque.addActionListener(e -> mostrarAtaques());
        btnObjetos.addActionListener(e -> mostrarObjetos());
        btnCambiar.addActionListener(e -> mostrarCambiarPokemon());
        btnEquipo.addActionListener(e -> mostrarEquipo());

        panelOpciones.add(btnAtaque);
        panelOpciones.add(btnObjetos);
        panelOpciones.add(btnCambiar);
        panelOpciones.add(btnEquipo);

        panelInferior.add(panelMensaje);
        panelInferior.add(panelOpciones);

        campo.add(panelInferior);

        campo.setComponentZOrder(panelInferior, 0);
    }

    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);

        boton.setFont(fuente(17f));
        boton.setForeground(new Color(45, 45, 45));
        boton.setBackground(new Color(248, 246, 232));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }

    private void mostrarAtaques() {
        JDialog dialogo = crearDialogo("ATAQUES", 500, 300);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(248, 246, 232));

        JLabel titulo = new JLabel("SELECCIONA UN ATAQUE", SwingConstants.CENTER);
        titulo.setFont(fuente(18f));

        JPanel panelAtaques = new JPanel(new GridLayout(2, 2, 10, 10));
        panelAtaques.setBackground(new Color(248, 246, 232));

        JButton btnImpactrueno = crearBotonDialogo("IMPACTRUENO");
        JButton btnAtaqueRapido = crearBotonDialogo("ATAQUE RÁPIDO");
        JButton btnOndaTrueno = crearBotonDialogo("ONDA TRUENO");
        JButton btnChispa = crearBotonDialogo("CHISPA");

        panelAtaques.add(btnImpactrueno);
        panelAtaques.add(btnAtaqueRapido);
        panelAtaques.add(btnOndaTrueno);
        panelAtaques.add(btnChispa);

        btnImpactrueno.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialogo, "PIKACHU utilizó IMPACTRUENO.");
            dialogo.dispose();
        });

        btnAtaqueRapido.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialogo, "PIKACHU utilizó ATAQUE RÁPIDO.");
            dialogo.dispose();
        });

        btnOndaTrueno.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialogo, "PIKACHU utilizó ONDA TRUENO.");
            dialogo.dispose();
        });

        btnChispa.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialogo, "PIKACHU utilizó CHISPA.");
            dialogo.dispose();
        });

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(panelAtaques, BorderLayout.CENTER);

        dialogo.add(panelPrincipal);
        dialogo.setVisible(true);
    }

    private void mostrarCambiarPokemon() {
        JDialog dialogo = crearDialogo("SELECCIONAR POKÉMON", 500, 420);

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        principal.setBackground(new Color(248, 246, 232));

        JLabel titulo = new JLabel("SELECCIONAR POKÉMON", SwingConstants.CENTER);
        titulo.setFont(fuente(18f));

        JPanel listaPokemon = new JPanel();
        listaPokemon.setLayout(new BoxLayout(listaPokemon, BoxLayout.Y_AXIS));
        listaPokemon.setBackground(new Color(248, 246, 232));

        JRadioButton pikachu = crearOpcionPokemon("PIKACHU", 25, 100, 100, false);
        JRadioButton bulbasaur = crearOpcionPokemon("BULBASAUR", 1, 75, 100, false);
        JRadioButton squirtle = crearOpcionPokemon("SQUIRTLE", 7, 0, 100, true);

        ButtonGroup grupo = new ButtonGroup();

        grupo.add(pikachu);
        grupo.add(bulbasaur);
        grupo.add(squirtle);

        pikachu.setEnabled(false);
        squirtle.setEnabled(false);

        listaPokemon.add(pikachu);
        listaPokemon.add(Box.createVerticalStrut(8));
        listaPokemon.add(bulbasaur);
        listaPokemon.add(Box.createVerticalStrut(8));
        listaPokemon.add(squirtle);

        JButton btnCambiar = crearBotonDialogo("CAMBIAR");

        btnCambiar.addActionListener(e -> {
            if (bulbasaur.isSelected()) {
                JOptionPane.showMessageDialog(dialogo, "Cambiaste a BULBASAUR.");
                dialogo.dispose();
                return;
            }

            JOptionPane.showMessageDialog(dialogo, "Selecciona un Pokémon disponible.");
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(248, 246, 232));
        panelBoton.add(btnCambiar);

        principal.add(titulo, BorderLayout.NORTH);
        principal.add(listaPokemon, BorderLayout.CENTER);
        principal.add(panelBoton, BorderLayout.SOUTH);

        dialogo.add(principal);
        dialogo.setVisible(true);
    }

    private JRadioButton crearOpcionPokemon(String nombre, int id, int vida, int vidaMaxima, boolean derrotado) {
        String texto = nombre + "      " + vida + "/" + vidaMaxima;

        if (derrotado) {
            texto += "      DERROTADO";
        }

        JRadioButton opcion = new JRadioButton(texto);
        opcion.setFont(fuente(15f));
        opcion.setBackground(new Color(248, 246, 232));
        opcion.setForeground(new Color(45, 45, 45));
        opcion.setIconTextGap(15);

        URL recurso = BatallaPanel.class.getResource("/Imagenes/" + id + ".png");

        if (recurso != null) {
            ImageIcon icono = new ImageIcon(recurso);
            Image imagen = icono.getImage().getScaledInstance(55, 55, Image.SCALE_FAST);
            opcion.setIcon(new ImageIcon(imagen));
        }

        return opcion;
    }

    private void mostrarObjetos() {
        JDialog dialogo = crearDialogo("OBJETOS", 500, 380);

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        principal.setBackground(new Color(248, 246, 232));

        JLabel titulo = new JLabel("OBJETOS", SwingConstants.CENTER);
        titulo.setFont(fuente(18f));

        JPanel opciones = new JPanel();
        opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
        opciones.setBackground(new Color(248, 246, 232));

        JRadioButton pocion = crearOpcionObjeto("POCIÓN        Recupera 20 HP");
        JRadioButton superPocion = crearOpcionObjeto("SUPERPOCIÓN   Recupera 50 HP");
        JRadioButton revivir = crearOpcionObjeto("REVIVIR       Recupera Pokémon");

        ButtonGroup grupo = new ButtonGroup();

        grupo.add(pocion);
        grupo.add(superPocion);
        grupo.add(revivir);

        opciones.add(pocion);
        opciones.add(Box.createVerticalStrut(15));
        opciones.add(superPocion);
        opciones.add(Box.createVerticalStrut(15));
        opciones.add(revivir);

        JButton btnUtilizar = crearBotonDialogo("UTILIZAR");
        JButton btnCerrar = crearBotonDialogo("CERRAR");

        btnUtilizar.addActionListener(e -> {
            if (pocion.isSelected()) {
                JOptionPane.showMessageDialog(dialogo, "Usaste una POCIÓN.");
                dialogo.dispose();
                return;
            }

            if (superPocion.isSelected()) {
                JOptionPane.showMessageDialog(dialogo, "Usaste una SUPERPOCIÓN.");
                dialogo.dispose();
                return;
            }

            if (revivir.isSelected()) {
                JOptionPane.showMessageDialog(dialogo, "Usaste REVIVIR.");
                dialogo.dispose();
                return;
            }

            JOptionPane.showMessageDialog(dialogo, "Selecciona un objeto.");
        });

        btnCerrar.addActionListener(e -> dialogo.dispose());

        JPanel botones = new JPanel();
        botones.setBackground(new Color(248, 246, 232));

        botones.add(btnUtilizar);
        botones.add(btnCerrar);

        principal.add(titulo, BorderLayout.NORTH);
        principal.add(opciones, BorderLayout.CENTER);
        principal.add(botones, BorderLayout.SOUTH);

        dialogo.add(principal);
        dialogo.setVisible(true);
    }

    private JRadioButton crearOpcionObjeto(String texto) {
        JRadioButton opcion = new JRadioButton(texto);

        opcion.setFont(fuente(15f));
        opcion.setBackground(new Color(248, 246, 232));
        opcion.setForeground(new Color(45, 45, 45));

        return opcion;
    }

    private void mostrarEquipo() {
        JDialog dialogo = crearDialogo("MI EQUIPO", 520, 420);

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        principal.setBackground(new Color(248, 246, 232));

        JLabel titulo = new JLabel("MI EQUIPO", SwingConstants.CENTER);
        titulo.setFont(fuente(20f));

        JPanel lista = new JPanel();
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
        lista.setBackground(new Color(248, 246, 232));

        lista.add(crearFilaEquipo("PIKACHU", 25, 15, 100, 100, false));
        lista.add(Box.createVerticalStrut(8));

        lista.add(crearFilaEquipo("BULBASAUR", 1, 15, 75, 100, false));
        lista.add(Box.createVerticalStrut(8));

        lista.add(crearFilaEquipo("SQUIRTLE", 7, 15, 0, 100, true));

        JLabel disponibles = new JLabel("POKÉMON DISPONIBLES: 2");
        disponibles.setFont(fuente(15f));

        JButton cerrar = crearBotonDialogo("CERRAR");
        cerrar.addActionListener(e -> dialogo.dispose());

        JPanel inferior = new JPanel(new BorderLayout());
        inferior.setBackground(new Color(248, 246, 232));

        inferior.add(disponibles, BorderLayout.WEST);
        inferior.add(cerrar, BorderLayout.EAST);

        principal.add(titulo, BorderLayout.NORTH);
        principal.add(lista, BorderLayout.CENTER);
        principal.add(inferior, BorderLayout.SOUTH);

        dialogo.add(principal);
        dialogo.setVisible(true);
    }

    private JPanel crearFilaEquipo(String nombre, int id, int nivel, int vida, int vidaMaxima, boolean derrotado) {
        JPanel fila = new JPanel(new BorderLayout(10, 5));
        fila.setBackground(new Color(248, 246, 232));

        JLabel imagen = new JLabel();

        URL recurso = BatallaPanel.class.getResource("/Imagenes/" + id + ".png");

        if (recurso != null) {
            ImageIcon icono = new ImageIcon(recurso);
            Image sprite = icono.getImage().getScaledInstance(55, 55, Image.SCALE_FAST);
            imagen.setIcon(new ImageIcon(sprite));
        }

        String texto = nombre + "     Lv." + nivel + "     " + vida + "/" + vidaMaxima;

        if (derrotado) {
            texto += "     DERROTADO";
        }

        JLabel informacion = new JLabel(texto);
        informacion.setFont(fuente(14f));

        fila.add(imagen, BorderLayout.WEST);
        fila.add(informacion, BorderLayout.CENTER);

        return fila;
    }

    private void mostrarHistorial() {
        JDialog dialogo = crearDialogo("HISTORIAL DE BATALLA", 520, 400);

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        principal.setBackground(new Color(248, 246, 232));

        JLabel titulo = new JLabel("HISTORIAL DE BATALLA", SwingConstants.CENTER);
        titulo.setFont(fuente(18f));

        JTextArea historial = new JTextArea();

        historial.setEditable(false);
        historial.setFont(fuente(14f));
        historial.setBackground(new Color(248, 246, 232));
        historial.setForeground(new Color(45, 45, 45));

        historial.setText(
                "Turno 1\n"
                + "PIKACHU utilizó IMPACTRUENO.\n"
                + "CHARMANDER perdió 25 HP.\n\n"
                + "Turno 1\n"
                + "CHARMANDER utilizó ASCUAS.\n"
                + "PIKACHU perdió 15 HP.\n"
        );

        JScrollPane scroll = new JScrollPane(historial);

        JButton cerrar = crearBotonDialogo("CERRAR");
        cerrar.addActionListener(e -> dialogo.dispose());

        JPanel inferior = new JPanel();
        inferior.setBackground(new Color(248, 246, 232));
        inferior.add(cerrar);

        principal.add(titulo, BorderLayout.NORTH);
        principal.add(scroll, BorderLayout.CENTER);
        principal.add(inferior, BorderLayout.SOUTH);

        dialogo.add(principal);
        dialogo.setVisible(true);
    }

    private JDialog crearDialogo(String titulo, int ancho, int alto) {
        Window ventanaPadre = SwingUtilities.getWindowAncestor(this);

        JDialog dialogo = new JDialog(ventanaPadre, titulo, Dialog.ModalityType.APPLICATION_MODAL);

        dialogo.setSize(ancho, alto);
        dialogo.setLocationRelativeTo(this);
        dialogo.setResizable(false);

        return dialogo;
    }

    private JButton crearBotonDialogo(String texto) {
        JButton boton = new JButton(texto);

        boton.setFont(fuente(15f));
        boton.setBackground(new Color(248, 246, 232));
        boton.setForeground(new Color(45, 45, 45));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }

    private JLabel cargarImagen(String ruta, int ancho, int alto) {
        JLabel label = new JLabel();

        URL recurso = BatallaPanel.class.getResource(ruta);

        if (recurso == null) {
            System.out.println("No se encontró: " + ruta);
            return label;
        }

        ImageIcon icono = new ImageIcon(recurso);
        Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        label.setIcon(new ImageIcon(imagen));

        return label;
    }

    private JLabel crearSprite(int id, boolean espalda) {
        JLabel label = new JLabel();

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        String ruta;

        if (espalda) {
            ruta = "/Imagenes/" + id + " back.png";
        } else {
            ruta = "/Imagenes/" + id + ".png";
        }

        URL recurso = BatallaPanel.class.getResource(ruta);

        if (recurso == null) {
            System.out.println("No se encontró: " + ruta);

            label.setText("NO ENCONTRADA");
            label.setForeground(Color.RED);

            return label;
        }

        ImageIcon icono = new ImageIcon(recurso);
        Image imagen = icono.getImage().getScaledInstance(240, 240, Image.SCALE_FAST);

        label.setIcon(new ImageIcon(imagen));

        return label;
    }
}