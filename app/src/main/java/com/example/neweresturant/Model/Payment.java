package com.example.neweresturant.Model;

public class Payment {

    private String pid,cardexdate,cardname,cardnumber,cardsecode,paytotalamount;

    public Payment() {
    }

    public Payment(String pid, String cardexdate, String cardname, String cardnumber, String cardsecode, String paytotalamount) {
        this.pid = pid;
        this.cardexdate = cardexdate;
        this.cardname = cardname;
        this.cardnumber = cardnumber;
        this.cardsecode = cardsecode;
        this.paytotalamount = paytotalamount;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCardexdate() {
        return cardexdate;
    }

    public void setCardexdate(String cardexdate) {
        this.cardexdate = cardexdate;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardsecode() {
        return cardsecode;
    }

    public void setCardsecode(String cardsecode) {
        this.cardsecode = cardsecode;
    }

    public String getPaytotalamount() {
        return paytotalamount;
    }

    public void setPaytotalamount(String paytotalamount) {
        this.paytotalamount = paytotalamount;
    }
}
