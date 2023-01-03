#include "triangle.h"

/**
 * @brief Fonction qui permet de detruire un triangle
 * @param triangle 
 */
void detruire_triangle(void * triangle) {
  triangle_t * t = triangle;

  for(int i = 0; i < t->nbSegment; i++) {
      void * s = t->listeSegments[i];
      detruire_segment(s);
  }
  free(t->listeSegments);
  free(t);
}

/**
 * @brief Fonction qui permet d'afficher un triangle
 * @param rendu 
 * @param triangle 
 */
void afficher_triangle(SDL_Renderer * rendu, void * triangle){
  triangle_t * t = triangle;
  for(int i = 0; i < t->nbSegment; i++) {
      void * s = t->listeSegments[i];
      afficher_segment(rendu,s);
  }
}

/**
 * @brief Fonction qui permet de créer un triangle 
 * @param pointA 
 * @param pointB 
 * @param pointC 
 * @param couleur 
 * @return triangle_t* 
 */
triangle_t * creer_triangle(point_t * pointA, point_t * pointB, point_t * pointC, couleur_t couleur){
  triangle_t * t = malloc(sizeof(triangle_t));
  t->nbSegment = 3;
  t->couleur = couleur;
  t->detruire = detruire_triangle;
  t->afficher = afficher_triangle;

  void * tmpA = creer_point(pointA->x,pointA->y,pointA->couleur);
  void * tmpB = creer_point(pointB->x,pointB->y,pointB->couleur);
  void * tmpC = creer_point(pointC->x,pointC->y,pointC->couleur);
  
  segment_t * s1 = creer_segment(pointA, pointB, t->couleur);
  segment_t * s2 = creer_segment(tmpB, pointC, t->couleur);
  segment_t * s3 = creer_segment(tmpC, tmpA, t->couleur);

  segment_t ** tab = malloc(sizeof(triangle_t *)*3);
  tab[0] = s1;
  tab[1] = s2;
  tab[2] = s3;
  t->listeSegments = tab;
  return t;
}

