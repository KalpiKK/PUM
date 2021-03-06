package pl.wroc.uni.ift.android.quizactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        QuestionBank mQuestionsBank = QuestionBank.get(this);
        mAdapter = new QuestionAdapter(mQuestionsBank.getmQuestions());
        mRecyclerView.setAdapter(mAdapter);
    }

    private class QuestionHolder extends RecyclerView.ViewHolder
    {
        private TextView mQuestionTextView;
        private Question mQuestion;

        public QuestionHolder(LayoutInflater inflater, ViewGroup parent)
        {
            //przechowywany widok
            super(inflater.inflate(R.layout.activity_question_item,parent,false));
            mQuestionTextView = (TextView) itemView.findViewById(R.id.question_text);

        }
        // dane do wyswietlenia
        public void bind(Question question)
        {
            mQuestion = question;
            mQuestionTextView.setText(mQuestion.getTextResId());
        }
    }
    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder>
    {
        //źródło danych
        private List<Question> mQuestions;
        public QuestionAdapter(List<Question> questions)
        {
            mQuestions = questions;
        }
        //pojedynczy element
        @Override
        public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(QuestionListActivity.this);
            return new QuestionHolder(layoutInflater,parent);

        }
        //podlaczenie danych do holdera
        @Override
        public void onBindViewHolder(QuestionHolder holder, int position) {
            Question question = mQuestions.get(position);
            holder.bind(question);
        }
        //ile ich
        @Override
        public int getItemCount() {return mQuestions.size();}
    }
}


