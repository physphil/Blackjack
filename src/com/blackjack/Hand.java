package com.blackjack;

import java.util.ArrayList;
import java.util.List;

/*	This object represents a player's hand
 * 	Initially consists of 2 cards, can be dealt additional cards with hitMe() method
 * 	When a card is dealt it is removed from the deck so it cannot be dealt again * 
 */

public class Hand {
	private List<Card> hand = new ArrayList<Card>();
	Deck gameDeck = null;
	
	//Initially deal 2 cards to both the player and dealer
	public Hand(Deck gameDeck){
		this.gameDeck = gameDeck;
		hand.add(deal(this.gameDeck));
		hand.add(deal(this.gameDeck));
	}
	
	public List<Card> getHand(){
		return hand;
	}
	
	/* Calculate the value of the hand. Aces=11, unless it causes the player to bust.
	 * If the player's score is greater than 21 and their hand has at least one Ace,
	 * convert each Ace from 11 to 1 until their score is less than 21, or they
	 * run out of Aces in their hand
	 */
	
	public int score(){ 
		int totalScore=0;
		int numAces=0;
		for(Card o: hand){
			totalScore += o.getValue();
			if(o.getNumber()==1) numAces++;
		}
		while(totalScore>21 && numAces>0){
			numAces--;
			totalScore -= 10;
		}
		return totalScore;
	}
	
	public void hitMe(){
		hand.add(deal(gameDeck));
	}
	
	//Print out the cards in the player's hand
	public String printHand(){
		String rank="";
		String suit="";
		String stringHand="";
		for(Card o: hand){
			switch(o.getNumber()){
				case 1: rank = "Ace";
				break;
				case 11: rank = "Jack";
				break;
				case 12: rank = "Queen";
				break;
				case 13: rank = "King";
				break;
				default: rank = o.getNumber().toString();
				break;
			}
			switch(o.getSuit()){
				case 1: suit = "Spades";
				break;
				case 2: suit = "Clubs";
				break;
				case 3: suit = "Hearts";
				break;
				case 4: suit = "Diamonds";
				break;
			}
			stringHand=stringHand+rank+" of "+suit+", ";
		}
		return stringHand;
	}
	
	/*	This method deals a new card to the player
	 * 	It pulls a random card from those remaining in the deck, which is controlled by activeDeck.size()
	 * 	The divide and modulus functions determine the suit and number of the card
	 *  Remove the selected card from the deck once it's been dealt
	 */
	
	public Card deal(Deck gameDeck){
		int index = (int) (Math.floor(Math.random()*gameDeck.activeDeck.size()));
		int card = gameDeck.activeDeck.get(index);
		int suit = (int) (Math.floor(card/13)+1);
		int number = card%13+1;
		Card newCard = new Card(suit, number);
		gameDeck.activeDeck.remove(index);
		//gameDeck.displayCards();
		return newCard;
	}
}
