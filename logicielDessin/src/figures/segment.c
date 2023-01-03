#include "segment.h"
#include "point.h"

 /**
 * @brief Fonction qui permet de détruire un segment
 * @param segment 
 */
void detruire_segment(void * segment) {
  segment_t * s = segment;
  detruire_point(s->debut);
  detruire_point(s->fin);
  free(s);
}

/**
 * @brief Retourne le signe de v
 * @param v 
 * @return int 
 */
int sgn(int v) {
  if (v < 0) return -1;
  if (v > 0) return 1;
  return 0;
}

 /**
 * @brief Fonction qui permet d'afficher un segment
 * @param render 
 * @param segment 
 */
void afficher_segment(SDL_Renderer *render, void * segment) {

  segment_t * s = segment;
  point_t * deb = s->debut;
  point_t * fin = s->fin;

  unsigned int x1 = deb->x;
  unsigned int y1 = deb->y;
  unsigned int x2 = fin->x;
  unsigned int y2 = fin->y;

  unsigned int x = x1;
  unsigned int y = y1;

  int s1 = sgn(x2-x1);
  int s2 = sgn(y2-y1);

  float dx = abs(x2 -x1);
  float dy = abs(y2 - y1);

  int interchange = 0;
  float tmp = 0;

  if(dy > dx) {
    tmp = dx;
    dx = dy;
    dy = tmp;
    interchange = 1;
  }
  else {
    interchange = 0;
  }

  int e = 2*dy - dx;
  int a = 2*dy;
  int b = (2*dy) - (2*dx);

  point_t * p = creer_point(x, y, s->couleur);
  afficher_point(render, p);

  for(float i = 1; i < dx; i++) {

    if(e < 0) {
      if(interchange == 1)
        y = y + s2;
      else
        x = x + s1;
      e = e + a;
    }
    else {
      y = y +s2;
      x = x +s1;
      e = e + b;
    }
    point_t * p1 = creer_point(x, y, s->couleur);
    afficher_point(render, p1);
  }
}

 /**
 * @brief Fonction qui permet de créer un segment
 * @param debut 
 * @param fin 
 * @param couleur 
 * @return segment_t* 
 */
segment_t * creer_segment(point_t *debut, point_t *fin, couleur_t couleur){
  segment_t * s = malloc(sizeof(segment_t));
  s->detruire = detruire_segment;
  s->afficher = afficher_segment;
  s->couleur = couleur;
  s->debut = debut;
  s->fin = fin;
  return s;
}


