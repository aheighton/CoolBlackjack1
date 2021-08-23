package com.aheighton.blackjackTest;

import com.aheighton.blackjack.*;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest
{
	@Test
	public void testGameStart(){
		Player player1 = new BlackJackPlayer("P1",true,false);
		Player player2 = new BlackJackPlayer("P2",true,true);
		List<Player> players = new LinkedList<>();
		players.add(player1);
		players.add(player2);
		Game game = new BlackJackGame(players);

		assertNotEquals(game.getDeck().toString(),new Deck().toString(), "Deck is not shuffled at game start.");

		game.setDeck(new Deck());
		game.deal();

		assertEquals(player1.getHand().getContents().size(),2,"Players are not dealt 2 cards to start.");
		assertEquals(player1.getHand().getContents().size(),player2.getHand().getContents().size(),"Players are not dealt the same size hand to start.");

		Hand hand1 = new Hand();
		Hand hand2 = new Hand();

		List<Card> hand1List = new LinkedList<>();
		List<Card> hand2List = new LinkedList<>();

		hand1List.add(new Card("A",'D'));
		hand2List.add(new Card("K",'D'));
		hand1List.add(new Card("Q",'D'));
		hand2List.add(new Card("J",'D'));

		hand1.setContents(hand1List);
		hand2.setContents(hand2List);

		assertEquals(player1.getHand().toString(),hand1.toString(),"Cards are not dealt correctly from the top of the deck at game start.");
		assertEquals(player2.getHand().toString(),hand2.toString(),"Cards are not dealt correctly from the top of the deck at game start.");
	}
}
