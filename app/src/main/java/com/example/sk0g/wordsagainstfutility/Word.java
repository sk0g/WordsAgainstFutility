package com.example.sk0g.wordsagainstfutility;

import java.io.IOException;

import static com.example.sk0g.wordsagainstfutility.FileHandling.getRandomLine;
import static java.lang.Math.min;

/**
 * Created by sk0g on 10/15/17.
 */

public class Word {

    private String germanWord;
    private String gender;
    private String englishTranslation;
    private String wordType;

    public String getGermanWord() {
        return germanWord;
    }

    public String getGender() {
        return ((gender == null) ? "" : gender);
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public String getWordType() {
        return ((wordType == null) ? "" : wordType);
    }

    public Word() throws IOException {
        String dictionaryLine = getRandomLine();
        String[] parsedArray = parseLine(dictionaryLine);
        germanWord = parsedArray[0];
        gender = parsedArray[1];
        englishTranslation = parsedArray[2];
        wordType = parsedArray[3];
    }

    private static String[] parseLine(String dictionaryLine) {
        String[] line = dictionaryLine.split("\\t");
        String[] fixedLine = new String[6];

    /*  Format of fixedLine:
     *  0: German Word
     *  1: (nullable) gender
     *  2: English translation
     *  3: word type
     */

        String tempWord = line[0];
        int genderStartingIndex = tempWord.indexOf('{');
        if (genderStartingIndex != -1) {
            // if a gender letter was found ('{' occurs in the string)
            char genderLetter = tempWord.charAt(genderStartingIndex + 1);
            fixedLine[0] = trimGermanWord(tempWord.substring(0, genderStartingIndex - 1));
            fixedLine[1] = detectWordGender(genderLetter);
        } else {
            // no gender was found, assign entire tempWord string
            fixedLine[0] = trimGermanWord(tempWord);
        }

        // Add translation to fL
        fixedLine[2] = line[1];

        // Assign type only if the type is not null
        if (detectWordType(line[2]) != null) { fixedLine[3] = line[2]; }

        return fixedLine;
    }

    private static String detectWordType(String potentialType) {
        if (potentialType == null) { return null; }
        switch (potentialType) {
            case "adj":
                return "adjective";
            case "adv":
                return "adverb";
            case "verb":
                return potentialType;
            case "noun":
                return potentialType;
            default:
                return null;
        }
    }

    private static String detectWordGender(char potentialGender) {
        switch (potentialGender) {
            case 'm':
                return "male";
            case 'f':
                return "female";
            case 'n':
                return "neutral";
            case 'p':
                return "plural";
            default:
                return "null";
        }
    }

    private static String trimGermanWord(String startingWord) {
        int indexOfSquareBracket = startingWord.indexOf('[');
        int indexOfForwardSlash = startingWord.indexOf('/');
        if (indexOfForwardSlash == -1 && indexOfSquareBracket == -1) {
            // no illegal characters were found, return entire string
            return startingWord;
        } else {
            // if either variable is a -1/ doesn't occur, it will interfere with the min() step
            indexOfSquareBracket = (indexOfSquareBracket == -1) ? 1000 : indexOfSquareBracket;
            indexOfForwardSlash  = (indexOfForwardSlash  == -1) ? 1000 : indexOfForwardSlash;
            int earlier = min(indexOfForwardSlash, indexOfSquareBracket);
            return startingWord.substring(0, earlier);
        }
    }
}
