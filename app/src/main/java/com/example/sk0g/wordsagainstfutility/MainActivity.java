package com.example.sk0g.wordsagainstfutility;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import static com.example.sk0g.wordsagainstfutility.FileHandling.getRandomLine;

public class MainActivity extends AppCompatActivity {

    private Word mWordInstance;

    private Button mNextWordButton;
    private Button mOnlineSearchButton;

    private TextView mGermanWord;
    private TextView mWordGender;
    private TextView mWordType;
    private TextView mEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGermanWord = (TextView) findViewById(R.id.german_word_text);
        mWordGender = (TextView) findViewById(R.id.german_word_gender);
        mWordType   = (TextView) findViewById(R.id.german_word_type);
        mEnglish    = (TextView) findViewById(R.id.english_translation_text);

        if (mGermanWord.getText() == null) { displayNextWord(); }

        mNextWordButton     = (Button) findViewById(R.id.next_word_button);
        mNextWordButton.setText(R.string.next_button_label);
        mNextWordButton.setOnClickListener((View v) -> displayNextWord());

        mOnlineSearchButton = (Button) findViewById(R.id.search_online_button);
        mOnlineSearchButton.setText(R.string.search_button_label);
        mOnlineSearchButton.setOnClickListener((View v) -> searchCurrentWord());
    }

    private void displayNextWord() {
        try {
            String dictionaryLine = getRandomLine(this.getApplicationContext());
            mWordInstance = new Word(dictionaryLine);
        }
        catch (IOException e) { e.printStackTrace(); }

        mGermanWord.setText(mWordInstance.getGermanWord());
        mWordGender.setText(mWordInstance.getGender());
        mWordType.setText(mWordInstance.getWordType());
        mEnglish.setText(mWordInstance.getEnglishTranslation());
    }

    private void searchCurrentWord() {
        return;
    }
}
