package com.blackjack;

import java.util.ArrayList;
import java.util.List;

/*	Create deck object to manage 52 card deck
 * 	Deck is an array of 52 numbers, 0-51
 */

public class Deck {
	List<Integer> activeDeck = new ArrayList<Integer>();
	
	public Deck(){
		for(int i=0;i<52;i++){
			activeDeck.add(i);
		}		
	}
	
	// Displays all cards remaining in the deck
	public void displayCards(){
		for(Integer o: activeDeck){
			System.out.print(o.toString()+" ");
		}
		System.out.println("");
	}
}
