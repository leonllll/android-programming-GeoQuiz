package geoquiz.android.bignerdranch.com.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

    private TextView mQuestionTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private int mCurrentQuestionIndex = 0;

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
        new TrueFalse(R.string.question_beijing, true),
        new TrueFalse(R.string.question_shanghai, true),
        new TrueFalse(R.string.question_guangzhou, false)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if(savedInstanceState != null && savedInstanceState.get(KEY_INDEX) != null) {
            mCurrentQuestionIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrentQuestionIndex = (mCurrentQuestionIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentQuestionIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentQuestionIndex].isTrueQuestion();

        int messageResId = 0;

        if(answerIsTrue == userPressTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState() called");
        savedInstanceState.putInt(KEY_INDEX, mCurrentQuestionIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }


}
