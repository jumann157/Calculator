import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A simple calculator that works in order operation. Returns in int only.
 *  Better used with whole number to avoid miscalculations.
 *
 * @Juman
 * @v3
 */
public class Calculator implements ActionListener
{
    private JFrame frame;
    private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JButton add, sub, div, mult, ac, eq;
    private JPanel buttonPanel;
    private JPanel textFieldPanel;
    private JTextField textField;

    public Calculator()
    {
        makeFrame();
    }

    private void makeFrame()
    {

        frame = new JFrame();
        frame.setTitle("MyCalculator");
        frame.setSize(350,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textFieldPanel = new JPanel(new BorderLayout());
        textField = new JTextField(350);
        textField.setEditable(false);
        textFieldPanel.setBackground(Color.WHITE);
        textField.setPreferredSize(new Dimension(350, 75));
        textFieldPanel.add(textField, BorderLayout.CENTER);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBorder(null);
        textField.setFont(new Font("Arial", Font.BOLD, 35));

        buttonPanel = new JPanel(new GridLayout(0,4,-5,-5));
        buttonPanel.setBackground(Color.WHITE);    

        for(int i = 1; i <= 16; i++) {
            switch(i) {
                case 1:
                    b7 = new JButton("7");
                    buttonPanel.add(b7);
                    break;
                case 2:
                    b8 = new JButton("8");
                    buttonPanel.add(b8);
                    break;
                case 3:
                    b9 = new JButton("9");
                    buttonPanel.add(b9);
                    break;
                case 4:
                    div = new JButton("÷");
                    div.setForeground(Color.BLUE);
                    buttonPanel.add(div);
                    break;
                case 5:
                    b4 = new JButton("4");
                    buttonPanel.add(b4);
                    break;     
                case 6:
                    b5 = new JButton("5");
                    buttonPanel.add(b5);
                    break;
                case 7:
                    b6 = new JButton("6");
                    buttonPanel.add(b6);
                    break;
                case 8:
                    mult = new JButton("x");
                    mult.setForeground(new Color(0,0,255,255));
                    buttonPanel.add(mult);
                    break;
                case 9:
                    b1 = new JButton("1");
                    buttonPanel.add(b1);
                    break;
                case 10:
                    b2 = new JButton("2");
                    buttonPanel.add(b2);
                    break;
                case 11:
                    b3 = new JButton("3");
                    buttonPanel.add(b3);
                    break;
                case 12:
                    sub = new JButton("-");
                    sub.setForeground(Color.BLUE);
                    buttonPanel.add(sub);
                    break;
                case 13:
                    ac = new JButton("AC");
                    ac.setForeground(new Color(255,200,3,255));
                    buttonPanel.add(ac);
                    break;
                case 14:
                    b0 = new JButton("0");
                    buttonPanel.add(b0);
                    break;
                case 15:
                    eq = new JButton("=");
                    eq.setForeground(Color.RED);
                    buttonPanel.add(eq);
                    break;
                case 16:
                    add = new JButton("+");
                    add.setForeground(Color.BLUE);
                    buttonPanel.add(add);
                    break;

            }
        }
        

        Font font = new Font("Arial", Font.BOLD, 35);
        for(Component c: buttonPanel.getComponents()) {
            if(c instanceof JButton) {
                ((JButton) c).setFont(font);
                ((JButton) c).setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
                ((JButton) c).addActionListener(this);

            }
        }
        frame.add(textFieldPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b0) {
            handleNumberButton(b0);
        }
        else if (e.getSource() == b1) {
            handleNumberButton(b1);
        }
        else if (e.getSource() == b2) {
            handleNumberButton(b2);
        }
        else if (e.getSource() == b3) {
            handleNumberButton(b3);
        }
        else if (e.getSource() == b4) {
            handleNumberButton(b4);
        }
        else if (e.getSource() == b5) {
            handleNumberButton(b5);
        }
        else if (e.getSource() == b6) {
            handleNumberButton(b6);
        }
        else if (e.getSource() == b7) {
            handleNumberButton(b7);
        }
        else if (e.getSource() == b8) {
            handleNumberButton(b8);
        }
        else if (e.getSource() == b9) {
            handleNumberButton(b9);
        }
        else if (e.getSource() == add) {
            textField.setText(textField.getText() + "+");
        }
        else if (e.getSource() == sub) {
            textField.setText(textField.getText() + "-");
        }
        else if (e.getSource() == mult) {
            textField.setText(textField.getText() + "x");
        }
        else if (e.getSource() == div) {
            textField.setText(textField.getText() + "÷");
        }
        else if (e.getSource() == ac) {
            textField.setText("0");
        }
        else if (e.getSource() == eq){
            String text = textField.getText();

            if(text.isEmpty()) {
                textField.setText("0");
            }
            else {
            	System.out.println(text);
            	String postfix = infixToPostfix(text);
            	System.out.println(postfix);
            	int answer = evaluation(postfix);
                textField.setText(String.valueOf(answer));
                
               }
            }     
        }
    
    public static String infixToPostfix(String e) {
		StringBuilder output = new StringBuilder();
		Stack<Character> operator = new Stack<>();
	
		
		for(int i = 0; i < e.length(); i++) {
			char pre = e.charAt(i);
			
		 if(pre >= '0' && pre <= '9') {
				output.append(pre);
				System.out.println(output + "-> output");
			}
			else if(isOperator(pre)) {
				output.append(' ');
				if(operator.isEmpty()) {
					operator.push(pre );
				}
				else {
					while(!operator.isEmpty() && isHigherPrecendence(operator.peek(), pre)) {
						output.append(operator.pop());
						output.append(' ');
					}
					operator.push(pre);
					System.out.println(operator + "-> operator");
				}
			}
	     }
		
		while(!operator.isEmpty()) {
			output.append(' ');
			output.append(operator.pop());
		}
		
		System.out.println(output + "-> output");
		return output.toString();
	}
    
    public static boolean isHigherPrecendence(char op1 , char op2) {
		boolean value = false;
		if((op1 == 'x' || op1 == '÷') && (op2 == '+' || op2 == '-')) {
			value = true;
		}
		else if ((op1 == 'x' || op1 == '÷') && (op2 == 'x' || op2 == '÷')) {
			value = true;
		}
		else if ((op1 == '+' || op1 == '-') && (op2 == '+' || op2 == '-')) {
			value =  true;
		}
		else { 
			value = false;
		}
		
		return value;
	}
	
	public static boolean isOperator(char c) {
		return c == 'x' || c == '÷' || c == '-' || c == '+';
	}
         
	public static int evaluation(String postfix) {
		Stack<Integer> eval = new Stack<>();
		String[] tokens = postfix.split("\\s+"); // Split by one or more spaces
		
		
		for(String token: tokens) {
		            if (isNumeric(token)) {
				int integer = Integer.parseInt(token); 
				eval.push(integer);
				System.out.println(eval);
			}
			else if(isOperatorV(token)) {
				int num1 = eval.pop();
				System.out.println(num1);
				int num2 = eval.pop();
				System.out.println(num2);
				
				int value = 0;
				switch(token) {
				
				case "+":
					value = num2 + num1;
					break;
				case "-":
					value = num2 - num1;
					break;
				case "x" :
					value = num2 * num1;
					break;
				case "÷":
					value = num2 / num1;
					break;
				}
				eval.push(value);
				System.out.println(eval);
			
			}
			}
		System.out.println(eval);
		return eval.peek();
		}
	
	public static boolean isNumeric(String s) {
		boolean b = false;
		for(int i = 0; i < s.length(); i++) {
			switch(s.charAt(i)) {
			case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
				b = true;
			}
		}
		return b;
	}
	
	public static boolean isOperatorV(String c) {
		return c.equals("x") || c.equals("÷") || c.equals("+") || c.equals("-");
	}

    private void handleNumberButton(JButton button) {
        if (textField.getText().equals("0")) {
            textField.setText(button.getText());
        } else {
            textField.setText(textField.getText() + button.getText());
        }
    }
    
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }
}
            
            

