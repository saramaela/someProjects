<?php

//require("Casting.php");
//require("CastingStorage.php");

/** Classe qui implemente l'interface CastingStorage*/

class CastingStorageStub implements CastingStorage{
    
    public $castingTab;
    
    public function __construct() {
        $this->castingTab = array(
            'davis' => new Casting('Viola Davis', 'Nanisca', 57),
            'boyega' => new Casting('John Boyega', 'Roi Ghezo', 30),
            'lynch' => new Casting('Lashana Lynch', 'Izogie', 34),
            'mbedu' => new Casting('Thuso Mbedu', 'Nawi', 31),
            'kidjo' => new Casting('Angelique Kidjo', 'la Meunon', 62),
            'bolger' => new Casting('Jordan Bolger', 'Malik', 28),
            'lawson' => new Casting('Jayme Lawson', 'Shante', 25)
        );
    }

    public function read($id){
        if(array_key_exists($id, $this->castingTab)){
            return $this->castingTab[$id];
        }
        return NULL;
    }

    public function readAll(){
        return  $this->castingTab;
    }

    

    public function create(Casting $personnage){
    //     $newId = strtolower(substr($personnage->getName(),0,4));
        
    //     $this->castingTab[$newId] = $personnage;
    //     return $newId;
        
    }

    public function delete($id){
        //     $newId = strtolower(substr($personnage->getName(),0,4));
            
        //     $this->castingTab[$newId] = $personnage;
        //     return $newId;
            
    }


    
}
?>