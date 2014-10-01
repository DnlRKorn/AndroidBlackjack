package com.AndroidBlackjack;

import java.util.ArrayList;

public class AndroidBlackjack{
		private Deck d1;
		private Card[] d2;
		private ArrayList<Card> discard;
		private int CardCount;
		private int Bank;
		private boolean gameState;
		private Card[] player;
		private Card[] dealer;
		
		public AndroidBlackjack() {
			d1 = new Deck();
			gameState = false;
			dealer = new Card[7];
			player = new Card[7];
			CardCount = 0;
			Bank = 1000;
			d1.shuffle();
			discard = new ArrayList<Card>();
			d2 = d1.getDeck();
		}
		public void deal(){
			if(!gameState){
			dealer[0] = getCard();
			dealer[1] = getCard();
			player[0] = getCard();
			player[1] = getCard();
			gameState = true;
			}
		}
		
		private void blackjack(){
			int win = 0;
			
			int d = sum(dealer);
			int p = sum(player);
			if(d>21){
				System.out.println("The dealer busts");
				d = 0;
			}
			else if(d==21){
				System.out.println("The dealer hits blackjack");
			}
			if(p>21){
				p = 0;
			}
			if(d==p){
				System.out.println("You push");
				win = 1;
			}
			else if(d>p){
				System.out.println("You lose");
				win = 0;
			}
			else if(p>d){
				System.out.println("You win");
				if(p==21){
					win = 3;
				}
				else{
					win = 2;
				}
			}
			/*switch(win){
			case 0:
				break;
			case 1:
				Bank += bet;
				break;
			case 2:
				Bank += (bet *2);
				break;
			case 3:
				Bank += (bet * 2.5);
				break;
			}*/
			System.out.println("You have " + Bank);
			discard(player);
			discard(dealer);
			
		}

		private Card getCard(){
			if(CardCount < d2.length);
			else{
				Card[] c = (Card[]) discard.toArray(new Card[discard.size()]);
				CardCount = 0;
				d1 = new Deck(c);
				discard.clear();
				d2 = d1.getDeck();
				System.out.println("SHUFFLING DISCARD");
			}
			Card tmp = d2[CardCount];
			d2[CardCount] = null;
			CardCount++;
			return tmp;
		}
		
		private void discard(Card c){
			discard.add(c);
		}
		
		private void discard(Card[] hand){
			for(int i = 0; i<hand.length;i++){
				if(hand[i]==null){
					break;
				}
				discard(hand[i]);
				hand[i] = null;
			}
		}
		
		public  int sum(Card[] hand){
			int sum = 0;
			int ace = 0;
			for(int i = 0; i<hand.length;i++){
				if(hand[i]==null){
					break;
				}
					int c = hand[i].getFace();
					if(2 <= c && c <= 10){
						sum+= c; 
					}
					else if(c > 10){
						sum+= 10;
					}
					else if(c==1){
						sum+= 11;
						ace++;
					}
			}
			
			while(sum > 21){
				if(ace > 0){
					ace--;
					sum-=10;
				}
				else{
					break;
				}
			}
			if(sum > 21){
				return 0;
			}
			return sum;
			
		}
		
		public  int sum(Card c){
			Card[] cA = {c};
			return (sum(cA));
		}
		public void dealerHit(){
				Card[] hand = dealer;
				for(int i = 0; i<hand.length;i++){
					if(hand[i]==null){
						hand[i]=getCard();
						break;
					}
				}
		}
		
		public boolean getGameState(){
			return gameState;
		}
		
		public void hit(){
			if(gameState){
			Card[] hand = player;
			for(int i = 0; i<hand.length;i++){
				if(hand[i]==null){
					hand[i]=getCard();
					break;
					}
				}
			}
		}
		
		public int[] getSums(){
			int[] a = new int[2];
			if(dealer[2] == null){
			a[0] = sum(player);
			a[1] = sum(dealer[0]);
			}
			else{
				a[0] = sum(player);
				a[1] = sum(dealer);
			}
			return a;
		}
		
		public int playerSum(){
			return sum(player);
		}
		
		public int dealerSum(){
			return sum(dealer);
		}
		public Card[] getPlayer() {
			return player;
		}
		
		public Card[] getDealer() {
			return dealer;
		}
		
		public Card getDealerFirst(){
			return dealer[0];
		}
		public void finish() {
			discard(player);
			discard(dealer);
			gameState = false;
		}
		
		public int win() {
			int p = sum(player);
			int d = sum(dealer);
		if(d==p){
			return 1;
		}
		else if(d>p){
			return 0;
		}
		else if(p>d){
			if(p==21){
				return 3;
			}
			else{
				return 2;
			}
		}
		return 1;
		}

}
