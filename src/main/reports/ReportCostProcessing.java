package main.reports;

import main.QuerySet.Query_Reports;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportCostProcessing {


    public static  ArrayList<Double> calculateMaxSales(ArrayList<Object> prices, ArrayList<Object>totalOrders){

        ArrayList <Double> MaxRevenue = new ArrayList<>(prices.size());

        for(int i = 0; i < prices.size(); i++){

            double currentPrice = new Double(prices.get(i).toString());
            int currentTotalLot = new Integer(totalOrders.get(i).toString());

            double currentMaxRevenue = currentPrice * currentTotalLot;

            MaxRevenue.add(currentMaxRevenue);

        }

        return MaxRevenue;

    }

    public static  ArrayList<Double> calculateTotalSales(ArrayList<Object> prices, ArrayList<Object>remainingOrders){

        ArrayList <Double> actualRevenue = new ArrayList<>(prices.size());

        for(int i = 0; i < prices.size(); i++){

            double currentPrice = new Double(prices.get(i).toString());
            int currentTotalLot = new Integer(remainingOrders.get(i).toString());

            double currentRevenue = currentPrice * currentTotalLot;

            actualRevenue.add(currentRevenue);

        }

        return actualRevenue;

    }

    public static  ArrayList<Double> calculateStagnantSales(ArrayList<Double> maxSales, ArrayList<Double>actualSales){

        ArrayList <Double> actualRevenue = new ArrayList<>(maxSales.size());

        for(int i = 0; i < maxSales.size(); i++){

            double currentMaxSales = maxSales.get(i);
            double currentTotalSales = actualSales.get(i);

            double currentRevenue = currentMaxSales - currentTotalSales;

            actualRevenue.add(currentRevenue);

        }

        return actualRevenue;

    }

    public static void main(String[] args) throws IOException, SQLException {

        System.out.println( calculateMaxSales(Query_Reports.selectAllProductPrices(),Query_Reports.selectAllTotalOrders()));


        System.out.println( calculateTotalSales(Query_Reports.selectAllProductPrices(),Query_Reports.selectAllRemainingOrders()));

        System.out.println( calculateStagnantSales(calculateMaxSales(Query_Reports.selectAllProductPrices(),Query_Reports.selectAllTotalOrders()),calculateTotalSales(Query_Reports.selectAllProductPrices(),Query_Reports.selectAllRemainingOrders())));
    }


}
