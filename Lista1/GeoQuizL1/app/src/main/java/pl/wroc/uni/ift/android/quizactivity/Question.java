package pl.wroc.uni.ift.android.quizactivity;

/**
 * Created by jpola on 26.07.17.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;;
    private boolean mDoneb;

    public Question(int textResId, boolean answerTrue, boolean doneb)    {

        mTextResId=textResId;
        mAnswerTrue = answerTrue;
        mDoneb = doneb;
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

    public boolean isDoneb() {
        return mDoneb;
    }

    public void setDoneb(boolean doneb) {
        mDoneb = doneb;
    }
}
