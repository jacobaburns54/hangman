
public class Letter {

	private String letter;
	private boolean hasBeenRevealed;
	
	public Letter(String letter) {
		this.letter = letter;
		this.hasBeenRevealed = false;
	}
	
	public String getLetter() {
		return letter;
	}
	
	public boolean isRevealed() {
		return hasBeenRevealed;
	}
	
	public void reveal() {
		hasBeenRevealed = true;
	}
	
	public boolean equals(Letter otherLetter) {
		if(letter.equals(otherLetter.getLetter())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return letter;
	}
}
