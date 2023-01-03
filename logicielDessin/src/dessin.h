/**
Projet L2 Structures Algébriques 2022
Auteur(e): Sara Salé
*/
#ifndef __DESSIN_H__
#define __DESSIN_H__

#include"couleur.h"
#include"liste_figure.h"


void tracer_ligne(int x1, int y1, int x2, int y2, liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render);
void tracer_rectangle(int x1, int y1, int x2, int y2, liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render);
void tracer_triangle(int x1, int y1, int x2, int y2, int x3, int y3, liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render);
void tracer_octant(int centre_x, int centre_y, unsigned int rayon,liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render);
void tracer_polygone(int x1, int y1, int x2, int y2,liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render, int nbSegmentAjout, int * tabSegmentAjout);


#endif
