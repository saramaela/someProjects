/**
Projet L2 Structures Algebrique 2022
Auteure . Marta Boshkovska
**/
#ifndef __RECTANGLE_H__
#define __RECTANGLE_H__
#include <SDL2/SDL.h>
#include "point.h"
#include "segment.h"
#include "../couleur.h"

/**
 * @brief Structure qui permet de representer un rectangle
 */
typedef struct rectangle_s {
    segment_t ** listeSegments;
    int nbSegment;
    couleur_t couleur;
    void (*detruire)(void *);
    void (*afficher)(SDL_Renderer *, void *);
}rectangle_t;

void detruire_rectangle(void * rectangle);
rectangle_t * creer_rectangle(point_t * pointA, point_t * pointC, couleur_t couleur);
void afficher_rectangle(SDL_Renderer * rendu, void * rectangle);

#endif