/**
Projet L2 Structures Algébriques 2022
Auteur(e): Romain Andres
*/
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

#include"../src/figures/point.h"
#include"../src/figures/segment.h"
#include"../src/figures/triangle.h"
#define ROUGE  "\x1B[31m"
#define VERT "\x1B[32m"
#define DEFAUT  "\x1B[0m"


int main(){
  printf("%s",ROUGE);

  point_t * p1 = creer_point(10, 10, creer_couleur(125, 0, 210));
  point_t * p2 = creer_point(20, 20, creer_couleur(125, 0, 210));
  point_t * p3 = creer_point(12, 20, creer_couleur(125, 0, 210));
  triangle_t * t1 = creer_triangle(p1,p2,p3,creer_couleur(125, 0, 210));
  triangle_t * t2 = creer_triangle(creer_point(43, 13, creer_couleur(125, 0, 210)),creer_point(10, 10, creer_couleur(125, 0, 210)),creer_point(10, 10, creer_couleur(125, 0, 210)),creer_couleur(125, 0, 210));
  assert(&t1 != &t2);
  detruire_triangle(t1);
  detruire_triangle(t2);
  printf("%stest_triangle a passé les tests avec succès\n", VERT);
  printf("%s",DEFAUT);
  return EXIT_SUCCESS;
}