from blocs import *




class Grille():
    
    def __init__(self,n):
        self.blocs = Blocs()
        self.n = n
        self.matrix = [[0]*self.n for i in range(self.n)]
        self.blocs.blocs_disponibles = self.blocs.proposed_blocs()
        self.score = 0
        self.score2 = 0 
 

    def effacer_lignes(self):     
        for colonne in self.matrix:
            if 0 not in colonne:
                self.score += 1
                for i in range(len(self.matrix)):
                    colonne[i]=0
        for i in range(len(self.matrix)):
            j=0
            for col in self.matrix:
                if col[i] == 'X':
                    j+=1
            if j == len(self.matrix):
                for col in self.matrix:
                    col[i] = 0
                self.score += 1


    def placer (self, x, y):
    # La methode permet de placer le bloc du joueur, elle verifie si c'est possible placer ce bloc s'il n'y  a pas un autre bloc déjà en place et si le bloc ne dépasse pas la grille

        if self.blocs.bloc_du_joueur != False :
            for pos in self.blocs.bloc_du_joueur:
                pos1=x + pos[0]
                pos2=y + pos[1]
                if pos1 > (len (self.matrix) - 1) or pos1 < 0:
                    return False
                elif pos2 > (len (self.matrix) - 1) or pos2 < 0:
                    return False                
                elif  self.matrix[x + pos[0]][y + pos[1]] != 0 :
                    return False


        if self.blocs.bloc_du_joueur != False :
            for pos in self.blocs.bloc_du_joueur:
                self.matrix[x + pos[0]][y + pos[1]] = 'X'
            return True
        
    def test_bloc(self,x,y,bloc):
        m = 0  
        if bloc:
            for position in bloc:
                pos3 = x + position[0]
                pos4 = y + position[1]
                if pos3 > (len (self.matrix) - 1) or pos3 < 0:
                    m+=1
                elif pos4 > (len (self.matrix)-1) or pos4 < 0 :
                    m+=1
                elif self.matrix[pos3][pos4] != 0:
                    m+=1
            if m != 0:
                return False
            else:
                return True




            
            
    def perdue(self):
        if any(self.blocs.blocs_disponibles) != False:
            for bloc in self.blocs.blocs_disponibles:
                for i in range(len(self.matrix)):
                    #print("i=", i)
                    for j in range(i):
                        #print("j=", j)
                        if (self.test_bloc(j,i,bloc)):
                            return False
            
            return True 


            
 
#if __name__== "__main__":

 #   test = Grille(10) 
  #  print(test.blocs.blocs_disponibles)
    # test.blocs.bloc_du_joueur = test.blocs.choix_bloc_joueur()

   # print(test.matrix)
    #print(test.score_joueur())

    #print(test.blocs.bloc_du_joueur)
    #print(test.blocs.blocs_disponibles)
