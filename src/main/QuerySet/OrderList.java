package main.QuerySet;

/**
 * From the main data entry point the constructors will be set by importing the setters and getters.
 * Allowing for Db specific searches and updates top take place
 */

public class OrderList
{
    private static int order_id;
    private static String date_ordered;
    private static int prod_id;
    private static int lot_size;
    private static int remaining_lot;
    private static String generic_Qr_block;

    public OrderList(int order_id, String date_ordered, int prod_id, int lot_size, int remaining_lot, String generic_Qr_block){
        this.order_id = order_id;
        this.date_ordered = date_ordered;
        this.prod_id = prod_id;
        this.lot_size = lot_size;
        this.remaining_lot = remaining_lot;
        this.generic_Qr_block = generic_Qr_block;
    }

    public int getOrder_id() {
        return order_id;
    }

    public static String getDate_ordered() {

        return date_ordered;
    }

    public int getProd_id() {
        return prod_id;
    }

    public int getLot_size() {
        return lot_size;
    }

    public int getRemaining_lot() {
        return remaining_lot;
    }

    public String getGeneric_Qr_block() {
        return generic_Qr_block;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setDate_ordered(String date_ordered) {
        this.date_ordered = date_ordered;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public void setLot_size(int lot_size) {
        this.lot_size = lot_size;
    }

    public void setRemaining_lot(int remaining_lot) {
        this.remaining_lot = remaining_lot;
    }

    public void setGeneric_Qr_block(String generic_Qr_block) {
        this.generic_Qr_block = generic_Qr_block;
    }
}
