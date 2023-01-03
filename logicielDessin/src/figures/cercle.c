#include "segment.h"
#include "point.h"
#include "cercle.h"

/**
 * Fonction detruire cercle
 * @ensure free the cercle
 * @param cercle 
 */
void detruire_cercle(void * cercle) {
  cercle_t * c = cercle;
  detruire_point(c->centre);
  free(c);
}

/**
 * @brief Fonction qui créer un cercle
 * @require rayon doit être un entier positif
 * @param centre 
 * @param rayon 
 * @param couleur 
 * @return cercle_t* 
 */
cercle_t * creer_cercle(point_t * centre, unsigned int rayon, couleur_t couleur){
  cercle_t * c = malloc(sizeof(cercle_t));
  c->detruire = detruire_cercle;
  c->afficher = afficher_cercle;
  c->centre = centre;
  c->rayon = rayon;
  c->couleur = couleur;
  return c;
}

 /**
 * @brief  Fonction qui sert à dessiner les cercles
 * @param render 
 * @param cercle 
 */
void afficher_cercle(SDL_Renderer *render, void * cercle) {
    cercle_t * c = cercle;
    point_t * centre = c->centre;
    couleur_t couleur = c->couleur;
    unsigned int y = c->rayon;
    unsigned int x = 0;
    int m = 3 - 2 * y;
    unsigned int  xc = centre->x;
    unsigned int  yc = centre->y;

    while(x <= y) {
        point_t * p1 = creer_point(x+xc,y+yc,couleur);
        point_t * p2 = creer_point(x+xc,-y+yc,couleur);
        point_t * p3 = creer_point(-x+xc,-y+yc,couleur);
        point_t * p4 = creer_point(-x+xc,y+yc,couleur);
        point_t * p5 = creer_point(y+xc,x+yc,couleur);
        point_t * p6 = creer_point(y+xc,-x+yc,couleur);
        point_t * p7 = creer_point(-y+xc,-x+yc,couleur);
        point_t * p8 = creer_point(-y+xc,x+yc,couleur);
        
        afficher_point(render,p1);
        afficher_point(render,p2);
        afficher_point(render,p3);
        afficher_point(render,p4);
        afficher_point(render,p5);
        afficher_point(render,p6);
        afficher_point(render,p7);
        afficher_point(render,p8);
        
        if (m > 0) {
            y = y-1;
            m = m + 4 *(x - y) + 10;
        }
        else {
          m = m + 4 * x + 6;
        }
        x = x + 1;
       
    }
}





    


