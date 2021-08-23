package com.aheighton.coolBlackjack;

import com.aheighton.blackjack.BlackJackGame;
import com.aheighton.blackjack.Card;
import com.aheighton.blackjack.Player;
import java.util.List;

public class CoolBlackjackGame extends BlackJackGame
{
	public CoolBlackjackGame(List<Player> players)
	{
		super(players);
	}

	@Override
	public void deal()
	{
		for (Player player: getPlayers()) play(player,"hit");
		for (Player player: getPlayers()) play(player, "hit");
		for (Player player: getPlayers()) player.newAbility();
	}

	@Override
	public String play(Player player)
	{
		StringBuilder output = new StringBuilder();

		if (player.isCPU())
		{
			String move;
			do
			{
				move = "stick";
				
				if (!player.getAbility().equals(""))
				{
					move = "cheat";
				} else if (player.isDealer())
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



			} while ((player.getHand().getScore() <= 21) && !move.equals("stick"));
		}
		else
		{
			output.append(player.getName()).append(" has ").append(player.getHand().toString());

			output.append(", a score of ").append(player.getHand().getScore()).append(".");

			output.append(player.getAbility().equals("")?"\nHit or Stick?":"\nHit, Stick, or Cheat? \nYour cheat is: "+player.getAbility()+".");

		}
		return output.toString();
	}

	@Override
	public String play(Player player, String move)
	{
		StringBuilder output = new StringBuilder();

		if (player.isCPU())
		{
			if (player.isDealer())
			{
				output.append("Dealer ");
			}
			output.append(player.getName()).append(" has ").append(player.getHand().toString());
			output.append(", a score of ").append(player.getHand().getScore()).append(". ");

			if (move.equals("stick")) output.append("Stick.");
			else if (move.equals("hit")) output.append("Hit.\n");
			else output.append("Cheat!\n");
		}

		if (move.equals("hit"))
		{
			player.hit(getDeck().removeCard());
		}

		else if (move.equals("cheat"))
		{
			if (player.isCPU())
			{
				switch (player.getAbility())
				{
					//TODO: make this more robust! Having the names of cheats hardcoded is not very good for expansion
					case "Free ace" -> {
						output.append(player.getName()).append(" snuck a card onto the table!\n");
						int suitNo = (int) (Math.random()*4);
						player.hit(new Card("A", new char[]{'C', 'H', 'S', 'D'}[suitNo]));
						//TODO: this should be a static variable.
					}
					case  "Ditch last card" -> {
						output.append(player.getName()).append(" snuck a card off the table!\n");
						player.getHand().removeCard();

						//TODO: make this a method to apply on a hand. Happens often enough
						for (Card card: player.getHand().getContents())
						{
							if (card.getValue().equals("1"))
							{
								card.setValue("A");
							}
						}

					}
					case "See other hands" -> output.append(player.getName()).append(" looked at everyone's hands!\n");
				}
			} else
			{
				output.append("When nobody is looking, ");

				switch (player.getAbility())
				{
					case "Free ace" -> {
						output.append("you sneak an ace onto the table!\n");
						int suitNo = (int) (Math.random() * 4);
						player.hit(new Card("A", new char[]{'C', 'H', 'S', 'D'}[suitNo]));
					}
					case "Ditch last card" -> {
						output.append("you slip your last card off the table!\n");
						player.getHand().removeCard();

						for (Card card : player.getHand().getContents())
						{
							if (card.getValue().equals("1"))
							{
								card.setValue("A");
							}
						}

					}
					case "See other hands" -> {
						output.append("you turn over everyone's cards!\n");
						for (Player opponent : getPlayers())
						{
							output.append(opponent.getName()).append(" has ").append(opponent.getHand().toString());
							output.append(".\n");
						}
					}

					default -> output.append("you try and fail to cheat again!\n");
				}
			}
			player.setAbility("");
		}

		if (player.getHand().getScore() > 21)
		{

			//TODO: this can also be a method applied on hand.
			for (Card card: player.getHand().getContents())
			{
				if (card.getValue().equals("A"))
				{
					card.setValue("1");
					if (player.getHand().getScore() <= 21) return output.toString();
				}
			}

			if (player.isDealer())
			{
				output.append("Dealer ");
			}

			output.append(player.getName()).append(" has ").append(player.getHand().toString()).append(", a score of ");
			output.append(player.getHand().getScore()).append(". Bust.");
		}

		return output.toString();
	}
}
