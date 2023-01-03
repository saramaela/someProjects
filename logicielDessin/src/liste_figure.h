#ifndef __LISTE_FIGURE_H__
#define __LISTE_FIGURE_H__
#include "../SDL2/SDL.h"

/**
 * @brief Structure qui permet de representer une figure
 */
typedef struct figure_s{
  void (*detruire)(void *);
  void (*afficher)(SDL_Renderer *, void *);
}figure_t;

/**
 * @brief Structure qui permet de representer un maillon
 */
typedef struct maillon_figure_s{
  figure_t * figure;
  struct maillon_figure_s * suivant;
}maillon_figure_t;

/**
 * @brief Structure qui permet de representer une liste de figures
 */
typedef struct liste_figure_s{
  int taille;
  maillon_figure_t * premier;
  maillon_figure_t * dernier;
}liste_figure_t;


/**
   Cette fonction initialise un maillon d'une liste de figures.
   @param figure est un pointeur vers une figure
   @return un pointeur vers un maillon d'une liste de figures.
*/
maillon_figure_t * creer_maillon(void * figure);

/**
   Libère l'espace mémoire réservé par un maillon et la figure qu'il contient.
   @param m est un pointeur sur un maillon d'une liste de figure.
*/
void detruire_maillon(maillon_figure_t * m);

/**
   Cette fonction initialise une liste de figures, vide.
   @return un pointeur vers une liste vide.
*/
liste_figure_t * creer_liste();

/**
   Libère l'espace mémoire utilisée par une liste, les maillons et les figures qu'elle contient.
   @param l est un pointeur vers la liste à libérer.
*/
void detruire_liste(liste_figure_t * l);

/**
   Vérifie si la liste passée en entrée est vide.
   @param l est un pointeur vers la liste à tester.
   @return 1 si la liste est vide, 0 sinon.
 */
int liste_est_vide(liste_figure_t * l);

/**
   Renvoie le nombre de maillon contenu dans la liste.
   @param l est un poitneur vers une liste de figures
   @return le nombre de maillon contenu dans la liste.
*/
int liste_taille(liste_figure_t * l);


/**
   Crée un maillon pour stocker la figure passée en argument et l'insère au début de la liste de figures
   @param l est un pointeur vers une liste de figures
   @param figure est un pointeur vers une figure.
 */
void liste_inserer_debut(liste_figure_t * l, void * figure);

/**
   Crée un maillon pour stocker la figure passée en argument et l'insère à la fin de la liste de figures
   @param l est un pointeur vers une liste de figures
   @param figure est un pointeur vers une figure.
*/
void liste_inserer_fin(liste_figure_t * l, void * figure);

/**
   Extrait un maillon du début d'une liste de figure
   @param l est un pointeur vers une liste de figures.
   @return un pointeur vers le maillon d'une liste de figures.
*/
maillon_figure_t * liste_extraire_debut(liste_figure_t * l);


#endif
