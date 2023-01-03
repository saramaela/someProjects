#include "polygone.h"

 /**
 * @brief Fonction qui sert à détruire un polygone
 * @param polygone 
 */
void detruire_polygone(void * polygone) {
  polygone_t * p = polygone;
  for(int i = 0; i < p->nbSegment; i++) {
      void * s = p->listeSegments[i];
      detruire_segment(s);
  }
  free(p->listeSegments);
  free(p);
}

 /**
 * @brief Fonction qui sert à afficher un polygone
 * @param rendu 
 * @param polygone 
 */
void afficher_polygone(SDL_Renderer * rendu, void * polygone) {
  polygone_t * p = polygone;
  for(int i = 0; i < p->nbSegment; i++) {
      void * s = p->listeSegments[i];
      afficher_segment(rendu,s);
  }

}

 /**
 * @brief Fonction qui sert à créer un polygone, qui par defaut n'est qu'un segment entre pointA et pointB
 * @param pointA 
 * @param pointB 
 * @param couleur 
 * @return polygone_t* 
 */
polygone_t * creer_polygone(point_t * pointA, point_t * pointB, couleur_t couleur) {
  polygone_t * p = malloc(sizeof(polygone_t));
  p->detruire = detruire_polygone;
  p->afficher = afficher_polygone;
  p->couleur = couleur;
  segment_t * s1 = creer_segment(pointA,pointB,p->couleur);
  segment_t ** tab = malloc(sizeof(segment_t *));
  tab[0] = s1;
  p->listeSegments = tab;
  p->nbSegment = 1;
  
  
  return p;
}

 /**
 * @brief Fonction qui permet d'ajouter des segments au polygone
 * On ajoute dans la listeSegment du polygone p un nouveau segment
 * @require Tous les segments se doivent d'être reliés au final donc il faut choisir des coordonnées de segments viables
 * @require p != null
 * @param p
 * @param p1 
 * @param p2 
 */
void addSegment(polygone_t *p, point_t * p2) { 
  point_t *ppoly = p->listeSegments[p->nbSegment-1]->fin;
  point_t * p1 = creer_point(ppoly->x,ppoly->y,p->couleur);
  segment_t * s = creer_segment(p1,p2,p->couleur);
  segment_t ** tab = malloc(sizeof(segment_t *) * (p->nbSegment + 1));
  for (int i = 0; i < p->nbSegment; i++) {
      tab[i] = p->listeSegments[i];
  }
  tab[p->nbSegment] = s;
  p->nbSegment ++;
  free(p->listeSegments);
  p->listeSegments = tab;
}

void endSegment(polygone_t *p) {
  addSegment(p, creer_point(p->listeSegments[0]->debut->x,p->listeSegments[0]->debut->y,p->couleur));
}