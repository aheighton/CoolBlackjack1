package com.aheighton.blackjack;

import java.util.*;

public class BlackJackGame extends Game
{
	final String HIT = "hit";
	final String STICK = "stick";
	final String HAS = " has ";
	final String SCOREOF = ", a score of ";

	public BlackJackGame(List<Player> players)
	{
		setPlayers(players);
		setDeck(new Deck());
		getDeck().shuffle();
	}

	@Override
	public void deal()
	{
		for (Player player: getPlayers()) play(player,HIT);
		for (Player player: getPlayers()) play(player,HIT);
	}
	
	public String play(Player player)
	{
		StringBuilder output = new StringBuilder();

		if (player.isCPU())
		{
			String move;
			do
			{
				move = STICK;

				if (player.isDealer())
				{
					move = player.getHand().getScore() < 16? HIT : STICK;
				}
				else
				{
					//TODO: work out what move the CPU will make
					if (player.getHand().getScore() < 16)
					{
						move = HIT;
					}
				}
				output.append(play(player, move));



			 } while ((player.getHand().getScore() <= 21) && move.equals(HIT));
		}
		else
		{
			output.append(player.getName()).append(HAS).append(player.getHand().toString());

			output.append(SCOREOF).append(player.getHand().getScore()).append(".");

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
			output += player.getName() + HAS + player.getHand().toString() +
					SCOREOF + player.getHand().getScore() + ". " + (move.equals(HIT) ? "Hit. \n" : "Stick.");
		}

		if (move.equals(HIT))
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

			output +=player.getName() + HAS + player.getHand().toString() +
					SCOREOF + player.getHand().getScore() +	". Bust.";
		}

		return output;
	}

	@Override
	public Player getWinner()
	{
		Player winner = getPlayers().get(0);
		for (Player player: getPlayers())
		{
			if ((winner.getHand().getScore() < player.getHand().getScore()) && (player.getHand().getScore() <= 21))
			{
					winner = player;
			}
		}
		return winner;
	}
}
