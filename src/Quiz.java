/**
 * Maman 13: Date 25/11/2021
 * Question 1: Quiz
 * Alexey Vartanov - 321641086
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for Quiz object,
 * Quiz object contains a list of loaded question from file "questions.txt"
 * Storing the number of right question and calculate the score of the user
 */
public class Quiz {

    public Quiz(String fileName) throws Exception {
        loadQuizFromFile(fileName);
    }

    private List<Question> QuestionsList;
    private int QuestionNumber = 0;
    private int NumberOfRightAnswers = 0;

    public double getQuizScore() {
        return (double) 100 / QuestionsList.size() * NumberOfRightAnswers;
    }

    public void updateNumberOfRightAnswers() {
        NumberOfRightAnswers++;
    }

    public Question getNextQuestion() {
        if (QuestionNumber < QuestionsList.size()) {
            Question question = QuestionsList.get(QuestionNumber++);
            question.ShuffleAnswers();
            return question;
        } else
            return null;
    }

    public Question getQuestionByIndex(int index) {
        return QuestionsList.get(index);
    }

    public int getCurrentQuestionNumber() {
        return QuestionNumber;
    }

    public void resetQuiz() {
        Collections.shuffle(QuestionsList);
        QuestionNumber = 0;
        NumberOfRightAnswers = 0;
    }

    public int getNumberOfQuestions() {
        return QuestionsList.size();
    }

    /**
     * Build List of questions with answers from
     * provided text file and shuffle the list
     *
     * @throws FileNotFoundException if the file not found
     */
    private void loadQuizFromFile(String fileName) throws Exception {

        QuestionsList = new ArrayList<>();
        InputStream input = this.getClass().getResourceAsStream(fileName);
        if (input != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String question;
            while ((question = reader.readLine()) != null) {
                Question newQuestion = new Question(question, reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine());
                QuestionsList.add(newQuestion);
            }
            Collections.shuffle(QuestionsList);
            reader.close();
        }
        else{
            throw new FileNotFoundException("File not found or corrupted");
        }
        input.close();
    }
}
