package me.zero.detector.util.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Brady
 * @since 1/5/2017 12:00 PM
 */
public class IOUtils {

    public static String getStringFromStream(InputStream is) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line).append("\n");
            }
            br.close();
            return result.toString();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
