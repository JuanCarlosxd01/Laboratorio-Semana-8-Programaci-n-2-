
package memoria;

public class User {
    String nombreUsr;
    String contra;
    public User(String nombreUsr, String contra){
        this.nombreUsr = nombreUsr;
        this.contra=contra;
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
    @Override
    public String toString(){
        return "Usuario: "+nombreUsr+". ";
    }
}
