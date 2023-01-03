/**
Projet L2 Structures Algebrique 2022
Auteure . Marta Boshkovska
**/
#ifndef __SEGMENT_H__
#define __SEGMENT_H__
#include <SDL2/SDL.h>
#include "point.h"
#include "../couleur.h"
#include <math.h>

/**
 * @brief Strcture qui permet de representer un segment
 */
typedef struct segment_s {
    point_t *debut;
    point_t *fin;
    couleur_t couleur;
    void (*detruire)(void *);
    void (*afficher)(SDL_Renderer *, void *);
}segment_t;

void afficher_segment(SDL_Renderer *render, void * segment);
void detruire_segment(void * segment);
segment_t * creer_segment(point_t *debut, point_t *fin, couleur_t couleur);

#endif