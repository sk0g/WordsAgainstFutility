package com.example.sk0g.wordsagainstfutility;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by sk0g on 10/17/17.
 */

public class FileHandling {
    private Context mContext;

//    public ReadFile(Context context) {
//        this.mContext = MainActivity.mActivity.getApplicationContext();
//    }

    public static String getRandomLine() throws IOException {
        FileInputStream fis;
        try {
            fis = new FileInputStream("dictionary.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int readLength = 300;
        long length = fis.available();

        Random r = new Random();
        long random = r.nextInt();
        random %= length - readLength;
        random  = abs(random);

        return (readFromInputStream(fis, random, readLength));
    }


    private static String readFromInputStream(FileInputStream input,
                                       long offset,
                                       int length) throws IOException {
        try { input.skip(offset); }
        catch (IOException e) { e.printStackTrace(); }

        byte[] mouthful = new byte[length];
        input.read(mouthful);

        String result = new String(mouthful);

        int firstN  = result.indexOf("\n");
        int secondN = result.substring(firstN + 1).indexOf("\n");

        result = result.substring((firstN + 1), (firstN + secondN + 1));

        input.close();
        return result;
    }
}
