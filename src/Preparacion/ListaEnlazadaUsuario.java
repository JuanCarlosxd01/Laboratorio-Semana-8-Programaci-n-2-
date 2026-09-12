
package Preparacion;


public class ListaEnlazadaUsuario {
    private NodoUser cabeza;
    private int cuenta;
    
    public ListaEnlazadaUsuario(){
        this.cabeza = null;
        this.cuenta=0;
    }
    
     public void insertar(User nuevoUsuario) throws NombreInvalidoExcepcion {
        if (nuevoUsuario == null || nuevoUsuario.getNombreUsr() == null
                || nuevoUsuario.getNombreUsr().trim().isEmpty()) {
            throw new NombreInvalidoExcepcion("El nombre de usuario no puede estar vacío.");
        }
        if (existeUser(nuevoUsuario.getNombreUsr())) {
            throw new NombreInvalidoExcepcion("Ya existe un usuario con ese nombre.");
        }
 
        NodoUser nuevoNodo = new NodoUser(nuevoUsuario);
 
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoUser actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        cuenta++;
    }
 
    
    public User buscarPorNombre(String nombre){
        if(nombre==null){
            return null;
        }
        NodoUser actual = cabeza;
        while (actual != null){
            if(actual.getDato().getNombreUsr().equals(nombre)){
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    private boolean existeUser(String nombre){
        return buscarPorNombre(nombre) !=null;
    }
    
    public User autenticar(String nombreUsuario, String password) {
        User usuario = buscarPorNombre(nombreUsuario);
        if (usuario != null && usuario.validarContra(password)) {
            return usuario;
        }
        return null;
    }
    public boolean eliminar(String nombreUsuario) {
        if (cabeza == null || nombreUsuario == null) {
            return false;
        }
 
        if (cabeza.getDato().getNombreUsr().equalsIgnoreCase(nombreUsuario)) {
            cabeza = cabeza.getSiguiente();
            cuenta--;
            return true;
        }
 
        NodoUser anterior = cabeza;
        NodoUser actual = cabeza.getSiguiente();
 
        while (actual != null) {
            if (actual.getDato().getNombreUsr().equalsIgnoreCase(nombreUsuario)) {
                anterior.setSiguiente(actual.getSiguiente());
                cuenta--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
        
    }
    public User[] listarTodos() {
        User[] usuarios = new User[cuenta];
        NodoUser actual = cabeza;
        int i = 0;
        while (actual != null) {
            usuarios[i] = actual.getDato();
            actual = actual.getSiguiente();
            i++;
        }
        return usuarios;
    }
    public int contar() {
        return cuenta;
    }
    public boolean estaVacia() {
        return cabeza == null;
    }
}


