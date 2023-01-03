<?php 
//require("CastingStorage.php");

class CastingStorageMySQL implements CastingStorage{
    private $pdo;
    private $data;

    public function __construct($pdo) {
        $this->pdo= $pdo;
        $this->data = array();
    }


    public function read($id){
        /**recuperer un personnage par son id */
        $requete= "SELECT * FROM WOMANKING WHERE id=:identifiant ;";
        $statement = $this->pdo->prepare($requete);
        $result = $statement->fetchAll();

        $dataTmp = array(":identifiant" => $id);
        $statement->execute($dataTmp);
        $result = $statement->fetchAll();

        if(!(empty($result))){
            return new Casting($result[0]["name"],$result[0]["role"], $result[0]["age"]);
        }
        return NULL;
    }

    public function readAll(){
        /**recupération de la BD brute */
        $requete= "SELECT * FROM WOMANKING;";
        $statement = $this->pdo->query($requete);
        $tabLignes = $statement->fetchAll();
        
        /** transformation des données brutes en array adapté*/
        foreach ($tabLignes as $ligne => $element){
            $this->data[$element[0]] = new Casting($element["name"],$element["role"],$element["age"]);
            
        }
        return $this->data;
    }

    public function generateId() {
        /**Copie de la fonction de generation de ids de ObjectFileDB.php */
        do {
            /*Génération des ids */
            $id = bin2hex(openssl_random_pseudo_bytes(2));
        }while (is_numeric($id[0]) || key_exists($id, $this->data));

        return $id;
    }

    public function create(Casting $personnage){
        /*ajouter un nouveau personnage à la BD*/

        $alea = $this->generateId(); //generation d'un nouvel id

        $requete = "INSERT INTO `WOMANKING` VALUES (:idaleatoire, :nm, :rl, :ag);";

        $statement = $this->pdo->prepare($requete);
        
        $dataTmp = array(
            ':idaleatoire' => $alea,
            ':nm' => $personnage->getName(),
            ':rl' => $personnage->getRole(),
            ':ag' => $personnage->getAge()
        );

        $statement->execute($dataTmp);
        $result = $statement->fetchAll();
        
        return $alea;
    }

    public function delete($id){
        /**Suppression d'un personnage*/

        $requete= "DELETE FROM WOMANKING WHERE id=:identifiant ;";
        $statement = $this->pdo->prepare($requete);
        $result = $statement->fetchAll();

        $dataTmp = array(":identifiant" => $id);
        $statement->execute($dataTmp);
        $result = $statement->fetchAll();

    }

    public function update($id, $personnage){
        /*ajouter un nouveau personnage à la BD*/

        $requete = "UPDATE `WOMANKING` SET  name=:nm, role=:rl, age=:ag WHERE id=:id;";

        $statement = $this->pdo->prepare($requete);
        $dataTmp = array(
            ':id' => $id,
            ':nm' => $personnage->getName(),
            ':rl' => $personnage->getRole(),
            ':ag' => $personnage->getAge()
        );

        $statement->execute($dataTmp);
        $result = $statement->fetchAll();
    }
  
}
?>
