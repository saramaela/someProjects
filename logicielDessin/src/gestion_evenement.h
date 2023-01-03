#ifndef __GESTION_EVENEMENTS_H__
#define __GESTION_EVENEMENTS_H__

#include"liste_figure.h"
#include"dessin.h"
#include"couleur.h"
#include <SDL2/SDL.h>

void gestion_evenements(int * close,  SDL_Renderer * renduAffichage, liste_figure_t * listeFig);
void liste_evenement();
int *raccourci_Point(SDL_Event evenement, int n);
void rendu_fenetre(SDL_Renderer * rendu, liste_figure_t * listeFig);
couleur_t couleur_point(SDL_Event evenement);


#endif
