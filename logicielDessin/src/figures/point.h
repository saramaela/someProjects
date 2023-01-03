/**
Projet L2 Structures Algebrique 2022
Auteur . Marta Boshkovska
**/
#ifndef __POINT_H__
#define __POINT_H__
#include"../couleur.h"
#include <SDL2/SDL.h>

/**
 * @brief Structure qui permet de representer un point
 */
typedef struct point_s{
  void (*detruire)(void *);
  void (*afficher)(SDL_Renderer *, void *);
  unsigned int x;
  unsigned int y;
  couleur_t couleur;
}point_t;

point_t * creer_point(unsigned int x, unsigned int y, couleur_t c);
void afficher_point(SDL_Renderer * rendu, void * point);
void detruire_point(void * point);

#endif
