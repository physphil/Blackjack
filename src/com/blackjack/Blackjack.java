package com.blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Phil Shadlyn, November 2012
 * Very basic console-based blackjack game 
 */

public class Blackjack {
	public static void main(String[] args) throws IOException {
		playGame();
	}
	
	//Create hand for the dealer, stop at 17
	public static Hand playAsDealer(Deck gameDeck){
		Hand dealerHand = new Hand(gameDeck);
		while(dealerHand.score()<17){
			dealerHand.hitMe();
		}
		return dealerHand;
	}
	
	//Create hand for the player, prompt for cards
	public static Hand playAsUser(Deck gameDeck) throws IOException{
		Hand playerHand = new Hand(gameDeck);
		boolean playerAction = true;
		String playerActionString = "";
		boolean noBust = true;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		while(playerAction && noBust){
			System.out.print(playerHand.score()+" - "+playerHand.printHand()+"do you want to hit? (y/n)");
			playerActionString = br.readLine();
			playerAction = convertPlayerAction(playerActionString);
			if (playerAction){
				playerHand.hitMe();
				if (playerHand.score() > 21) noBust = false;
			}			
		}
		return playerHand;
	}
	
	//Convert user input to boolean
	public static boolean convertPlayerAction(String choice){
		if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")){
			return true;
		}
		else return false;
	}
	
	//Determine winner based on scores
	public static String declareWinner(Hand userHand, Hand dealerHand){
		int userScore = userHand.score();
		int dealerScore = dealerHand.score();
		
		if(userScore>21 && dealerScore>21) return "You both lose!";
		else if(userScore>21 && dealerScore<=21) return "You lose!";
		else if(userScore<=21 && dealerScore>21) return "You win!";
		else{
			if(userScore>dealerScore) return "You win!";
			else if(userScore<dealerScore) return "You lose!";
			else return "Push!";
		}
	}
	
	//Create deck of 52 cards, deal 2 hands, declare winner
	public static void playGame() throws IOException{
		Deck gameDeck = new Deck();
		String playerHandString="";
		String dealerHandString="";
		boolean playAgain = true;
		String playAgainString = "";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		while(playAgain){
			//Deal hands to the player and then the dealer
			Hand playerHand = playAsUser(gameDeck);
			Hand dealerHand = playAsDealer(gameDeck);
	
			// remove final comma from the string returned from printHand()
			playerHandString = playerHand.printHand();
			playerHandString = playerHandString.substring(0, playerHandString.length()-2);
			dealerHandString = dealerHand.printHand();
			dealerHandString = dealerHandString.substring(0, dealerHandString.length()-2);
			
			//Print out final scores and hands
			System.out.println("\n"+declareWinner(playerHand,dealerHand)+"\n\nPlayer: "+playerHand.score()+" ("+playerHandString+")\nDealer: "+dealerHand.score()+" ("+dealerHandString+")");
			System.out.println(gameDeck.activeDeck.size()+" cards remaining");
			
			//Prompt user to play again
			System.out.print("\nDo you want to play again? (y/n)");
			playAgainString = br.readLine();
			playAgain = convertPlayerAction(playAgainString);
			
			// Shuffle deck if there is less than 15 cards available
			if(playAgain && gameDeck.activeDeck.size()<15){
				System.out.println("You're almost out of cards!  Reshuffling...");
				gameDeck.shuffle();
			}
			System.out.println("-------------------");
			System.out.println("");
		}
	}

}
