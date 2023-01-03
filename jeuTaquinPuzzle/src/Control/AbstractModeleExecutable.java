package Control;

import java.util.ArrayList;

/**
 * Classe abstraite qui permet d'ecouter le model
 */
public abstract class AbstractModeleExecutable {

    private ArrayList<EcouteurModele> ecouteurs;
    /**
     * Initialise la liste des ecouteurs en une liste vide
     */
    public AbstractModeleExecutable() {
        this.ecouteurs = new ArrayList<>();
    }

    /**
     * Permet d'ajouter un écouteur
     * @param e
     */
    public void ajoutEcouteur(EcouteurModele e) {
        ecouteurs.add(e);
    }

    /**
     * Permet de retirer un écouteur
     * @param e
     */
    public void retraitEcouteur(EcouteurModele e) {
        ecouteurs.remove(e);
    }

    /**
     * Met à jour tous les écouteurs
     */
    protected void exchangeChangement() {
        for(EcouteurModele e : ecouteurs) {
            e.modeleMisAJour(this);
        }
    }
}
    

