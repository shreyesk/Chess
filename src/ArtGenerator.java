import java.util.Arrays;
import java.util.Collections;

public class ArtGenerator {
	
	static int 	numOfPieces,
				numWhite,
				numBlack;
	
	static char[]	blackPieces,
					blackPossibilities = {'p', 'n', 'b', 'r', 'q', 'k'},
					whitePieces,
					whitePossibilities = {'P', 'N', 'B', 'R', 'Q', 'K'},
					combinedPieces;
	
	static int[][] squares;
	
	static String fen = "";
	
	public static void main(String[] args) {

		for(int i = 0; i < 100; i++) {
			
			fen = "";
			
			decideNumOfPieces();
			splitIntoBlackAndWhite();
			deriveBlackSpecifics();
			deriveWhiteSpecifics();
			selectSquaresOfBoard();
			combineWhiteAndBlack();
			shuffleAllPieces();
			populateSelectedSquares();
			
			System.out.println(fen);
			
		}

	}

	private static void populateSelectedSquares() {
		
		int piecePointer = 0;
		for(int row = 0; row < 8; row ++) {
			for(int col = 0; col < 8; col++) {
				if(!containedInSquares(row, col)) {
					fen += "1";
				} else {
					fen += combinedPieces[piecePointer];
					piecePointer++;
				}
			}
			fen += "/";
		}
		
	}

	private static boolean containedInSquares(int row, int col) {
		
		for(int i = 0; i < squares.length; i++) {
			if(row == squares[i][0]) {
				if(col == squares[i][1]) {
					return true;
				}
			}
		}
		return false;
		
	}

	private static void shuffleAllPieces() {
		
		for(int i = 0; i < combinedPieces.length; i++) {
			int randIndex = (int) (Math.round(Math.random() * (combinedPieces.length - 1)));
			char temp = combinedPieces[i];
			combinedPieces[i] = combinedPieces[randIndex];
			combinedPieces[randIndex] = temp;
		}
		
	}

	private static void combineWhiteAndBlack() {
		
		combinedPieces = new char[numOfPieces];
		for(int i = 0; i < blackPieces.length; i++) {
			combinedPieces[i] = blackPieces[i];
		}
		for(int i = blackPieces.length; i < blackPieces.length + whitePieces.length; i++) {
			combinedPieces[i] = whitePieces[i - blackPieces.length];
		}
		
	}

	private static void selectSquaresOfBoard() {
		
		squares = new int[numOfPieces][2];
		for(int i = 0; i < numOfPieces; i++) {
			int row = (int) (Math.round(Math.random() * 8));
			int col = (int) (Math.round(Math.random() * 8));
			squares[i][0] = row;
			squares[i][1] = col;
		}
		
	}

	private static void deriveWhiteSpecifics() {
		
		whitePieces = new char[numWhite];
		for(int i = 0; i < numWhite; i++) {
			int whichPiece = (int) (Math.round(Math.random() * 5));
			whitePieces[i] = whitePossibilities[whichPiece];
		}
		
	}

	private static void deriveBlackSpecifics() {
		
		blackPieces = new char[numBlack];
		for(int i = 0; i < numBlack; i++) {
			int whichPiece = (int) (Math.round(Math.random() * 5));
			blackPieces[i] = blackPossibilities[whichPiece];
		}
		
	}

	private static double sumOfWeights(double[] weights, int i) {
		
		double sum = 0;
		for(int j = 0; j < i; j++) {
			sum += weights[j];
		}
		return sum;
		
	}

	private static void splitIntoBlackAndWhite() {
		
		numWhite = (int) Math.round(Math.random() * numOfPieces);
		numBlack = numOfPieces - numWhite;
		
	}

	private static void decideNumOfPieces() {
		
		int totalPossible = 64;
		numOfPieces = (int) Math.round(Math.random() * totalPossible);
		
	}

}