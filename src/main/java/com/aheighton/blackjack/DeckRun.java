package com.aheighton.blackjack;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeckRun
{
	public static void main(String[] args)
	{
		runGame();
		runGame();
		runGame();
	}
	public static void runGame()
	{
		final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		String output;

		List<Player> players = new LinkedList<>();
		Player player = new BlackJackPlayer("Player",false,false);
		Player cpu = new BlackJackPlayer("CPU1", true, false);
		Player dealer = new BlackJackPlayer("CPU2",true,true);
		players.add(player);
		players.add(cpu);
		players.add(dealer);


		Game game = new BlackJackGame(players);

		game.deal();

		Scanner scanner = new Scanner(System.in);
		String move;
		do
		{
			output = game.play(player);
			logger.log(Level.INFO,output);
			move = scanner.nextLine().toLowerCase();

			if (move.equals("hit") || move.equals("stick")){
				output = game.play(player, move);
				logger.log(Level.INFO,output);
			}
		} while ((!move.equals("stick") && (player.getHand().getScore() <= 21)));


		output = game.play(players.get(1));
		logger.log(Level.INFO,output);

		output = game.play(players.get(2));
		logger.log(Level.INFO,output);

		output = game.getWinner().getName() + " wins!";
		logger.log(Level.INFO,output);

	}
}
