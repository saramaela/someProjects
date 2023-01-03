def tous_les_blocs():
    return { "L" : [(0,0), (1,0), (2,0), (2,1),],"carre_2" : [(0,0),(0,1),(1,0),(1,1)],"carre_3" : [(0,0),(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2)],"colonne_3" : [(0,0),(0,1),(0,2)],"ligne_3" : [(0,0),(1,0),(2,0)], "reverse_z" :[(0,0),(0,1), (1,1), (1,2)], "q" :[(0,0),(1,0), (1,1), (1,2)], "sleeping_l" :[(0,0),(0,1), (1,1), (2,1)], "z" :[(0,0),(1,0), (1,1), (2,1)], "t" :[(0,0),(1,0), (1,1), (2,0)]}

import random


class Blocs():

    def __init__(self):
        # Cet attribut désigne la liste dees trois blocs parmis lesquels le joueur doit choisir       
        self.blocs_disponibles = [] 
        #Cet attribut désigne le bloc actuellement selectionné par le joueur
        self.bloc_du_joueur = []


# 3 listes de tuple sont choisis aléatoirement parmis le dictionnaire
    def proposed_blocs(self):
        totaux_blocs = tous_les_blocs()
        res = []
        for a in totaux_blocs.values():
            res.append(a)
        return random.choices(res, k = 3)




    
