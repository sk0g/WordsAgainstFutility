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

    private static String[] parseLine(String dictionaryLine) {
        String[] lines = dictionaryLine.split("\\t");
        String[] fixedLines = new String[6];

    /*  Format of fixedLines:
     *  0: German Word
     *  1: (nullable) gender
     *  2: English translation
     *  3: word type
     */

        String tempWord = lines[0];
        // Step through the first item, find the gender, discard everything after that
        for (int i = 1; i < (tempWord.length() - 1); i++) {
            if (tempWord.charAt(i - 1) == '{' &&
                    tempWord.charAt(i + 1) == '}') {
                // German word isolated, place it in fL[0]
                fixedLines[0] = tempWord.substring(0, i - 2)
                // Gender found, place it in fL[1]
                fixedLines[1] = Character.toString(tempWord.charAt(i));
            }
        }

        return fixedLines;
    }
}
