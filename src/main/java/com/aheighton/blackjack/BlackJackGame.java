package com.aheighton.blackjack;

import java.util.*;

public class BlackJackGame extends Game
{
	public BlackJackGame(List<Player> players)
	{
		setPlayers(players);
		setDeck(new Deck());
		getDeck().shuffle();
	}

	@Override
	public void deal()
	{
		for (Player player: getPlayers()) play(player,"hit");
		for (Player player: getPlayers()) play(player, "hit");
	}
	
	public String play(Player player)
	{
		StringBuilder output = new StringBuilder();

		if (player.isCPU())
		{
			String move;
			do
			{
				move = "stick";

				if (player.isDealer())
				{
					move = player.getHand().getScore() < 16? "hit" : "stick";
				}
				else
				{
					//TODO: work out what move the CPU will make
					if (player.getHand().getScore() < 16)
					{
						move = "hit";
					}
				}
				output.append(play(player, move));



			 } while ((player.getHand().getScore() <= 21) && move.equals("hit"));
		}
		else
		{
			output.append(player.getName()).append(" has ").append(player.getHand().toString());

			output.append(", a score of ").append(player.getHand().getScore()).append(".");

			output.append("\nHit or Stick?");
		}
		return output.toString();
	}

	@Override
	public String play(Player player, String move)
	{
		String output =  "";

		if (player.isCPU())
		{
			if (player.isDealer())
			{
				output += "Dealer ";
			}
			output += player.getName() + " has " + player.getHand().toString() +
					", a score of " + player.getHand().getScore() + ". " + (move.equals("hit") ? "Hit. \n" : "Stick.");
		}

		if (move.equals("hit"))
		{
			player.hit(getDeck().removeCard());
		}

		if (player.getHand().getScore() > 21)
		{
			for (Card card: player.getHand().getContents())
			{
				if (card.getValue().equals("A"))
				{
					card.setValue("1");
					if (player.getHand().getScore() <= 21) return output;
				}
			}

			if (player.isDealer())
			{
				output += "Dealer ";
			}

			output +=player.getName() + " has " + player.getHand().toString() +
					", a score of " + player.getHand().getScore() +	". Bust.";
		}

		return output;
	}

	@Override
	public Player getWinner()
	{
		Player winner = getPlayers().get(0);
		for (Player player: getPlayers())
		{
			if (winner.getHand().getScore() < player.getHand().getScore())
			{
				if (player.getHand().getScore() <= 21)
				{
					winner = player;
				}
			}
		}
		return winner;
	}
}
