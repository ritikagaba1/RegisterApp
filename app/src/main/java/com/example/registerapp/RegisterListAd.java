package com.example.registerapp;

public class RegisterListAd {
    String customername,whatsappno,email_id,plan_start_date,plan_date,plan_amount;

    public String getEmail_id() {
        return email_id;
    }

    public String getPlan_date() {
        return plan_date;
    }

    public void setPlan_date(String plan_date) {
        this.plan_date = plan_date;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPlan_start_date() {
        return plan_start_date;
    }

    public void setPlan_start_date(String plan_start_date) {
        this.plan_start_date = plan_start_date;
    }



    public String getPlan_amount() {
        return plan_amount;
    }

    public void setPlan_amount(String plan_amount) {
        this.plan_amount = plan_amount;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getWhatsappno() {
        return whatsappno;
    }

    public void setWhatsappno(String whatsappno) {
        this.whatsappno = whatsappno;
    }

    public RegisterListAd(String customername, String whatsappno,String email_id,String plan_date,String plan_amount){
this.customername=customername;
this.whatsappno=whatsappno;
        this.email_id=email_id;
        this.plan_date=plan_date;
        this.plan_amount=plan_amount;

    }
}
