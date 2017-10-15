package com.example.sk0g.wordsagainstfutility;

/**
 * Created by sk0g on 10/15/17.
 */

public class Word {
    String germanWord;
    String gender;
    String englishTranslation;
    String wordType;

    public Word(String dictionaryLine) {
        String[] parsedArray = parseLine(dictionaryLine);
        germanWord = parsedArray[0];
    }
}

private static String[] parseLine(String dictionaryLine) {
    return;
}
