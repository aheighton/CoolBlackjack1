package com.aheighton.coolblackjack;

import com.aheighton.blackjack.Game;
import com.aheighton.blackjack.Player;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoolGameRun
{
	public static void main(String[] args)
	{
		final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		Player player1 = new CoolBlackjackPlayer("Player1", false, false);
		Player player2 = new CoolBlackjackPlayer("Player2", true, false);
		Player player3 = new CoolBlackjackPlayer("Player3", true, true);
		List<Player> players = new LinkedList<>();
		players.add(player1);
		players.add(player2);
		players.add(player3);

		Game game = new CoolBlackjackGame(players);

		game.deal();

		Scanner scanner = new Scanner(System.in);
		String move;
		do
		{
			System.out.println(game.play(player1));
			move = scanner.nextLine().toLowerCase();

			if (move.equals("hit") || move.equals("stick") || move.equals("cheat")) LOGGER.log(Level.INFO,game.play(player1, move));
		} while ((!move.equals("stick") && (player1.getHand().getScore() <= 21)));


		LOGGER.log(Level.INFO,game.play(players.get(1)));
		LOGGER.log(Level.INFO,game.play(players.get(2)));

		LOGGER.log(Level.INFO,game.getWinner().getName() + " wins!");
	}

}
