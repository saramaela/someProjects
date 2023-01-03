gcc main.c src/*.c src/figures/*.c -o main `sdl2-config --libs --cflags`
make
./projet
