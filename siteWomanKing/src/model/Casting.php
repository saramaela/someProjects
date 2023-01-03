<?php

class Casting {
    /**Classe de l'objet Casting avec en attributs son nom, son espece et son age*/

    public $name;
    public $role;
    public $age;

    public function __construct($name, $role, $age) {
        $this->name = $name;
        $this->role = $role;
        $this->age = $age;
    }

    public function getName() {
        return $this->name;
    }

    public function getRole() {
        return $this->role;
    }
    
    public function getAge() {
        return $this->age;
    }
}


?>