package pl.wroc.uni.ift.android.quizactivity;

/**
 * Created by kozik on 10.10.2017.
 */

public class Question {
    int mResourceId;
    private boolean isAnswerTrue;

    Question(int resourceId, boolean answerTrue) {
        mResourceId = resourceId;
        isAnswerTrue = answerTrue;
    }
    public void setisAnswetTrue (boolean answerTrue) {
        isAnswerTrue = answerTrue;
    }
    public boolean isAnswerTrue() { return isAnswerTrue; }

    public int getmResourceId() {
        return mResourceId;
    }
}

