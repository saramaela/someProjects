package test.model;

import blackjack.model.*;
import carte.model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class PlayerHandTest {
	private Player p;
	private Game g;
	private PlayerHand h;
	
	@Before
	public void setUp() {
		this.p = new Player(100);
		this.g = new Game(new ArrayList<>());
		this.h = new PlayerHand (10, this.p, this.g);
		this.p.addHand(this.h);
	}
	
	@After
	public void tearDown() {
		this.h = null;
		this.p = null;
	}
	
	@Test
	public void testSplit() {
		this.h.hit();
		this.h.hit();
		int nbHand = this.p.getHands().size();
		double balanceBefore = this.p.getBalance();
		this.h.split();
		assertEquals(this.p.getHands().size(), nbHand + 1);
		assertEquals(this.p.getBalance(), balanceBefore - this.h.getBet(), 0.001);
	}
	@Test
	public void testHit() {
		int nbCarte = this.h.getHand().getPaquetSize();
		this.h.hit();
		assertEquals(this.h.getHand().getPaquetSize(), nbCarte + 1);
		this.h.hit();
		assertEquals(this.h.getHand().getPaquetSize(), nbCarte + 2);
	}
	@Test
	public void testMakeDouble() {
		this.h.hit();
		this.h.hit();
		double balanceBefore = this.p.getBalance();
		int nbCarte = this.h.getHand().getPaquetSize();
		this.h.makeDouble();
		assertEquals(this.h.getHand().getPaquetSize(), nbCarte + 1);
		assertEquals(this.p.getBalance(), balanceBefore - this.h.getBet(), 0.001);
		assertEquals(this.h.isEnd(), true);
		assertEquals(this.h.isDouble(), true);
	}
	@Test
	public void testStay() {
		this.h.stay();
		assertEquals(this.h.isEnd(), true);
	}
	@Test
	public void testPay() {
		double balanceBefore = this.p.getBalance();
		double toPay = this.h.getBet() * 2;
		this.h.pay(2);
		assertEquals(this.p.getBalance(), balanceBefore + toPay, 0.0001);
	}
	@Test
	public void testCanSplit() {
		this.h.add(new Carte(Factory.Valeur.AS, Factory.Couleur.CARREAU));
		this.h.add(new Carte(Factory.Valeur.AS, Factory.Couleur.CARREAU));
		assertEquals(this.h.canSplit(), true);
		this.h.add(new Carte(Factory.Valeur.DEUX, Factory.Couleur.CARREAU));
		assertEquals(this.h.canSplit(), false);
		this.h.getHand().removeCarte(1);
		assertEquals(this.h.canSplit(), false);
	}
	@Test
	public void testCanDouble() {
		this.h.add(new Carte(Factory.Valeur.AS, Factory.Couleur.CARREAU));
		this.h.add(new Carte(Factory.Valeur.AS, Factory.Couleur.CARREAU));
		assertEquals(this.h.canDouble(), true);
		this.h.add(new Carte(Factory.Valeur.DEUX, Factory.Couleur.CARREAU));
		assertEquals(this.h.canDouble(), false);
	}
	@Test
	public void testIsEnd() {
		this.h.add(new Carte(Factory.Valeur.AS, Factory.Couleur.CARREAU));
		assertEquals(this.h.isEnd(), false);
		this.h.add(new Carte(Factory.Valeur.DIX, Factory.Couleur.CARREAU));
		assertEquals(this.h.isEnd(), true);
	}
	@Test
	public void testIsBlackjack() {
		this.h.add(new Carte(Factory.Valeur.AS, Factory.Couleur.CARREAU));
		assertEquals(this.h.isBlackjack(), false);
		this.h.add(new Carte(Factory.Valeur.DIX, Factory.Couleur.CARREAU));
		assertEquals(this.h.isBlackjack(), true);
	}
	@Test 
	public void testCount() {
		this.h.add(new Carte(Factory.Valeur.AS, Factory.Couleur.CARREAU));
		assertEquals(this.h.count(), 11);
		this.h.add(new Carte(Factory.Valeur.DIX, Factory.Couleur.CARREAU));
		assertEquals(this.h.count(), 21);
		this.h.add(new Carte(Factory.Valeur.CINQ, Factory.Couleur.CARREAU));
		assertEquals(this.h.count(), 16);
		this.h.add(new Carte(Factory.Valeur.AS, Factory.Couleur.CARREAU));
		assertEquals(this.h.count(), 17);
	}

}
