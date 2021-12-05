/**
 * Maman 13: Date 25/11/2021
 * Question 1: Quiz
 * Alexey Vartanov - 321641086
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javax.swing.*;

public class QuizPaneController {

    @FXML
    private Pane Pane;

    @FXML
    private Label QuNumberLabel;

    @FXML
    private Label QuestionLabel;

    @FXML
    private Label AnswerLabel;

    @FXML
    private ToggleGroup AnswersGroup;

    @FXML
    private RadioButton Option_A;

    @FXML
    private RadioButton Option_B;

    @FXML
    private RadioButton Option_C;

    @FXML
    private RadioButton Option_D;

    @FXML
    private Button SubmitBtn;

    @FXML
    private Button NextBtn;


    private RadioButton[] OptionsToSelect;
    private Quiz quiz;

    @FXML
    void initialize() throws Exception {
        try {
            OptionsToSelect = new RadioButton[]{Option_A, Option_B, Option_C, Option_D};
            quiz = new Quiz("exam.txt");
            loadNextQuestion(quiz.getNextQuestion());
            NextBtn.setDisable(true);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage() +
                    "\nTry to add to the source directory and name it \"exam.txt\"");
            throw ex;
        }
    }

    @FXML
    void nextBtnClick(ActionEvent event) {
        String btnText = ((Button)event.getSource()).getText();
        AnswersGroup.getSelectedToggle().setSelected(false);
        if(btnText.contains("Retake")){
            retakeQuiz();
        }
        else{
            loadNextQuestion(quiz.getNextQuestion());
        }
        NextBtn.setDisable(true);
        SubmitBtn.setDisable(false);
    }

    @FXML
    void submitAnswer(ActionEvent event) {
        try {
            String selectedAnswer = ((RadioButton)AnswersGroup.getSelectedToggle()).getText();
            if (selectedAnswer.equals(quiz.getQuestionByIndex(quiz.getCurrentQuestionNumber() - 1).getRightAnswer())) {
                quiz.updateNumberOfRightAnswers();
                AnswerLabel.setText("Right Answer");
            } else {
                AnswerLabel.setText("Wrong Answer");
            }
            changeRadioButtonsState(true);
            SubmitBtn.setDisable(true);
            NextBtn.setDisable(false);
            if (quiz.getCurrentQuestionNumber() == quiz.getNumberOfQuestions()){
                AnswerLabel.setText(String.format("Your quiz score is: %.0f", quiz.getQuizScore()));
            }
        }
        catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Please choose answer before to submit");
        }
    }

    /**
     * Start the quiz again after question were shuffled
     */
    private void retakeQuiz() {
        AnswerLabel.setText("");
        quiz.resetQuiz();
        loadNextQuestion(quiz.getNextQuestion());
        NextBtn.setText("Next");
    }

    /**
     * Load next question to GUI
     * @param qu - question as Question object
     */
    private void loadNextQuestion(Question qu) {
        changeRadioButtonsState(false);
        QuNumberLabel.setText(String.format("Question %d/%d", quiz.getCurrentQuestionNumber(), quiz.getNumberOfQuestions()));
        if (qu != null){
            QuestionLabel.setText(qu.getQuestionString());
            for (int i=0; i<qu.getAnswers().size(); i++){
                OptionsToSelect[i].setText(qu.getAnswers().get(i));
            }
        }
        if (quiz.getCurrentQuestionNumber() == quiz.getNumberOfQuestions()){
            NextBtn.setText("Retake Quiz");
        }
        AnswerLabel.setText("");
    }

    /**
     * Change Radio buttons state according to needs
     * @param ifDisable - if true disable radio button components, else enable
     */
    private void changeRadioButtonsState(Boolean ifDisable){
        for (RadioButton button: OptionsToSelect){
            button.setDisable(ifDisable);
        }
    }
}