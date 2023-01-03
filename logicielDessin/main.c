#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <SDL2/SDL_timer.h>
#include "src/liste_figure.h"
#include "src/figures/point.h"
#include "src/figures/segment.h"
#include "src/figures/rectangle.h"
#include "src/gestion_evenement.h"
#include "src/couleur.h"


int inialisation_SDL(SDL_Window ** fenetre, int largeur, int hauteur, SDL_Renderer ** renderer);
void fermeture_SDL(SDL_Window * fenetre, SDL_Renderer * renderer);
void gestion_evenements(int * close,  SDL_Renderer * renduAffichage, liste_figure_t * listeFig);
void rendu_fenetre(SDL_Renderer * renderer, liste_figure_t * listeFig);

/* test*/

int main(int argc, char *argv[])
{

  SDL_Window * fenetre = NULL;
  SDL_Renderer * rendu = NULL;
  liste_figure_t * listeFig = creer_liste();
  int close = inialisation_SDL(&fenetre, 1000, 1000, &rendu);
  if( close < 0 )
    return EXIT_FAILURE;
  
  while (!close) {
	  gestion_evenements(&close, rendu,listeFig);
  }

  fermeture_SDL(fenetre, rendu);
  return EXIT_SUCCESS;
}



int inialisation_SDL(SDL_Window ** fenetre, int largeur, int hauteur, SDL_Renderer ** rendu){
  liste_evenement();

  // Quitte le programme en cas d'erreur
  if (SDL_Init(SDL_INIT_EVERYTHING) != 0) {
    printf("error initializing SDL: %s\n", SDL_GetError());
    return -1;
  }
  *fenetre = SDL_CreateWindow("PROJET L2 Structures Algébriques",
			      SDL_WINDOWPOS_CENTERED,
			      SDL_WINDOWPOS_CENTERED,
			      largeur, hauteur, 0);
  *rendu = SDL_CreateRenderer(*fenetre, -1, 0);
 
  return 0;
}



void fermeture_SDL(SDL_Window * fenetre, SDL_Renderer * rendu){
  SDL_DestroyRenderer(rendu);
  SDL_DestroyWindow(fenetre);   
  SDL_Quit();
}




