package sio.projetbuffteauv3.entities;

import java.util.Date;

public class Demande {
    private String matiere;
    private String sousmatiere;
    private  int id;
    private String date;

    public Demande(String matiere, String sousmatiere, int id, String date){
        this.matiere = matiere;
        this.sousmatiere = sousmatiere;
        this.id = id;
        this.date = date;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getSousmatiere() {
        return sousmatiere;
    }

    public void setSousmatiere(String sousmatiere) {
        this.sousmatiere = sousmatiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
