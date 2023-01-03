<?php


require("view/View.php");
require("control/Controller.php");


class Router {
    
    public $gestionDataCasting;

    public function __construct($gestionDataCasting) {
        $this->gestionDataCasting = $gestionDataCasting;
    }

    /*******************************************
    * Methodes qui renvoient les différents url*/
    
    

    public function getCastingURL($id){
        /** renvoie l'identifiant pour l'url*/
        return $_SERVER["SCRIPT_NAME"]."/{$id}";
    }
    public function getCastingListeURL(){
        /** renvoie l'identifiant pour l'url*/
        return $_SERVER["SCRIPT_NAME"]."/liste";
    }

    public function getDahomeyDiscoverPage(){
        /** page special Dahomey*/ 
        return $_SERVER["SCRIPT_NAME"]."/dahomey";
    }

    public function getAProposPage(){
        /** page à propos*/ 
        return $_SERVER["SCRIPT_NAME"]."/propos";
    }

    public function getCastingCreationURL(){
        /** renvoie l'identifiant pour l'url*/ 
        return $_SERVER["SCRIPT_NAME"]."/nouveau";
    }

    public function getCastingSaveURL(){
        /** renvoie l'identifiant pour l'url*/ 
        return $_SERVER["SCRIPT_NAME"]."/sauverNouveau";
    }

    public function getCastingAskDeletionURL($id){
        /** page supprimant effectivement le personnage*/ 
        return $_SERVER["SCRIPT_NAME"]."/{$id}/askDeletion";
    }

    public function getCastingDeletionURL($id){
        /** page demandant à l'internaute de confirmer son souhait de supprimer le personnage*/ 
        return $_SERVER["SCRIPT_NAME"]."/{$id}/deletion";
    }

    public function getCastingAskModifyURL($id){
        /** page modifiant effectivement le personnage*/ 
        return $_SERVER["SCRIPT_NAME"]."/{$id}/askModify";
    }

    public function getCastingModifiedURL($id){
        /** page demandant à l'internaute de confirmer son souhait de modifier le personnage*/ 
        return $_SERVER["SCRIPT_NAME"]."/{$id}/modify";
    }

    public function POSTredirect($url, $feedback){
        /**envoie une réponse HTTP de type 303 demandant au client de se rediriger vers l'URL passée en argument */
        $_SESSION["feedback"] = $feedback;
        header("Location: ". $url, true, 303);
        die;
    }




    public function main() {
        session_start();
        $feedback = "";

        if (key_exists("feedback", $_SESSION)){
            $feedback = $_SESSION["feedback"];
        }

		$_SESSION["feedback"] = "";
        

        $view1 = new View($this, $feedback);

        /**reinit reinitialise la BD */
        //$this->gestionDataCasting->reinit();

        $controller1 = new Controller($view1, $this->gestionDataCasting);
        

        if(!key_exists("PATH_INFO", $_SERVER)){ 
            /**renvoie la page d'accueil */
            $view1->makeHomePage();
        }
        
        else {

            $tabPathInfo = explode("/",$_SERVER["PATH_INFO"]);


            if(count($tabPathInfo)=== 2 and strlen($tabPathInfo[1])===4){
                /**renvoie la page selon l'id qui est l'identifiant d'un personnage */
                $controller1->showInformation($tabPathInfo[1]);
            }

            if($_SERVER["PATH_INFO"] == "/liste"){
                /**renvoie la liste des personnages créés */
                $controller1->showList();
            }

            if ($_SERVER["PATH_INFO"] == "/nouveau"){
                /**Formulaire d'ajout du nouveau personnage au tableau*/
                $view1->makeCastingCreationPage($controller1->newCasting($_POST));                
            }

            if ($_SERVER["PATH_INFO"] == "/dahomey"){
                /**renvoie sur la page decouverte Dahomey*/
                $controller1->dahomeyPage();
            }

            if ($_SERVER["PATH_INFO"] == "/propos"){
                /**renvoie sur la page à propos*/
                $controller1->aProposPage();
            }

            if ($_SERVER["PATH_INFO"] == "/sauverNouveau"){
                /**Ajout du nouveau personnage au tableau*/
                $controller1->saveNewCasting($_POST);
            }

            if (in_array("askDeletion", $tabPathInfo)){
                /**Confirmation de suppresion d'un personnage*/
                $view1->makeCastingDeletionPage($tabPathInfo[1]);
            }
            if (in_array("deletion", $tabPathInfo)){
                /**Suppression définitive d'un personnage */
                $controller1->deleteCasting($tabPathInfo[1]);
            }
            if (in_array("askModify", $tabPathInfo)){
                /**Page de modification d'un personnage */
                $controller1->modifyCasting($tabPathInfo[1]);
            }
            if (in_array("modify", $tabPathInfo)){
                /**Modification confirmée d'un personnage */
                $controller1->updateCasting($tabPathInfo[1], $_POST);
            }
        
        }
        
        $controller1->getView()->render();    

    }

    
}
?>