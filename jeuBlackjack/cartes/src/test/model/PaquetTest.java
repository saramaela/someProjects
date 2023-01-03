package test.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import carte.model.*;
import carte.model.Factory.Couleur;
import carte.model.Factory.Valeur;

public class PaquetTest {

	private Paquet paquet;
	private Carte carte1;
	private Carte carte2;
	private Carte carte3;

	@Before
	public void setUp() {
		
		this.paquet = new Paquet();
		this.carte1 = new Carte(Valeur.ROI, Couleur.PIQUE);
		this.carte2 = new Carte(Valeur.DEUX, Couleur.COEUR);
		this.carte3 = new Carte(Valeur.TROIS, Couleur.PIQUE);
	}
	

	@Test
	public void TestAjoutCarteAbove() {


		this.paquet.ajoutCarteAbove(this.carte1);
		this.paquet.ajoutCarteAbove(this.carte2);
		this.paquet.ajoutCarteAbove(this.carte3);
		
		assertEquals(this.carte3, this.paquet.getPaquet().get(this.paquet.getPaquet().size()-1));

	}
	
	@Test
	public void TestAjoutCarteBelow() {


		this.paquet.ajoutCarteBelow(this.carte1);
		this.paquet.ajoutCarteBelow(this.carte2);
		this.paquet.ajoutCarteBelow(this.carte3);
		
		assertEquals(this.carte3, this.paquet.getPaquet().get(0));

	}
	
	@Test
	public void TestRemoveFirstCard() {
		
		
		this.paquet.ajoutCarteBelow(this.carte1);
		this.paquet.ajoutCarteBelow(this.carte2);
		this.paquet.ajoutCarteBelow(this.carte3);
		this.paquet.removeFirstCard();
		
		assertEquals(2, this.paquet.getPaquetSize());
		assertFalse(this.paquet.getPaquet().contains(this.carte3));

	}
	
	@Test
	public void TestRemoveCarte() {
		
		
		this.paquet.ajoutCarteBelow(this.carte1);
		this.paquet.ajoutCarteBelow(this.carte2);
		this.paquet.ajoutCarteBelow(this.carte3);
		this.paquet.removeCarte(0);
		
		assertEquals(2, this.paquet.getPaquetSize());
		assertFalse(this.paquet.getPaquet().contains(this.carte3));

	}
	
	@Test
	public void TestDistribuer() {
		
		
		this.paquet.ajoutCarteBelow(this.carte1);
		this.paquet.ajoutCarteBelow(this.carte2);
		this.paquet.ajoutCarteBelow(this.carte3);
		this.paquet.removeCarte(0);
		
		assertEquals(2, this.paquet.getPaquetSize());
		assertFalse(this.paquet.getPaquet().contains(this.carte3));

	}
	
	@Test
	public void TestCouperPaquet() {
		
		
		this.paquet.ajoutCarteBelow(this.carte1);
		this.paquet.ajoutCarteBelow(this.carte2);
		this.paquet.ajoutCarteBelow(this.carte3);
		this.paquet.ajoutCarteBelow(this.carte3);
		this.paquet.ajoutCarteBelow(this.carte3);
		this.paquet.ajoutCarteBelow(this.carte3);
		
		this.paquet = this.paquet.couperPaquet();
		
		assertTrue(this.paquet.getPaquetSize() < 6);

	}

	@After
	public void tearDown() {
		this.paquet = null;
	}
}
