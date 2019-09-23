package main.QRcode;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static java.util.ArrayList.*;
import static main.QRcode.QrEncoder.retrieveIvBytes;
import static main.QRcode.QrEncoder.retrieveKeyBytes;
import static main.QRcode.QrEncoder.createEncCbcCipher;
import static main.QRcode.QrEncoder.encryptPlainText;
import static main.QRcode.QrEncoder.plainTextToBytes;


public class QrDecoder {

    private static byte [] keyBytes = retrieveKeyBytes();
    private static byte [] ivBytes= retrieveIvBytes();
    private static ArrayList <Object> decryptionResults;




    //Need an function to trim the end of the byte array

    public static String bytesToPlaintext(byte [] decrypted,ArrayList<Object>decryptionResults){
        String plainTextAndBuffer = null;

        // Needed to trim off the extra initialisation bytes from the plaintext
        int plainTextLength = (int) decryptionResults.get(0);

        try {
            plainTextAndBuffer = new String(decrypted, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String plainText = plainTextAndBuffer.substring(0,plainTextLength);

        return plainText;

    }

    public static Cipher createDecCbcCipher(ArrayList<Object> encryptionValues){
        // creates key spec and the Initialisation vector for the stored key bytes
        SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
        IvParameterSpec initVectorSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, initVectorSpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }




        return cipher;
    }


    public static ArrayList<Object> decryptToBytes(ArrayList<Object> encryptedValues, Cipher cipherType) {

        ArrayList<Object> decryptionResults = new ArrayList<>();

        Object encryptionLengthObj = encryptedValues.get(0);

        Object encryptionBytesObj = encryptedValues.get(1);

        int encryptionLength = (int) encryptionLengthObj;

        byte[] encryptedBytes = (byte[]) encryptionBytesObj;

        byte[] decryptedBytes = new byte[cipherType.getOutputSize(encryptionLength)];

        int decryptionLength = 0;
        try {
            decryptionLength = cipherType.update(encryptedBytes, 0, encryptionLength, decryptedBytes, 0);
            decryptionLength += cipherType.doFinal(decryptedBytes, decryptionLength);

        } catch (ShortBufferException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        decryptionResults.add(decryptionLength);
        decryptionResults.add(decryptedBytes);

        return decryptionResults;
    }



    public static void main(String[] args) {
        byte [] input = plainTextToBytes("My left hand is the blade that binds reality");

        System.out.println(input);

        Cipher cbc = createEncCbcCipher();
        ArrayList <Object> results = encryptPlainText(input, cbc);

        System.out.println(results);



        Cipher decCbc= createDecCbcCipher(results);

        ArrayList dec = decryptToBytes(results,decCbc);

        System.out.println(dec);

        byte [] dec2 = (byte []) dec.get(1);

        System.out.println(bytesToPlaintext(dec2,dec));


    }

}
