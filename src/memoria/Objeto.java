/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memoria;

/**
 *
 * @author diego
 */
public enum Objeto {
    POCION(20, Estado.Efecto.NINGUNO),
    SUPERPOCION(50, Estado.Efecto.NINGUNO),
    ANTIDOTO(0, Estado.Efecto.VENENO),
    ANTIPARALIZADOR(0, Estado.Efecto.PARALISIS);

    private final int curacion;
    private final Estado.Efecto cura;

    Objeto(int curacion, Estado.Efecto cura) {
        this.curacion = curacion;
        this.cura = cura;
    }

    public boolean sePuedeUsar(Pokemon pokemon) {
        if (pokemon == null || pokemon.estaDerrotado()) {
            return false;
        }

        return curacion > 0
                ? pokemon.getHp() < pokemon.getHpMaximo()
                : pokemon.getEstado() == cura;
    }

    int aplicar(Pokemon pokemon) {
        if (!sePuedeUsar(pokemon)) {
            throw new IllegalArgumentException("El objeto no tiene efecto");
        }

        if (curacion > 0) {
            return pokemon.curar(curacion);
        }

        pokemon.curarEstado();
        return 0;
    }
}
