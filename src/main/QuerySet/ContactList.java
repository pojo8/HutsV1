package main.QuerySet;

public class ContactList {

    private int prod_id;
    private String contact1_email;
    private String contact2_email;
    private String vendor_email;
    private int trigger_level;

    public ContactList(int prod_id, String contact1_email, String contact2_email, String vendor_email, int trigger_level) {
        this.prod_id = prod_id;
        this.contact1_email = contact1_email;
        this.contact2_email = contact2_email;
        this.vendor_email = vendor_email;
        this.trigger_level = trigger_level;
    }

    public int getProd_id() {
        return prod_id;
    }

    public String getContact1_email() {
        return contact1_email;
    }

    public String getContact2_email() {
        return contact2_email;
    }

    public String getVendor_email() {
        return vendor_email;
    }

    public int getTrigger_level() {
        return trigger_level;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public void setContact1_email(String contact1_email) {
        this.contact1_email = contact1_email;
    }

    public void setContact2_email(String contact2_email) {
        this.contact2_email = contact2_email;
    }

    public void setVendor_email(String vendor_email) {
        this.vendor_email = vendor_email;
    }

    public void setTrigger_level(int trigger_level) {
        this.trigger_level = trigger_level;
    }
}
