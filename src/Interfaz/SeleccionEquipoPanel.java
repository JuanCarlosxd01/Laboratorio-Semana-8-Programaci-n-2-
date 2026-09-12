
package interfaz;

import Preparacion.EntrenadoresPredeterminados;
import Preparacion.Sesion;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import memoria.Catalogo;
import memoria.Entrenador;
import memoria.Pokemon;

public class SeleccionEquipoPanel extends JPanel {

    private VentanaPokemon ventana;

    private TarjetaPokemon[] tarjetas;
    private JLabel lblCantidad;

    public SeleccionEquipoPanel(VentanaPokemon ventana) {
        this.ventana = ventana;

        setLayout(new BorderLayout());

        crearEncabezado();
        crearPokemon();
        crearPie();
    }

    private void crearEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(new Color(39, 103, 76));
        encabezado.setBorder(new EmptyBorder(15, 25, 15, 25));

        JLabel titulo = new JLabel("SELECCIONA TU EQUIPO");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Elige los Pokémon que llevarás a la batalla");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(Color.WHITE);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        textos.add(titulo);
        textos.add(subtitulo);

        encabezado.add(textos, BorderLayout.WEST);

        add(encabezado, BorderLayout.NORTH);
    }

    private void crearPokemon() {
        JPanel fondo = new FondoPokemonPanel();
        fondo.setLayout(new GridBagLayout());

        JPanel catalogo = new JPanel(new GridLayout(2, 5, 15, 15));
        catalogo.setOpaque(false);
        catalogo.setBorder(new EmptyBorder(20, 20, 20, 20));

        tarjetas = new TarjetaPokemon[10];

        tarjetas[0] = new TarjetaPokemon(1, "Bulbasaur", "Planta");
        tarjetas[1] = new TarjetaPokemon(4, "Charmander", "Fuego");
        tarjetas[2] = new TarjetaPokemon(7, "Squirtle", "Agua");
        tarjetas[3] = new TarjetaPokemon(25, "Pikachu", "Eléctrico");
        tarjetas[4] = new TarjetaPokemon(37, "Vulpix", "Fuego");
        tarjetas[5] = new TarjetaPokemon(54, "Psyduck", "Agua");
        tarjetas[6] = new TarjetaPokemon(74, "Geodude", "Roca");
        tarjetas[7] = new TarjetaPokemon(92, "Gastly", "Fantasma");
        tarjetas[8] = new TarjetaPokemon(152, "Chikorita", "Planta");
        tarjetas[9] = new TarjetaPokemon(179, "Mareep", "Eléctrico");

        for (TarjetaPokemon tarjeta : tarjetas) {
            catalogo.add(tarjeta);
        }

        fondo.add(catalogo);

        add(fondo, BorderLayout.CENTER);
    }

    private void crearPie() {
        JPanel pie = new JPanel(new BorderLayout());
        pie.setBorder(new EmptyBorder(12, 20, 12, 20));

        lblCantidad = new JLabel("Pokémon seleccionados: 0");
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 15));

        JButton btnActualizar = new JButton("ACTUALIZAR");
        JButton btnBatalla = new JButton("INICIAR BATALLA");

        btnActualizar.addActionListener(e -> actualizarCantidad());
        btnBatalla.addActionListener(e -> iniciarBatalla());

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        botones.add(btnActualizar);
        botones.add(btnBatalla);

        pie.add(lblCantidad, BorderLayout.WEST);
        pie.add(botones, BorderLayout.EAST);

        add(pie, BorderLayout.SOUTH);
    }

    private void actualizarCantidad() {
        int cantidad = 0;

        for (TarjetaPokemon tarjeta : tarjetas) {
            if (tarjeta.isSeleccionado()) {
                cantidad++;
            }
        }

        lblCantidad.setText("Pokémon seleccionados: " + cantidad);
    }

    private void iniciarBatalla() {
        int cantidad = contarSeleccionados();

        if (cantidad == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona al menos un Pokémon.",
                    "Equipo vacío",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Entrenador entrenador = Sesion.getInstancia().getEntrenadorActual();

        if (entrenador == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay un entrenador con sesión iniciada.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ventana.mostrarLogin();
            return;
        }

        vaciarEquipo(entrenador);

        try {
            agregarPokemonSeleccionados(entrenador);

            Entrenador rival = EntrenadoresPredeterminados.aleatorio();

            Sesion.getInstancia().prepararBatalla(rival);

            JOptionPane.showMessageDialog(
                    this,
                    "Tu rival será " + rival.getNombre() + ".",
                    "Batalla preparada",
                    JOptionPane.INFORMATION_MESSAGE
            );

            ventana.mostrarBatalla();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo preparar la batalla: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private int contarSeleccionados() {
        int cantidad = 0;

        for (TarjetaPokemon tarjeta : tarjetas) {
            if (tarjeta.isSeleccionado()) {
                cantidad++;
            }
        }

        return cantidad;
    }

    private void vaciarEquipo(Entrenador entrenador) {
        while (entrenador.contar() > 0) {
            entrenador.eliminar(0);
        }
    }

    private void agregarPokemonSeleccionados(Entrenador entrenador) {
        for (TarjetaPokemon tarjeta : tarjetas) {
            if (tarjeta.isSeleccionado()) {
                Pokemon pokemon = Catalogo.buscar(tarjeta.getNombrePokemon());
                entrenador.agregar(pokemon);
            }
        }
    }

    public void reiniciarSeleccion() {
        for (TarjetaPokemon tarjeta : tarjetas) {
            tarjeta.setSeleccionado(false);
        }

        actualizarCantidad();
    }
}