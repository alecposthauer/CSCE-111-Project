
/**
 * FinalProject
 * 
 * @author John Villanueva - 732001682
 * 
 *         Section: CSCE 111-504
 *         Date: 4/10/2024
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class MarchMadnessBracket implements ActionListener {
  private List<String> foodItems;
  private List<String> winners;
  private JPanel bracketPanel;
  private JFrame frame;

  public MarchMadnessBracket() {
    frame = new JFrame();
    frame.setTitle("March Madness Bracket");
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Set frame location to the center of the screen
    frame.setLocationRelativeTo(null);

    // Initialize foodItems list using ArrayList and add method
    foodItems = new ArrayList<>();
    foodItems.add("Pizza");
    foodItems.add("Burger");
    foodItems.add("Taco");
    foodItems.add("Sushi");
    foodItems.add("Pasta");
    foodItems.add("Salad");
    foodItems.add("Steak");
    foodItems.add("Sandwich");
    foodItems.add("Ice Cream");
    foodItems.add("Cake");
    foodItems.add("Donut");
    foodItems.add("Fries");
    foodItems.add("Pancakes");
    foodItems.add("Burrito");
    foodItems.add("Chicken");
    foodItems.add("Ramen");

    winners = new ArrayList<>();
    bracketPanel = new JPanel();
    bracketPanel.setLayout(new BoxLayout(bracketPanel, BoxLayout.Y_AXIS));

    // Create the bracket UI
    createBracketUI();

    frame.setVisible(true);
  }

  private void createBracketUI() {
    frame.add(bracketPanel);
    // Shuffle the list of teams randomly
    Collections.shuffle(foodItems);
    displayRound(foodItems);

    // Add a button to trigger the next round
    JButton nextRoundButton = new JButton("Next Round");
    nextRoundButton.addActionListener(this);
    frame.add(nextRoundButton, BorderLayout.SOUTH);
  }

  private void displayRound(List<String> roundTeams) {
    bracketPanel.removeAll(); // Clear the panel before adding new components

    for (int i = 0; i < roundTeams.size(); i += 2) {
      String team1 = roundTeams.get(i);
      String team2 = roundTeams.get(i + 1);
      JLabel matchLabel = new JLabel(team1 + " vs " + team2);
      bracketPanel.add(matchLabel);

      JComboBox<String> winnerDropdown = new JComboBox<>();
      winnerDropdown.addItem(team1);
      winnerDropdown.addItem(team2);
      winnerDropdown.setPreferredSize(new Dimension(100, 100));
      bracketPanel.add(winnerDropdown);
    }

    frame.revalidate();
    frame.repaint();
  }

  private void simulateNextRound() {
    winners.clear();
    for (Component component : bracketPanel.getComponents()) {
      if (component instanceof JComboBox) {
        @SuppressWarnings("unchecked")
        JComboBox<String> comboBox = (JComboBox<String>) component;
        winners.add(comboBox.getSelectedItem().toString());
      }
    }

    // Proceed to the next round if there are more than 1 winner
    if (winners.size() > 1) {
      displayRound(winners);
    } else {
      // Display the winner if there is only one winner left
      JOptionPane.showMessageDialog(frame, "The champion is: " + winners.get(0));
      frame.dispose(); // Close the frame after displaying the winner
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    simulateNextRound();
  }
}
