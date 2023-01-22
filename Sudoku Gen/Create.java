import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Create {

	int[][] puzzle = new int[9][9];		// Stores the Answer Key (Solution)
	int[][] quest = new int[9][9];		// Stores the Question (Puzzle)
	int[][] ans = new int[9][9];		// Stores the Answer submitted by the user

	int br,bc;		// Used for finding the 3x3 box that the element belongs in
	int e;			// Used to check if program is stuck in some place
	int rf=0,cf=0;		// Used for backtracking
	
	int box=0;		// Gives the 3x3 box (1 to 9)
	
	JLabel[][] lab = new JLabel[9][9];		// Creates the labels which store the fixed numbers
	JTextField[][] text = new JTextField[9][9];		// Creates the textfields in which numbers are to be inserted
	
	JFrame frame = new JFrame();		// Creates the Base JFrame window
	
	JPanel[] SudPanel = new JPanel[9];		// Creates the base JPanel for 9 3x3 boxes
	
	void Creater() {				// Creates the Matrix
		
		int flag = CreateMatrix();
		while (flag==-2) {
			flag = CreateMatrix();
			
		}
		
		rf=0;
		cf=0;
		
		CopyMatrix();
		
	}
	
	int CreateMatrix(){		// Fills the matrix
		
		e=0;
		int r=0,c=0;
		
		for(r=rf;r<9;r++) {
			for(c=0;c<9;c++) {				// iterates through the elements
				
				int num = MatrixCheck(r, c);
				while(num ==-1) {
					num = MatrixCheck(r, c);
					
					e+=1;
					if(e>=20) {
						rf=r;
						cf=c;
						
						for(int count = 0;count<c;count++) {
							puzzle[r][count]=0;
						}
						
						e=0;
						return -2;
					}
					
				}
				puzzle[r][c]=num; 			// Sets the matrix value
				
			}

		}

		return 0;
	}
	
	void CopyMatrix() {			// Copies the Solution (puzzle) into Question (quest)
		
		int r=0,c=0;
		for(r=0;r<9;r++) {
			for(c=0;c<9;c++) {
				quest[r][c] = puzzle[r][c];
			}
	
		}
		
	}
	
	void SetZero() {		// Sets the Whole Matrix to 0 
		
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				puzzle[r][c] = 0;
			}
	
		}
		
	}
	
	int NumberGen() {		// Generates a random number between 1 and 9
		
		Random rand = new Random();
		int num = rand.nextInt(9)+1;
		return num;
		
	}
	
	int HV_Check(int r, int c, int num) {		// Checks Horizontal and vertical compatibility of num. Returns -1 if repeated
		
		int flag=0;
		for(int j=0;j<9;j++) {
			if(puzzle[r][j]==num) {
				flag=-1;
				break;
			}
		}
		
		for(int i=0;i<9;i++) {
			if(puzzle[i][c]==num) {
				flag=-1;
				break;
			}
		}
		
		return flag;
	}
	
	int BoxCheck(int r, int c, int num) {		// Checks 3x3 Box compatibility of num. Returns -1 if repeated
		int flag = 0;
		CheckBox(r,c);
		for(int i=br;i<(br+3);i++) {
			for(int j=bc;j<(bc+3);j++) {
				if(i==r&&j==c)
					continue;
				if(puzzle[i][j]==num) {
					flag=-1;
					break;
				}
			
			}
		}
		
		return flag;
	}
	
	int MatrixCheck(int r, int c) {		// Generates num and Checks Both HV & Box. Returns num if compatible -1 if not
		
		int num = NumberGen();

		if(HV_Check(r, c, num)==-1) {
			return -1;
		}
		else if(BoxCheck(r, c, num)==-1) {
			return -1;
		}
		
		return num;
		
	}
	
	void CheckBox(int r, int c) {		// Checks which 3x3 box the element belongs to
		if(r>=0&&r<3) {
			if(c>=0&&c<3) {
				br=0;bc=0;
			}
			if(c>=3&&c<6) {
				br=0;bc=3;
			}
			if(c>=6&&c<9) {
				br=0;bc=6;
			}
		}
		else if(r>=3&&r<6) {
			if(c>=0&&c<3) {
				br=3;bc=0;
			}
			if(c>=3&&c<6) {
				br=3;bc=3;
			}
			if(c>=6&&c<9) {
				br=3;bc=6;
			}
		}
		else {
			if(c>=0&&c<3) {
				br=6;bc=0;
			}
			if(c>=3&&c<6) {
				br=6;bc=3;
			}
			if(c>=6&&c<9) {
				br=6;bc=6;
			}
		
		}
		
	}
	
	void Print() {					// Prints the entire Matrix
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				System.out.print(puzzle[r][c]+"\t");
			}
			System.out.print("\n");
		}
	}
	
	void PrintQuest() {		// Prints the question
		
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				System.out.print(quest[r][c]+"\t");
			}
			System.out.print("\n");
		}
		
	}
	
	void Poke() {		// Pokes holes (replace with 0) in the matrix to produce question
		
		Random random = new Random();
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				int chance = random.nextInt(3)+1;
				if(chance==1) {
					quest[r][c]=0;
				}
			}
	
		}
		
	}

//==========================================================================================
//											GUI
//==========================================================================================
	
	public void CreateGUI() {		// Creates the grid and the rest of the GUI
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(615,807);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.gray);
		frame.setLayout(null);
		
		CreateGrid();
		
		JPanel toolbox = new JPanel();
		toolbox.setBorder(new LineBorder(Color.RED, 3));
		toolbox.setLayout(new GridLayout(2, 2, 5, 5));
		toolbox.setBounds(0, 600,600,170);
		
		JButton submit = new JButton();
		submit.setText("Submit");
		toolbox.add(submit);
		
		JButton newgame = new JButton();
		newgame.setText("New");
		toolbox.add(newgame);
		
		JButton exit = new JButton();
		exit.setText("Exit");
		toolbox.add(exit);
		
		frame.add(toolbox);
		submit.addActionListener(e->Submit());
		newgame.addActionListener(e->New());
		exit.addActionListener(e->Exit());
	}
	
	void CreateGrid() {		// Creates and adds the grid to the frame
		
		JPanel Base = new JPanel();
		Base.setLayout(new GridLayout(3,3,5,5));
		Base.setBorder(new LineBorder(Color.RED, 3));
		Base.setBounds(0,0,600,600);
		frame.add(Base);		
		
		for(int i=0;i<9;i++) {
			
			SudPanel[i] = new JPanel();
			SudPanel[i].setLayout(new GridLayout(3, 3, 2, 2));
			SudPanel[i].setBorder(new LineBorder(Color.GREEN, 1));
			Base.add(SudPanel[i]);
		}
		SetNum();
		
	}
	
	void SetNum() {		// Sets the numbers
		int r=0,c=0;
		for(r=0;r<9;r++) {
			for(c=0;c<9;c++) {
				AddNum(quest[r][c],r,c);
			}
		}
	}
	
	void AddNum(int num, int r, int c) {	// If not blank (!0) uses label else uses textfield

		Integer n = num;
		
		if(n!=0) {
			lab[r][c] = new JLabel("Blank");
			lab[r][c].setText(null);
			lab[r][c].setText(n.toString());
			lab[r][c].paintImmediately(lab[r][c].getVisibleRect());
			lab[r][c].setHorizontalAlignment(JLabel.CENTER);
			lab[r][c].setFont(new Font("Calibri", Font.BOLD, 30));
			lab[r][c].setBackground(Color.WHITE);
				
			FindBox(r, c);
			SudPanel[box].add(lab[r][c]);
			SudPanel[box].repaint();
		}
		else {
			text[r][c] = new JTextField();
			text[r][c].setHorizontalAlignment(JTextField.CENTER);
			text[r][c].setFont(new Font("Calibri", Font.BOLD, 30));
			FindBox(r, c);
			SudPanel[box].add(text[r][c]);
		}
			
	}
	
	void ChangeNum() {		// Sets the numbers
		int r=0,c=0;
		for(r=0;r<9;r++) {
			for(c=0;c<9;c++) {
				AddNewNum(quest[r][c],r,c);
			}
		}
	}
	
	void RemoveLabel() {				// Removes all the labels and textboxes
		for(int row =0;row<9;row++) {
			for(int col=0;col<9;col++) {
				FindBox(row, col);
				SudPanel[box].removeAll();
			}
		}
	}
	
	void AddNewNum(int num, int r, int c) {		// If not blank (!0) uses label else uses textfield
		Integer n = num;
		
		if(n!=0) {
			lab[r][c] = new JLabel("Blank");
			lab[r][c].setText(null);
			lab[r][c].setText(n.toString());
			lab[r][c].paintImmediately(lab[r][c].getVisibleRect());
			lab[r][c].setHorizontalAlignment(JLabel.CENTER);
			lab[r][c].setFont(new Font("Calibri", Font.BOLD, 30));
			lab[r][c].setBackground(Color.WHITE);
				
			FindBox(r, c);
			SudPanel[box].add(lab[r][c]);
			SudPanel[box].repaint();
		}
		else {
			text[r][c] = new JTextField();
			text[r][c].setHorizontalAlignment(JTextField.CENTER);
			text[r][c].setFont(new Font("Calibri", Font.BOLD, 30));
			FindBox(r, c);
			SudPanel[box].add(text[r][c]);
		}
			
	}
	
	void Submit() {			// Creates array of answers
		int r=0,c=0,emptyCounter=0,choice=3;
		for(r=0;r<9;r++) {
			for(c=0;c<9;c++) {
				if(quest[r][c]==0) {
					if(text[r][c].getText().equalsIgnoreCase("")) {
						ans[r][c] = 0;
						emptyCounter+=1;
					} else
						try {
							ans[r][c] = Integer.parseInt(text[r][c].getText());
						} 
						catch (NumberFormatException e) {
							ans[r][c] = 0;
							emptyCounter+=1;
						}
					
				}
				else {
					ans[r][c] = quest[r][c];
				}
			}
		}
		if(CheckAns() == 1) {
			JOptionPane.showConfirmDialog(null, "Wrong Answers have been removed", "Wrong Answer", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			if(emptyCounter==0) {
				choice = JOptionPane.showConfirmDialog(null, "Congratulations, you win\nDo you want to play again ?", "You Win!!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showConfirmDialog(null, "No Incorrect Answers", "Correct So Far", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		}
		if(choice==0) {
			New();
		}
	}
	
	int CheckAns() {		// Checks Answer and replaces wrong digits with "" (null). If wrong found returns 1
		int flag = 0;
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				if(ans[r][c]!=puzzle[r][c]&&!(text[r][c].getText()).equals("")) {
					flag = 1;
					try {
						text[r][c].setText("");
					} 
					catch (NullPointerException e) {
						
					}
				}
			}
		}
		
		return flag;		
	}
	
	void FindBox(int r, int c) {		// Checks which 3x3 box the element belongs to
		if(r>=0&&r<3) {
			if(c>=0&&c<3) {
				box=0;
			}
			if(c>=3&&c<6) {
				box=1;
			}
			if(c>=6&&c<9) {
				box=2;
			}
		}
		else if(r>=3&&r<6) {
			if(c>=0&&c<3) {
				box=3;
			}
			if(c>=3&&c<6) {
				box=4;
			}
			if(c>=6&&c<9) {
				box=5;
			}
		}
		else {
			if(c>=0&&c<3) {
				box=6;
			}
			if(c>=3&&c<6) {
				box=7;
			}
			if(c>=6&&c<9) {
				box=8;
			}
		
		}
		
	}
		
	void SetVisible() {		// Sets the frame visible
		frame.setVisible(true);
	}
	
	void New() {				// Creates a new Sudoku Puzzle
		frame.setVisible(false);
		SetZero();
		Creater();
		CopyMatrix();
		Poke();
		RemoveLabel();
		ChangeNum();
		SetVisible();
	}
	void NewTerminal() {
		SetZero();
		Creater();
		CopyMatrix();
		Poke();
	}
	
	void Exit() {		// Exits the program
		System.exit(0);
	}
	
}
