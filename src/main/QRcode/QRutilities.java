package main.QRcode;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class QRutilities {

    private static String pathTmpQr;

    /**
     * @return list of QrFiles
     * Within the temporary folde
     */
    public static List<String> listQrFiles(){


        pathTmpQr = "src/main/QRcode/TmpQr";

        List<String> QrCodeList = new ArrayList<>();

        File[] Qrfiles = new File(pathTmpQr).listFiles();

        for ( File QrFile : Qrfiles){
            if ( QrFile.isFile()){
                QrCodeList.add(QrFile.getName());
            }
        }

        return QrCodeList;
    }


    // Tested
    public static  void deleteQrFiles() {
        pathTmpQr = "src/main/QRcode/TmpQr/testdelete/";

        File QrImageDir = new File(pathTmpQr);

        if (QrImageDir.isDirectory()){
            for (File QrImage: QrImageDir.listFiles()){
                deleteFiles(QrImage);
            }
        }
        else{
            QrImageDir.delete();
        }

    }
    // FIXME Need them both to work
    private static void deleteFiles(File file) {

        if (file.isDirectory())
            for (File f : file.listFiles()) {
                deleteFiles(f);
            }
        else
            file.delete();
    }

    //Writes the Initialistion byte to file and the key bytes to file
    public static void writeKeyBytesToFile() {
        KeyGenerator keyMaker = null;
        KeyGenerator initVector = null;

        try {
            keyMaker = KeyGenerator.getInstance("DES");
            initVector=KeyGenerator.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyMaker.init(56);
        initVector.init(56);

        SecretKey key = keyMaker.generateKey();
        SecretKey initialisation = initVector.generateKey();

        byte [] keyBytes = key.getEncoded();
        byte [] IvBytes = initialisation.getEncoded();
        try {
            Files.write(Paths.get("src/main/resources/qrKey.txt"), keyBytes);
            Files.write(Paths.get("src/main/resources/qrInitVector.txt"), IvBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        File file = new File("src/main/QRcode/TmpQr/testdelete/");

        deleteQrFiles();
    }
}
