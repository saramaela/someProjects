<?php
class View {
    /**Cette classe récupère les données du casting et les prend comme élement à mettre dans le php/html*/

    public $title;
    public $image;
    public $imageAmazones;
    public $headTitle;
    public $contentRole;
    public $contentAge;
    public $r1;
    public $dateActuelle;
    public $menu;
    public $idPerso;
    public $feedback;
    public $form;
    public $synopsis;

    public function __construct($routeur, $feedback) {
        $this->title = null;
        $this->headTitle = null;
        $this->contentRole = null;
        $this->contentAge = null;
        $this->idPerso = null;
        $this->r1 = $routeur;
        $this->feedback = $feedback;
        $this->form = null;
        $this->image = null;
        $this->imageAmazones = null;
        $this->dateActuelle =  new \Datetime();
        $this->menu = array(
                "Accueil" => "https://dev-22009614.users.info.unicaen.fr/2022/TW4/tp6/womanKing.php",

                "Ajouter un personnage" => "{$this->r1->getCastingCreationURL()}",

                "La liste des personnages" => "{$this->r1->getCastingListeURL()}",

                "Découvrir le Dahomey" => "{$this->r1->getDahomeyDiscoverPage()}",

                "À propos" => "{$this->r1->getAProposPage()}"

        );
    }

    public function getTitle() {
        return $this->title;
    }

    public function getIdPerso() {
        return $this->idPerso;
    }

    public function getcontentRole() {
        return $this->contentRole;
    }

    public function getcontentAge() {
        return $this->contentAge;
    }

    public function render(){
        include ("squeletteRender.php");
    }
    /**
     * METHODES - REDIRECTION
     */

    public function  displayCastingCreationSuccess($id) {
        $this->feedback = "Le personnage a bien été créé ! Opération de création réussie.";
        return $this->r1->POSTredirect($this->r1->getCastingURL($id), $this->feedback);
    }

    public function  displayCastingModifySuccess($id) {
        $this->feedback = "Le personnage a bien été modifié ! Opération de modification réussie.";
        return $this->r1->POSTredirect($this->r1->getCastingURL($id), "Le personnage a bien été modifié ! Opération de modification réussie.");
    }

    public function  displayCastingDeleteSuccess($id) {
        $this->feedback = "Le personnage a bien été supprimé ! Opération de suppression réussie.";
        return $this->r1->POSTredirect($this->r1->getCastingURL($id), $this->feedback );
    }
    public function  displayCastingModifyFailure($id) {
        $this->feedback = "Erreur dans le formulaire pour la modification du personnage !";
        return $this->r1->POSTredirect($this->r1->getCastingAskModifyURL($id), $this->feedback );
    }

    public function displayCastingCreationFailure() {  
        $this->feedback = "Erreur dans le formulaire pour la création d'un nouveau personnage !" ;   
        return $this->r1->POSTredirect($this->r1->getCastingCreationURL(), $this->feedback);
    }
    /**
     * METHODES - CONTENU DES PAGES
     */

    public function makeHomePage(){
        /**renvoie la page d'accueil */
        $this->headTitle = "Accueil";
        
        $this->title = "Bienvenue sur le site spécial Woman King";
        $this->contentRole = "A fɔn ganjí à ! Nous sommes aujourd'hui le {$this->dateActuelle->format('d/m/Y H:i:s')}";
        $this->contentAge = null;
        $this->form = null;
        $this->image = '<img src="https://dev-22009614.users.info.unicaen.fr/2022/TW4/tp6/src/images/cover.jpg" id="cover" alt="Cover of Woman King Film">';

        $this->synopsis = "The Woman King est un film sortie en fin septembre 2022, qui retrace l'histoire extraordinaire des Agojié, une unité de guerrières qui protégèrent le royaume de Dahomey au XIXème siècle en Afrique de l'Ouest. Leurs aptitudes et leur fureur n'ont jamais trouvé d'égal.

        Inspiré de faits réels, The Woman King suit le destin épique de la Générale Nanisca, qui entraîne une nouvelle génération de recrues et les prépare à la bataille contre un ennemi déterminé à détruire leur mode de vie. Il y a des causes qui méritent d'être défendues... 
        <br>
        (source:<a href='https://www.allocine.fr/film/fichefilm_gen_cfilm=263074.html'>Allocine</a>)
        <br>
        <br>
        Ici, vous aurez la possibilité de retrouver tous les personnages principaux de ce film mais aussi d'ajouter des personnages pour créer votre histoire. Vous avez également accès à une page découverte du royaume du Dahomey. Que l'aventure commence !";
        $this->imageAmazones = '<img src="https://dev-22009614.users.info.unicaen.fr/2022/TW4/tp6/src/images/amazones.jpg" alt="Image of Amazones">';

    }

    public function makeCastingPage($name, $role, $age, $id){
        /**permet l'affichage d'une fiche contenant les propriétés d'un personnage du casting */
        $this->headTitle ="Affichage du personnage";
        $this->title = "NOM À L'ETAT CIVIL: {$name}";
        $this->contentRole = "Personnage incarné: {$role}";
        $this->contentAge = "Age: {$age}";
        $this->idPerso = $id;
        $this->form = null;
        $this->image = null;
        $this->synopsis = null;
    }
    
    public function makeAProposPage(){
        /**renvoie la page à propos */
        $this->headTitle="À propos";
        $this->title = "À propos";
        $this->contentRole = "N°Etudiant : 22009614
        <br>
        Nom et prénom : Sara Salé
        <br>
        Thème : Film The Woman King
        ";
        $this->contentAge = "<h3>Réalisations de base effectuées</h3>
        <ul> 
        <li>Liste d'objets, affichables indépendamment
        <li> Création basique d'objets
        <li> Modification basique d'objets
        <li> Builders pour la manipulation d'objets
        <li> Suppression d'objets
        <li> Redirection en GET après création/modif/suppression réussie
        <li> Gestion du feedback
        <li>Redirection en GET après POST même lors des erreurs
        <li>Utilisation d'une base de données MySQL
        </ul>
        <h3>Complément choisi :</h3>
        Routage via le chemin virtuel (PATH_INFO) dans les URL plutôt qu'avec des paramètres d'URL.
        <h3>Explications diverses:</h3>
        Je n'avais pas fait de dépôts git car j'avais commencé à fonctionner sans; cela explique l'absence de commits sur mon dépot avant la date du 28 novembre. Mon travail s'est fait au fur et à mesure sur le serveur.
        <br>J'ai choisi d'organiser tout mon rendu html sur un seul squeletteRender.php car cela semblait plus facilement adaptable. Je n'ai pas pu cependant bien travailler sur l'aspect responsive design du site.";
        $this->form = null;
        $this->image = null;
        $this->synopsis =null;
        $this->imageAmazones = null;
    }

    public function makeDahomeyPage(){
        /**permet l'affichage de la page spécial Dahomey */
        $this->headTitle="Dahomey";
        $this->title = "Dahomey : késako ?";
        $this->contentRole = "Nous sommes actuellement le {$this->dateActuelle->format('d/m/Y H:i:s')} et tu viens découvrir le Dahomey.";
        $this->contentAge = null;
        $this->image = '<img src="https://dev-22009614.users.info.unicaen.fr/2022/TW4/tp6/src/images/carteDahomey.JPEG" id="cover" alt="Maps of Dahomey Kingdom">';;
        $this->form = null;
        $this->imageAmazones = "<ul id ='ulDahomey'> <h3>Quelques liens intéressants : </h3>
        <li><a href ='https://www.universalis.fr/encyclopedie/dahomey-royaume-du/'>Universalis : DAHOMEY ROYAUME DU (XVIIIe-XIXe s.)<a/></li>
        <li><a href ='https://www.servicehistorique.sga.defense.gouv.fr/dossier-thematique/la-conquete-du-dahomey-1890-1894'>La conquête de Dahomey</li><a/></li>
        <li><a href ='https://www.dw.com/fr/les-amazones-du-royaume-de-dahomey/a-57496352'>Les amazones du royaume de Dahomey<a/></li>
        <li><a href ='https://www.rfi.fr/fr/connaissances/20210730-du-dahomey-au-b%C3%A9nin-les-dates-cl%C3%A9s'>Du Dahomey au Bénin<a/></li>
        </ul>";
        $this->synopsis = "Avant tout c'est là où a lieu l'histoire que retrace notre film. Le royaume du Dahomey — ou du Danhomè en langue fon — est un ancien royaume africain situé dans le sud de l'actuel Bénin et dont on connaît l'histoire entre le XVIIe siècle et la fin du XIXe siècle. Le Danhomè se développe sur le plateau d'Abomey(actuellement ville au sud de l'actuel Bénin) au début des années 1600 et devient une puissance régionale au XVIIIe siècle en conquérant des villes clés sur la côte Atlantique, en particulier le port de Wida ou Ouidah. Pendant la majeure partie des XVIIIe et XIXe siècles, le royaume de Danhomè se renforce : il cesse d'être tributaire du royaume d'Oyo et devient un centre de la traite transatlantique, fournissant, comme bien d'autres états de la région, de nombreux esclaves." ;
    }

    public function makeDebugPage($variable) {
        $this->headTitle = "Debug";
        $this->title = 'Debug';
        $this->content = '<pre>'.htmlspecialchars(var_export($variable, true)).'</pre>';
        $this->form = null;
        $this->imageAmazones = null;
        $this->synopsis = numfmt_get_locale;

    }

    public function makeUnknownCastingPage(){
        /**Donne les informations de la page erreur */
        $this->headTitle = "Erreur";
        $this->title = "Cet personnage n'existe pas, veuillez ressayer !";
        $this->contentRole = null;
        $this->contentAge = null;
        $this->form = null;
        $this->image = null;
        $this->synopsis = null;
        $this->imageAmazones = null;


    }

    public function makeCastingAskDeletionPage($id){
        /**permet la création d'un bouton pour une éventuelle suppression */
        $this->form .= '<form action="' . $this->r1->getCastingAskDeletionURL($id).' " method=POST>
            <br>
            <br>
            <button type=submit>Supprimer definitivement le personnage</button>
        </form>'; 
    }

    public function makeCastingDeletionPage($id){
        /**permet l'affichage d'une page où on retrouve les élements de suppressions d'un personnage*/
        $this->headTitle="Suppression";
        $this->title = null;
        $this->contentRole = null;
        $this->contentAge = null;
        $this->synopsis = null;
        $this->imageAmazones = null;
        $this->image = null;


        $this->form =  '<h4 style="color:red;"> Voulez vous réellement supprimer ce personnage ?</h4>';

        $this->form .= '<form action="' . $this->r1->getCastingDeletionURL($id).' " method=POST>
            <br>
            <br>
            <button type=submit>OUI, pour la suppression definitive du personnage</button>
        </form>';

        $this->form .= '<form action="' . $this->r1->getCastingURL($id).' " method=POST>
        <br>
        <br>
        <button type=submit>NON, je ne veux plus supprimer</button>
        </form>'; 

    }

    public function makeCastingAskModifyCasting($id){
        /**permet la création d'un bouton pour une éventuelle modification */
        $this->form .= '<form action="' . $this->r1->getCastingAskModifyURL($id).' " method=POST>
            <br>
            <br>
            <button type=submit>Modifier les informations du personnage</button>
        </form>'; 
    }


    public function makeCastingModifiedPage($id,$builder){
        /**permet l'affichage d'une page où on retrouve les élements de modification d'un personnage*/
        $this->headTitle="Modification";

        $this->title = "Modification d'un personnage";
        $this->contentRole = null;
        $this->contentAge = null;
        $this->synopsis = "Règles de modification des personnages : les noms et roles des personnages ne peuvent être des nombres; ceux-ci ne peuvent également commencer par un espace ou tout autre caractère qui ne fait pas partie de l'alphabet comme @. Les âges des personnages ne peuvent être des chaines de charactères et inférieurs à 0. Merci pour votre attention et enjoy la crétion de vos personnages";;
        $this->image = null;
        $this->imageAmazones = null;

        $tabPost = $builder->getData();
        $error = $builder->getError();
        
        /**Récuperation des données éventuelles déja présentes dans le tableau*/
        if (count($tabPost) === 0){
            $dataTmp = array(
                "nom" => "",
                "age" => "",
                "role" => ""
            );
        }else{
            $dataTmp = array(
                "nom" => $tabPost[$builder::NOM_PERSONNAGE],
                "age" => $tabPost[$builder::AGE_PERSONNAGE],
                "role" => $tabPost[$builder::ROLE_PERSONNAGE]
            );

        }
            
        $this->form = '<form action="' . $this->r1->getCastingModifiedURL($id).' " method=POST>

            <label>Nom à l\'état civil: </label>
            <input type=text pattern="^[a-zA-Z].*" name=nom value="' . $dataTmp["nom"]. '"  />
            <br>

            <label>Age: </label>
            <input type=number min="0" name=age value="'. $dataTmp["age"]. '" />
            <br>

            <label>Nom de votre personnage: </label>
            <input type=text pattern="^[a-zA-Z].*" name=role value="'. $dataTmp["role"]. '"  />
            <br>
            <br>

            <h4 style="color:red;"> Voulez vous modifier ce personnage avec les informations ci-dessus ?</h4>

            <button type=submit>OUI, modifier le personnage</button>
            </form>'; 

        if($error !== NULL){
            for($i = 0; $i < count($error); $i ++){
                $this->form .= "<p style='color:red;'>";
                $this->form .= $error[$i];
                $this->form .= "</p>";
            }
            
        }

        $this->form .= '<form action="' . $this->r1->getCastingURL($id).' " method=POST>
        <br>
        <br>
        <button type=submit>NON, annuler la modification</button>
        </form>'; 

    }

    public function makeListPage($tab){
        /**donne les informations de la page avec les listes */
        $this->headTitle = "Liste des personnages";
        $this->title = "Liste des personnages officiels et ajoutés";
        $this->contentRole = null;
        $this->contentAge = null;
        $this->synopsis = null;
        $this->image = null;
        $this->imageAmazones = null;

        
        $this->form = "<h3>Ce sont les personnages ci-dessous: </h3><ul>";

        foreach($tab as $personne => $info){
            /** liens vers les pages des membres du casting */
            
            $this->form .= "<li><h4><a href = '{$this->r1->getCastingURL($personne)}'>{$info->getName()}</a></h4></li>";


        }
        $this->form .= "</ul>";
        
    }

    public function makeCastingCreationPage($builder){
        /**crée le formulaire pour l'ajout de nouveaux personnages*/

        $this->title = "Ajout d'un nouveau personnage";
        $this->contentRole = null;
        $this->contentAge = null;
        $this->headTitle = "Ajout d'un personnage";
        $this->image = null;
        $this->imageAmazones = null;
        $this->synopsis = "Règles d'ajout des personnages : les noms et roles des personnages ne peuvent être des nombres; ceux-ci ne peuvent également commencer par un espace ou tout autre caractère qui ne fait pas partie de l'alphabet comme @. Les âges des personnages ne peuvent être des chaines de charactères et inférieurs à 0. Merci pour votre attention et enjoy la crétion de vos personnages";



        $tabPost = $builder->getData();
        $error = $builder->getError();
        
        /**Récuperation des données éventuelles déja présentes dans le tableau*/
        if (count($tabPost)=== 0){
            $dataTmp = array(
                "nom" => "",
                "age" => "",
                "role" => ""
            );
        }else{
            $dataTmp = array(
                "nom" => $tabPost[$builder::NOM_PERSONNAGE],
                "age" => $tabPost[$builder::AGE_PERSONNAGE],
                "role" => $tabPost[$builder::ROLE_PERSONNAGE]
            );

        }
            
        $this->form = '<form action="'. $this->r1->getCastingSaveURL() .' " method=POST>

            <label>Nom à l\'état civil: </label>
            <input type=text pattern="^[a-zA-Z].*" name=nom value="' . $dataTmp["nom"]. '"  />
            <br>

            <label>Age: </label>
            <input type=number min="0" name=age value="'. $dataTmp["age"]. '" />
            <br>

            <label>Nom de votre personnage: </label>
            <input type=text pattern="^[a-zA-Z].*" name=role value="'. $dataTmp["role"]. '"  />
            <br>
            <br>

            <button type=submit>Envoyer !</button>
        </form>'; 

        if($error !== NULL){
            for($i = 0; $i < count($error); $i ++){
                $this->form .= "<p style='color:red;'>";
                $this->form .= $error[$i];
                $this->form .= "</p>";
            }
            
        }
    }


}
?>