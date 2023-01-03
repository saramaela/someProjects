#include"point.h"

 /**
 * @brief Fonction qui detruit un point 
 * @param point 
 */
void detruire_point(void * point){
  point_t * p = point;
  free(p);
}

 /**
 * @brief Fonction qui sert à afficher un point
 * @param rendu 
 * @param point 
 */
void afficher_point(SDL_Renderer * rendu, void * point){
  point_t * p = point;
  SDL_SetRenderDrawColor(rendu, p->couleur.rouge,
			 p->couleur.vert, p->couleur.bleu, 255);
  SDL_RenderDrawPoint(rendu, p->x, p->y);
}

 /**
 * @brief Fonction qui sert à créer un point
 * @require x et y doivent être des entiers positifs
 * @param x 
 * @param y 
 * @param c 
 * @return point_t* 
 */
point_t * creer_point(unsigned int x, unsigned int y, couleur_t c){
  point_t * p = malloc(sizeof(point_t));
  p->detruire = detruire_point;
  p->afficher = afficher_point;
  p->x = x;
  p->y = y;
  p->couleur = c;
  return p;
}
