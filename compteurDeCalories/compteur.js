"use strict";
let group = {}
function calories (){
	
	let liste_ingredients = document.getElementById("select-ingredient");
	// obtention d'une liste déroulante des différents ingrédients
	for (let i = 0; i < data.length; i++){
		
		let options = document.createElement("option");
		let categorie = data[i]["categorie"];
		let ingredient = data[i]["ingredient"];
		let id = data[i]["id"];
		options.value = id;
		options.textContent = ingredient;
		
		// améliorations (optgroup) faite pendant le tutorat
		if (!(categorie in group )){
			let optgroup = document.createElement("optgroup");
			optgroup.label = categorie.replace(categorie[0], categorie[0].toUpperCase());
			group[categorie] = optgroup;
			liste_ingredients.appendChild(optgroup);
			};
			//optgroup est en quelque sorte un titre pour option
			
		group[categorie].appendChild(options);
		};
	
	let formulaire = document.getElementById("selection");
	formulaire.addEventListener("submit", ajouterIngredient);
	};
	
let total, liste;
let total_energie = 0;
let prot = 0;
let glu = 0;
let lip = 0;
let suc = 0;
let supp;

function ajouterIngredient(event){
	
	event.preventDefault();
	//ajouter les différents ingrédients sur la partie de droitenet afficher la somme des calories
	let liste_ingredients = document.getElementById("select-ingredient");
	let id = liste_ingredients.value;
	let obj = recherche(id);
	let poids = document.getElementById("selection").elements.poids.value;
	let compteur = document.getElementById("compteur");


	if (!liste){
		liste = document.createElement("ul");
		compteur.appendChild(liste);
		liste.setAttribute("id", "liste_i")
		};
	let item = document.createElement("li");
	liste.appendChild(item);
	let energie = (poids * obj["energie"])/ 100;
	let sucres = (poids * obj["sucres"])/ 100;
	let glucides_s = (poids * obj["glucides"])/ 100 + sucres;
	let proteines = (poids * obj["proteines"])/ 100;
	let lipides = (poids * obj["lipides"])/ 100;
	item.textContent = "Pour " + poids + " grammes de " + obj["ingredient"] + " nous avons " + energie +" kCal.";
	
	if (!total){
		total = document.createElement("div");
		compteur.appendChild(total);
		};
	
	total_energie = total_energie + energie ;
	prot = prot+ proteines ;
	glu = glu + glucides_s ;
	lip = lip + lipides ;
	suc = suc + sucres ;
	
	total.textContent = "Au total, nous avons " + total_energie.toFixed(2) + "kCal. Dont protéines " + prot.toFixed(2) + " Lipides : " + lip.toFixed(2) + " glucides " + glu.toFixed(2) + " dont sucres" + suc.toFixed(2);
	
	item.onclick = enlever;
	
	function enlever(){
		//améliorations :enlever un élément de la liste sélectionné
		item.remove()
		suc = suc - (poids * obj["sucres"])/ 100;
		glu = glu - (poids * obj["glucides"])/ 100 - (poids * obj["sucres"])/ 100;
		prot = prot-((poids * obj["proteines"])/ 100);
		lip = lip -(poids * obj["lipides"])/ 100;
		total_energie = total_energie - ((poids * obj["energie"])/ 100) ;
		total.textContent = "Au total, nous avons " + total_energie.toFixed(2) + "kCal. Dont protéines " + prot.toFixed(2) + " Lipides : " + lip.toFixed(2) + " glucides " + glu.toFixed(2) + " dont sucres" + suc.toFixed(2);
		};
	
	if (!supp){
		supp = document.createElement("button");
		supp.setAttribute("type", "reset")
		supp.textContent = "Remettre à zero" ;
		let formulaire = document.getElementById("selection");
		compteur.appendChild(supp);
		};
		
	supp.addEventListener("click", removing);
	};

//terminé et optimisé pendant le tutorat
function removing(event){
	let liste_ingredients = document.getElementById("select-ingredient");
	let id = liste_ingredients.value;
	let section = document.querySelector("section");
	let compte = document.getElementById("compteur");
	
	total_energie = 0;
	let prot = 0;
	let glu = 0;
	let lip = 0;
	let suc = 0;


	if (compte){
		// total.remove();
		// supp.remove();
	let ul = document.getElementById("liste_i");
		if (ul){

			while (ul.firstChild) ul.removeChild(ul.firstChild);
			// liste = document.createElement("ul");
			// liste.setAttribute("id", "liste_i")
			// console.log(liste)
			// compte.appendChild(liste);
		};
	};
	// if (!(document.getElementById("tot"))){
		// total = document.createElement("div");
		// total.setAttribute("id", "tot");
		// section.appendChild(total);
	// };
	// section.appendChild(supp)

	total.textContent = "Au total, nous avons " + total_energie.toFixed(2) + "kCal. Dont protéines " + prot.toFixed(2) + " Lipides : " + lip.toFixed(2) + " glucides " + glu.toFixed(2) + " dont sucres" + suc.toFixed(2);

};
function recherche(id){
	for (let i = 0; i < data.length; i++){ 
		if (id == data[i]["id"]){
			return data[i];
			};
		};		 
	};

//pour connaitre la valeur de l'input on utilise value
