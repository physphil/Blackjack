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
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println(playerHand.printHand()+"do you want to hit? (y/n)");
		String playerActionString = br.readLine();
		boolean playerAction = convertPlayerAction(playerActionString);
		while(playerAction){
			playerHand.hitMe();
			System.out.println(playerHand.printHand()+"do you want to hit? (y/n)");
			playerActionString = br.readLine();
			playerAction = convertPlayerAction(playerActionString);
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
/*		boolean playAgain = true;
		String playAgainString = "";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);*/
		
		//while(playAgain){
			//Deal hands to the player and then the dealer
			Hand playerHand = playAsUser(gameDeck);
			Hand dealerHand = playAsDealer(gameDeck);
	
			// remove final comma from the string returned from printHand()
			playerHandString = playerHand.printHand();
			playerHandString = playerHandString.substring(0, playerHandString.length()-2);
			dealerHandString = dealerHand.printHand();
			dealerHandString = dealerHandString.substring(0, dealerHandString.length()-2);
			
			//Print out final scores and hands
			System.out.println(declareWinner(playerHand,dealerHand)+"\nPlayer: "+playerHand.score()+" ("+playerHandString+")\nDealer: "+dealerHand.score()+" ("+dealerHandString+")");
			
			//Prompt user to play again
/*			System.out.print("Do you want to play again? (y/n)");
			playAgainString = br.readLine();
			playAgain = convertPlayerAction(playAgainString);*/
		//}
	}

}
