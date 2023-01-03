/**
Projet L2 Structures Algebrique 2022
Auteure . Marta Boshkovska
**/
#ifndef __CERCLE_H__
#define __CERCLE_H__
#include <SDL2/SDL.h>
#include "point.h"

typedef struct cercle_s {
    point_t * centre;
    unsigned int rayon;
    couleur_t couleur;
    void (*detruire)(void *);
    void (*afficher)(SDL_Renderer *, void *);
}cercle_t;

void detruire_cercle(void * cercle);
cercle_t * creer_cercle(point_t * centre, unsigned int rayon, couleur_t couleur);
void afficher_cercle(SDL_Renderer *render, void * cercle);

#endif