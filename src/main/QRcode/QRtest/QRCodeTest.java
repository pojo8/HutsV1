package main.QRcode.QRtest;

import main.QRcode.QrDecoder;
import main.QRcode.QrEncoder;
import org.junit.*;
import main.QRcode.QrDecoder.*;
import main.QRcode.QrEncoder.*;

import javax.crypto.Cipher;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class QRCodeTest {

    // The encryption process
    byte [] input = QrEncoder.plainTextToBytes("My left hand is the blade that binds reality");
    Cipher encCiphBlockChain = QrEncoder.createEncCbcCipher();
    ArrayList<Object> encResults = QrEncoder.encryptPlainText(input, encCiphBlockChain);

    // The decryption process
    Cipher decCipBlockChain = QrDecoder.createDecCbcCipher(encResults);
    ArrayList decrypted = QrDecoder.decryptToBytes(encResults,decCipBlockChain);

    //Removing the padding bytes
    byte [] paddingBytes = (byte []) decrypted.get(1);

    @Test
    public void EncryptToDecrypt(){

        assertEquals("My left hand is the blade that binds reality", QrDecoder.bytesToPlaintext(paddingBytes, decrypted));
    }

    @Test
    public void encrypt(){

        assertEquals("[B@48cf768c",encResults.get(1).toString());
    }




}
