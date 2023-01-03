#include "rectangle.h"

 /**
 * @brief Fonction qui sert à détruire un rectangle
 * @param rectangle 
 */
void detruire_rectangle(void * rectangle) {
  rectangle_t * r = rectangle;
  for(int i = 0; i < r->nbSegment; i++) {
      void * s = r->listeSegments[i];
      detruire_segment(s);
  }
  free(r->listeSegments);
  free(r);
}

/*Une autre version (Si on n'avait pas fait les copies des points dans le creer_rectangle) que je trouve pire que l'actuelle*/
/*
Nous ne détruisons que les deux segments du rectangle, nous détruisons donc les 4 points et nous n'avons pas de double free
void detruire_rectangle(void * rectangle) {
  rectangle_t * r = rectangle;
  void * s1 = r->listeSegments[0];
  void * s2 = r->listeSegments[2];
  detruire_segment(s1);
  detruire_segment(s2);
  free(r);
}
*/

 /**
 * @brief Fonction qui sert à afficher un rectangle
 * @param rendu 
 * @param rectangle 
 */
void afficher_rectangle(SDL_Renderer * rendu, void * rectangle){
  rectangle_t * r = rectangle;
  for(int i = 0; i < r->nbSegment; i++) {
      void * s = r->listeSegments[i];
      afficher_segment(rendu,s);
  }
}

 /**
 * @brief Fonction qui sert à créer un rectangle
 * @param pointA 
 * @param pointC 
 * @param couleur 
 * @return rectangle_t* 
 */
rectangle_t * creer_rectangle(point_t * pointA, point_t * pointC, couleur_t couleur){
  rectangle_t * r = malloc(sizeof(rectangle_t));
  r->nbSegment = 4;
  r->couleur = couleur;
  r->detruire = detruire_rectangle;
  r->afficher = afficher_rectangle;

  point_t * pointB = creer_point(pointC->x,pointA->y,pointA->couleur);
  point_t * pointD = creer_point(pointA->x,pointC->y,pointA->couleur);

  void * tmpA = creer_point(pointA->x,pointA->y,pointA->couleur);
  void * tmpB = creer_point(pointC->x,pointA->y,pointA->couleur);
  void * tmpD = creer_point(pointA->x,pointC->y,pointA->couleur);
  void * tmpC = creer_point(pointC->x,pointC->y,pointC->couleur);

  segment_t * s1 = creer_segment(pointA, pointB, r->couleur);
  segment_t * s2 = creer_segment(tmpB, pointC, r->couleur);
  segment_t * s3 = creer_segment(tmpC,pointD, r->couleur);
  segment_t * s4 = creer_segment(tmpD,tmpA, r->couleur);

  segment_t ** tab = malloc(sizeof(segment_t *)*4);
  tab[0] = s1;
  tab[1] = s2;
  tab[2] = s3;
  tab[3] = s4;
  r->listeSegments = tab;
  return r;
}

