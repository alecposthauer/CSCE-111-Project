import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Collections;

/**
 * FinalProject
 * 
 * @author Alec Flores - 731000753
 * 
 *         Section: CSCE 111-504
 *         Date: 4/10/2024
 */

// Class that implements action listener for Food Trivia Quiz
public class FoodTriviaQuiz implements ActionListener {

    // class to hold the question, answer, and text field for GUI
    private class Question {
        JLabel label;
        JTextField textField;
        String answer;

        // Constructor for Question
        Question(String text, String answer) {
            this.label = new JLabel(text);
            this.textField = new JTextField(10);
            this.answer = answer;
        }
    }

    // list that holds all questions created
    private ArrayList<Question> questions;

    // list that selects and holds 5 random questions from the questions list
    private ArrayList<Question> selectedQuestions;

    // buttons for submiting answers and showing answers
    private JButton SubmitButton;
    private JButton ShowAnswersButton;

    // frame that holds the GUI
    private JFrame alecFFrame;

    // checks if the user is taking the quiz for the first time or not to hide intro
    // message
    private boolean isFirstTime;

    // constructor for AlecFButtonAction
    FoodTriviaQuiz() {
        // initialize the list of questions
        questions = new ArrayList<>();

        // Questions for the quiz to select from
        questions.add(new Question(
                "<html>Which mineral is found in bananas?<br>1. Potassium<br>2. Iron<br>3. Calcium<br>4. Zinc</html>",
                "Potassium"));
        questions.add(new Question(
                "<html>Which nut is used to make pesto?<br>1. Almonds<br>2. Pine Nuts<br>3. Walnuts<br>4. Cashews</html>",
                "Pine Nuts"));
        questions.add(new Question(
                "<html>Which legume is hummus made from?<br>1. Kidney Beans<br>2. Black Beans<br>3. Chickpeas<br>4. Lentils</html>",
                "Chickpeas"));
        questions.add(new Question(
                "<html>Which fruit is known for having a high water content?<br>1. Apple<br>2. Banana<br>3. Orange<br>4. Watermelon</html>",
                "Watermelon"));
        questions.add(new Question(
                "<html>What is tofu made from?<br>1. Soybeans<br>2. Chickpeas<br>3. Lentils<br>4. Peas</html>",
                "Soybeans"));
        questions.add(new Question(
                "<html>Which fish is mostly commmonly used in Sushi?<br>1. Tuna<br>2. Tilapia<br>3. Cod<br>4. Trout</html>",
                "Tuna"));
        questions.add(new Question(
                "<html>What country does the dish Paella originate from?<br>1. Italy<br>2. Mexico<br>3. Argentina<br>4. Spain</html>",
                "Spain"));
        questions.add(new Question(
                "<html>What bread is typically used for a classic Reuben Sandwich?<br>1. Sourdough<br>2. Rye<br>3. Wheat<br>4. Pumpernickel</html>",
                "Rye"));
        questions.add(new Question(
                "<html>Which country is famous for their chocolate?<br>1. Switzerland<br>2. USA<br>3. Brazil<br>4. Canada</html>",
                "Switzerland"));
        questions.add(new Question(
                "<html>Which herb is used in a classic Pesto?<br>1. Basil<br>2. Cilantro<br>3. Parsley<br>4. Mint</html>",
                "Basil"));
        questions.add(new Question(
                "<html>What is the main ingredient in a traditional Caprese Salad?<br>1. Tomato<br>2. Cucumber<br>3. Bell Pepper<br>4. Onion</html>",
                "Tomato"));
        questions.add(new Question(
                "<html>What type of fish is used in a traditional Fish and Chips?<br>1. Cod<br>2. Salmon<br>3. Tuna<br>4. Tilapia</html>",
                "Cod"));
        questions.add(new Question(
                "<html>Which of the following is NOT an ingredient in a traditional Margherita Pizza?<br>1. Tomato Sauce<br>2. Cheese<br>3. Thyme<br>4. Olive Oil</html>",
                "Thyme"));
        questions.add(new Question(
                "<html>What type of meat is NOT found in a traditional Gyro?<br>1. Beef<br>2. Chicken<br>3. Lamb<br>4. Venison</html>",
                "Venison"));
        questions.add(new Question(
                "<html>What is the main ingredient in a traditional Ratatouille?<br>1. Eggplant<br>2. Spinach<br>3. Bell Pepper<br>4. Onion</html>",
                "Eggplant"));
        questions.add(new Question(
                "<html>Which is highest in sugar content?<br>1. Apple<br>2. Banana<br>3. Orange<br>4. Watermelon</html>",
                "Watermelon"));
        questions.add(new Question(
                "<html>Which is NOT a variety of apple?<br>1. Fuji<br>2. Gala<br>3. Granny Smith<br>4. Valencia</html>",
                "Valencia"));

        // shuffles the questions to randomize the order
        Collections.shuffle(questions);

        // selects the first 5 questions from the shuffled list to display to the user
        selectedQuestions = new ArrayList<>(questions.subList(0, 5));

        // sets the boolean to true to display the intro message
        isFirstTime = true;

        // sets the buttons for the GUI and adds action listeners to show answers
        ShowAnswersButton = new JButton("Show Answers");
        ShowAnswersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // calls method to show answers to user if they want to see them
                showAnswers();
            }
        });

        // sets the buttons for the GUI and adds action listeners to submit answers
        SubmitButton = new JButton("Submit");
        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call methood to check answers and add points if correct
                checkAnswers();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // displays the intro if the user is taking the quiz for the first time
        if (isFirstTime) {
            String intro = "<html>Welcome to the Food Trivia Quiz!<br>" +
                    "Instructions:<br>" +
                    "1. Please type your answer in the field menu.<br>" +
                    "2. Click 'Submit' to check your answers and see your score.<br>" +
                    "3. Click 'Show Answers' to reveal the correct answers.<br>" +
                    "Good luck!</html>";
          JOptionPane.showMessageDialog(null, intro, "Intro", JOptionPane.INFORMATION_MESSAGE);
            // set the boolean to false so the intro message does not display again
            isFirstTime = false;
        }

        // creates the GUI for the quiz
        alecFFrame = new JFrame();
        alecFFrame.setTitle("Food Trivia Quiz");
        alecFFrame.setSize(800, 650);
        alecFFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // creates a panel to hold the questions
        JPanel panel = new JPanel(new GridLayout(0, 1));

        // sets location of the frame to center of the screen
        alecFFrame.setLocationRelativeTo(null);

        // loops through the selected questions and adds them to the panel
        for (Question question : selectedQuestions) {
            JPanel questionPanel = new JPanel(new BorderLayout());

            // sets the font for the questions
            question.label.setFont(new Font("Arial", Font.BOLD, 14));
            // adds the question and text field to the panel
            questionPanel.add(question.label, BorderLayout.NORTH);
            // sets the font for the text field
            questionPanel.add(question.textField, BorderLayout.CENTER);

            // adds the question panel to the main panel
            panel.add(questionPanel);
        }

        // sets the font for the buttons
        SubmitButton.setFont(new Font("Arial", Font.BOLD, 14));
        ShowAnswersButton.setFont(new Font("Arial", Font.BOLD, 14));

        // adds the buttons to the panel
        panel.add(SubmitButton);

        // adds the panel to the frame and sets it to visible
        alecFFrame.add(panel);
        alecFFrame.setVisible(true);

        // creates a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(SubmitButton);
        buttonPanel.add(ShowAnswersButton);
        panel.add(buttonPanel);

    }

    // method to check answers and add points if correct
    private void checkAnswers() {

        // variable to hold the score
        int score = 0;
        // loops through the selected questions and checks if the answer is correct
        for (Question question : selectedQuestions) {
            if (question.answer.equalsIgnoreCase(question.textField.getText())) {
                score++;
            }
        }

        // outputs the score to the user
        JOptionPane.showMessageDialog(null, "Your score is: " + score);

        // shuffles the questions to randomize the order
        Collections.shuffle(questions);
        // selects the first 5 questions from the shuffled list to display to the user
        selectedQuestions = new ArrayList<>(questions.subList(0, 5));

        // closes the frame so quiz does not stay open on screen after sumbitting
        // answers
        alecFFrame.dispose();

        // calls the actionPerformed method to display the new quiz
        actionPerformed(null);

    }

    // method to display correct answers to user
    private void showAnswers() {

        // string to hold the correct answers with html for formatting
        String answers = "<html>Correct Answers:<br>";

        // for each loop that goes through each question
        for (Question question : selectedQuestions) {
            // adds the correct answer to the answers string with each answer on a new line
            // with html break tag
            answers += question.answer + "<br>";
        }
        // closes the html tag
        answers += "</html>";

        // shows message with correct asnwers to user
        JOptionPane.showMessageDialog(null, answers);
    }
}