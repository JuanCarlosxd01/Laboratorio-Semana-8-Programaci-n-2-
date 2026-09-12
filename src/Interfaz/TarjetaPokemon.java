
package interfaz;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TarjetaPokemon extends JPanel {

    private String nombre;
    private String tipo;
    private int id;

    private JLabel lblImagen;
    private JLabel lblNombre;
    private JLabel lblTipo;

    private boolean seleccionado;

    public TarjetaPokemon(int id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;

        configurar();
        crearContenido();
    }

    private void configurar() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(175, 190));
        setBackground(new Color(250, 252, 247));
        setBorder(new LineBorder(new Color(71, 96, 82), 3));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cambiarSeleccion();
            }
        });
    }

    private void crearContenido() {
        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        cargarImagen();

        JPanel informacion = new JPanel();
        informacion.setOpaque(false);
        informacion.setLayout(new BoxLayout(informacion, BoxLayout.Y_AXIS));

        lblNombre = new JLabel(nombre.toUpperCase());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblTipo = new JLabel(tipo);
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTipo.setForeground(new Color(65, 85, 75));
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);

        informacion.add(lblNombre);
        informacion.add(lblTipo);
        informacion.add(Box.createVerticalStrut(7));

        add(lblImagen, BorderLayout.CENTER);
        add(informacion, BorderLayout.SOUTH);
    }

    private void cargarImagen() {
        String ruta = "/imagenes/pokemon/" + id + ".png";
        URL recurso = getClass().getResource(ruta);

        if (recurso == null) {
            lblImagen.setText("Sin imagen");
            return;
        }

        ImageIcon original = new ImageIcon(recurso);

        Image imagen = original.getImage().getScaledInstance(110, 110, Image.SCALE_FAST);

        lblImagen.setIcon(new ImageIcon(imagen));
    }

    private void cambiarSeleccion() {
        seleccionado = !seleccionado;

        if (seleccionado) {
            setBorder(new LineBorder(new Color(232, 178, 37), 5));
            setBackground(new Color(255, 247, 209));
        } else {
            setBorder(new LineBorder(new Color(71, 96, 82), 3));
            setBackground(new Color(250, 252, 247));
        }
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public String getNombrePokemon() {
        return nombre;
    }

    public int getIdPokemon() {
        return id;
    }
}
