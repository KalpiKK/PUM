package pl.wroc.uni.ift.android.quizactivity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * Created by kozik on 14.11.2017.
 */

public class QuestionBank {
    private static QuestionBank sQuestionBank;

    private List<Question> mQuestions;

    public static QuestionBank get(Context context) {
        if (sQuestionBank == null)
        {
            sQuestionBank = new QuestionBank(context);
        }
        return sQuestionBank;
    }

    private QuestionBank(Context context) {

        mQuestions = new ArrayList<>();

        mQuestions.add(new Question(R.string.question_stolica_polski, true));
        mQuestions.add(new Question(R.string.question_stolica_dolnego_slaska, false));
        mQuestions.add(new Question(R.string.question_sniezka, true));
        mQuestions.add(new Question(R.string.question_wisla, true));


    }

    public List<Question> getmQuestions() {
        return mQuestions;
    }

    public Question getQuestion (int index) {
        return mQuestions.get(index);

    }
    public int size(){
        return mQuestions.size();
    }
}
