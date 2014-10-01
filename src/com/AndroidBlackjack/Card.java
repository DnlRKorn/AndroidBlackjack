package com.AndroidBlackjack;

public class Card {
	private int face;
	private Suit suit;
	public Card(Suit suit, int face){
		this.suit = suit;
		this.face = face;
	}
	public Card(int face){
		this(Suit.H, face);
	}
	
	public Suit getSuit(){
		return getSuit();
	}
	
	public String getSuitString(){
		if(suit==Suit.S){
			return "S";
		}
		else if(suit==Suit.H){
			return "H";
		}
		else if(suit==Suit.C){
			return "C";
		}
		else if(suit==Suit.D){
			return "D";
		}
		else{
			throw new RuntimeException("Suit");
		}
	}
	
	public int getFace(){
		return face;
	}
}
