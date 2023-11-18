package sio.projetbuffteauv3.entities;

public class Utilisateur {
    private String email;
    private String motDePasse;
    private String role;

    public Utilisateur(String email, String motDePasse){
        this.email = email;
        this.motDePasse = motDePasse;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
