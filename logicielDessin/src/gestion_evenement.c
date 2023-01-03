#include"gestion_evenement.h"
#include"figures/point.h"


int r = 255;
int g = 255;
int b = 255;

/**
 * @brief Méthode événementielle principale du projet qui permet d'utiliser les raccourcis indiqués dans la console
 * @param close 
 * @param renduAffichage 
 * @param listeFig 
 */
void gestion_evenements(int * close, SDL_Renderer * renduAffichage, liste_figure_t * listeFig){
	SDL_Event event;
	int *essai, *tabSegmentAjout;
	int nbSegmentAjout;
	unsigned int rayon;
	char nomFichier_a_sauvegarder[1024];
	couleur_t couleurBase = creer_couleur(r,g,b);
   
  // Events management
	while (SDL_PollEvent(&event)) {
		switch (event.type) {
      
			case SDL_KEYUP:
				switch (event.key.keysym.sym){

					case SDLK_q:
						printf("Au revoir\n");
						*close = 1;
					break;
					
					case SDLK_p:
					/*Raccourci pour dessiner des points sur la fenêtre
					*
					*Il y a deux moyens pour le faire.
					*
					*
					*Moyen 1
					
						while(SDL_WaitEvent(&event)){
							if(event.type == SDL_MOUSEBUTTONDOWN){
								liste_inserer_fin(listeFig, creer_point(event.button.x, event.button.y, couleurBase));
								
								printf("PAS DE PB ICI\n");
								rendu_fenetre(renduAffichage, listeFig);
								break;
							}
						}
					
					
					Moyen 2*/
					essai = raccourci_Point(event, 1);
					point_t * pointdessin = creer_point(essai[0], essai[1], couleurBase);
					afficher_point(renduAffichage, pointdessin);
					liste_inserer_fin(listeFig,pointdessin);
					SDL_RenderPresent(renduAffichage);
					break;

					case SDLK_l:
					/*Raccourci pour dessiner des lignes sur la fenêtre*/
						essai = raccourci_Point(event, 2);
						tracer_ligne(essai[0], essai[1], essai[2], essai[3], listeFig,couleurBase,renduAffichage);
						free(essai);
					break;	

					case SDLK_r:
					/*Raccourci pour dessiner des rectangles sur la fenêtre*/
						essai = raccourci_Point(event, 2);
						tracer_rectangle(essai[0], essai[1], essai[2], essai[3], listeFig,couleurBase,renduAffichage);
						free(essai);
					break;

					case SDLK_v:
						/*Raccourci pour dessiner des triangles sur la fenêtre*/
						essai = raccourci_Point(event, 3);
						tracer_triangle(essai[0], essai[1], essai[2], essai[3], essai[4], essai[5],listeFig,couleurBase,renduAffichage);
						free(essai);
					break;

					case SDLK_o:
						/*Raccourci permettant de créér des cercles, il y a erreur de segmentation pour le moment*/
            printf("Entrez un rayon :\n");
						scanf("%d",&rayon);
            printf("Faites un clic sur la fenêtre.\n");
						essai = raccourci_Point(event, 1);
						
						tracer_octant(essai[0], essai[1], rayon, listeFig,couleurBase,renduAffichage);
						free(essai);
					break;

					case SDLK_y:
					/*Raccourci pour dessiner des polygones sur la fenêtre*/
					
						printf("Tracez votre premier segment !\n");
						essai = raccourci_Point(event, 2);
						printf("Combien de segments voulez-vous que votre polygone contienne?\n");
						scanf("%d", &nbSegmentAjout);
						
						printf("Pour tracer vos %d segments, il faut %d clics !\n", nbSegmentAjout, nbSegmentAjout-1);
						tabSegmentAjout = raccourci_Point(event, nbSegmentAjout-1);
						tracer_polygone(essai[0], essai[1], essai[2], essai[3], listeFig,couleurBase,renduAffichage, nbSegmentAjout, tabSegmentAjout);
						free(essai);
					break;

					case SDLK_s:
					/*Raccourci pour sauvegarder le dessin actuel*/
						
						printf("Quel nom souhaitez vous pour votre fichier\n");
						scanf("%s",nomFichier_a_sauvegarder);
						printf("Le fichier %s a bien été sauvegardé.\n", nomFichier_a_sauvegarder);
					break;	

					case SDLK_x:
					/*Raccourci pour charger un dessin ou une image*/
						printf("La taille de la liste de figures avant la suppression est %d\n", listeFig->taille);

						/* En faisant detruire_liste(listeFig), il y a une erreur de segmentation par ce que listeFig est égal à NULL. Pour éviter cette erreur, on efface peut effacer tout sauf le premier maillon. Faire par exemple:
						*
						*
						*
						while(listeFig->taille > 1){
							printf("PAS VIDE \n");
    						detruire_maillon(liste_extraire_debut(listeFig));
						}
						*/
						
						detruire_liste(listeFig);
					break;

					case SDLK_c:
						/*Raccourci pour le changement de couleur des dessins*/
						printf("DONE\n");
						printf("Vous souhaitez definir une couleur.\nLa saisie de symboles qui ne sont pas des chiffres ne fonctionne pas.\nSaisissez un nombre entre 0 et 255 pour la composante rouge\n");
						scanf("%d",&r);
						printf("Saisissez un nombre entre 0 et 255 pour la composante verte\n");
						scanf("%d",&g);
						printf("Saisissez un nombre entre 0 et 255 pour la composante bleu\n");
						scanf("%d",&b);
						if(r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255){
							printf("Il y a une erreur parmi les valeurs données. Rééssayez en appuyant de nouveau sur C.\n");
						}else{
							printf("Les données saisies sont correctes.\n");
							couleurBase = creer_couleur(r,g,b);
						}
						
					break;
				}
			break;

			case SDL_QUIT:
				printf("Au revoir\n");
				*close = 1;
			break;

			case SDL_MOUSEBUTTONDOWN:
				printf("Vous avez cliqué sur la souris :\n");
				printf("\tfenêtre : %d\n",event.button.windowID);
				printf("\tbouton : %d\n",event.button.button);
				printf("\tposition : %d;%d\n",event.button.x,event.button.y);
			break;
		}
	}
	
}

int *raccourci_Point(SDL_Event evenement, int n){
	/*Récupère les coordonnées des points pour la création des différentes figures dans un tableau 
	*
	*
	L'utilisateur entre le nombre de points qu'il souhaite obtenir*/
	int *tab;
	tab = malloc(sizeof(int)*20);
	int i = 0;
	int suivi = 0;
	while(SDL_WaitEvent(&evenement) && suivi < n){
		if(evenement.type == SDL_MOUSEBUTTONDOWN){
				printf(" IL Y A CLIC\n");
				printf("position : X -> %d; Y -> %d\n",evenement.button.x,evenement.button.y);
				tab[i] = evenement.button.x;
				int tmp = i+1;
				tab[tmp] = evenement.button.y;
				i += 2;
				suivi++;
		}
	}
	return tab;
}

/**
 * @brief Affiche les différents raccourcis clavier dans la console avec leurs utilité
 */
void liste_evenement(){
	printf("BIENVENUE DANS NOTRE LOGICIEL DE DESSIN.\nPour une utilisation optimale, utilisez les raccourcis : \n-Q : pour quitter le logiciel, \n-P : qui permet après un clic de dessiner des points sur la fenêtre, \n-C : qui permet de saisir la couleur voulue pour vos points,\n-S : pour sauvegarder le dessin actuel,\n-L : pour dessiner des segments,\n-R : pour dessiner des rectangles,\n-V : pour dessiner des triangles, \n-O : pour dessiner des cercles,\n-Y : pour dessiner des polygones,\n-X : pour charger un dessin ou une image.\n");
}

/**
 * @brief Permet de faire le rendu de la fenêtre pour afficher
 * @param rendu 
 * @param l 
 */
void rendu_fenetre(SDL_Renderer * rendu, liste_figure_t * l){
	/*Permet l'affichage des figures sur la fenêtre*/
	for(maillon_figure_t * tmp = l->premier; tmp != NULL; tmp = tmp->suivant){
		tmp->figure->afficher(rendu, tmp->figure);
	}
	SDL_RenderPresent(rendu);  
}




