package com.aheighton.coolBlackjackTest;

import com.aheighton.blackjack.*;
import com.aheighton.coolblackjack.CoolBlackjackGame;
import com.aheighton.coolblackjack.CoolBlackjackPlayer;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CoolGameTest
{
	@Test
	void testGameStart(){
		Player player1 = new CoolBlackjackPlayer("P1",true,false);
		Player player2 = new CoolBlackjackPlayer("P2",true,true);
		List<Player> players = new LinkedList<>();
		players.add(player1);
		players.add(player2);
		Game game = new CoolBlackjackGame(players);

		assertNotEquals(game.getDeck().toString(),new Deck().toString(), "Deck is not shuffled at game start.");

		game.setDeck(new Deck());
		game.deal();

		assertEquals(2, player1.getHand().getContents().size(),"Players are not dealt 2 cards to start.");
		assertEquals(player1.getHand().getContents().size(),player2.getHand().getContents().size(),"Players are not dealt the same size hand to start.");

	}

	@Test
	void testGamePlay()
	{
		Player player1 = new CoolBlackjackPlayer("P1",false,false);
		Player player2 = new CoolBlackjackPlayer("P2",true,true);
		List<Player> players = new LinkedList<>();
		players.add(player1);
		players.add(player2);
		Game game = new CoolBlackjackGame(players);
		game.setDeck(new Deck());
		//TODO: make this a specific deck to target all possible actions

		game.deal();
		game.play(player1);
		
		player1.setAbility("Free ace");
		game.play(player1,"cheat");

		player1.setAbility("Ditch last card");
		game.play(player1,"cheat");

		player1.setAbility("See other hands");
		game.play(player1,"cheat");

		player1.setAbility("Free ace");
		game.play(player2,"cheat");

		player1.setAbility("Ditch last card");
		game.play(player2,"cheat");

		player1.setAbility("See other hands");
		game.play(player2,"cheat");

		game.play(player2);
		assertEquals(player1.toString(),game.getWinner().toString(),"The winner does not win.");
	}
}
