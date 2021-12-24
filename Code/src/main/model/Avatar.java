package main.model;

import main.view.FenetrePrincipale;

import javax.swing.*;

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
    private FenetrePrincipale principale;




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
        this.nourriture = 10;
        this.energie = 10;
        this.hygiene = 10;
        this.divertissement = 10;
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
        System.out.println("SetSante : " + nouvelleSante);
        if(nouvelleSante < 0){
            this.sante = 0;
        }else if(nouvelleSante > 10){
            this.sante = 10;
        }else{
            this.sante = nouvelleSante;
        }
        JLabel label1 =  principale.getCurrentEnvironnement().getSante();   //Récupération de l'affichage de la barre de santé
        label1.setIcon(principale.getJeu().choixBarreStats(nouvelleSante)); //Mise à jour de l'affichage de la santé avec la nouvelle valeur
    }

    public void setBonheur(int nouveauBonheur){
        System.out.println("SetBonheur : " + nouveauBonheur);
        if(nouveauBonheur < 0) {
            this.bonheur = 0;
        }
        else if(nouveauBonheur > 10) {
            this.bonheur = 10;
        }
        else {
            this.bonheur = nouveauBonheur;
        }
        //System.out.println("bonheur : " + bonheur);
        JLabel label2 =  principale.getCurrentEnvironnement().getBonheur();
        label2.setIcon(principale.getJeu().choixBarreStats(nouveauBonheur));
    }
    
    public void setNourriture(int nouvellenourriture){
        System.out.println("SetNourriture : " + nouvellenourriture);
        if(nouvellenourriture < 0) {
            this.nourriture = 0;
        }
        else if(nouvellenourriture > 10) {
            this.nourriture = 10;
        }
        else {
            this.nourriture = nouvellenourriture;
        }

        //System.out.println("nourriture : " + nourriture);
        JLabel label1 =  principale.getCurrentEnvironnement().getNourriture();
        label1.setIcon(principale.getJeu().choixBarreStats(nouvellenourriture));
    }

    public void setEnergie(int nouvelleenergie){
        System.out.println("setEnergie : " + nouvelleenergie);
        if(nouvelleenergie < 0) {
            this.energie = 0;
        }
        else if(nouvelleenergie > 10) {
            this.energie = 10;
        } else {
            this.energie = nouvelleenergie;
        }

        //System.out.println("energie : " + energie);
        JLabel label1 =  principale.getCurrentEnvironnement().getEnergie();
        label1.setIcon(principale.getJeu().choixBarreStats(nouvelleenergie));
    }

    public void setHygiene(int nouvelleHygiene){
        System.out.println("setHygiene : " + nouvelleHygiene);
        if(nouvelleHygiene < 0) {
            this.hygiene = 0;
        }
        else if(nouvelleHygiene > 10) {
            this.hygiene = 10;
        }
        else {
            this.hygiene = nouvelleHygiene;
        }

        //System.out.println("hygiene : " + hygiene);
        JLabel label1 =  principale.getCurrentEnvironnement().getHygiene();
        label1.setIcon(principale.getJeu().choixBarreStats(nouvelleHygiene));

    }

    public void setDivertissement(int nouveauxdivertissement){
        System.out.println("setDivertissement : " + nouveauxdivertissement);
        if(nouveauxdivertissement < 0) {
            this.divertissement = 0;
        }
        else if(nouveauxdivertissement > 10) {
            this.divertissement = 10;
        }
        else {
            this.divertissement = nouveauxdivertissement;
        }

        //System.out.println("divertissement : " + divertissement);
        JLabel label3 =  principale.getCurrentEnvironnement().getDivertissement();
        label3.setIcon(principale.getJeu().choixBarreStats(nouveauxdivertissement));
    }

    public void setPrincipale(FenetrePrincipale principale) {
        this.principale = principale;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
