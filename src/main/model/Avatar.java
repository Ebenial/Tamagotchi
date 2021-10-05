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
        this.sante = 10;
        this.bonheur = 10;
        this.faim = 8;
        this.fatigue = 8;
        this.hygiene = 8;
        this.besoins = 8;
    }

    /**
     * Construit un avatar à partir d'une sauvegarde
     * @param type - le type d'avatar
     * @param nom - le nom donné à l'avatar
     * @param sante - la santé indiquée dans la sauvegarde
     * @param bonheur - le bonheur indiqué dans la sauvegarde
     * @param faim - la faim indiquée dans la sauvegarde
     * @param fatigue - la fatigue indiquée dans la sauvegarde
     * @param hygiene - l'hygiène indiquée dans la sauvegarde
     * @param besoins - les besoins indiqués dans la sauvegarde
     */
    public Avatar(String type, String nom, int sante, int bonheur, int faim, int fatigue, int hygiene, int besoins){
        this.type = type;
        this.nom = nom;
        this.sante = sante;
        this.bonheur = bonheur;
        this.faim = faim;
        this.fatigue = fatigue;
        this.hygiene = hygiene;
        this.besoins = besoins;
    }

    /**
     * Modifie la barre de faim de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire à la faim
     */
    public void modifierFaim(int valeur){
        this.faim += valeur;
    }

    /**
     * Modifie la barre de fatigue de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire à la fatigue
     */
    public void modifierFatigue(int valeur){
        this.fatigue += valeur;
    }

    /**
     * Modifie la barre de d'hygiène de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire à l'hygiène
     */
    public void modifierHygiene(int valeur){
        this.hygiene += hygiene;
    }

    /**
     * Modifie la barre de besoins de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire aux besoins
     */
    public void modifierBesoins(int valeur){
        this.besoins += besoins;
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
