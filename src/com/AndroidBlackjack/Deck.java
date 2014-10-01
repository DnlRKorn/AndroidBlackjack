package com.AndroidBlackjack;

public class Deck {
	private Card[] cards;
	private Suit[] suits = Suit.values();
	
	public Deck(){
		cards = new Card[52];
		for(int i=0;i<cards.length;i++){
			cards[i] = CardBuild(i);
		}
	}
	
	public Deck(Card[] cardArray){
		cards = cardArray;
	}
	
	private Card CardBuild(int a){
		int f = (a%13) + 1;
		Suit s = suits[a%4];
		return new Card(s,f);
	}
	
	public Card[] getDeck(){
		return cards.clone();
	}
	
	public void shuffle(){
		for(int i = 0; i < cards.length;i++){
			int c1 = (int) (Math.random()*52);
			int c2 = (int) (Math.random()*52);
			Card tmp = cards[c1];
			cards[c1] = cards[c2];
			cards[c2] = tmp;
		}
	}
}
