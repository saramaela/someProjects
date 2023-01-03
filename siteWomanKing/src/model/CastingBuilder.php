<?php

class CastingBuilder{
    /**Classe de l'objet Casting avec en attributs son nom, son espece et son age*/

    private $data;
    public $error;

    public function __construct($data) {
        $this->data = $data;
        $this->error = [];
    }

    const NOM_PERSONNAGE = "nom";
    const AGE_PERSONNAGE = "age";
    const ROLE_PERSONNAGE = "role";


    public function getData() {
        return $this->data;
    }

    public function getError() {
        return $this->error;
    }

    public static function castingToCastingBuilder(Casting $personne) {
		return new CastingBuilder(array(
			"nom" => $personne->getName(),
			"age" => $personne->getAge(),
            "role" => $personne->getRole()
		));
	}
    
    public function createCasting(){
        /**crée une nouvelle instance de Casting en utilisant l'attribut $data */
        return new Casting($this->data->self::NOM_PERSONNAGE,$this->data->self::ROLE_PERSONNAGE, $this->data->self::AGE_PERSONNAGE);
    }

    public function isValid(){
        /**vérifie que les données de l'attribut $data sont correctes et renvoie un tableau d'erreurs*/
       
        if(empty($this->data["nom"]) or !key_exists("nom", $this->data)){
            array_push($this->error, "Erreur au niveau de  la saisie du nom");
        }
        if(empty($this->data["role"]) or !key_exists("role", $this->data)){
            array_push($this->error, "Erreur au niveau de  la saisie du rôle");
        }
        if(strlen($this->data["age"]) == 0 or $this->data["age"] < 0  or !key_exists("age", $this->data)){
            array_push($this->error, "Erreur au niveau de  la saisie de l'âge");            
        }
        return $this->error;
    }
}
?>