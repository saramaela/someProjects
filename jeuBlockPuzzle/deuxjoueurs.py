# -*- coding: utf-8 -*-
"""
Created on Sun Mar 14 13:39:53 2021

@author: lucgu
"""


import pygame
import sys
import random
import copy

from grid import *
from interface import *

class deuxJoueurs():

    def __init__(self,n):
        self.grilles = Grille(n) 
        self.unePersonne = Jeu(n)
        #Créer la surface de la fenêtre principale 
        self.surface_x= 1200 
        self.surface_y = 500 
        self.screen = pygame.display.set_mode((self.surface_x ,self.surface_y),)        
        self.surfaceGrille = None
        self.nombreLigneJeu = n
        self.surfaceBloc1 = None
        self.surfaceBloc2 = None
        self.surfaceBloc3 = None
        self.materialisationBloc1= [[0]*3 for i in range(3)]
        self.materialisationBloc2 =[[0]*3 for i in range(3)]
        self.materialisationBloc3 = [[0]*3 for i in range(3)]
        self.grilles.blocs.blocs_disponibles = self.grilles.blocs.proposed_blocs()
        self.scoreJoueur1 = copy.deepcopy(self.grilles.score)
        self.scoreJoueur2 = copy.deepcopy(self.grilles.score)
        self.boutonRecommencer = pygame.Rect((50,450,100,35))
        self.image1 = pygame.image.load("marron.png")
        
        # self.variableDeposeOuRetourBloc = False 


    def boutonRecommencerPartie(self):
        arial_font =  pygame.font.SysFont("arial", 20,)
        entrer = arial_font.render("recommencer ", False, (250,0,0))
        pygame.draw.rect(self.screen,(176,196,222),self.boutonRecommencer)
        self.screen.blit(entrer, self.boutonRecommencer)

    def emplacementScore(self, coordonneeEmplacement1, coordonneeEmplacement2, scoreJoueur):
        arial_font =  pygame.font.SysFont("arial", 20,)
        score = arial_font.render("Score : " + str(scoreJoueur), False , (0,0,0))
        self.screen.blit(score,(coordonneeEmplacement1,coordonneeEmplacement2))


    def deposeBloc(self):
        x,y = pygame.mouse.get_pos()
        if x >= 500 and x <= 650:
            if y >= 20 and y <= 180:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[0]
                self.grilles.blocs.blocs_disponibles[0] = False
            elif y >= 180 and y <= 340:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[1]
                self.grilles.blocs.blocs_disponibles[1] = False
            elif y >= 340 and y <= 490:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[2]
                self.grilles.blocs.blocs_disponibles[2] = False
            else: 
                self.grilles.blocs.bloc_du_joueur = False

#permet de reposer la liste de self.deposeBloc en cas de non disponibilité   
    def retourneBloc(self):
        x,y = pygame.mouse.get_pos()
        if x >= 500 and x <= 650:
            if y >= 20 and y <= 170:
                self.variableDeposeOuRetourBloc = True 
                return 0
            elif y >= 180 and y <= 330:
                self.variableDeposeOuRetourBloc = True 
                return 1
            elif y >= 340 and y <= 490:
                self.variableDeposeOuRetourBloc = True 
                return 2
        else:
            self.variableDeposeOuRetourBloc = False 

#convertion des pixels en coordonnées x,y  
    def convert(self): 
        s1 = (pygame.mouse.get_pos()[0]-20) // 40 
        t1= (pygame.mouse.get_pos()[1]-20) // 40 
        v = (s1,t1)
        return v 




#fonction principale avec les séries d'événements
    def main(self):
   
        while True:   

            self.screen.fill((240,240,240)) #Afficher l'écran en blanc
            self.unePersonne.dessine_matrix(self.nombreLigneJeu, self.surfaceGrille, 20,20, (0,100,111))
            self.unePersonne.dessine_bloc(self.materialisationBloc1, 0, self.surfaceBloc1, 500, 20, (240,180,230))
            self.unePersonne.dessine_bloc(self.materialisationBloc2, 1, self.surfaceBloc2, 500, 180, (240,180,230))
            self.unePersonne.dessine_bloc(self.materialisationBloc3, 2, self.surfaceBloc3, 500, 340, (240,180,230))
            self.unePersonne.dessine_matrix(self.nombreLigneJeu, self.surfaceGrille, 770,20, (0,100,111))
            
            
            self.boutonRecommencerPartie()
            self.emplacementScore(300,450, self.scoreJoueur1)
            self.emplacementScore(800,450, self.scoreJoueur2)
            if self.grilles.perdue() :
                self.affichePerdu()
            #pour tous les événements dans pygame
            for event in pygame.event.get():
                if event.type == pygame.QUIT: 
                    sys.exit()
                if event.type == pygame.MOUSEBUTTONDOWN:
                    self.deposeBloc()

                    m = self.retourneBloc()
                if event.type == pygame.MOUSEBUTTONUP: 
                    if self.variableDeposeOuRetourBloc:
                        a,b = self.convert()
                        print("Voici a et b", a, b)
                        if (a > 9  or (a > 18 and a < 28))or  b > len(self.grilles.matrix)-1 or self.grilles.placer(a, b)== False :                          
                            self.grilles.blocs.blocs_disponibles[m] =self.grilles.blocs.bloc_du_joueur 
                            self.grilles.blocs.bloc_du_joueur = False                            
                        else :            
                            self.variableDeposeOuRetourBloc = False
                            self.grilles.placer(a,b)
 
                            self.grilles.blocs.bloc_du_joueur = False
                    if not (any(self.grilles.blocs.blocs_disponibles)): #La régénération automatique des blocs  
                        self.grilles.blocs.blocs_disponibles = self.grilles.blocs.proposed_blocs() 
                print("Voici les blocs du j", self.grilles.blocs.blocs_disponibles)
                if event.type == pygame.MOUSEMOTION and self.grilles.blocs.bloc_du_joueur:
                    pos1,pos2 = event.pos
                    
                    # self.image = self.grilles.blocs.bloc_du_joueur
                    y = pygame.Surface((40,40))
                    for pos in self.grilles.blocs.bloc_du_joueur:
                        i = pygame.Rect((pos1,pos2),((pos[0])* 40 , (pos[1])* 40  ))    
                        self.screen.blit(y,((pos[0])*40 + pos1 ,(pos[1])*40 + pos2 ))
                       


                        pygame.display.update(i)
                    pygame.display.flip()

                
            pygame.display.flip()


if __name__== "__main__":
    pygame.init()
    deuxJoueurs(10).main()
    pygame.quit()