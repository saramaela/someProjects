#include"couleur.h"
#include<stdlib.h>

/**
 * @brief Fonction qui permet de créer une couleur
 * @param rouge 
 * @param vert 
 * @param bleu 
 * @required 0 <= rouge <= 255 &&  0 <= vert <= 255 &&  0 <= bleu <= 255
 * @return couleur_t 
 */
couleur_t creer_couleur(unsigned int rouge, unsigned int vert, unsigned int bleu){
  couleur_t resultat;
  resultat.rouge = rouge;
  resultat.vert = vert;
  resultat.bleu = bleu;
  return resultat;
}

