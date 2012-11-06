package com.blackjack;

/*  Create a card object, which is the basis for each player's hand
 *  1-4 = spades, clubs, hearts, diamonds respectively
 *  1-13 = A,2,3,4,5,6,7,8,9,10,J,Q,K
 */

public class Card {
	private int suit;
	private int number;
	
	public Card(int suit, int number){
		this.suit = suit;
		this.number = number;
	}
	
	public Integer getNumber(){
		return number;
	}
	
	public int getSuit(){
		return suit;
	}

	/* Assign values to each card in the deck.
	 * Aces=11, Face cards = 10, numeric cards = their value
	 */
	
	public int getValue(){				
		if (number ==1) return 11;		
		else if(number>10) return 10;   
		else return number;				
	}
}
