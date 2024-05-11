/*
* author: Alec Posthauer
*
* Recipe Generator
* This JFrame uses buttons and a list to send ingredients to ChatGPT using an API call.
* It then responds to the prompt and returns that response to the user.
*
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class RecipeGenerator implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Recipe Generator")) {
      // Create a new JFrame for Recipe Generator
      JFrame frame = new JFrame("Recipe Generator");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close to close only this frame

      // Create components for the new JFrame
      JPanel panel = new JPanel();
      JLabel label = new JLabel("Add or remove ingredients:");
      JTextField ingredientField = new JTextField(20);
      JButton addButton = new JButton("Add");
      JButton removeButton = new JButton("Remove");
      DefaultListModel<String> ingredientsListModel = new DefaultListModel<>();
      JList<String> ingredientsList = new JList<>(ingredientsListModel);
      JScrollPane scrollPane = new JScrollPane(ingredientsList);
      JButton submitButton = new JButton("Submit");

      // Action listener for Add button
      addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String ingredient = ingredientField.getText().trim();
          if (!ingredient.isEmpty() && !ingredientsListModel.contains(ingredient)) {
            ingredientsListModel.addElement(ingredient);
            ingredientField.setText("");
          }
        }
      });

      // Action listener for Remove button
      removeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int selectedIndex = ingredientsList.getSelectedIndex();
          if (selectedIndex != -1) {
            ingredientsListModel.remove(selectedIndex);
          } else {
            JOptionPane.showMessageDialog(frame, "Please select an ingredient to remove.");
          }
        }
      });

      // Action listener for Submit button
      submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Get all ingredients from the model
          List<String> ingredients = new ArrayList<>();
          for (int i = 0; i < ingredientsListModel.size(); i++) {
            ingredients.add(ingredientsListModel.getElementAt(i));
          }

          // Call method to send all ingredients to ChatGPT
          String response = chatGPT(
              "Generate one recipe that use some of these ingredients: " + String.join(", ", ingredients));

          // Display the response
          if (response != null) {
            // Create a JTextArea to display the response
            JTextArea textArea = new JTextArea(response);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);

            // Put the text area in a scroll pane
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 300));

            // Show the scrollable response in a dialog
            JOptionPane.showMessageDialog(frame, scrollPane);
          } else {
            JOptionPane.showMessageDialog(frame, "Failed to get response from ChatGPT.");
          }
        }
      });

      // Add components to the panel
      panel.add(label);
      panel.add(ingredientField);
      panel.add(addButton);
      panel.add(removeButton);
      panel.add(scrollPane);
      panel.add(submitButton);

      // Add panel to the frame
      frame.add(panel);

      // Set frame size and visibility
      frame.setSize(400, 300);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    }
  }

  public static String chatGPT(String message) {
    String url = "https://api.openai.com/v1/chat/completions";
    String apiKey = "YOUR-API-KEY"; // API key goes here
    String model = "gpt-3.5-turbo"; // current model of chatgpt api

    try {
      // Create the HTTP POST request
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Authorization", "Bearer " + apiKey);
      con.setRequestProperty("Content-Type", "application/json");

      // Build the request body
      String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message
          + "\"}]}";
      con.setDoOutput(true);
      OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
      writer.write(body);
      writer.flush();
      writer.close();

      // Get the response
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      // returns the extracted contents of the response.
      return extractContentFromResponse(response.toString());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // This method extracts the response expected from chatgpt and returns it.
  public static String extractContentFromResponse(String response) {
    int startMarker = response.indexOf("content") + 11; // Marker for where the content starts.
    int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
    return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
  }
}
