
package memoria;

import interfaz.VentanaPokemon;
import javax.swing.SwingUtilities;


public class Memoria {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPokemon().setVisible(true);
        });
    }

}
