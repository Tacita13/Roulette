/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

/*This is a game called Roulette in which the player bets an amount for one 
or more rounds on either a number(0-36), a color (red or black) or a 
column(left, middle, right).
All the inputs must be given in numbers ie. every answer has its number and 
the game has its own user input control. After the input the game compares the
result of a dice(random number) with the given bet and shows if the player has
won or lost. Additionally the player starts with a back account up to 1000euros
and if he loses the his bet will be substracted, vice versa if he wins added.
*/
   
package roulette;
import java.util.Scanner;
import java.util.Random;
public class Roulette {
    
static Scanner in = new Scanner(System.in);
static int balance=1000, add, sub, answerBet, answerRound, answerMenu, answerGive, rounds, counter;
static boolean won, valid;   
static Random number = new Random();
static int ComputerValue;
   
//Shows if the player has won/lost and either add/sub bet from bank account.
public static void cashCount(boolean won, int answerbet){
    add=answerBet;
    if (won==true){
    balance=balance+add;
    System.out.println("Your won!"); 
    System.out.printf("Your current balance is €%s. \n", balance);
    } //Shows current balance in the bank accoun
     else{
        balance=balance-add;
    System.out.println("You lost :( ");
    System.out.printf("Your current balance is €%s. \n", balance);
    }
}
/*The dice is the random number that depending on the 3 different game modes
calculates the CompuerValue differentlly (ie. ==,%2,%3)
*/
   public static void dice(){
    if(answerMenu==0){ 
ComputerValue=number.nextInt(36); //random number from 0 to 36
    System.out.println("The ball is rolling...");
    System.out.printf("The ball has reachead number %s.\n", ComputerValue);
    } // Shows the random number without any changes
   else if(answerMenu==1){ 
ComputerValue=number.nextInt(36);
    System.out.println("The ball is rolling...");
    System.out.printf("The ball has reachead number %s.\n", ComputerValue);
ComputerValue=ComputerValue%2;
    }
  else if(answerMenu==2){  
ComputerValue=number.nextInt(36);
    System.out.println("The ball is rolling...");
    System.out.printf("The ball has reachead number %s.\n", ComputerValue);
ComputerValue=ComputerValue%3;
            }
    }

/*Evaluates if the user input for the three game modes is valid or not ie a 
  number that stays between the ranges needed. If evaluation sheds false
  an error message will be shown and the last question will be repeated*/
//note that I did not use int readInt and assume we have enough freedom of choice
public static void readInt(){
valid=false;
while(valid==false){ //loop insures a valid response
  if (answerMenu==0){ //Number mode
         System.out.println("Please enter a number from 0 to 36.\n");
         if (in.hasNextInt() ){
             answerGive=in.nextInt();
             if (answerGive<0 || answerGive > 36){             
                 System.out.println("Error, please enter a valid input.\n");
            }
            else{
         valid=true;
         }	
    }
         else {
                  System.out.println("Error, please enter a number. \n");
                  in.next();
         }
}
    else if (answerMenu==1){ //Color mode
        System.out.println("Choose either (0)red or (1)black.");
        if (in.hasNextInt() ){
           answerGive=in.nextInt();
           if (answerGive<0 || answerGive > 1){
        System.out.println("Error, please enter a valid input.\n");
           }
           else{
        valid=true;
            }	
         }
        else {
             System.out.println("Error, please enter a number. \n");
                  in.next();
         }
    }
 else if (answerMenu==2){ //Column mode
        System.out.println("Please choose a (1)left, (2)middle or (3)right column.\n");
       if (in.hasNextInt() ){
          answerGive=in.nextInt();
          if (answerGive<1 || answerGive > 3){
       System.out.println("Error, please enter a number. \n");
          }
          else{
       valid=true;
         }	
         }
       else {
             System.out.println("Error, please enter a number. \n");
                  in.next();
         }
    }
  
}
}
/* Not only is it then in which all the methods come together, but we also have:
greetings, main questions(rounds, game mode, bet), exit and the evaluation of
every user input with added error messages and Restart of the questions.
Finally here it is evaluated if the player won/lost.
*/
public static void main(String[] args) {
 //Greeting
 System.out.println("*******************");       
 System.out.println("Welcome to Roulette");
 System.out.println("*******************\n");    
 System.out.println("How many rounds would you like to play? If none press (0) to exit.\n");
 valid=false; //Gives the number of rounds and or exists
 while(valid==false){ //if all of the input is valid it will become true and stop
    if(in.hasNextInt()){ //Makes sure the input is a number
    rounds= in.nextInt();
     if(rounds<0){ //Makes sure that it is the correct input
        System.out.println("Error, please enter 1 or more rounds or press (0) to exit. \n");
        in.next();
     }
     else if(rounds==0){
        System.out.println("We are sad to see you go. :( ");
        System.exit(0); //exit mode with 0
        }
     else {
        valid=true;
        for(counter=1; counter<=rounds; counter++){ //loop for the number of rounds
            System.out.println("Please choose between (0)Number, (1)Color or (2)Column.\n");
            if(in.hasNextInt()){ //Chooses the game mode
                answerMenu = in.nextInt();
                if(answerMenu<0 || answerMenu>2){
                    System.out.println("Error, please choose between (0)Number, (1)Color or (2)Column. \n");
                    valid=false;
                }
                else{
                    valid=true;
                    System.out.println("How much would you like to bet?\n" );
                    if(in.hasNextInt()){ //Gives the bet           
                        answerBet = in.nextInt();
                        if(answerBet<1){
                            System.out.println("Error, please enter a bet greater than 1€. \n");
                            System.out.println("Reset Start. Please enter the number of rounds.\n");
                             valid=false;
                        }
                        else{ //use of the methods readInt, dice and cashCount
                            valid=true;
                            readInt();
                            dice();    
                            if(answerGive==ComputerValue){
                                won=true;
                            }
                            else{
                              won=false;
                           }
                            cashCount(won, add);                    
                        }
                    }  
                    else{
                     System.out.println("Error, please enter a valid input.");              
                     valid=false; 
                    }
                }
                }
            else{
            System.out.println("Error, please enter a number value.\n ");
            valid=false;
                    }
        }
        } 
    }
   else {
       System.out.println("Reset Start. Please enter the number of rounds.\n");
       in.next();  //Instead of giving the same question, the player will be redirected to the main menu
        }   
   }
}
}

