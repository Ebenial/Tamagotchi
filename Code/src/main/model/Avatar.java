package main.model;

/**
 * Créé un avatar
 */
public class Avatar {
    
    private String type;
    private String nom;
    private int sante;
    private int bonheur;

    private int nourriture;
    private int energie;
    private int hygiene;
    private int divertissement;




    /**
     * Construit un avatar
     * @param type - le type d'avatar à créer
     * @param nom - le nom donné à l'avatar
     */
    public Avatar(String type, String nom){
        this.type = type;
        this.nom = nom;
        this.sante = 10;
        this.bonheur = 9;
        this.nourriture = 8;
        this.energie = 7;
        this.hygiene = 6;
        this.divertissement = 5;
    }

    /**
     * Construit un avatar à partir d'une sauvegarde
     * @param type - le type d'avatar
     * @param nom - le nom donné à l'avatar
     * @param sante - la santé indiquée dans la sauvegarde
     * @param bonheur - le bonheur indiqué dans la sauvegarde
     * @param nourriture - la nourriture indiquée dans la sauvegarde
     * @param energie - la energie indiquée dans la sauvegarde
     * @param hygiene - l'hygiène indiquée dans la sauvegarde
     * @param divertissement - les divertissement indiqués dans la sauvegarde
     */
    public Avatar(String type, String nom, int sante, int bonheur, int nourriture, int energie, int hygiene, int divertissement){
        this.type = type;
        this.nom = nom;
        this.sante = sante;
        this.bonheur = bonheur;
        this.nourriture = nourriture;
        this.energie = energie;
        this.hygiene = hygiene;
        this.divertissement = divertissement;
    }

 
    /**
     * Modifie la barre de santé de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire à la santé
     */
    public void modifierSante(int valeur){
        if(this.sante > 0){
            this.sante += valeur;
        }
    }

    /**
     * Modifie la barre de bonheur de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire au bonheur
     */
    public void modifierBonheur(int valeur){
        if(this.bonheur > 0){
            this.bonheur += valeur;
        }
    }

    /**
     * Modifie la barre de nourriture de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire à la nourriture
     */
    public void modifierNourriture(int valeur){
        if(this.nourriture > 0){
            this.nourriture += valeur;
        }
    }

    /**
     * Modifie la barre de energie de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire à la energie
     */
    public void modifierEnergie(int valeur){
        if(this.energie > 0){
            this.energie += valeur;
        }
    }

    /**
     * Modifie la barre de d'hygiène de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire à l'hygiène
     */
    public void modifierHygiene(int valeur){
        if(this.hygiene > 0){
            this.hygiene += valeur;
        }
    }

    /**
     * Modifie la barre de divertissement de l'avatar
     * @param valeur - la valeur à ajouter ou soustraire aux divertissement
     */
    public void modifierDivertissement(int valeur){
        if(this.divertissement > 0){
            this.divertissement += valeur;
        }
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

    public int getNourriture(){
        return this.nourriture;
    }

    public int getEnergie(){
        return this.energie;
    }

    public int getHygiene(){
        return this.hygiene;
    }

    public int getDivertissement(){
        return this.divertissement;
    }

    //SETTERS

    public void setSante(int nouvelleSante){
        if(nouvelleSante < 0){
            this.sante = 0;
        }else if(nouvelleSante > 10){
            this.sante = 10;
        }else{
            this.sante = nouvelleSante;
        }
    }

    public void setBonheur(int nouveauBonheur){
        this.bonheur = nouveauBonheur;
    }
    
    public void setNourriture(int nouvellenourriture){
        this.nourriture = nouvellenourriture;
    }

    public void setEnergie(int nouvelleenergie){
        this.energie = nouvelleenergie;
    }

    public void setHygiene(int nouvelleHygiene){
        this.hygiene = nouvelleHygiene;
    }

    public void setDivertissement(int nouveauxdivertissement){
        this.divertissement = nouveauxdivertissement;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
