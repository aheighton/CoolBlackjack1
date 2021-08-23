package com.aheighton.coolBlackjackTest;

import com.aheighton.coolBlackjack.*;
import com.aheighton.blackjack.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest
{
	@Test
	public void testDeckConstruction()
	{
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		assertEquals(deck1.toString(),deck2.toString(),"Deck construction is not consistent.");
		deck2.shuffle();
		assertNotEquals(deck1,deck2,"Deck shuffling does not shuffle.");
		deck1.shuffle();
		assertNotEquals(deck1,deck2,"Deck shuffling is not randomised.");
	}

	@Test
	public void testDeckDealing()
	{
		Deck deck = new Deck();

		assertEquals(deck.getContents().size(), 52, "Deck size is not 52.");

		Player player = new CoolBlackjackPlayer("player", false, false);
		player.hit(deck.removeCard());

		assertEquals(deck.getContents().size(), 51, "Removing cards does not correctly change deck size.");
		assertEquals(player.getHand().getContents().size(), 1, "Hand does not update from deck dealing.");

		deck.addCard(player.getHand().removeCard());
		assertEquals(deck.toString(),new Deck().toString(),"Adding and removing cards is not consistent.");
	}
}
