
package interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
        encabezado.setBackground(new Color(43, 102, 77));
        encabezado.setBorder(new EmptyBorder(18, 30, 18, 30));

        JLabel titulo = new JLabel("SELECCIONA TU EQUIPO");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Selecciona los Pokémon que deseas llevar al combate");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitulo.setForeground(new Color(220, 240, 228));

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

        JPanel catalogo = new JPanel(new GridLayout(2, 5, 16, 16));
        catalogo.setOpaque(false);
        catalogo.setBorder(new EmptyBorder(25, 25, 25, 25));

        tarjetas = new TarjetaPokemon[10];

        tarjetas[0] = new TarjetaPokemon(25, "Pikachu", "Eléctrico");
        tarjetas[1] = new TarjetaPokemon(4, "Charmander", "Fuego");
        tarjetas[2] = new TarjetaPokemon(7, "Squirtle", "Agua");
        tarjetas[3] = new TarjetaPokemon(1, "Bulbasaur", "Planta");
        tarjetas[4] = new TarjetaPokemon(37, "Vulpix", "Fuego");
        tarjetas[5] = new TarjetaPokemon(54, "Psyduck", "Agua");
        tarjetas[6] = new TarjetaPokemon(152, "Chikorita", "Planta");
        tarjetas[7] = new TarjetaPokemon(179, "Mareep", "Eléctrico");
        tarjetas[8] = new TarjetaPokemon(74, "Geodude", "Roca");
        tarjetas[9] = new TarjetaPokemon(92, "Gastly", "Fantasma");

        for (TarjetaPokemon tarjeta : tarjetas) {
            catalogo.add(tarjeta);
        }

        fondo.add(catalogo);

        add(fondo, BorderLayout.CENTER);
    }

    private void crearPie() {
        JPanel pie = new JPanel(new BorderLayout(20, 0));
        pie.setBackground(new Color(245, 248, 243));
        pie.setBorder(new EmptyBorder(15, 25, 15, 25));

        lblCantidad = new JLabel("Pokémon seleccionados: 0");
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        botones.setOpaque(false);

        JButton btnBuscar = crearBoton("BUSCAR");
        JButton btnAgregar = crearBoton("AGREGAR");
        JButton btnEliminar = crearBoton("ELIMINAR");
        JButton btnBatalla = crearBoton("INICIAR BATALLA");

        btnAgregar.addActionListener(e -> actualizarSeleccion());
        btnBuscar.addActionListener(e -> buscarPokemon());
        btnEliminar.addActionListener(e -> eliminarPokemon());
        btnBatalla.addActionListener(e -> iniciarBatalla());

        botones.add(btnBuscar);
        botones.add(btnAgregar);
        botones.add(btnEliminar);
        botones.add(btnBatalla);

        pie.add(lblCantidad, BorderLayout.WEST);
        pie.add(botones, BorderLayout.EAST);

        add(pie, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBackground(new Color(52, 118, 87));
        boton.setForeground(Color.WHITE);
        boton.setPreferredSize(new Dimension(145, 40));
        return boton;
    }

    private void actualizarSeleccion() {
        int cantidad = contarSeleccionados();
        lblCantidad.setText("Pokémon seleccionados: " + cantidad);

        JOptionPane.showMessageDialog(this, cantidad + " Pokémon agregados al equipo.");
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

    private void buscarPokemon() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del Pokémon:");

        if (nombre == null || nombre.trim().isEmpty()) {
            return;
        }

        for (TarjetaPokemon tarjeta : tarjetas) {
            if (tarjeta.getNombrePokemon().equalsIgnoreCase(nombre.trim())) {
                JOptionPane.showMessageDialog(this, "Pokémon encontrado: " + tarjeta.getNombrePokemon());
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Pokémon no encontrado.");
    }

    private void eliminarPokemon() {
        JOptionPane.showMessageDialog(this, "Esta opción se conectará con ListaEnlazada.eliminar().");
    }

    private void iniciarBatalla() {
        if (contarSeleccionados() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un Pokémon.");
            return;
        }

        ventana.mostrarBatalla();
    }
}