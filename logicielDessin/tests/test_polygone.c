/**
Projet L2 Structures Algébriques 2022
Auteur(e): Romain Andres
*/
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include"../src/figures/segment.h"
#include"../src/figures/point.h"
#include"../src/figures/polygone.h"

#define ROUGE  "\x1B[31m"
#define VERT "\x1B[32m"
#define DEFAUT  "\x1B[0m"

int main(){
  printf("%s",ROUGE);

  point_t * p1 = creer_point(10, 10, creer_couleur(125, 0, 210));
  point_t * p2 = creer_point(20, 20, creer_couleur(125, 0, 210));
  //point_t * p3 = creer_point(13, 32, creer_couleur(122, 0, 212));
  polygone_t * poly1 = creer_polygone(p1, p2, creer_couleur(125, 0, 210));
  polygone_t * poly2 = creer_polygone(creer_point(p1->x, p1->x, creer_couleur(125, 0, 210)), creer_point(p2->x, p2->x, creer_couleur(125, 0, 210)), creer_couleur(125, 0, 210));
  addSegment(poly1, creer_point(25, 36, creer_couleur(125, 0, 210)));
  addSegment(poly1 , creer_point(58, 64, creer_couleur(125, 0, 210)));
  endSegment(poly1); 
  assert(&poly1 != &poly2);
  detruire_polygone(poly1);
  detruire_polygone(poly2);

  printf("%stest_polygone a passé les tests avec succès\n", VERT);
  printf("%s",DEFAUT);
  return EXIT_SUCCESS;
}