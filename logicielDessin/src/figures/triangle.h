/**
Projet L2 Structures Algebrique 2022
Auteure . Marta Boshkovska
**/
#ifndef __TRIANGLE_H__
#define __TRIANGLE_H__
#include <SDL2/SDL.h>
#include "point.h"
#include "segment.h"
#include "../couleur.h"

typedef struct triangle_s {
    segment_t ** listeSegments;
    int nbSegment;
    couleur_t couleur;
    void (*detruire)(void *);
    void (*afficher)(SDL_Renderer *, void *);
}triangle_t;

void detruire_triangle(void * triangle);
void afficher_triangle(SDL_Renderer * rendu, void * triangle);
triangle_t * creer_triangle(point_t * pointA, point_t * pointB, point_t * pointC, couleur_t couleur);

#endif