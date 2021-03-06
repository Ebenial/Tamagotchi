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
    private boolean canEat = true;
    private boolean canShower = true;
    private boolean canPlay = true;
    private boolean canSleep = true;





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

    /**
     * Le getter de type
     * @return le type
     */
    public String getType(){
        return this.type;
    }

    /**
     * Le getter de nom
     * @return le nom
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Le getter de sante
     * @return la sante
     */
    public int getSante(){
        return this.sante;
    }

    /**
     * Le getter de bonheur
     * @return le bonheur
     */
    public int getBonheur(){
        return this.bonheur;
    }

    /**
     *
     * Le getter de nourriture
     * @return la nourriture
     */
    public int getNourriture(){
        return this.nourriture;
    }

    /**
     * Le getter de energie
     * @return l'energie
     */
    public int getEnergie(){
        return this.energie;
    }

    /**
     * Le getter de hygiene
     * @return l'hygiene
     */
    public int getHygiene(){
        return this.hygiene;
    }

    /**
     * Le getter de divertissement
     * @return le divertissement
     */
    public int getDivertissement(){
        return this.divertissement;
    }

    //SETTERS

    /**
     * Le setter de sante
     * @param nouvelleSante
     */
    public void setSante(int nouvelleSante){
        if(nouvelleSante < 0){
            this.sante = 0;
        }else if(nouvelleSante > 10){
            this.sante = 10;
        }else{
            this.sante = nouvelleSante;
        }

        JLabel label1 =  principale.getCurrentEnvironnement().getSante();   //Récupération de l'affichage de la barre de santé
        label1.setIcon(principale.getJeu().choixBarreStats(this.sante)); //Mise à jour de l'affichage de la santé avec la nouvelle valeur
    }

    /**
     * Le setter de bonheur
     * @param nouveauBonheur
     */
    public void setBonheur(int nouveauBonheur){
        if(nouveauBonheur < 0) {
            this.bonheur = 0;
        }
        else if(nouveauBonheur > 10) {
            this.bonheur = 10;
        }
        else {
            this.bonheur = nouveauBonheur;
        }

        JLabel label2 =  principale.getCurrentEnvironnement().getBonheur();
        label2.setIcon(principale.getJeu().choixBarreStats(this.bonheur));
    }

    /**
     * Le setter de nourriture
     * @param nouvellenourriture
     */
    public void setNourriture(int nouvellenourriture){
        if(nouvellenourriture < 0) {
            this.nourriture = 0;
        }
        else if(nouvellenourriture > 10) {
            this.nourriture = 10;
        }
        else {
            this.nourriture = nouvellenourriture;
        }

        JLabel label1 =  principale.getCurrentEnvironnement().getNourriture();
        label1.setIcon(principale.getJeu().choixBarreStats(this.nourriture));
    }

    /**
     * Le setter de energie
     * @param nouvelleenergie
     */
    public void setEnergie(int nouvelleenergie){
        if(nouvelleenergie < 0) {
            this.energie = 0;
        }
        else if(nouvelleenergie > 10) {
            this.energie = 10;
        } else {
            this.energie = nouvelleenergie;
        }

        JLabel label1 =  principale.getCurrentEnvironnement().getEnergie();
        label1.setIcon(principale.getJeu().choixBarreStats(this.energie));
    }

    /**
     * Le setter de hygiene
     * @param nouvelleHygiene
     */
    public void setHygiene(int nouvelleHygiene){
        if(nouvelleHygiene < 0) {
            this.hygiene = 0;
        }
        else if(nouvelleHygiene > 10) {
            this.hygiene = 10;
        }
        else {
            this.hygiene = nouvelleHygiene;
        }

        JLabel label1 =  principale.getCurrentEnvironnement().getHygiene();
        label1.setIcon(principale.getJeu().choixBarreStats(this.hygiene));

    }

    /**
     * Le setter de divertissement
     * @param nouveauxdivertissement
     */
    public void setDivertissement(int nouveauxdivertissement){
        if(nouveauxdivertissement < 0) {
            this.divertissement = 0;
        }
        else if(nouveauxdivertissement > 10) {
            this.divertissement = 10;
        }
        else {
            this.divertissement = nouveauxdivertissement;
        }

        JLabel label3 =  principale.getCurrentEnvironnement().getDivertissement();
        label3.setIcon(principale.getJeu().choixBarreStats(this.divertissement));
    }

    /**
     * Le setter de principale
     * @param principale
     */
    public void setPrincipale(FenetrePrincipale principale) {
        this.principale = principale;
    }

    /**
     * Le setter de canEat
     * @param bool
     */
    public void setCanEat(boolean bool) {
        this.canEat = bool;
    }

    /**
     * Le setter de canShower
     * @param bool
     */
    public void setCanShower(boolean bool) {
        this.canShower = bool;
    }

    /**
     * Le setter de canPlay
     * @param bool
     */
    public void setCanPlay(boolean bool) {
        this.canPlay = bool;
    }

    /**
     * Le setter de canSleep
     * @param bool
     */
    public void setCanSleep(boolean bool) {
        this.canSleep = bool;
    }

    /**
     * Le setter de canEat
     * @return canEat
     */
    public boolean getCanEat() {
        return canEat;
    }

    /**
     * Le getter de canPlay
     * @return canPlay
     */
    public boolean getCanPlay() {
        return canPlay;
    }

    /**
     * le getter de canShower
     * @return canShower
     */
    public boolean getCanShower() {
        return canShower;
    }

    /**
     * le getter de canSleep
     * @return canSleep
     */
    public boolean getCanSleep() {
        return canSleep;
    }
}
