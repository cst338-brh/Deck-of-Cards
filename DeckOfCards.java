/*Heather McCabe
 *Brett Hansen
 *Randall Rood
 *CST 338
 *9/17/16
 *Module 3: Deck of Cards
 */
public class DeckOfCards {

   public static void main(String[] args) {      
      // Test Card class
      System.out.println("/* ----- Card class testing ----- */");
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
      System.out.println("*/ ------------------------------ */");
      
      // Phase 2
      Hand hand1 = new Hand();
      card1 = new Card('4',Card.Suit.CLUBS);
      card2 = new Card('6',Card.Suit.DIAMONDS);
      card3 = new Card('Q',Card.Suit.HEARTS);
      
      // TODO while(takeCard == true){    }

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
      } else {
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
   public static int MAX_CARDS = 50;        //Constant set for class to terminate program
   private Card[] myCards;    //An array of cards
   private int numCards;      //The number of cards in "Hand"
   
   /**
    * Default Constructor. Initializes empty Hand.
    */
   public Hand(){
      resetHand();
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
    * @return        True if hand is valid, otherwise false
    */
   public boolean takeCard(Card card){
      if(MAX_CARDS < myCards.length-1){
         myCards[numCards].set(card.getValue(), card.getSuit());
         numCards++;
         return true;
      }else return false;
   }
   
   /**
    * Gets the last Card in the array. Removes the Card from the array
    * and decrements the counter.
    * @return        The Card to play
    */
   public Card playCard(){
      Card nextCard = new Card(myCards[numCards].getValue(), myCards[numCards].getSuit()); //Create a temp card
      numCards--;
      return nextCard;
   }
   
   /**
    * NEEDS work
    */
   public String toString(){
      while(numCards > 0){
         numCards--;
         return myCards[numCards+1].toString();
      }
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
      Card nextCard = new Card(myCards[k].getValue(), myCards[k].getSuit()); //Create a temp card
      return nextCard;
   }

}

class Deck {
   
}
