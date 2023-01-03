<?php
require("model/CastingBuilder.php");
require("model/Casting.php");

class Controller {

    /**Controleur */
    public $view;
    public $castingTab;

    public function __construct($view, $castingTab) {
        $this->view = $view;
        $this->castingTab = $castingTab;
    }

    public function getView() {
        /*renvoie la vue */
        return $this->view;
    }

    public function getCastingTab() {
        /**renvoie l'ensemble des personnages */
        return $this->castingTab->castingTab;
    }

    public function newCasting($postValue) {
        /**crée la nouvelle instance de CastingBuilder */
        if (key_exists('currentNewCasting', $_SESSION)){
            return $_SESSION['currentNewCasting'];
        }
        return new CastingBuilder($postValue);
    }


    public function showInformation($id){
        /**Permet à la vue d'afficher dans notre squelette les differentes informations obtenues selon l'id donné*/
        if($this->castingTab->read($id) !== NULL){
            $this->view->makeCastingPage($this->castingTab->read($id)->getName(), $this->castingTab->read($id)->getRole(), $this->castingTab->read($id)->getAge(), $id);   
            $this->view->makeCastingAskModifyCasting($id);
            $this->view->makeCastingAskDeletionPage($id);
        }
        else if($this->castingTab->read($id) === NULL) {
            $this->view->makeUnknownCastingPage(); 
        }
        

    }

    public function showList(){
        /**Permet à la vue d'afficher dans notre squelette toutes les informations de notre base de donnée*/
        $this->view->makeListPage($this->castingTab->readAll());
    }

    public function saveNewCasting(array $tabPost){
        /**recupere les informations envoyées dans le formulaire par $_POST pour les ajouter dans la base de donnée*/
        //$this->view->makeDebugPage($tabPost);

        $builder = new CastingBuilder($tabPost);

        if($builder->isValid()){
            /**il existe un tableau d'erreurs */
            $_SESSION['currentNewCasting']= $builder;
            //$this->view->makeCastingCreationPage($builder);
            $this->view->displayCastingCreationFailure();
        }
        else{
            //$_SESSION['currentNewCasting']= new CastingBuilder([]);
            $this->view->displayCastingCreationSuccess($this->castingTab->create(new Casting(htmlspecialchars($builder->getData()["nom"]), htmlspecialchars($builder->getData()["role"]), htmlspecialchars($builder->getData()["age"])))); 
        }
           
    }

    public function askCastingDeletion($id){
        /**confirmation de suppression d'un personnage */
        if($this->castingTab->read($id) !== NULL){
            $this->view->makeCastingDeletionPage($id);
        }
        else{
            $this->view->makeUnknownCastingPage();
            echo "Le personnage ne peut être supprimé";
        }

    }

    public function deleteCasting($id){
        /**suppression d'un personnage */
        $this->castingTab->delete($id);
        $this->view->displayCastingDeleteSuccess($id);

    }

    public function modifyCasting($id){
        /**confirmation de modification d'un personnage */
        if($this->castingTab->read($id) !== NULL){
            $builder = CastingBuilder::castingToCastingBuilder($this->castingTab->read($id));
            $this->view->makeCastingModifiedPage($id,$builder);
        }
        else{
            $this->view->makeUnknownCastingPage();
        }
          
    }

    public function updateCasting($id, $newInfos){
        /**modification d'un personnage */
        $personne = new Casting($newInfos["nom"],$newInfos["role"],$newInfos["age"]);
        

        $builder = CastingBuilder::castingToCastingBuilder($personne);   

        if($builder->isValid()){
            /**il existe un tableau d'erreurs */
            $_SESSION['currentNewCasting']= $builder;

            $this->view->displayCastingModifyFailure($id);
        }
        else{
            $this->castingTab->update($id, $personne);
            $this->view->displayCastingModifySuccess($id); 
        }

        
        
    }

    public function dahomeyPage(){
        /**lancement de la page Dahomey */
        $this->view->makeDahomeyPage();
    }

    public function aProposPage(){
        /**lancement de la page Apropos */
        $this->view->makeAProposPage();
    }


}
?>