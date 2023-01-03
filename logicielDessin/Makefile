#Projet L2 Structures Algébriques 2022
#Auteur(e): Romain Andres
#valgrind make test      l'allocation mémo à revoir, essayez des copies
CFLAGS	=	-ggdb3 -O0 --std=c99 -Wall -lm `sdl2-config --libs --cflags` -lSDL2_image 

FIGURES	=	src/figures/point.c\
			src/figures/segment.c\
			src/figures/rectangle.c\
			src/figures/polygone.c\
			src/figures/cercle.c\
			src/figures/triangle.c\

all: main.c src/liste_figure.c src/couleur.c src/gestion_evenement.c src/dessin.c $(FIGURES)
	@echo "Compilation en cours"
	@gcc main.c src/liste_figure.c src/dessin.c src/couleur.c src/gestion_evenement.c $(FIGURES) -o projet $(CFLAGS)

test_couleur: tests/test_couleur.c src/couleur.c
	@gcc tests/test_couleur.c src/couleur.c -o tests/test_couleur
	@./tests/test_couleur
	@rm tests/test_couleur

test_point: tests/test_point.c src/figures/point.c src/couleur.c
	@gcc tests/test_point.c src/figures/point.c src/couleur.c -o tests/test_point $(CFLAGS)
	@./tests/test_point
	@rm tests/test_point

test_segment: tests/test_segment.c src/figures/segment.c src/couleur.c
	@gcc tests/test_segment.c src/figures/segment.c src/couleur.c src/figures/point.c -o tests/test_segment $(CFLAGS)
	@./tests/test_segment
	@rm tests/test_segment

test_cercle: tests/test_cercle.c src/figures/point.c src/couleur.c src/figures/cercle.c
	@gcc tests/test_cercle.c src/figures/cercle.c src/couleur.c src/figures/point.c -o tests/test_cercle $(CFLAGS)
	@./tests/test_cercle
	@rm tests/test_cercle

test_rectangle: tests/test_rectangle.c src/figures/point.c src/couleur.c src/figures/rectangle.c src/figures/segment.c
	@gcc tests/test_rectangle.c src/figures/rectangle.c src/couleur.c src/figures/point.c src/figures/segment.c -o tests/test_rectangle $(CFLAGS)
	@./tests/test_rectangle
	@rm tests/test_rectangle

test_polygone: tests/test_polygone.c src/figures/point.c src/couleur.c src/figures/polygone.c src/figures/segment.c
	@gcc tests/test_polygone.c src/couleur.c src/figures/point.c src/figures/polygone.c src/figures/segment.c -o tests/test_polygone $(CFLAGS)
	@./tests/test_polygone
	@rm tests/test_polygone

test_triangle: tests/test_triangle.c src/figures/point.c src/couleur.c src/figures/triangle.c src/figures/segment.c
	@gcc tests/test_triangle.c src/couleur.c src/figures/point.c src/figures/triangle.c src/figures/segment.c -o tests/test_triangle $(CFLAGS)
	@./tests/test_triangle
	@rm tests/test_triangle

test: test_couleur test_point test_segment test_triangle test_cercle test_rectangle test_polygone
