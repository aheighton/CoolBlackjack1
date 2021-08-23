package com.aheighton.coolBlackjackTest;

import com.aheighton.blackjack.*;
import com.aheighton.coolblackjack.*;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CheatTest
{
	@Test
	void testCheatAssign()
	{
		Player player = new CoolBlackjackPlayer("Player",true,false);

		for (int i = 0; i < 100; i++)
		{
			player.newAbility();
			assertTrue(Arrays.asList(CoolBlackjackPlayer.CHEATS).contains(player.getAbility()),"Cheat assigned incorrectly.");
		}
	}

	@Test
	void testRemoveCard()
	{
		Player player = new CoolBlackjackPlayer("Player",true,false);
		Player dealer = new CoolBlackjackPlayer("Dealer",true,true);
		List<Player> players = new LinkedList<>();
		players.add(player);
		players.add(dealer);
		Game game = new CoolBlackjackGame(players);
		game.deal();
		player.setAbility("Ditch last card");

		assertEquals(2, player.getHand().getContents().size(),"Player's hand before cheat is incorrect.");

		game.play(player,"cheat");
		assertEquals(1, player.getHand().getContents().size(),"Player's hand after cheat is incorrect.");

		Hand hand1 = new Hand();
		hand1.addCard(new Card("A",'C'));
		hand1.addCard(new Card("10",'H'));
		hand1.addCard(new Card("5",'S'));
		player.setHand(hand1);
		player.setAbility("Ditch last card");

		game.play(player, "stick");

		assertEquals(16, player.getHand().getScore(), "Player's score before cheat is incorrect.");

		game.play(player,"cheat");
		assertEquals(21, player.getHand().getScore(), "Player's score after cheat does not adjust Aces.");
	}

	@Test
	void testAddAce()
	{
		Player player = new CoolBlackjackPlayer("Player",true,false);
		Player dealer = new CoolBlackjackPlayer("Dealer",true,true);
		List<Player> players = new LinkedList<>();
		players.add(player);
		players.add(dealer);
		Game game = new CoolBlackjackGame(players);
		game.deal();
		player.setAbility("Free ace");

		assertEquals(2, player.getHand().getContents().size(),"Player's hand before cheat is incorrect.");

		game.play(player,"cheat");
		assertEquals(3, player.getHand().getContents().size(),"Player's hand after cheat is incorrect.");

		Hand hand1 = new Hand();
		hand1.addCard(new Card("A",'C'));
		hand1.addCard(new Card("10",'H'));

		player.setHand(hand1);
		player.setAbility("Free ace");

		game.play(player, "stick");

		assertEquals(21, player.getHand().getScore(),  "Player's score before cheat is incorrect.");

		game.play(player,"cheat");
		assertEquals(12, player.getHand().getScore(),  "Player's score after cheat does not adjust Aces.");
	}
}
