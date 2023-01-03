# -*- coding: utf-8 -*-
"""
Created on Sun Mar 14 13:39:53 2021

@author: lucgu
"""


import pygame
import sys
import random
from grid import *
from interface import *

class deuxJoueurs():
   def __init__(self,n):
        self.grilles = Grille(n) 
        #Créer la surface de la fenêtre principale 
        self.surface_x= 1500 
        self.surface_y = 700 
        self.screen = pygame.display.set_mode((self.surface_x ,self.surface_y),)        
        self.tot = None
        self.n = n
        self.t1 = None
        self.t2 = None
        self.t3 = None
        self.t1s= [[0]*3 for i in range(3)]
        self.t2s =[[0]*3 for i in range(3)]
        self.t3s = [[0]*3 for i in range(3)]
        self.grilles.blocs.blocs_disponibles = self.grilles.blocs.proposed_blocs()
        self.m = 0



#convertion des pixels en coordonnées x,y  
   def convert(self): 
        s1 = (pygame.mouse.get_pos()[0]-20) // 50 
        t1= (pygame.mouse.get_pos()[1]-20) // 50 
        v = (s1,t1)
        return v 

#Récupère une liste de bloc_disponibles en fonction de la position de la souris        
   def carre(self):
        x,y = pygame.mouse.get_pos()
        if x >= 540 and x <= 740:
            if y >= 20 and y <= 220:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[0]
                self.grilles.blocs.blocs_disponibles[0] = False
            elif y >= 220 and y <= 420:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[1]
                self.grilles.blocs.blocs_disponibles[1] = False
            elif y >= 420 and y <= 620:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[2]
                self.grilles.blocs.blocs_disponibles[2] = False

   def carre2(self):
        x,y = pygame.mouse.get_pos()
        if x >= 1290 and x <= 1490:
            if y >= 20 and y <= 220:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[0]
                self.grilles.blocs.blocs_disponibles[0] = False
            elif y >= 220 and y <= 420:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[1]
                self.grilles.blocs.blocs_disponibles[1] = False
            elif y >= 420 and y <= 620:
                self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[2]
                self.grilles.blocs.blocs_disponibles[2] = False
 
 
    
#permet de reposer la liste de self.carre en cas de non disponibilité   
   def contrecarre(self):
        x,y = pygame.mouse.get_pos()
        if x >= 540 and x <= 740:
            if y >= 20 and y <= 220:
                return 0
            elif y >= 220 and y <= 420:
                return 1
            elif y >= 420 and y <= 620:
                return 2
            else:
                return -1
            
   def contrecarre2(self):
        x,y = pygame.mouse.get_pos()
        if x >= 1290 and x <= 1490:
            if y >= 20 and y <= 220:
                return 0
            elif y >= 220 and y <= 420:
                return 1
            elif y >= 420 and y <= 620:
                return 2
            else:
                return -1


        
            
        
#dessine les trois blocs secondaires et place les listes de tuple de bloc_disponible       
   def dessine_bloc(self,place,position,parent, point1, point2, surf_color):
        parent =  pygame.Surface((200,200))
        parent.fill(surf_color)          
        if self.grilles.blocs.blocs_disponibles[position] != False :            
            for pos in self.grilles.blocs.blocs_disponibles[position]:  
                x= 0;
                place[x + pos[0]][x + pos[1]] = 'X'
                if place[x + pos[0]][x + pos[1]] == 'X':
                    y = pygame.Surface((50,50))
                    parent.blit(y,( (pos[0])* 50, (pos[1])* 50 ))
        self.screen.blit(parent,(point1,point2))

#dessine la grille principale et place la liste de liste de self.matrix
   def dessine_matrix(self, taille, space, point1, point2, surf_color):
        space = pygame.Surface((taille*50, taille*50)) #création d'un rectangle pour la grille avec la matrix
        space.fill(surf_color)
        for x in range(0,taille*50,50):#a partir du N sélectionner au départ, créer une grille 
            pygame.draw.line(space,(255,255,255),[x,0],[x,taille*50],1)
        for y in range(0,taille*50,50):
            pygame.draw.line(space,(255,255,255),[0,y],[taille*50,y],1)

        for val in range (len(self.grilles.matrix)) :
            for i in range(len (self.grilles.matrix[val])):
                if self.grilles.matrix[val][i] == 'X':
                    x = pygame.Surface((50,50))
                    space.blit(x,(val*50,i*50))
           
        self.screen.blit(space,(point1,point2))
        
        
        
#affiche le score      
   def score(self):           
        arial_font =  pygame.font.SysFont("arial", 20,)
        Score = arial_font.render("Score : " + str(self.grilles.score), False , (0,0,0))
        self.screen.blit(Score,(50,600))
   def score2(self):           
        arial_font =  pygame.font.SysFont("arial", 20,)
        Score = arial_font.render("Score : " + str(self.grilles.score2), False , (0,0,0))
        self.screen.blit(Score,(800,600))
    
    

                    

#fonction principale avec les séries d'événements
   def main(self):
        while True:   
            self.grilles.effacer_lignes()
            self.screen.fill((240,240,240)) #Afficher l'écran en blanc
            self.dessine_matrix(self.n, self.tot, 20,20, (0,100,111))
            self.dessine_bloc(self.t1s, 0, self.t1, 540,20, (250,250,250))
            self.dessine_bloc(self.t2s, 1, self.t2,540, 220, (250,250,250))
            self.dessine_bloc(self.t3s, 2, self.t3, 540,420, (250,250,250))
            self.dessine_matrix(self.n, self.tot, 770,20, (0,100,111))
            self.dessine_bloc(self.t1s, 0, self.t1, 1290,20, (250,250,250))
            self.dessine_bloc(self.t2s, 1, self.t2,1290, 220, (250,250,250))
            self.dessine_bloc(self.t3s, 2, self.t3, 1290,420, (250,250,250))
            self.score()
            self.score2()
            for event in pygame.event.get():
                if event.type == pygame.QUIT: #pour tout les événements dans pygame
                    sys.exit() 
                if event.type == pygame.MOUSEBUTTONDOWN:
                    self.carre()
                    self.m = self.contrecarre()
                    print(self.m)
                    print( self.grilles.blocs.bloc_du_joueur)
                if event.type == pygame.MOUSEBUTTONUP: 
                    a,b = self.convert()
                    if a > len(self.grilles.matrix)-1  or  b > len(self.grilles.matrix)-1 or self.grilles.placer(a, b)== False :
                         self.grilles.blocs.bloc_du_joueur = self.grilles.blocs.blocs_disponibles[self.m] 
                    else:             
                        self.grilles.placer(a,b)
                    self.grilles.blocs.bloc_du_joueur = False
                    if not (any(self.grilles.blocs.blocs_disponibles)):
                        self.grilles.blocs.blocs_disponibles = self.grilles.blocs.proposed_blocs() 
                if event.type == pygame.MOUSEMOTION and self.grilles.blocs.bloc_du_joueur:
                    pos1,pos2 = event.pos
                    self.image = self.grilles.blocs.bloc_du_joueur
                    y = pygame.Surface((50,50))
                    for pos in self.grilles.blocs.bloc_du_joueur:
                        i = pygame.Rect((pos1,pos2),((pos[0])* 50 , (pos[1])* 50  ))    
                        self.screen.blit(y,((pos[0])*50 + pos1 ,(pos[1])*50 + pos2 ))


                    pygame.display.update(i)
                    pygame.display.flip()

                        
                else : 
                    pygame.display.flip()


if __name__== "__main__":
    pygame.init()
    deuxJoueurs(10).main()
    pygame.quit()
    
    
    
    