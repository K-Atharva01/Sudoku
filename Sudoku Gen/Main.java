// Sudoku game
// Java mini project
// D6AD - Group 15

//import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		
//		Scanner scan = new Scanner(System.in);
		Create sud = new Create();		// Creates a sudoku object of Create class
		sud.Creater();
		sud.Poke();
		sud.CreateGUI();
		sud.SetVisible();
//		int op = 0;
//		while(op!=-1) {
//			System.out.print("OP (1:Print 2:GUI 3:Solution 4:New 5:exit) : ");
//			op = scan.nextInt();
//			switch(op) {
//			case 1:
//				sud.PrintQuest();
//				break;
//			case 2:
//				sud.CreateGUI();
//				sud.SetVisible();
//				break;
//			case 3:
//				System.out.println("Solution :");
//				sud.Print();
//				break;
//			case 4:
//				sud.NewTerminal();
//				System.out.println("New Sudoku Created");
//				break;
//			case 5:
//				op = -1;
//				break;
//			default:
//				System.out.println("Not Valid!");
//				break;
//			}
//		}
//		scan.close();
	}

}
