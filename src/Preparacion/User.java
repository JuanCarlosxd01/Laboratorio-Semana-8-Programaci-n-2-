
package Preparacion;

import memoria.Entrenador;

public class User {
    String nombreUsr;
    String contra;
    private final Entrenador entrenador;

    public User(String nombreUsr, String contra){
        this.nombreUsr = nombreUsr;
        this.contra=contra;
        this.entrenador = new Entrenador(nombreUsr); 

    }

    public String getNombreUsr() {
        return nombreUsr;
    }

    public void setNombreUsr(String nombreUsr) {
        this.nombreUsr = nombreUsr;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
    public boolean validarContra(String intento){
        if(intento.equals(contra)){
            return true;
        }
        return false;
    }
     public Entrenador getEntrenador() {   
        return entrenador;
    }
    @Override
    public String toString(){
        return "Usuario: "+nombreUsr+". ";
    }
}
