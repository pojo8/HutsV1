package main.QuerySet;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class QueryUtilities {



    //FIXME The array splitting is not working correctly
    public static ArrayList<ArrayList<Object>> productsSet(ArrayList LargeSet) throws Exception {

        int totalLength = LargeSet.size();
        if(totalLength % 6 == 0){
            ArrayList<Object> subSet= new ArrayList<>();
            ArrayList<ArrayList<Object>>collectedSets = new ArrayList<>();

            for(int i= 0; i<totalLength; i++){

                subSet.add(LargeSet.get(i));
                if(i%6==0){
                    collectedSets.add(subSet);
                }
            }
            return collectedSets;

        }
        else{
            throw new Exception("The array being split must have a size that is multiple of 6");
        }
    }



    public static ArrayList<ArrayList<Object>> orderssSet(ArrayList LargeSet) throws Exception {

        int totalLength = LargeSet.size();
        if(totalLength % 6 == 0){
            ArrayList<Object> subSet= new ArrayList<>();
            ArrayList<ArrayList<Object>>collectedSets = new ArrayList<>();

            for(int i= 0; i<totalLength; i++){

                subSet.add(LargeSet.get(i));
                if(i%6==0){
                    collectedSets.add(subSet);
                }

            }
            return collectedSets;

        }
        else{
            throw new Exception("The array being split must have a size that is multiple of 6");
        }
    }

    public static ArrayList<ArrayList<Object>> contactssSet(ArrayList LargeSet) throws Exception {

        int totalLength = LargeSet.size();
        if(totalLength % 5 == 0){
            ArrayList<Object> subSet= new ArrayList<>();
            ArrayList<ArrayList<Object>>collectedSets = new ArrayList<>();

            for(int i= 0; i<totalLength; i++){

                subSet.add(LargeSet.get(i));
                if(i%5==0){
                    collectedSets.add(subSet);
                }
            }
            return collectedSets;

        }
        else{
            throw new Exception("The array being split must have a size that is multiple of 6");
        }
    }

    public static List<List<Object>> twoResultssplit( ArrayList<Object> sqlResults){
        Object [][] finalSet = new Object[][] {{}};

        List<List<Object>> listData = new ArrayList<List<Object>>();

       // int ArraySize = sqlResults.size();

        for(int i=0; i<sqlResults.size()-1; i= i+2){

            listData.add(Arrays.asList(sqlResults.get(i), sqlResults.get(i+1)));
         //   System.out.println(listData);
        }

        return listData;


    } public static List<List<Object>> threeResultssplit( ArrayList<Object> sqlResults){
        Object [][] finalSet = new Object[][] {{}};

        List<List<Object>> listData = new ArrayList<List<Object>>();

        // int ArraySize = sqlResults.size();

        for(int i=0; i<sqlResults.size()-1; i= i+3){

            listData.add(Arrays.asList(sqlResults.get(i), sqlResults.get(i+1),sqlResults.get(i+2)));
            //   System.out.println(listData);
        }

        return listData;


    }

    public static int createUniqueID(){
        Random randomNumber = new Random();

        int primaryKey = randomNumber.nextInt(10000000 -1)+1;

        return primaryKey;
    }

    public static void main(String[] args) throws Exception {
//        ArrayList <Object> set= new ArrayList(Arrays.asList(11981, "Mobile Armour", 2018-06-18, 2018-06-18, 7.77, "GBP",11881, "Mobile Armour", 2018-06-18, 2018-06-18, 7.77, "GBP"));
//
//        System.out.println(productsSet(set));

//        System.out.println(createUniqueID());
//        System.out.println(createUniqueID());

        ArrayList vals = Query_ProductList.selectProductAndLot();

        System.out.println(threeResultssplit(vals));

        List v2 =threeResultssplit(vals);

        System.out.println(v2.get(1));

        List v3 = (List) v2.get(1);

        System.out.println(v3.get(1));

        System.out.println(v2.get(2));



    }



}
