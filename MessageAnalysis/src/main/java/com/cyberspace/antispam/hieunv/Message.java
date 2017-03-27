package com.cyberspace.antispam.hieunv;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hieunv on 27/03/2017.
 */
public class Message {
    private final double MIN_VALUE = 0.7;
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

    public boolean isSame(Message m){
        Set<String> s1 = splitText(m.getMessage());
        Set<String> s2 = splitText(message);
        int count = 0;
        for (String s : s1){
            if(s2.contains(s)){
                count++;
            }
        }
        double ans = (double)count/(s1.size()+s2.size()-count);
        return (ans > MIN_VALUE);
    }

    private Set<String> splitText(String s){
        Set<String> set = new HashSet<String>();
        String[] res = s.split("[\\p{Punct}\\s]+");
        for (String x : res) set.add(x);
        return set;
    }
}
