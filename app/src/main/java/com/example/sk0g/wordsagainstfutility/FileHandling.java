package com.example.sk0g.wordsagainstfutility;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by sk0g on 10/17/17.
 */

class FileHandling {

    static String getRandomLine(Context context) throws FileNotFoundException, IOException {

        InputStream dictFile = context.getResources().openRawResource(R.raw.dictionary);

        int readLength = 300;
        long length = dictFile.available();

        Random r = new Random();
        long random = r.nextInt();
        random %= length - readLength;
        random  = abs(random);

        return (readFromFile(dictFile, random, readLength));
    }


    private static String readFromFile(InputStream file, long position, int length) throws IOException {
        file.skip(position);
        byte[] mouthful = new byte[length];
        file.read(mouthful);
        String result = new String(mouthful);

        int firstN  = result.indexOf("\n");
        int secondN = result.substring(firstN + 1).indexOf("\n");

        result = result.substring((firstN + 1), (firstN + secondN + 1));

        file.close();

        return result;
    }
}
