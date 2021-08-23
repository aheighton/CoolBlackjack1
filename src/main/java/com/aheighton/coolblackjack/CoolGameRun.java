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
		final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		String output;

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
			output = game.play(player1);
			logger.log(Level.INFO,output);
			move = scanner.nextLine().toLowerCase();

			if (move.equals("hit") || move.equals("stick") || move.equals("cheat"))
			{
				output = game.play(player1, move);
				logger.log(Level.INFO,output);
			}
		} while ((!move.equals("stick") && (player1.getHand().getScore() <= 21)));


		output = game.play(players.get(1));
		logger.log(Level.INFO,output);
		output = game.play(players.get(2));
		logger.log(Level.INFO,output);

		output = game.getWinner().getName() + " wins!";
		logger.log(Level.INFO,output);
	}

}
