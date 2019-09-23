package main.reports;

import java.awt.print.PrinterJob;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import main.QuerySet.Query_Reports;
import main.QuerySet.Query_Reports.*;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

import static main.QuerySet.Query_Reports.*;

public class ReportGenerator {


    public static String writeToFile() throws IOException, SQLException {

        StringBuffer reportBuffer = createReportStringBuffer();

       // Files.createFile(Paths.get("src/main/reports/generatedReports/createdReport.txt"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();

        String pathToReport = "src/main/reports/generatedReports/Report-"+ dtf.format(localDate) +".txt";

        BufferedWriter br = new BufferedWriter(new FileWriter(
                new File(pathToReport)));
      //  main/reports/generatedReports/hammer.txt
        br.write(reportBuffer.toString());
        br.flush();

        br.close();

        return pathToReport;
    }

    public static StringBuffer createReportStringBuffer() throws IOException, SQLException {

        StringBuffer ReportString = new StringBuffer();

        ArrayList<Object> products = selectAllProductnames();

        ArrayList<Object> prices = selectAllProductPrices();

        ArrayList<Object> totalOrds = selectAllTotalOrders();

        ArrayList<Object> remOrds = selectAllRemainingOrders();

        String doubleSep = "===============================================\n";

        String singleSep = "-----------------------------------------------\n";

        String flatSep = "_______________________________________________\n";


        for (int i = 0; i < products.size(); i++) {
            ReportString.append(doubleSep);

            ReportString.append("Product:                     " + products.get(i) + "\n");

            ReportString.append("Price:                       " + prices.get(i) + "\n");
            ReportString.append(doubleSep);
            ReportString.append("\n");

            ReportString.append("Amount sold:                 " + (Integer.valueOf(totalOrds.get(i).toString()) - Integer.valueOf(remOrds.get(i).toString())) + "\n");
            ReportString.append("\n");

            ReportString.append("Stock remaining:             " + remOrds.get(i));
            ReportString.append("\n");

            ReportString.append(singleSep);

            ReportString.append("Current Revenue:             " + Double.valueOf(prices.get(i).toString()) * (Integer.valueOf(totalOrds.get(i).toString()) - Integer.valueOf(remOrds.get(i).toString())) + "\n");
            ReportString.append(singleSep);
            ReportString.append("\n");
            ReportString.append("\n");
            ReportString.append("Maximum Revenue:             " + Double.valueOf(prices.get(i).toString()) * (Integer.valueOf(totalOrds.get(i).toString())) + "\n");
            ReportString.append("\n");
            ReportString.append("\n");
            ReportString.append(singleSep);
            ReportString.append("\n");
            ReportString.append("Stagnant Revenue:            " + (Double.valueOf(prices.get(i).toString()) * (Integer.valueOf(totalOrds.get(i).toString())) - (Double.valueOf(prices.get(i).toString()) * (Integer.valueOf(totalOrds.get(i).toString()) - Integer.valueOf(remOrds.get(i).toString())))) + "\n");
            //  System.out.println();
            ReportString.append(flatSep);
            ReportString.append("\n");
            ReportString.append("\n");


        }
        return ReportString;
    }


    public static void main(String[] args) throws IOException, SQLException, PrintException {


        writeToFile();





    }

}


