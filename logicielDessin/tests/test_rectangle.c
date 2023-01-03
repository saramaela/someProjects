/**
Projet L2 Structures Algébriques 2022
Auteur(e): Romain Andres
*/
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

#include"../src/figures/point.h"
#include"../src/figures/segment.h"
#include"../src/figures/rectangle.h"
#define ROUGE  "\x1B[31m"
#define VERT "\x1B[32m"
#define DEFAUT  "\x1B[0m"


int main(){
  printf("%s",ROUGE);

  point_t * p1 = creer_point(10, 10, creer_couleur(125, 0, 210));
  point_t * p2 = creer_point(20, 20, creer_couleur(125, 0, 210));
  rectangle_t * rect1 = creer_rectangle(p1, p2, creer_couleur(125, 0, 210));
  rectangle_t * rect2 = creer_rectangle(creer_point(p1->x, p1->y, creer_couleur(125, 0, 210)), creer_point(p2->x, p2->y, creer_couleur(125, 0, 210)), creer_couleur(125, 0, 210));
  assert(rect1->nbSegment == 4);
  assert(rect1->listeSegments != NULL);
  detruire_rectangle(rect1);
  detruire_rectangle(rect2);
  
  printf("%stest_rectangle a passé les tests avec succès\n", VERT);
  printf("%s",DEFAUT);
  return EXIT_SUCCESS;
}