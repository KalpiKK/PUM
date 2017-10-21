package pl.wroc.uni.ift.android.quizactivity;

/**
 * Created by jpola on 26.07.17.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mDoneAnswer;

    public Question(int textResId, boolean answerTrue, boolean doneAnswer)    {

        mTextResId=textResId;
        mAnswerTrue = answerTrue;
        mDoneAnswer = doneAnswer;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isDoneAnswer() {
        return mDoneAnswer;
    }

    public void setDoneAnswer(boolean doneAnswer) {
        mDoneAnswer = doneAnswer;
    }
}
