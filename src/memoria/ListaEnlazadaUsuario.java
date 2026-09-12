
package memoria;


public class ListaEnlazadaUsuario {
    private NodoUser cabeza;
    private int cuenta;
    
    public ListaEnlazadaUsuario(){
        this.cabeza = null;
        this.cuenta=0;
    }
    
    public void insertar(User nuevoUser){
        if(nuevoUser==null||nuevoUser.getNombreUsr()==null){
        /// excepcion aqui
        }
          
    
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
    
}
