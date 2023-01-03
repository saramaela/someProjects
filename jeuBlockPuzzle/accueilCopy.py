# -*- coding: utf-8 -*-
"""
Created on Mon Mar 15 10:22:55 2021

@author: lucgu
"""

# -*- coding: utf-8 -*-
"""
Created on Thu Feb  4 13:14:17 2021

@author: lucgu
"""

import pygame
import sys
import random
from grid import *
from interface import * 
from deuxjoueurs import *



class Accueil() : 
    
    def __init__(self):
        #attributs pour recevoir du texte (nombre de joueur)
        self.inputJoueur = pygame.Rect((200,300,350,32))
        self.Nbre_joueurs = ''
        self.active = False
        #attributs pour recevoir du texte (nombre de lignes) 
        self.inputLignes = pygame.Rect((200,350,350,32))
        self.Nbre_ligne = ''
        self.activei = False
        self.Nbre_lignes = 0
        #attribut pour le bouton entrer 
        self.entrer = pygame.Rect((320,400,100,50))
        #création de la fenetre 
        self.accueil = pygame.Surface((400,300))
        self.screen = pygame.display.set_mode((750 ,700),)#pygame.FULLSCREEN) #création d'une page 

  


    def choix_joueur(self):

        while True:
            for event in pygame.event.get():
                if event.type == pygame.QUIT: #pour tout les événements dans pygame
                    sys.exit() 
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if self.inputJoueur.collidepoint(event.pos):
                        self.active = True
                    else:
                        self.active = False
                if event.type == pygame.KEYDOWN:
                    if self.active == True :
                        if event.key == pygame.K_BACKSPACE:
                            self.Nbre_joueurs = self.Nbre_joueurs[:-1]
                        else:
                            self.Nbre_joueurs += event.unicode
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if self.inputLignes.collidepoint(event.pos):
                        self.activei = True
                    else:
                        self.activei = False
                if event.type == pygame.KEYDOWN:
                    if self.activei == True :
                        if event.key == pygame.K_BACKSPACE:
                            self.Nbre_ligne = self.Nbre_ligne[:-1]
                        else:
                            self.Nbre_ligne += event.unicode
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if self.entrer.collidepoint(event.pos):
                        if self.Nbre_joueurs ==  '1':
                            self.Nbre_lignes = int(self.Nbre_ligne)
                            Jeu(self.Nbre_lignes).fonction_principale()
                            self.Nbre_joueurs ==  ''
                            self.Nbre_lignes = 0
                        elif self.Nbre_joueurs == '2' :
                            self.Nbre_lignes = int(self.Nbre_ligne)
                            deuxJoueurs(self.Nbre_lignes).main() 
                        else:
                             self.Nbre_joueurs = ''
                             self.Nbre_ligne = ''
                             
            test = pygame.image.load("option.jpg") 
            self.screen.blit(test,(0,0))              
            #Afficher l'écran en blanc

            
            #affichage du carré vert
            self.accueil.fill((176,196,222))
            self.screen.blit(self.accueil, (175,200))
            
            #intialisation de la police Arial
            arial_font =  pygame.font.SysFont("arial", 20,)
            arial_font2 =pygame.font.SysFont("arial", 30,) 
            
            #initialisation du texte présent
            Bienvenue = arial_font2.render("Bienvenue sur Block Puzzle" ,True,(0,0,0))
            self.screen.blit(Bienvenue,(200,250))
            
            Entrer = arial_font.render("C'est parti !! ", True, (0,0,0))
            pygame.draw.rect(self.screen,(176,196,222),self.entrer) 
            self.screen.blit(Entrer, self.entrer)
            
            Aff_joueur= arial_font.render('Nombres de joueur : ' + self.Nbre_joueurs, True, (0,0,0))
            pygame.draw.rect(self.screen,(176,250,222),self.inputJoueur)
            self.screen.blit( Aff_joueur,self.inputJoueur)
            
            Aff_ligne= arial_font.render('Nombres de lignes:'+ self.Nbre_ligne , True, (0,0,0))
            pygame.draw.rect(self.screen,(176,250,222),self.inputLignes)
            self.screen.blit(  Aff_ligne,self.inputLignes)                           
                            
                             
                   
                        
                    
                    

         
            pygame.display.flip() #mettre à jour la page    
        
                  

if __name__== "__main__":
    pygame.init()
    Accueil().choix_joueur()
    pygame.quit()

