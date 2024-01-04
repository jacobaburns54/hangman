import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private static Scanner userIn = new Scanner(System.in);
	private static int guesses = 0;
	private static int wrongGuesses = 0;
	private static String theWord;
	//returns true if char a exists in string b
	
	private static String getWord() throws FileNotFoundException {
		ArrayList<String> allWords = new ArrayList<>();
		File file = new File("words_alpha.txt");
		Scanner fromFile = new Scanner(file);

		while(fromFile.hasNext()) {
			String next = fromFile.next();
			allWords.add(next);
		}
		fromFile.close();
		
		for(int i = allWords.size()-1; i >= 0; i--) {
			if(allWords.get(i).length() < 4) {
				allWords.remove(i);
			}
		}
		
		System.out.println("Welcome to hangman! Enter your word to start or 'r' to pick a random word: ");

		theWord = userIn.next().toUpperCase();
		
		int allWordsSize = allWords.size();
		
		if(theWord.equals("R")) {
			int idx = (int)((Math.random()) * allWordsSize);
			System.out.println("word is word at index " + idx);
			theWord = allWords.get(idx).toUpperCase();
		}

		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		return theWord;
	}
	
	private static void gameLost() throws FileNotFoundException {
		System.out.print("\n\n\n\n\nSorry! you couldn't guess the word. It was " + theWord.toUpperCase() + ". Try again? (Y/N)");
		String option = userIn.next();
		
		if(option.toUpperCase().equals("Y")) {
			String str = getWord();
			boolean isWon = playGame(str);
			if(isWon) {
				gameWon();
			}
			else {
				gameLost();
			}
		}
	}
	
	private static void gameWon() throws FileNotFoundException {
		System.out.print("\n\n\n\n\nCongratulations! you guessed the word " + theWord.toUpperCase() + " in " + guesses + " guesses! play again? (Y/N)");
		String option = userIn.next();
		
		if(option.toUpperCase().equals("Y")) {
			String str = getWord();
			boolean isWon = playGame(str);
			if(isWon) {
				gameWon();
			}
			else {
				gameLost();
			}
		}
	}
	
	private static boolean playGame(String theWord) {
		guesses = 0;
		wrongGuesses = 0;
		
			String hangmanStages[] = {
				
				   "  ----------\n"
				 + "  |        |\n"
				 + "  |\n"
			     + "  |\n"
				 + "  |\n"
				 + "  |\n"
			     + "  |\n"
			     + "  |\n"
			     + "  |\n"
				 + "  |\n"
			     + "-----",
			     
			       "  ----------\n"
			     + "  |        |\n"
			     + "  |       --- \n"
			     + "  |      | ツ |\n"  
			     + "  |       --- \n"
			     + "  |\n"
			     + "  |\n"
			     + "  |\n"
			     + "  |\n"
			     + "  |\n"
			     + "-----",
			     
			       "  ----------    \n"
			     + "  |        |    \n"
			     + "  |       ---   \n"
			     + "  |      | ツ | \n"  
			     + "  |       ---   \n"
			     + "  |        |    \n"
			     + "  |        |    \n"
			     + "  |\n"
			     + "  |\n"
			     + "  |\n"
			     + "-----",
			     
			       "  ----------    \n"
			     + "  |        |    \n"
			     + "  |       ---   \n"
			     + "  |      | ツ | \n"  
			     + "  |       ---   \n"
			     + "  |       /|    \n"
			     + "  |      / |    \n"
			     + "  |\n"
			     + "  |\n"
			     + "  |\n"
			     + "-----",
			     
			       "  ----------    \n"
			     + "  |        |    \n"
			     + "  |       ---   \n"
			     + "  |      | ツ |  \n"  
			     + "  |       ---   \n"
			     + "  |       /|\\   \n"
			     + "  |      / | \\  \n"
			     + "  |\n"
			     + "  |\n"
			     + "  |\n"
			     + "-----",
			     
			       "  ----------     \n"
			     + "  |        |     \n"
			     + "  |       ---    \n"
			     + "  |      | ツ |  \n"  
			     + "  |       ---    \n"
			     + "  |       /|\\   \n"
			     + "  |      / | \\  \n"
			     + "  |       /      \n"
			     + "  |      /       \n"
			     + "  |\n"
			     + "-----",

			       "  ----------     \n"
			     + "  |        |     \n"
			     + "  |       ---    \n"
			     + "  |      | ツ |  \n"  
			     + "  |       ---    \n"
			     + "  |       /|\\   \n"
			     + "  |      / | \\  \n"
			     + "  |       / \\     \n"
			     + "  |      /   \\    \n"
			     + "  |\n"
			     + "-----"
			     
				};





			final int WORD_LENGTH = theWord.length();
			final Letter[] charArr = new Letter[WORD_LENGTH];
			for(int i = 0; i < WORD_LENGTH; i++) {
				charArr[i] = new Letter(theWord.substring(i, i+1).toUpperCase());
			}

			guesses = 0;
			System.out.println(hangmanStages[0]);
			
			ArrayList<Letter> guessedLetters = new ArrayList<>();
			while(wrongGuesses < 6) {
				System.out.println("Make your guess!: ");
				
				for(Letter lett : charArr) {
					if(lett.isRevealed()) {
						System.out.print(lett + " ");
					}
					else {
						System.out.print("_ ");
					}
				}
				
				System.out.print("\n");
				
				boolean allRevealed = true;
				for(Letter lett : charArr) {
					if(!lett.isRevealed()) {
						allRevealed = false;
					}
				}
				if(allRevealed) {
					return true;
				}
				
				Letter guess = new Letter(userIn.next().substring(0, 1).toUpperCase());
				guesses++;					
				
//				System.out.println(guess.getLetter());
//				System.out.println(theWord);

				if(theWord.contains(guess.getLetter())) {
//					System.out.println(theWord);
//					System.out.println("letter is in word\n");					
					
					for(int i = 0; i < charArr.length; i++) {
//						System.out.println("does " + guess.getLetter() + " equal " + charArr[i].getLetter() + "?");
						if(charArr[i].getLetter().equals(guess.getLetter())) {
//							System.out.println("letter " + charArr[i] + " at index " + i + " in word has been revealed");
							charArr[i].reveal();
						}
					}
					System.out.println(hangmanStages[wrongGuesses]);
					System.out.println("Guessed letters: " + guessedLetters);
				} 
				else {
//					System.out.println("letter is not in word");
					wrongGuesses++;
					guessedLetters.add(guess);
					System.out.println(hangmanStages[wrongGuesses]);
					System.out.println("Guessed letters: " + guessedLetters);
				}
			}
			return false;
	}
	
	private static void main(String[] args) throws FileNotFoundException {
		String str = getWord();
		boolean isWon = playGame(str);
		if(isWon) {
			gameWon();
		}
		else {
			gameLost();
		}
	}
}
