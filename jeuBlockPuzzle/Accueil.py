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
        self.boolNombreJoueurs = False
        #attributs pour recevoir du texte (nombre de lignes) 
        self.inputLignes = pygame.Rect((200,350,350,32))
        self.Nbre_ligne = ''
        self.boolNombreLignes = False
        #attribut pour le boutonDemarrerPartie 
        self.boutonDemarrerPartie = pygame.Rect((320,400,100,25))
        #création de la fenetre et du carré secondaire
        self.accueil = pygame.Surface((400,300))
        self.screen = pygame.display.set_mode((750 ,500))
  


    def choix_joueur(self):

        while True:
            for event in pygame.event.get():
                if event.type == pygame.QUIT: #pour tout les événements dans pygame
                    sys.exit() 
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if self.inputJoueur.collidepoint(event.pos):
                        self.boolNombreJoueurs = True
                    else:
                        self.boolNombreJoueurs = False
                if event.type == pygame.KEYDOWN:
                    if self.boolNombreJoueurs == True :
                        if event.key == pygame.K_BACKSPACE:
                            self.Nbre_joueurs = self.Nbre_joueurs[:-1]
                        else:
                            self.Nbre_joueurs += event.unicode
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if self.inputLignes.collidepoint(event.pos):
                        self.boolNombreLignes = True
                    else:
                        self.boolNombreLignes = False
                if event.type == pygame.KEYDOWN:
                    if self.boolNombreLignes == True :
                        if event.key == pygame.K_BACKSPACE:
                            self.Nbre_ligne = self.Nbre_ligne[:-1]
                        else:
                            self.Nbre_ligne += event.unicode
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if self.boutonDemarrerPartie.collidepoint(event.pos):
                        if self.Nbre_joueurs ==  '1':
                            Jeu(int(self.Nbre_ligne)).fonction_principale()
                        elif self.Nbre_joueurs == '2' :
                            deuxJoueurs(int(self.Nbre_ligne)).main() 
                        else:
                             self.Nbre_joueurs = ''
                             self.Nbre_ligne = ''
                             # TODO: Par exemple le joueur entre le nombre de joueurs mais pas le nombre de lignes et viceversa
            
            
            
            test = pygame.image.load("background.png") #insertion du background
            self.screen.blit(test,(0,0))
            self.accueil.fill((144,12,63))
            self.screen.blit(self.accueil, (175,150))
            
            #intialisation de la police Arial
            arial_font =  pygame.font.SysFont("arial", 20,)
            arial_font2 = pygame.font.SysFont("arial", 30,)
            arial_font3 = pygame.font.SysFont("arial", 14,)
            
            #initialisation du texte présent
            bienvenue = arial_font2.render("Bienvenue sur Block Puzzle" ,True,(0,0,0))
            self.screen.blit(bienvenue,(200,250))
            
            precautionJeu = arial_font3.render("*Attention la limite de joueurs est 2 et la limite de ligne pour la grille de jeu est 10 !", True, (0,0,0))
            self.screen.blit(precautionJeu, (70,50))
            
            boutonDemarrerPartie = arial_font.render("C'est parti !! ", True, (0,0,0))
            pygame.draw.rect(self.screen,(176,196,222),self.boutonDemarrerPartie) 
            self.screen.blit(boutonDemarrerPartie, self.boutonDemarrerPartie)
            
            Aff_joueur= arial_font.render('Nombres de joueur : ' + self.Nbre_joueurs, True, (0,0,0))
            pygame.draw.rect(self.screen,(176,250,222),self.inputJoueur)
            self.screen.blit( Aff_joueur,self.inputJoueur)
            
            Aff_ligne= arial_font.render('Nombres de lignes:'+ self.Nbre_ligne , True, (0,0,0))
            pygame.draw.rect(self.screen,(176,250,222),self.inputLignes)
            self.screen.blit(Aff_ligne,self.inputLignes)


            pygame.display.flip() #mettre à jour la page    
        


if __name__== "__main__":
    pygame.init()
    Accueil().choix_joueur()
    pygame.quit()

