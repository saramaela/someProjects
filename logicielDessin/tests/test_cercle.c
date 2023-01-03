/**
Projet L2 Structures Algébriques 2022
Auteur(e): Romain Andres
*/
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include"../src/figures/point.h"
#include"../src/figures/cercle.h"

#define ROUGE  "\x1B[31m"
#define VERT "\x1B[32m"
#define DEFAUT  "\x1B[0m"


int main(){
  printf("%s",ROUGE);

  point_t * p1 = creer_point(10, 10, creer_couleur(125, 0, 210));
  point_t * p2 = creer_point(14, 10, creer_couleur(125, 0, 210));
  cercle_t * cercle1 = creer_cercle(p1,12, creer_couleur(125, 0, 210));
  cercle_t * cercle2 = creer_cercle(p2,12, creer_couleur(125, 0, 210));
  assert(cercle1->centre != NULL);
  assert(cercle1->rayon == 12);
  detruire_cercle(cercle1);
  detruire_cercle(cercle2);

  printf("%stest_cercle a passé les tests avec succès\n", VERT);
  printf("%s",DEFAUT);
  return EXIT_SUCCESS;
}