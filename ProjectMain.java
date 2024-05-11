import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * FinalProject
 * 
 * @author Alec Posthauer - 630000913
 * @author John Villanueva - 732001682
 * @author Alec Flores - 731000753
 * 
 *         Section: CSCE 111-504
 *         Date: 4/6/2024
 */
public class ProjectMain {

  public static void main(String[] args) {

    // New Frame called appFrame
    JFrame appFrame = new JFrame();

    // set title
    appFrame.setTitle("CSCE 111 Final Project - Food ");

    // set default size
    appFrame.setSize(1000, 700);

    // set default location of frame on screen to center
    appFrame.setLocationRelativeTo(null);

    // Set color background
    appFrame.getContentPane().setBackground(Color.gray);

    // Panel to hold all buttons
    JPanel panel1 = new JPanel();
    appFrame.add(panel1);
    panel1.setSize(50, 50);
    panel1.setBackground(Color.gray);

    // Button 1
    JButton b1 = new JButton();
    b1.setSize(50, 50);
    b1.setVisible(true);
    b1.setText("Recipe Generator");
    panel1.add(b1);

    // Button 2
    JButton b2 = new JButton();
    b2.setSize(50, 50);
    b2.setVisible(true);
    b2.setText("Food Trivia Quiz");
    panel1.add(b2);

    // Button 3
    JButton b3 = new JButton();
    b3.setSize(50, 50);
    b3.setVisible(true);
    b3.setText("March Madness Bracket");
    panel1.add(b3);

    // button 4
    // button 4
    JButton b4 = new JButton();
    b4.setSize(50, 50);
    b4.setVisible(true);
    b4.setText("Video");
    panel1.add(b4);

    // Add ActionListener to button "RecipeGenerator"
    b1.addActionListener(new RecipeGenerator());

    // Add ActionListener to button "John V"
    b2.addActionListener(new FoodTriviaQuiz());

    // Add ActionListener to button "John V"
    b3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new MarchMadnessBracket();
      }
    });
    // action listener for the video
    b4.addActionListener(new Video());

    // set operation on exit
    appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // close one specific frame instead of whole program
    // appFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    // make Frame visible
    appFrame.setVisible(true);
  }
}