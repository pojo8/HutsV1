package main.QRcode;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class QrEncoder {

    private static byte [] keyBytes = retrieveKeyBytes();
    private static byte [] ivBytes = retrieveIvBytes();






    public static byte[] retrieveKeyBytes(){
        byte [] keyBytes = new byte[0];
        try {
            keyBytes = Files.readAllBytes(Paths.get("src/main/resources/qrKey.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyBytes;
    }

    public static byte[] retrieveIvBytes(){
        byte [] IvBytes = new byte[0];
        try {
            IvBytes = Files.readAllBytes(Paths.get("src/main/resources/qrInitVector.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IvBytes;
    }


    public static byte[] plainTextToBytes(String plainText){
        byte [] plainBytes = plainText.getBytes(StandardCharsets.UTF_8);
        return plainBytes;
    }


    public static Cipher createEncCbcCipher(){
        // creates key spec and the Initialisation vector for the stored key bytes
        SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
        IvParameterSpec initVectorSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, initVectorSpec);
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

    /**
     * The main encryption that takes in
     * @param plainTextBytes
     * @param cipherType
     * @return an arrayList of the encryption length and encypted bytes
     * These are bothe needed for the decryption
     */
    public static ArrayList<Object> encryptPlainText(byte [] plainTextBytes, Cipher cipherType){

        ArrayList<Object> encryptedValues = new ArrayList();

        byte [] encrypted = new byte[cipherType.getOutputSize(plainTextBytes.length)];

        int encryptionLength = 0;
        try {
            encryptionLength = cipherType.update(plainTextBytes, 0, plainTextBytes.length, encrypted, 0);
        } catch (ShortBufferException e) {
            e.printStackTrace();
        }

        try {
            encryptionLength += cipherType.doFinal(encrypted, encryptionLength);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }


        encryptedValues.add(encryptionLength);
        encryptedValues.add(encrypted);

        return encryptedValues;

    }


    public static void main(String[] args) throws BadPaddingException, IllegalBlockSizeException, ShortBufferException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
       byte [] input = plainTextToBytes("My left hand is the blade that binds reality");


//        System.out.println(input);
//       SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
//       IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
//
//       Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//
//       //enc
//        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
//        byte [] encrypted = new byte[cipher.getOutputSize(input.length)];
//        System.out.println(encrypted);
//
//        int enc_len = cipher.update(input, 0, input.length, encrypted, 0);
//        enc_len += cipher.doFinal(encrypted, enc_len);

        Cipher cbc = createEncCbcCipher();
        System.out.println("Diff");
        ArrayList <Object> results = encryptPlainText(input, cbc);

        System.out.println(results);


        Cipher cbc2 = createEncCbcCipher();
        System.out.println("Diff");
        ArrayList <Object> results2 = encryptPlainText(input, cbc);

        System.out.println(results);

    }
}
