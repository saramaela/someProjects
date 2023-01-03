/**
Projet L2 Structures Algébriques 2022
Auteur(e): Romain Andres
*/
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include"../src/figures/point.h"


#define ROUGE  "\x1B[31m"
#define VERT "\x1B[32m"
#define DEFAUT  "\x1B[0m"

int main(){
  printf("%s",ROUGE);

  point_t * p1 = creer_point(10, 12, creer_couleur(125, 0, 210));
  point_t * p2 = creer_point(10, 12, creer_couleur(125, 0, 210));
  assert( p1->x = 10);
  assert( p1->x = 12);
  assert( p1->detruire !=NULL );
  assert( p1->afficher !=NULL );
  assert(&p1 != &p2);
  p1->detruire(p1);
  p2->detruire(p2);

  printf("%stest_point a passé les tests avec succès\n", VERT);
  printf("%s",DEFAUT);
  return EXIT_SUCCESS;
}
