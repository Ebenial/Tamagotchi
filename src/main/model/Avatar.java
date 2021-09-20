package main.model;

/**
 * Créé un avatar
 */
public class Avatar {
    
    private String type;
    private String nom;
    private int sante;
    private int bonheur;
    private int faim;
    private int fatigue;
    private int hygiene;
    private int besoins;

    /**
     * Construit un avatar
     * @param type - le type d'avatar à créer
     * @param nom - le nom donné à l'avatar
     */
    public Avatar(String type, String nom){
        this.type = type;
        this.nom = nom;
    }


    //GETTERS
    
    public String getType(){
        return this.type;
    }

    public String getNom(){
        return this.nom;
    }

    public int getSante(){
        return this.sante;
    }

    public int getBonheur(){
        return this.bonheur;
    }

    public int getFaim(){
        return this.faim;
    }

    public int getFatigue(){
        return this.fatigue;
    }

    public int getHygiene(){
        return this.hygiene;
    }

    public int getBesoins(){
        return this.besoins;
    }

    //SETTERS

    public void setSante(int nouvelleSante){
        this.sante = nouvelleSante;
    }

    public void setBonheur(int nouveauBonheur){
        this.bonheur = nouveauBonheur;
    }
    
    public void setFaim(int nouvelleFaim){
        this.faim = nouvelleFaim;
    }

    public void setFatigue(int nouvelleFatigue){
        this.fatigue = nouvelleFatigue;
    }

    public void setHygiene(int nouvelleHygiene){
        this.hygiene = nouvelleHygiene;
    }

    public void setBesoins(int nouveauxBesoins){
        this.besoins = nouveauxBesoins;
    }
}
