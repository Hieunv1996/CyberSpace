package com.cyberspace.antispam.hieunv;

/**
 * Created by hieunv on 27/03/2017.
 */
public class Message {
    private String servicePhone;
    private String message;
    private int duplicte;

    public Message(String servicePhone, String message, int duplicte) {
        this.servicePhone = servicePhone;
        this.message = message;
        this.duplicte = duplicte;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDuplicte() {
        return duplicte;
    }

    public void setDuplicte(int duplicte) {
        this.duplicte = duplicte;
    }
}
