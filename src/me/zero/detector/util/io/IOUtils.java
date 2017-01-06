package me.zero.detector.util.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Brady on 1/5/2017.
 */
public class IOUtils {

    public static String getStringFromStream(InputStream is) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line + "\n";
            }
            br.close();
            return result;
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
