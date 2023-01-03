/**
Projet L2 Structures Algébriques 2022
Auteur(e): Romain Andres
*/
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include"../src/figures/segment.h"
#include"../src/figures/point.h"


#define ROUGE  "\x1B[31m"
#define VERT "\x1B[32m"
#define DEFAUT  "\x1B[0m"


int main(){
  printf("%s",ROUGE);

  point_t * p1 = creer_point(10, 10, creer_couleur(125, 0, 210));
  point_t * p2 = creer_point(20, 20, creer_couleur(125, 0, 210));
  segment_t * s1 = creer_segment(p1, p2, creer_couleur(125, 0, 210));
  segment_t * s2 = creer_segment(creer_point(p1->x, p1->y, creer_couleur(125, 0, 210)), creer_point(p2->x, p2->y, creer_couleur(125, 0, 210)), creer_couleur(125, 0, 210));
  assert( s1->debut !=NULL );
  assert( s1->fin !=NULL );
  assert(&s1 != &s2);
  detruire_segment(s1);
  detruire_segment(s2);

  printf("%stest_segment a passé les tests avec succès\n", VERT);
  printf("%s",DEFAUT);
  return EXIT_SUCCESS;
}