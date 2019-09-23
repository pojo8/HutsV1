package main.QRcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QRgenerator {

    // TODO go the route of the stitching same image side by side

    public static void  createQRcode(String qrCodeData, int number)
            throws WriterException, IOException {


        Map hintMap = new HashMap();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix matrixx = new MultiFormatWriter().encode( new String(qrCodeData.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, 100, 100);

        for (int i = 0; i<number ; i++) {

            String filePath = "src/main/QRcode/TmpQr/testdelete/QrTemp" + i + ".png";

            OutputStream output = new FileOutputStream(filePath);

            MatrixToImageWriter.writeToStream(matrixx, "PNG", output);
        }
    }




    public static void main(String[] args) throws IOException, WriterException {
        createQRcode("My left hand is the hand that binds reality",1);



    }

}
