package se.osbe.emoreactor.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Helper {
    /**
     * Method to digest a string using MD5 (Message Digest 5) algorithm.
     *
     * @param str The string to be digested.
     * @return the digested string using MD5-algorithm.
     */
    public static String digest(String str) {
        byte[] defaultBytes = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1)
                    hexString.append('0');

                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        return hexString.toString();
    }
}
