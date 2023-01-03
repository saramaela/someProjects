<?php

require("src/lib/ObjectFileDB.php");
require("CastingStorage.php");
require("CastingStorageStub.php");


class CastingStorageFile implements CastingStorage{
    /**Classe qui gère la base de donnée*/

    private $db;

    public function __construct($file) {
        $this->db = new ObjectFileDB($file);
    }
    public function getDB(){
        return $this->db;
    }

    public function reinit(){
        /**Fonction qui permet d'utiliser notre base de donnée initiale */
        $stub = new CastingStorageStub();
        $ancienneTab = $stub->readAll();
        $this->db->deleteAll();
        foreach($ancienneTab as $ancienID => $objet){
            $this->db->insert($objet);
        }

        
    }


    public function read($id){
        /**Renvoie les différentes informations liées à l'identifiant donnné en parametre */
        if($this->db->exists($id)){
            return $this->db->fetch($id);
        }
        return NULL;
    }

    public function readAll(){
        /**Renvoie toutes les informations liées aux identifiants */
        return  $this->db->fetchAll();
    }

    public function create(Casting $personne){
        /**Insere un nouveau personnage dans notre BD en créeant alétoirement un id  et renvoie le nouvel id*/
        return $this->db->insert($personne);
    }

    public function delete($id){
        /**Supprime l'id et l'objet auquel celui_ci est lié*/
        return $this->db->delete($id);
    }

    public function update($id, $personne){
        /**Remplace l'objet de l'id par celui donné en argument*/
        return $this->db->update($id, $personne);
    }
}


?>