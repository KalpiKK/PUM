package pl.wroc.uni.ift.android.quizactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QAFragment extends Fragment {
    Question mQuestion;
    private Button mTrueButton;
    private Button mFalseButton;
    TextView mQuestionTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_qa, container, false);

        mTrueButton = (Button) rootView.findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        checkAnswer(true);
                    }
                }
        );
        mQuestionTextView = (TextView) rootView.findViewById(R.id.question_text_view);

        mFalseButton = (Button) rootView.findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mQuestionTextView.setText(mQuestion.getTextResId());
        return rootView;

    }
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestion.isAnswerTrue();

        int toastMessageId;

        if (userPressedTrue == answerIsTrue) {
            toastMessageId = R.string.correct_toast;
            //     mCorrectAnswer[mCurrentIndex] = 1;
          //  mScore = mScore + 1;
        } else {
            toastMessageId = R.string.incorrect_toast;
            //    mCorrectAnswer[mCurrentIndex] = 2;
        }
        //      DoneAnswers();
       // mScoreview.setText(" Score : " + mScore);
        Toast.makeText(getActivity(), toastMessageId, Toast.LENGTH_SHORT).show();
    }
}
