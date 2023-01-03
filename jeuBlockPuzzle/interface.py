# -*- coding: utf-8 -*-
"""
Created on Thu Feb  4 13:14:17 2021

@author: lucgu
"""

import pygame
import sys
import random
from grid import *




class Jeu() : 
    
    def __init__(self,n):
        self.grilles = Grille(n)
        #Créer la surface de la fenêtre principale 
        self.surface_x = 750 
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

        
        self.boutonRecommencer = pygame.Rect((200,450,100,35))
        self.variableDeposeOuRetourBloc = False 
        

        



#convertion des pixels en coordonnées x,y  
    def convert(self): 
        s1 = (pygame.mouse.get_pos()[0]-20) // 40 
        t1= (pygame.mouse.get_pos()[1]-20) // 40 
        v = (s1,t1)
        return v 

#Récupère une liste de bloc_disponibles en fonction de la position de la souris        
    def deposeBloc(self):
        x,y = pygame.mouse.get_pos()
        if x >= 500 and x <= 650:
            if y >= 20 and y <= 170:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[0]
                self.grilles.blocs.blocs_disponibles[0] = False
            elif y >= 180 and y <= 330:
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
            if y >= 20 and y <= 180:
                self.variableDeposeOuRetourBloc = True 
                return 0
            elif y >= 180 and y <= 340:
                self.variableDeposeOuRetourBloc = True 
                return 1
            elif y >= 340 and y <= 490:
                self.variableDeposeOuRetourBloc = True 
                return 2
        else:
            self.variableDeposeOuRetourBloc = False 
                

        
            
        
#dessine les trois blocs secondaires et place les listes de tuple de bloc_disponible       
    def dessine_bloc(self,place,position,parent, point1, point2, surf_color):
        parent =  pygame.Surface((150,150))
        parent.fill(surf_color)
        if self.grilles.blocs.blocs_disponibles[position] != False :            
            for pos in self.grilles.blocs.blocs_disponibles[position]:  
                x= 0;
                place[x + pos[0]][x + pos[1]] = 'X'
                if place[x + pos[0]][x + pos[1]] == 'X':
                    y = pygame.Surface((40,40))
                 
                    parent.blit(y,( (pos[0])* 40, (pos[1])* 40 ))
        self.screen.blit(parent,(point1,point2))

#dessine la grille principale et place la liste de liste de self.matrix
    def dessine_matrix(self, taille, space, point1, point2, surf_color):
        space = pygame.Surface((taille*40, taille*40)) #création d'un rectangle pour la grille avec la matrix
        space.fill(surf_color)
        for x in range(0,taille*40,40):#a partir du N sélectionner au départ, créer une grille 
            pygame.draw.line(space,(255,255,255),[x,0],[x,taille*40],1)
        for y in range(0,taille*40,40):
            pygame.draw.line(space,(255,255,255),[0,y],[taille*40,y],1)

        for val in range (len(self.grilles.matrix)) :
            for i in range(len (self.grilles.matrix[val])):
                if self.grilles.matrix[val][i] == 'X':
                    x = pygame.Surface((40,40))
                    space.blit(x,(val*40,i*40))
           
        self.screen.blit(space,(point1,point2))
        
        
        
#affiche le score      
    def score(self):           
        arial_font =  pygame.font.SysFont("arial", 20,)
        Score = arial_font.render("Score : " + str(self.grilles.score), False , (0,0,0))
        self.screen.blit(Score,(50,450))
        
    
    def boutonRecommencerPartie(self):
        arial_font =  pygame.font.SysFont("arial", 20,)
        Entrer = arial_font.render("recommencer ", False, (250,0,0))
        pygame.draw.rect(self.screen,(176,196,222),self.boutonRecommencer)
        self.screen.blit(Entrer, self.boutonRecommencer)
        
    def affichePerdu(self):
        arial_font =  pygame.font.SysFont("arial", 48,)
        textePerdu = arial_font.render("Vous avez perdu ", False, (0,0,0))
        carrePerdu = pygame.Rect((250,150,300,150))
        pygame.draw.rect(self.screen,(190,50,67), carrePerdu)
        self.screen.blit(textePerdu, carrePerdu)

       

                              

#fonction principale avec les séries d'événements
    def fonction_principale(self):
        while True:
            
            self.grilles.effacer_lignes()
            self.screen.fill((240,240,240)) #Afficher l'écran en blanc
            self.dessine_matrix(self.nombreLigneJeu, self.surfaceGrille, 20,20, (0,100,111))
            self.dessine_bloc(self.materialisationBloc1, 0, self.surfaceBloc1, 500, 20, (240,180,230))
            self.dessine_bloc(self.materialisationBloc2, 1, self.surfaceBloc2, 500, 180, (240,180,230))
            self.dessine_bloc(self.materialisationBloc3, 2, self.surfaceBloc3, 500, 340, (240,180,230))
            self.score()
            self.boutonRecommencerPartie()
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
                        if a > len(self.grilles.matrix)-1  or  b > len(self.grilles.matrix)-1 or self.grilles.placer(a, b)== False :                          
                            self.grilles.blocs.blocs_disponibles[m] =self.grilles.blocs.bloc_du_joueur 
                            self.grilles.blocs.bloc_du_joueur = False                            
                        else :            
                            self.variableDeposeOuRetourBloc = False
                            self.grilles.placer(a,b)
 
                            self.grilles.blocs.bloc_du_joueur = False
                    if not (any(self.grilles.blocs.blocs_disponibles)):
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

                        
                else : 
                    pygame.display.flip()
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if self.boutonRecommencer.collidepoint(event.pos):
                         Jeu(self.nombreLigneJeu).fonction_principale()


       
               
# if __name__== "__main__":
   # print("Fonction entrée")
   # pygame.init()

            
   # Jeu(10).fonction_principale()
   # pygame.quit()


