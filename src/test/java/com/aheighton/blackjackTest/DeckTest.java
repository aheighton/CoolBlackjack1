package com.aheighton.blackjackTest;

import com.aheighton.blackjack.*;
import org.junit.jupiter.api.*;

import static com.aheighton.blackjack.Card.*;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest
{
	@Test
	void testDeckConstruction()
	{
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		assertEquals(deck1.toString(),deck2.toString(),"Deck construction is not consistent.");
		deck2.shuffle();
		assertNotEquals(deck1,deck2,"Deck shuffling does not shuffle.");
		assertEquals(deck2.getContents().size(),deck1.getContents().size(), "Deck shuffling changes the size of the deck.");
		deck1.shuffle();
		assertNotEquals(deck1,deck2,"Deck shuffling is not randomised.");
	}

	@Test
	void testDeckDealing()
	{
		Deck deck = new Deck();

		assertEquals(52, deck.getContents().size(), "Deck size is not 52.");

		Player player = new BlackJackPlayer("player", false, false);
		player.hit(deck.removeCard());

		assertEquals(51, deck.getContents().size(), "Removing cards does not correctly change deck size.");
		assertEquals(1, player.getHand().getContents().size(), "Hand does not update from deck dealing.");

		deck.addCard(player.getHand().removeCard());
		assertEquals(deck.toString(),new Deck().toString(),"Adding and removing cards is not consistent.");
	}

	@Test
	void testIncorrectAssignments()
	{
		Card card = new Card("2",'?');
		assertEquals(ANSI_RED+"[2?]"+ANSI_RESET, card.toString(), "Incorrect suit generates correctly. ");

		BlackJackPlayer player = new BlackJackPlayer("Player", false, false);
		assertNull(player.getAbility(),"getAbility is not null for standard blackjack.");
		try
		{
			player.newAbility();
			fail("newAbility generated without error for standard blackjack");
		} catch (Exception ignored){}

		try
		{
			player.setAbility("");
			fail("setAbility executed without error for standard blackjack");
		} catch (Exception ignored){}


	}
}
