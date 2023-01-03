<?php

/** Declaration de l'interface CastingStorage*/ 
interface CastingStorage
{
    public function read($id);
    public function readAll();
    public function create(Casting $personnage);
    public function delete($id);
}


?>