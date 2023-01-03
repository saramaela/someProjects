"use strict";
	
	
//variables globales
let memo1;
let memo2;
let cible;
let nb_coup = 0;
let nb_score = 0;
let nb_paires = 0;
let compare_score = 0;

	
// obtenir la largeur et la hauteur de la zone disponible
function startMemoryGame (){

	let liste = [];
	let choix= document.getElementById("choix");
	let bouton = choix.elements.bouton;
	let nblig = choix.elements.ligne.value;
	let nbcol = choix.elements.colonne.value;
	let nbimg;
	let jeu = document.getElementById("jeu");
	let n;
	
	
	
	
	// paramétrage informations du jeu (score, ...)
	let informations = document.getElementById("infos");
	
	let info_score = document.createElement("h6");
	info_score.id = "score";
	informations.appendChild(info_score);
	
	let info_paire = document.createElement("h6");
	info_paire.id = "paires";
	informations.appendChild(info_paire);
	
	let info_coup = document.createElement("h6");
	info_coup.id = "coups";
	informations.appendChild(info_coup);
	
	let meilleur = document.getElementById("meilleur");
	let meilleur_score = document.createElement("h6");
	meilleur_score.id = "best";
	meilleur.appendChild(meilleur_score);
	
	
	
	//non-existence de parties enregistrée
	if (!(localStorage.getItem("memoire_partie"))){
		nbimg= (nbcol*nblig)/2;
		// création de la grille des images
		let data_window = window.getComputedStyle(jeu);
		let largeur_jeu = parseInt(data_window.getPropertyValue("width"));
		let largeur_carte = largeur_jeu/nbcol;
		let hauteur_jeu = parseInt(data_window.getPropertyValue("height"));
		let hauteur_carte = hauteur_jeu/nblig;
		jeu.style.gridTemplateColumns = "repeat("+nbcol+","+largeur_carte+"px)";

		//créer une liste de nombres aléaloires (id)
		for (let i = 0 ; i < nbimg; i++){
			n = Math.floor(Math.random()*(32 - 1)+1);
			liste.push(n);
			liste.push(n);
		};
		// permutation des id afin de mélanger les images (algorithme de Fischer-Yqates)
		for (let j = liste.length-1 ; j > 1; j--){
			let x = Math.floor(Math.random()*(j));
			//permutation
			let temp =liste[x];
			liste[x] = liste[j];
			liste[j] =temp;
		};

		for (let i = 0; i < liste.length; i++){
			let cases = document.createElement("div");
			jeu.appendChild(cases);
			let image = document.createElement("img");
			cases.setAttribute("data-numero", liste[i]);
			cases.appendChild(image);
			image.src ="images/js-logo.jpg";
			cases.addEventListener("click", montrer);
			
		};
		
		info_score.textContent = "Score : " + nb_score;
		info_paire.textContent = "Paires trouvées : " + nb_paires;
		info_coup.textContent = "Coups : " + nb_coup;
		
		if (nbcol == 6 && nblig == 4 ){
			meilleur_score.textContent = "Votre meilleur score est : " +localStorage.getItem("best_sc");
		}
		else{
			meilleur_score.textContent = "Votre meilleur score est indisponible ";
		};

	}
	
	else{
		//récupération des données de la partie enregistrée
		let data = JSON.parse(localStorage.getItem("memoire_partie"));
		console.log(data);
		liste = data["jeu"];
		nblig = data["nombrelig"] ;
		nbcol = data["nombrecol"];
		nb_coup = data["coups"];
		nb_score = data["score"];
		nb_paires = data["pTrouvees"];
		
		// création de la grille des images
		let data_window = window.getComputedStyle(jeu);
		let largeur_jeu = parseInt(data_window.getPropertyValue("width"));
		let largeur_carte = largeur_jeu/nbcol;
		let hauteur_jeu = parseInt(data_window.getPropertyValue("height"));
		let hauteur_carte = hauteur_jeu/nblig;
		jeu.style.gridTemplateColumns = "repeat("+nbcol+","+largeur_carte+"px)";
			
		for (let i = 0; i < liste.length; i++){
			let cases = document.createElement("div");
			jeu.appendChild(cases);
			let numero = parseInt(liste[i]);
			cases.setAttribute("data-numero", numero);
			
			if (!(parseInt(cases.getAttribute("data-numero")) == -1)){
				let image = document.createElement("img");
				cases.appendChild(image);
				image.src ="images/js-logo.jpg";
				cases.addEventListener("click", montrer);
			};
		};
		
		info_score.textContent = "Score : " + nb_score;
		info_paire.textContent = "Paires trouvées : " + nb_paires;
		info_coup.textContent = "Coups : " + nb_coup;
		
		if (nbcol == 6 && nblig == 4 ){
			meilleur_score.textContent = "Votre meilleur score est : " +localStorage.getItem("best_sc");
		}
		else{
			meilleur_score.textContent = "Votre meilleur score est indisponible ";
		};
	};
	
	
	//démarrer un nouvelle partie
	bouton.onclick = recommencer;
	
	function recommencer(event){
		event.preventDefault();
		
		nblig = parseInt(choix.elements.ligne.value);
		nbcol = parseInt(choix.elements.colonne.value);
		nbimg = (nbcol*nblig)/2;
		let jeu = document.getElementById("jeu");
		
		// création de la grille des images
		let data_window = window.getComputedStyle(jeu);
		let largeur_jeu = parseInt(data_window.getPropertyValue("width"));
		let largeur_carte = largeur_jeu/nbcol;
		let hauteur_jeu = parseInt(data_window.getPropertyValue("height"));
		let hauteur_carte = hauteur_jeu/nblig;
		jeu.style.gridTemplateColumns = "repeat("+nbcol+","+largeur_carte+"px)";
		
		// faire une nouvelle liste d'id
		liste =[];
		
		for (let i = 0 ; i < nbimg; i++){
		n = Math.floor(Math.random()*(32 - 1)+1);
			liste.push(n);
			liste.push(n);
		};
		
		for (let j = liste.length-1 ; j > 1; j--){
			let x = Math.floor(Math.random()*(j));
			//permutation
			let temp =liste[x];
			liste[x] = liste[j];
			liste[j] =temp;
		};
		
		//changer les images
		while(jeu.firstChild){
			jeu.removeChild(jeu.firstChild);
		};
		
		for (let i = 0; i < liste.length; i++){
			let cases = document.createElement("div");
			jeu.appendChild(cases);
			let image = document.createElement("img");
			cases.setAttribute("data-numero", liste[i]);
			cases.appendChild(image);
			image.src ="images/js-logo.jpg";
			cases.addEventListener("click", montrer);
		};
		
		nb_coup = 0;
		info_coup.textContent = "Coups : " + nb_coup;
		nb_score = 0;
		info_score.textContent = "Score : " + nb_score;
		nb_paires = 0;
		info_paire.textContent = "Paires trouvées : " + nb_paires;
		memo1 = null;
		memo2 = null;
		
		if (nbcol == 6 && nblig == 4 ){
			meilleur_score.textContent = "Votre meilleur score est : " +localStorage.getItem("best_sc");
		}
		else{
			meilleur_score.textContent = "Votre meilleur score est indisponible ";
		};

	};

	//enregistrer une partie
	let partie = document.getElementById("partie");
	partie.onclick = enregistrer;

};
// enregister les données d'une partie
function enregistrer(event){
		event.preventDefault();
		let jeu = document.getElementById("jeu");
		let recup = [];
		let choix = document.getElementById("choix");
		let nbligne = parseInt(choix.elements.ligne.value);
		let nbcolonne = parseInt(choix.elements.colonne.value);
		let paire_pas_trouve = (nbligne * nbcolonne)/2;
		for (let i = 0; i<jeu.children.length; i++){
			if (!(jeu.children[i].firstChild)){
				jeu.children[i].setAttribute("data-numero", -1);
				paire_pas_trouve -= 0.5;
			};
			recup.push(jeu.children[i].getAttribute("data-numero"));
		};
		
		let enregistrement = {};
		enregistrement["coups"] = nb_coup;
		enregistrement["score"] = nb_score;
		enregistrement["pTrouvees"] = nb_paires;
		enregistrement["prestantes"] = paire_pas_trouve;
		enregistrement["nombrelig"] = nbligne;
		enregistrement["nombrecol"] = nbcolonne;
		enregistrement["jeu"] = recup; 
		let enregistrementLocal = JSON.stringify(enregistrement);
		
		
		localStorage.setItem("memoire_partie", enregistrementLocal);
		
	};
	
	
function montrer(event){

	cible = event.currentTarget;
	let id = cible.getAttribute("data-numero");
	let image = cible.querySelector("img");
	image.src = "images/"+id+".jpg";
	let duree = 1000;

	// comparaison des deux cartes au clic
	if (cible){
			
		if (memo1 == null){
			let coups = document.getElementById("coups");
			memo1 = cible;
			nb_coup = nb_coup + 1;
			coups.textContent = "Coups : " + nb_coup;
			console.log("memo1", memo1.dataset.numero);
			}
			
		else if (memo2 == null){
			nb_coup = nb_coup + 1;
			coups.textContent = "Coups : " + nb_coup;
			memo2 = cible;
			console.log("memo2", memo2.dataset.numero);
			
			if (!(memo1.dataset.numero == memo2.dataset.numero)){
				let timer = setTimeout(delai, duree);
				let score = document.getElementById("score");
				nb_score += (-1);
				score.textContent = "Score : " + nb_score;
			}
		
			else {
				let enlever_carte = setTimeout(enlever, duree);
				nb_score += 3;
				score.textContent = "Score : " + nb_score;

				let paires = document.getElementById("paires");
				nb_paires = nb_paires + 1;
				paires.textContent = "Paires trouvées : " + nb_paires;

			};
		}
		else if (!((memo2 == null) && (memo1 == null))){
			image.src = "images/js-logo.jpg";
			return;
		};
			
	};
	//stockage meilleur score
	let choix = document.getElementById("choix");
	let nblig = choix.elements.ligne.value;
	let nbcol = choix.elements.colonne.value;
			
	if (nbcol == 6 && nblig == 4 ){
		let meilleur_sc = document.getElementById("best");

		if (nb_paires == 12 && nb_score >= compare_score){
			compare_score = nb_score;	
		};
		if (!localStorage.getItem("best_sc")){
			localStorage.setItem("best_sc", compare_score);
		}
		else{
			if (parseInt(localStorage.getItem("best_sc")) < compare_score){
				localStorage["best_sc"] = compare_score;
			};
		};
		document.getElementById("best").textContent = "Votre meilleur score est : " +localStorage.getItem("best_sc");
	};
};

//retourner les images non identiques
function delai(){
	memo1.querySelector("img").src = "images/js-logo.jpg";
	memo2.querySelector("img").src = "images/js-logo.jpg";
	memo1 = null;
	memo2 = null;
	};

// supprimer les fonctions identiques
	function enlever(){
	memo1.querySelector("img").remove();
	memo2.querySelector("img").remove();
	memo1 = null;
	memo2 = null; 
	};




	
	
	
	
