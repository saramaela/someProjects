#include"liste_figure.h"

/**
 * @brief Permet de créer un maillon
 * @param figure 
 * @return maillon_figure_t* 
 */
maillon_figure_t * creer_maillon(void * figure){
  maillon_figure_t * resultat = malloc(sizeof(maillon_figure_t));
  resultat->figure = figure;
  resultat->suivant = NULL;
  return resultat;
}

/**
 * @brief Permet de detruire un maillon
 * @param m 
 */
void detruire_maillon(maillon_figure_t * m){
  m->figure->detruire(m->figure);
  free(m);
}

/**
 * @brief Permet de créer une liste
 * @return liste_figure_t* 
 */
liste_figure_t * creer_liste(){
  liste_figure_t * resultat = malloc(sizeof(liste_figure_t));
  resultat->taille = 0;
  resultat->premier = NULL;
  resultat->dernier = NULL;
  return resultat;
}

/**
 * @brief Méthode qui indique si la liste est vide
 * @param l 
 * @return int 
 */
int liste_est_vide(liste_figure_t * l){
  return l->taille == 0;
}

/**
 * @brief Méthode qui retourne la taille d'une liste l
 * @param l 
 * @return int 
 */
int liste_taille(liste_figure_t * l){
  return l->taille;
}

/**
 * @brief Méthode qui permet de detruire une liste l
 * @param l
 */
void detruire_liste(liste_figure_t * l){
  while(!liste_est_vide(l))
    detruire_maillon(liste_extraire_debut(l));
  free(l);
}

/**
 * @brief Méthode qui permet d'inserer une figure au debut d'une liste de figures
 * @param l 
 * @param figure 
 */
void liste_inserer_debut(liste_figure_t * l, void * figure){
  maillon_figure_t * nouveau = creer_maillon(figure);
  nouveau->suivant = l->premier;
  l->premier = nouveau;
  if( l->taille == 0 )
    l->dernier = nouveau;
  l->taille++;
}

/**
 * @brief Méthode qui permet d'inserer une figure a la fin d'une liste de figures
 * @param l 
 * @param figure 
 */
void liste_inserer_fin(liste_figure_t * l, void * figure){
  maillon_figure_t * nouveau = creer_maillon(figure);
  if( l->taille == 0 ){
    l->dernier = nouveau;
    l->premier = nouveau;
  }
  else{
    l->dernier->suivant = nouveau;
    l->dernier = nouveau;
  }
  l->taille++;
}

/**
 * @brief Méthode qui permet d'avoir la premiere figure d'une liste de figures
 * @param l 
 * @return maillon_figure_t* 
 */
maillon_figure_t * liste_extraire_debut(liste_figure_t * l){
  maillon_figure_t * resultat = l->premier;
  if(l->taille > 0){
    l->premier = l->premier->suivant;
    resultat->suivant = NULL;
    l->taille--;
  }
  return resultat;
}
