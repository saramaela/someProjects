/**
Projet L2 Structures Algébriques 2022
Auteur(e):
*/
#ifndef __FICHIERS_IMAGES_H__
#define __FICHIERS_IMAGES_H__
#include "liste_figure.h"

//void sauvegarder_image(char * chemin, bitmap_t * b);
//int charger_image(char * chemin, bitmap_t * b);


void sauvegarder_liste_figures(char * chemin, liste_figure_t * l);
int charger_liste_figures(char * chemin, liste_figure_t * l);

#endif 
