/**
 * Maman 13: Date 25/11/2021
 * Question 1: Quiz
 * Alexey Vartanov - 321641086
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class for Question object, contains question as a string and answers as list of strings
 */
public class Question {

    private final String QuestionString;
    private final List<String> Answers;
    private final String RightAnswer;

    public String getQuestionString() {
        return QuestionString;
    }

    public String getRightAnswer() {
        return RightAnswer;
    }

    public List<String> getAnswers() {
        return Answers;
    }

    public Question(String question, String option_a, String option_b, String option_c, String option_d)
    {
        QuestionString = question;
        RightAnswer = option_a;
        Answers = Arrays.asList(option_a, option_b, option_c, option_d);
    }

    /**
     * Shuffle the answers in the answers list
     */
    public void ShuffleAnswers(){
        Collections.shuffle(Answers);
    }
}
