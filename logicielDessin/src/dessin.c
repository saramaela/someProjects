/**
Projet L2 Structures Algébriques 2022
Auteur(e): Sara Salé
*/
#include <SDL2/SDL_image.h>
#include"dessin.h"

#include "figures/cercle.h"
#include "figures/segment.h"
#include "figures/polygone.h"
#include "figures/rectangle.h"
#include "figures/triangle.h"
#include "figures/point.h"

/*Ensemble de méthodes pour tracer les différentes figures existantes*/

void tracer_ligne(int x1, int y1, int x2, int y2,liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render){
	point_t * p1 = creer_point(x1, y1, colorBase);
	point_t * p2 = creer_point(x2, y2, colorBase);
	segment_t * seg = creer_segment(p1, p2, colorBase);

	liste_inserer_fin(listeFig, seg);
	afficher_segment(render, seg);
	SDL_RenderPresent(render);
}

void tracer_rectangle(int x1, int y1, int x2, int y2,liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render){
	point_t * p1 = creer_point(x1, y1, colorBase);
	point_t * p2 = creer_point(x2, y2, colorBase);
	rectangle_t * rect = creer_rectangle(p1, p2, colorBase);

	liste_inserer_fin(listeFig, rect);
	afficher_rectangle(render, rect);
	SDL_RenderPresent(render);
}

void tracer_triangle(int x1, int y1, int x2, int y2, int x3, int y3,liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render){
	point_t * p1 = creer_point(x1, y1, colorBase);
	point_t * p2 = creer_point(x2, y2, colorBase);
	point_t * p3 = creer_point(x3, y3, colorBase);
	triangle_t * triangle = creer_triangle(p1, p2, p3, colorBase);

	liste_inserer_fin(listeFig, triangle);
	afficher_triangle(render, triangle);
	SDL_RenderPresent(render);
}

void tracer_octant(int centre_x, int centre_y, unsigned int rayon,liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render){
	point_t * p1 = creer_point(centre_x, centre_y, colorBase);
	cercle_t * cercle = creer_cercle(p1, rayon, colorBase);

	liste_inserer_fin(listeFig, cercle);
	afficher_cercle(render, cercle);
	SDL_RenderPresent(render);
}

void tracer_polygone(int x1, int y1, int x2, int y2,liste_figure_t * listeFig, couleur_t colorBase,SDL_Renderer * render, int nbSegmentAjout, int * tabSegmentAjout){
	point_t * p1 = creer_point(x1, y1, colorBase);
	point_t * p2 = creer_point(x2, y2, colorBase);
	polygone_t * poly = creer_polygone(p1, p2, colorBase);
	int i = 0;
	point_t * p1Bis;

	while ( i < nbSegmentAjout){
		
		p1Bis = creer_point(tabSegmentAjout[i],tabSegmentAjout[i+1], colorBase);
		addSegment(poly,p1Bis);
		i+=2;
	}
	endSegment(poly);
	liste_inserer_fin(listeFig, poly);
	afficher_polygone(render, poly);
	SDL_RenderPresent(render);

}

