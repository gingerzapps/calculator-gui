import java.awt.*; 	//essential for visual components
import javax.swing.*; 	//essential for the visual components
import java.awt.event.*;	//essential for handling events such as ActionListener

//	source tutorial:
//	http://www.dreamincode.net/forums/topic/321933-creating-a-calculator-using-jframe/

public class Calculator extends JFrame implements ActionListener{ //visual classes need to extend JFrame, always
	public static void main(String[] args){
		Calculator calc = new Calculator();
	}
	
	JPanel[] row = new JPanel[5];		//first row of panel for buttons
	JButton[] button = new JButton[19];	//array of buttons
	String[] btnString = {	"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", ".", "/", "C", "SqrRt", "+/-", "=", "0"}; 	//array of strings that will label the buttons

	int[] dimW = {300, 45, 100, 90};	//our widths for the different components
	int[] dimH = {35, 40}; 	//our heights will be 35 for display and 40 for buttons
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]);	//for display
	Dimension regularDimension = new Dimension(dimW[1], dimH[1]);	//for most btns
	Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);	//for right column of btns
	Dimension zeroBtnDimension = new Dimension(dimW[3], dimH[1]);	//for zero btn
	
	boolean[] function = new boolean[4];	//booleans for other functions (add, subtract, mult, and divide)
	double[] temp = {0, 0};	//temporary doubles for calculations
	JTextArea display = new JTextArea(1, 20);	//create display area
	Font font = new Font("Times New Roman", Font.BOLD, 14);	//just for fun, use different font
	
	//begin constructor
	Calculator(){
		super("Calculator");
		setDesign();
		setSize(380, 250);
		setResizable(false);	// can not resize window, which can sometimes mess up layout
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GridLayout grid = new GridLayout(5, 5); 	//5 rows and 5 columns
		setLayout(grid);
			
		//set all four boolean values in 'function' array to false
		for(int i = 0; i < 4; i++){
			function[i] = false;
		}
		
		//FlowLayout manager drops components in an area from left to right and top to bottom in the order they're passed
		FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);	//only for row 1
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);	//1's are ints for horiz and vert gap
		
		//initializing JPanels
		for(int i = 0; i < 5; i++){
			row[i] = new JPanel();
		}
		//setting layout made to the rows
		for(int i = 1; i < 5; i++){
			row[i].setLayout(f2);
		}
		
		//SETTING UP COMPONENTS
		
		for(int i = 0; i < 19; i++){					//set up all 19 buttons
			button[i] = new JButton();			//initialize button
			button[i].setText(btnString[i]);	//set text from String[] value
			button[i].setFont(font);					//set font established above
			button[i].addActionListener(this);	//add action listener for button
		}
		
		display.setFont(font);			//set font
		display.setEditable(false);	//make the display text box un-editable, so a user can't type in the display
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);	//displays right to left
		
		//SET COMPONENT SIZES
		
		display.setPreferredSize(displayDimension);	//set size of display component
		for(int i = 0; i < 18; i++){	//set button sizes
			if(i >= 14) button[i].setPreferredSize(rColumnDimension);	//far right column has separate sizes
			button[i].setPreferredSize(regularDimension);	//rest of buttons have regular size
		}
		button[18].setPreferredSize(zeroBtnDimension);	//set size for zero (0) button
		
		//ADD COMPONENTS TO PANEL
		row[0].add(display);		// add display to row 1
		add(row[0]);					//add row 1 to panel
		
		for(int i = 0; i < 4; i++){
			row[1].add(button[i]);	//add first 4 buttons to row 2
		}
		row[1].add(button[14]);		//add right column button to row 2
		add(row[1]);						//add row 2 to panel
		
		for(int i = 4; i < 8; i++){
			row[2].add(button[i]);		//add next row of buttons
		}
		row[2].add(button[15]);		//add right column button to row 3
		add(row[2]);						//add row 3 to panel
		
		for(int i = 8; i < 12; i++){
			row[3].add(button[i]);	//add next row of buttons to row 4
		}
		row[3].add(button[16]);		//add right column button to row 4
		add(row[3]);						//add row 4 to panel
		
		row[4].add(button[18]);		//add 0 button
		for(int i = 12; i < 14; i++){	
			row[4].add(button[i]);	//add rest of normal buttons to row 5
		}
		row[4].add(button[17]);		//add right column button to row 5
		add(row[4]);						//add row 5 to panel
		
		setVisible(true);	
	}
	
	public final void setDesign(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e){
			
		}
	}
	
	public void actionPerformed(ActionEvent ae){ /** PERFORM ACTION WHEN BUTTONS PRESSED*/
		if(ae.getSource() == button[0]) display.append("7");
		if(ae.getSource() == button[1]) display.append("8");
		if(ae.getSource() == button[2]) display.append("9");
		if(ae.getSource() == button[3]){	//add function [0]
			temp[0] = Double.parseDouble(display.getText());
			function[0] = true;
			display.setText("");
		}
		if(ae.getSource() == button[4]) display.append("4");
		if(ae.getSource() == button[5]) display.append("5");
		if(ae.getSource() == button[6]) display.append("6");
		if(ae.getSource() == button[7]){	//subtract function[1]
			temp[0] = Double.parseDouble(display.getText());
			function[1] = true;
			display.setText("");
		}
		if(ae.getSource() == button[8]) display.append("1");
		if(ae.getSource() == button[9]) display.append("2");
		if(ae.getSource() == button[10]) display.append("3");
		if(ae.getSource() == button[11]){	//mult function[2]
			temp[0] = Double.parseDouble(display.getText());
			function[2] = true;
			display.setText("");
		}
		if(ae.getSource() == button[12]) display.append(".");
		if(ae.getSource() == button[13]){	//divide function[3]
			temp[0] = Double.parseDouble(display.getText());
			function[3] = true;
			display.setText("");
		}
		if(ae.getSource() == button[14]) clear();
		if(ae.getSource() == button[15]) getSqrt();
		if(ae.getSource() == button[16]) getPosNeg();
		if(ae.getSource() == button[17]) getResult();
		if(ae.getSource() == button[18]) display.append("0");
	}
	
	public void clear(){	/** RESET THE CALCULATOR*/
		try{
			display.setText("");		//resets the display to empty
			for(int i = 0; i < 4; i++){
				function[i] = false;		//sets the temporary functions back to false
			}
			for(int i = 0; i < 2; i++){
				temp[i] = 0;					//sets the temporary doubles back to 0
			}
		}
		catch(NullPointerException e){
			
		}
	}
	
	public void getSqrt(){ /** FIND SQUARE ROOT*/
		try{
			double value = Math.sqrt(Double.parseDouble(display.getText()));
			display.setText(Double.toString(value));	//set display to new value
		}
		catch(NumberFormatException e){
			
		}
	}
	
	public void getPosNeg(){ /** SWITCH NUMBER BETWEEN POSITIVE AND NEGATIVE*/
		try{
			double value = Double.parseDouble(display.getText());	
			if(value != 0){
				value = value * (-1);
				display.setText(Double.toString(value));
			}
		}
		catch(NumberFormatException e){
			
		}
	}
	
	public void getResult(){ /** PARSE INFO TO RETRIVE RESULT AND DISPLAY IN DISPLAY ROW*/
		double result = 0;
		temp[1] = Double.parseDouble(display.getText()); 	//our second temp number from display
		String temp0 = Double.toString(temp[0]);	//necessary string for text of first temp
		String temp1 = Double.toString(temp[1]);	//necessary string for text of second temp
		try{
			if(temp0.contains("-")){
				String[] temp00 = temp0.split("-", 2);	//split  into two strings at the dash (-)
				temp[0] = (Double.parseDouble(temp00[1]) * -1);	//puts string abck in double with real value
			}
			if(temp1.contains("-")){	//same as above with second temp element
				String[] temp11 = temp1.split("-", 2);
				temp[1] = Double.parseDouble(temp11[1]) * (-1);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			//start with multiplication because of the order of operations
			if(function[2] == true) result = temp[0] * temp[1];				//mult
			else if(function[3] == true) result = temp[0] / temp[1];		//divide
			else if(function[0] == true) result = temp[0] + temp[1];	//add
			else if(function[1] == true) result = temp[0] - temp[1];		//subtract
			
			display.setText(Double.toString(result));	//display result in display row
			
			for(int i = 0; 1 < 4; i++){
				function[i] = false;	//set all function booleans back to false
			}
		}
		catch(NumberFormatException e){
			
		}
	}
	
	
	
}


















