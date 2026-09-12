
package Preparacion;

import memoria.Entrenador;
import memoria.ListaEnlazada;
import memoria.Pokemon;
import memoria.Catalogo;
import java.util.Random;

public final class EntrenadoresPredeterminados {
    private EntrenadoresPredeterminados() {}

    private static Entrenador crear(String nombre, String... pokemones) {
        Entrenador entrenador = new Entrenador(nombre);

        for (String nombrePokemon : pokemones) {
            entrenador.agregar(Catalogo.buscar(nombrePokemon));
        }

        return entrenador;
    }

    public static ListaEnlazada<Entrenador> todos() {
        ListaEnlazada<Entrenador> lista = new ListaEnlazada<>();

        lista.insertar(crear("Ash", "Pikachu", "Charmander", "Squirtle"));
        lista.insertar(crear("Misty", "Psyduck", "Squirtle", "Bulbasaur"));
        lista.insertar(crear("Brock", "Geodude", "Bulbasaur", "Chikorita"));
        lista.insertar(crear("Gary", "Charmander", "Vulpix", "Mareep"));
        lista.insertar(crear("May", "Bulbasaur", "Chikorita", "Psyduck"));
        lista.insertar(crear("Dawn", "Squirtle", "Psyduck", "Pikachu"));
        lista.insertar(crear("Paul", "Gastly", "Geodude", "Mareep"));
        lista.insertar(crear("Iris", "Vulpix", "Gastly", "Chikorita"));
        lista.insertar(crear("Cynthia", "Gastly", "Mareep", "Geodude"));
        lista.insertar(crear("Red", "Pikachu", "Charmander", "Bulbasaur"));

        return lista;
    }

    public static Entrenador aleatorio() {
        ListaEnlazada<Entrenador> lista = todos();
        Random azar = new Random();

        return lista.obtener(azar.nextInt(lista.contar()));
    }
}

