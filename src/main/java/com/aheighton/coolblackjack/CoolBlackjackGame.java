package com.aheighton.coolblackjack;

import com.aheighton.blackjack.BlackJackGame;
import com.aheighton.blackjack.Card;
import com.aheighton.blackjack.Deck;
import com.aheighton.blackjack.Player;
import java.util.List;

public class CoolBlackjackGame extends BlackJackGame
{
	static final String HIT = "hit";
	static final String STICK = "stick";
	static final String HAS = " has ";
	static final String SCORE_OF = ", a score of ";
	static final String CHEAT = "cheat";

	public CoolBlackjackGame(List<Player> players)
	{
		super(players);
	}

	@Override
	public void deal()
	{
		for (Player player: getPlayers()) play(player, HIT);
		for (Player player: getPlayers()) play(player, HIT);
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
				move = STICK;
				
				if (!player.getAbility().equals(""))
				{
					move = CHEAT;
				} else if (player.isDealer())
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



			} while ((player.getHand().getScore() <= 21) && !move.equals(STICK));
		}
		else
		{
			output.append(player.getName()).append(HAS).append(player.getHand().toString());

			output.append(SCORE_OF).append(player.getHand().getScore()).append(".");

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
			output.append(player.getName()).append(HAS).append(player.getHand().toString());
			output.append(SCORE_OF).append(player.getHand().getScore()).append(". ");

			if (move.equals(STICK)) output.append("Stick.");
			else if (move.equals(HIT)) output.append("Hit.\n");
			else output.append("Cheat!\n");
		}

		if (move.equals(HIT))
		{
			player.hit(getDeck().removeCard());
		}

		else if (move.equals(CHEAT))
		{
			if (player.isCPU())
			{
				switch (player.getAbility())
				{
					//TODO: make this more robust! Having the names of cheats hardcoded is not very good for expansion
					case "Free ace" -> {
						output.append(player.getName()).append(" snuck a card onto the table!\n");
						int suitNo = Deck.getR().nextInt(4);
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

					default -> output.append(player.getName()).append(" tries and fails to cheat again!\n");
				}
			} else
			{
				output.append("When nobody is looking, ");

				switch (player.getAbility())
				{
					case "Free ace" -> {
						output.append("you sneak an ace onto the table!\n");
						int suitNo = (Deck.getR().nextInt(4));
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
							output.append(opponent.getName()).append(HAS).append(opponent.getHand().toString());
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

			output.append(player.getName()).append(HAS).append(player.getHand().toString()).append(SCORE_OF);
			output.append(player.getHand().getScore()).append(". Bust.");
		}

		return output.toString();
	}
}
