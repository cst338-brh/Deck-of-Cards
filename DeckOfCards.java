/*Heather McCabe
 *Brett Hansen
 *Randall Rood
 *CST 338
 *9/17/16
 *Module 3: Deck of Cards
 */

import java.util.*;
import java.lang.Math;

public class DeckOfCards {

   private static Scanner input = new Scanner(System.in);
   
   public static void main(String[] args) {      
      /* ----- Test Card class ----- */
      System.out.println("/* ----- Card class testing ----- /");
      Card card1 = new Card();                      // Default constructor
      Card card2 = new Card('4',Card.Suit.CLUBS);   // Other constructor
      Card card3 = new Card('Y',Card.Suit.HEARTS);  // Invalid value
      System.out.println(card1.toString());
      System.out.println(card2.toString());
      System.out.println(card3.toString());
      card3.set('Q',Card.Suit.CLUBS);               // Make invalid card valid
      card2.set('0',Card.Suit.DIAMONDS);            // Make valid card invalid
      System.out.println(card1.toString());
      System.out.println(card2.toString());
      System.out.println(card3.toString());
      System.out.println("/ ------------------------------ /");
      
      /* ----- Test Hand class ----- */
      System.out.println("\n/* ----- Hand class testing ----- /");
      // Create Cards and Hand
      card1 = new Card('1',Card.Suit.CLUBS);
      card2 = new Card('T',Card.Suit.SPADES);
      card3 = new Card('Q',Card.Suit.DIAMONDS);
      Card card4 = new Card('2',Card.Suit.CLUBS);
      Card card5 = new Card('5',Card.Suit.SPADES);
      Hand hand1 = new Hand();
      
      // Add cards to Hand until full
      int cardCount = 0;
      while (cardCount < Hand.MAX_CARDS) {
         hand1.takeCard(card1);
         hand1.takeCard(card2);
         hand1.takeCard(card3);
         hand1.takeCard(card4);
         hand1.takeCard(card5);
         cardCount++;
      }

      // Display full Hand
      System.out.println("Full Hand:");
      System.out.println("( " + hand1.toString() + " )");
      
      // Test inspectCard method
      System.out.println("\nInspect Cards:");
      System.out.println(hand1.inspectCard(0).toString());      // Should be valid
      System.out.println(hand1.inspectCard(Hand.MAX_CARDS).toString());   // Should be invalid
      
      // Test playCard method
      System.out.println("\nPlay all Cards:");
      for (int i = 0; i < Hand.MAX_CARDS; i++) {
         System.out.println("(" + i + ") Playing " + hand1.playCard().toString());
      }
      
      // Display empty Hand
      System.out.println("\nEmpty hand:");
      System.out.println("( " + hand1.toString() + " )");
      System.out.println("/* ------------------------------ /");
      
      // Running Deck Test 2 packs
      System.out.println("\nTesting Deck:\n");
      Deck testDeck = new Deck(2);
      testDeck.init(2);
      
      for (int i = 0; i < testDeck.getTopCard(); i++){
         System.out.print(testDeck.dealCard().toString() + " / ");
      }
      
      // Running Shuffle Test 2 packs
      System.out.println("\n\nTesting Shuffle Method:\n");
      testDeck.init(2);
      testDeck.shuffle();
      
      for (int i = 0; i < testDeck.getTopCard(); i++){
         System.out.print(testDeck.dealCard().toString() + " / ");
      }
      
      // Running Deck Test 1 packs
      System.out.println("\nTesting Deck:\n");
      Deck testDeckOne = new Deck(1);
      testDeckOne.init(1);
      
      for (int i = 0; i < testDeckOne.getTopCard(); i++){
         System.out.print(testDeckOne.dealCard().toString() + " / ");
      }
      
      // Running Shuffle Test 1 packs
      System.out.println("\n\n\nTesting Shuffle Method:\n");
      testDeckOne.init(1);
      testDeckOne.shuffle();
      
      for (int i = 0; i < testDeckOne.getTopCard(); i++){
         System.out.print(testDeckOne.dealCard().toString() + " / ");
      }
      
      //PHASE 4 TESTING
      System.out.println("\n\nPhase 4 Testing:\n");
      
      int numHands = -1;
      
      do {
         System.out.print("How many hands (1-10 Please): ");
         numHands = input.nextInt();
      } while (numHands < 1 || numHands > 10);
      
      System.out.println("\nHere are our hands from an unsorted deck: ");
      
      Hand[] testHands = new Hand[numHands];
      
      for (int k = 0; k < numHands; k++){
         testHands[k] = new Hand();
      }
      
      Deck testDeckTwo = new Deck(1);
      testDeckTwo.init(1);
     
      for (int i = 0; i < 52; i++){
         testHands[i % numHands].takeCard(testDeckTwo.dealCard());
      }
      
      for (int j = 0; j < numHands; j++){
         System.out.print("\nHand - ");
         System.out.print(testHands[j].toString());
      }        
      
      //Going again with shuffling
      System.out.println("\n\nHere are our hands from a shuffled deck: ");
      
      testDeckTwo.init(1);
      testDeckTwo.shuffle();
      
      for (int i = 0; i < numHands; i++){
         testHands[i].resetHand();
      }
      
      for (int m = 0; m < 52; m++){
         testHands[m % numHands].takeCard(testDeckTwo.dealCard());
      }
      
      for (int j = 0; j < numHands; j++){
         System.out.print("\nHand - ");
         System.out.print(testHands[j].toString());
      }        
      
   }

}

class Card {
   public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES };
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   /** 
    * Default Constructor. Initializes Card with value 'A' and suit SPADES.
    */
   public Card() {
      this('A',Suit.SPADES);
   }
   
   /** 
    * Constructor. Initializes Card with given value and Suit.
    * @param value   A char from 1-9,T,J,Q,K,A
    * @param suit    A value from the Suit enum (CLUBS,DIAMONDS,HEARTS,SPADES)
    */
   public Card(char value, Suit suit) {
      set(value,suit);
   }
   
   /** 
    * Provides a String representation of the Card.
    * @return        A String in the form of "[VALUE] of [SUIT]"
    */
   public String toString() {
      if (errorFlag == true) {
         return "[ invalid ]";
      }else {
         return value + " of " + suit;
      }
   }
   
   /** 
    * Updates the suit and value of the Card after validating data. 
    * The errorFlag is set to true if the provided data is not valid.
    * @param value   a char from 1-9,T,J,Q,K,A
    * @param suit    a value from the Suit enum (CLUBS,DIAMONDS,HEARTS,SPADES)
    * @return        true if valid value and suit were set, otherwise false
    */
   public boolean set(char value, Suit suit) {
      boolean isValid = isValid(value,suit);
      
      if (isValid) {
         this.value = Character.toUpperCase(value);
         this.suit = suit;
      }
      
      errorFlag = !isValid; // errorFlag is true if Card is not valid
      
      return isValid;
   }
   
   /**
    * Gets the Card's value.
    * @return        the Card's value
    */
   public char getValue() {
      return value;
   }
   
   /**
    * Gets the Card's Suit.
    * @return        the Card's Suit
    */
   public Suit getSuit() {
      return suit;
   }
   
   /**
    * Gets the status of the errorFlag.
    * @return        true if Card is invalid, otherwise false
    */
   public boolean getErrorFlag() {
      return errorFlag;
   }
   
   /**
    * Compares two Card objects for equality.
    * @param card    a Card to compare to this Card
    * @return        true if the Cards have equal value and Suit, otherwise false
    */
   public boolean equals(Card card) {
      return (this.getSuit() == card.getSuit()) && (this.getValue() == card.getValue());
   }
   
   /**
    * A helper method to validate Card data.
    * @param value   a char representing the value of the card
    * @param suit    a Suit enum object
    * @return        true if data is valid, otherwise false
    */
   private boolean isValid(char value, Suit suit) {
      boolean isValidValue = false;
      char[] values = {'1','2','3','4','5','6','7','8','9','T','J','Q','K','A'}; // Allowable values
      
      // Check if value is one of the valid characters
      for (char v : values) {
         if (Character.toUpperCase(value) == v) {
            isValidValue = true;
            break;
         }
      }
      
      return isValidValue; // Note: Suit is an enum type and does not need to be validated
   }
   
}

/**
 * This class is for the Hand and deals with phase two. The class elaborates on the Card class
 * and uses it to develop an array of objects calling on methods.
 */
class Hand {
   public static final int MAX_CARDS = 50;        //Constant set for class to terminate program
   private Card[] myCards;    //An array of cards
   private int numCards;      //The number of cards in "Hand"
   
   /**
    * Default Constructor. Initializes empty Hand.
    */
   public Hand(){
      resetHand(); //  Initializes empty hand
   }
   
   /**
    * Empties the array of Cards and resets the card counter.
    */
   public void resetHand(){
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }
   
   /**
    * Adds a given Card to the array if there is space.
    * @param Card    A Card to add to the Hand
    * @return        True if the Card was added to the Hand, otherwise false
    */
   public boolean takeCard(Card card){
      if(numCards < MAX_CARDS){
         myCards[numCards] = new Card(card.getValue(),card.getSuit());  // Creates a copy of the Card to add to the array
         numCards++;
         return true;
      } else {
         // The Hand is full
         return false;
      }
   }
   
   /**
    * Gets the last Card in the array. Removes the Card from the array
    * and decrements the counter.
    * @return        The Card to play
    */
   public Card playCard(){
      // Create a copy of the Card to be returned
      Card nextCard = new Card(myCards[numCards-1].getValue(), myCards[numCards-1].getSuit());
      
      // Remove the Card from the array and decrement the card count
      myCards[numCards-1] = null;
      numCards--;
      
      return nextCard;
   }
   
   /**
    * NEEDS work
    */
   public String toString(){
      String returnString = "";
      
      if (numCards > 0) {
         // Get the String version of each card in the array and add to returnString
         for(int i = 0; i < numCards; i++) {
            returnString += myCards[i].toString() + ", ";
         }      
         // Remove the last space and comma from the String for cleaner output
         returnString = returnString.substring(0, returnString.length()-2);
      }
      
      return returnString;
   }
   
   /**
    * Gets the number of Cards in the Hand
    * @return        The number of Cards in the Hand.
    */
   public int getNumCards(){
      return numCards;
   }
   
   /**
    * Gets the Card at a specific position in the Hand.
    * @param int     The position of the desired Card in the Hand's array
    * @return        The Card
    */
   public Card inspectCard(int k){
      // Return a copy of the Card if the given index is valid
      if (k < numCards) {
         return new Card(myCards[k].getValue(), myCards[k].getSuit());   
      } else {
         // Given index is invalid, generate and return an invalid card
         return new Card('-',Card.Suit.SPADES);
      }
      
   }

}

class Deck {
   private static Card[] masterPack = new Card[52];
   private Card[] cards;
   int topCard;
   int numPacks;
   
   /**
    * generates the "MasterPack" so that we don't have to generate cards over and over again
    */
   private static void allocateMasterPack(){
      if (masterPack[0] == null){
         char[] possValues = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
         Card.Suit[] possSuits = {Card.Suit.HEARTS, Card.Suit.CLUBS, Card.Suit.DIAMONDS, Card.Suit.SPADES};
         int cardCount = 0;
         
         for (int i = 0; i < 4; i++) {         
            for (int j = 0; j < 13; j++){
               masterPack[cardCount] = new Card(possValues[j],possSuits[i]);
               cardCount++;      
            }
         }
      }
   }
   
   /**
    * default constructor for Deck, defaulting to 1 pack in the deck
    */
   public Deck(){
      this(1);
   }
   
   /**
    * constructor for Deck, takes numPacks and generates an array of that many packs of cards
    */
   public Deck (int numPacks){
      topCard = numPacks * 52;
      this.numPacks = numPacks;
      
      cards = new Card[topCard];
   }
   
   /**
    * runs the deck from end to end and fills it with that card of the masterPack
    */
   public void init(int numPacks){
      allocateMasterPack();
      topCard = numPacks * 52;
      
      for (int i = 0; i < this.getTopCard(); i++){
         cards[i] = masterPack[i % 52];
      }
   }
   
   /**
    * uses random library to shuffle the deck from end to end
    */
   public void shuffle(){
      for (int i = 0; i < numPacks*52; i++){
         double randDouble = Math.random() * numPacks * 52;
         int randInt = (int) randDouble;
         
         Card tmpCard = cards[i];
         cards[i] = cards[randInt];
         cards[randInt] = tmpCard;
      }
   }
   
   /**
    * Deals the car off the top of the deck, erasing it from the deck
    */
   public Card dealCard(){
      if (cards.length > 0) {
         Card dealtCard = new Card(cards[topCard-1].getValue(), cards[topCard-1].getSuit());
         
         cards[topCard-1] = null;
         topCard--;
         
         return dealtCard;
      } else {
         // The deck is empty, return an invalid card
         return new Card('0',Card.Suit.SPADES);
      }      
   }
   
   /**
    * accessor for topCard
    */
   public int getTopCard() {
      return topCard;
   }
   
   /**
    * accessor for specific card in cards array
    */
   Card inspectCard(int k){
      Card tmpCard;
      if (k <= topCard){
         tmpCard = cards[k];
      }
      else{
         tmpCard = new Card('X',Card.Suit.SPADES);
      }
      
      return tmpCard;
   }
   
}
