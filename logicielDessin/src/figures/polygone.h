/**
Projet L2 Structures Algebrique 2022
Auteure . Marta Boshkovska
**/
#ifndef __POLYGONE_H__
#define __POLYGONE_H__
#include <SDL2/SDL.h>
#include "point.h"
#include "segment.h"

/**
 * @brief Structure qui permet de representer un polygone
 */
typedef struct polygone_s {
    segment_t ** listeSegments;
    int nbSegment;
    couleur_t couleur;
    void (*detruire)(void *);
    void (*afficher)(SDL_Renderer *, void *);
}polygone_t;

void detruire_polygone(void * polygone);
void afficher_polygone(SDL_Renderer * rendu, void * polygone);
polygone_t * creer_polygone(point_t * pointA, point_t * pointB, couleur_t couleur);
void addSegment(polygone_t *p, point_t * p2);
void endSegment(polygone_t *p);

#endif