/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memoria;

/**
 *
 * @author diego
 */
public final class Entrenador {
    public record Existencia(Objeto objeto, int cantidad) {}

    private final String nombre;

    private final ListaEnlazada<Pokemon> equipo = new ListaEnlazada<>();
    private final ListaEnlazada<Existencia> inventario = new ListaEnlazada<>();

    private int activo = -1;

    public Entrenador(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }

        this.nombre = nombre;

        inventario.insertar(new Existencia(Objeto.POCION, 2));
        inventario.insertar(new Existencia(Objeto.SUPERPOCION, 2));
        inventario.insertar(new Existencia(Objeto.ANTIDOTO, 2));
        inventario.insertar(new Existencia(Objeto.ANTIPARALIZADOR, 2));
    }

    public String getNombre() {
        return nombre;
    }

    public int getIndiceActivo() {
        return activo;
    }

    public int contar() {
        return equipo.contar();
    }

    public Pokemon getPokemon(int indice) {
        return equipo.obtener(indice).copiar();
    }

    public Pokemon getActivo() {
        return activo < 0 ? null : getPokemon(activo);
    }

    public void agregar(Pokemon pokemon) {
        if (pokemon == null) {
            throw new IllegalArgumentException("Pokémon nulo");
        }

        equipo.insertar(pokemon.copiar());

        if (activo < 0 && !pokemon.estaDerrotado()) {
            activo = equipo.contar() - 1;
        }
    }

    public Pokemon buscar(String nombre) {
        Pokemon encontrado = equipo.buscar(
                p -> p.getNombre().equalsIgnoreCase(nombre)
        );

        return encontrado == null ? null : encontrado.copiar();
    }

    public void eliminar(int indice) {
        equipo.eliminar(indice);

        if (indice == activo) {
            activo = siguienteDisponible();
        } else if (indice < activo) {
            activo--;
        }
    }

    public void modificar(int indice, Pokemon pokemon) {
        if (pokemon == null) {
            throw new IllegalArgumentException("Pokémon nulo");
        }

        equipo.modificar(indice, pokemon.copiar());

        if (activo < 0 || interno(activo).estaDerrotado()) {
            activo = siguienteDisponible();
        }
    }

    public int disponibles() {
        int total = 0;

        for (Pokemon pokemon : equipo) {
            if (!pokemon.estaDerrotado()) {
                total++;
            }
        }

        return total;
    }

    public int siguienteDisponible() {
        for (int i = 1; i <= equipo.contar(); i++) {
            int indice = (activo + i) % equipo.contar();

            if (!interno(indice).estaDerrotado()) {
                return indice;
            }
        }

        return -1;
    }

    public ListaEnlazada<Pokemon> getEquipo() {
        ListaEnlazada<Pokemon> copia = new ListaEnlazada<>();

        for (Pokemon pokemon : equipo) {
            copia.insertar(pokemon.copiar());
        }

        return copia;
    }

    public ListaEnlazada<Existencia> getInventario() {
        ListaEnlazada<Existencia> copia = new ListaEnlazada<>();

        for (Existencia existencia : inventario) {
            copia.insertar(existencia);
        }

        return copia;
    }

    public int cantidad(Objeto objeto) {
        Existencia existencia = inventario.buscar(
                e -> e.objeto() == objeto
        );

        return existencia == null ? 0 : existencia.cantidad();
    }

    Pokemon interno(int indice) {
        return equipo.obtener(indice);
    }

    Pokemon activoInterno() {
        return interno(activo);
    }

    void cambiar(int indice) {
        if (interno(indice).estaDerrotado()) {
            throw new IllegalArgumentException("Pokémon derrotado");
        }

        activo = indice;
    }

    void consumir(Objeto objeto) {
        for (int i = 0; i < inventario.contar(); i++) {
            Existencia existencia = inventario.obtener(i);

            if (existencia.objeto() == objeto && existencia.cantidad() > 0) {
                inventario.modificar(
                        i,
                        new Existencia(objeto, existencia.cantidad() - 1)
                );
                return;
            }
        }

        throw new IllegalArgumentException("Objeto agotado");
    }

    Entrenador copiar(boolean reiniciar) {
        Entrenador copia = new Entrenador(nombre);

        for (Pokemon pokemon : equipo) {
            copia.agregar(reiniciar ? pokemon.nuevo() : pokemon);
        }

        copia.activo = activo;

        if (!reiniciar) {
            for (int i = 0; i < inventario.contar(); i++) {
                copia.inventario.modificar(i, inventario.obtener(i));
            }
        }

        return copia;
    }
}
