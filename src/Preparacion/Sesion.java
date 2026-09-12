package Preparacion;
import memoria.Entrenador;
import memoria.Batalla;

public final class Sesion {
    private static final Sesion ahora = new Sesion();

    private final ListaEnlazadaUsuario usuarios = new ListaEnlazadaUsuario();
    private User usuarioActual;
    private Entrenador entrenadorActual;
    private Entrenador rivalActual;
    private Batalla batallaActual;

    private Sesion() {}

    public static Sesion getInstancia() {
        return ahora;
    }

    public ListaEnlazadaUsuario getUsuarios() {
        return usuarios;
    }

    public User getUsuarioActual() {
        return usuarioActual;
    }

    public void iniciarSesion(User usuario) {
        this.usuarioActual = usuario;
        this.entrenadorActual = usuario.getEntrenador();
    }

    public Entrenador getEntrenadorActual() {
        return entrenadorActual;
    }

    public void setRivalActual(Entrenador rival) {
        this.rivalActual = rival;
    }

    public Entrenador getRivalActual() {
        return rivalActual;
    }

    public void setBatallaActual(Batalla batalla) {
        this.batallaActual = batalla;
    }

    public Batalla getBatallaActual() {
        return batallaActual;
    }
}
