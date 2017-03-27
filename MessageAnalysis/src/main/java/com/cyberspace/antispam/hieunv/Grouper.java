package com.cyberspace.antispam.hieunv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hieunv on 27/03/2017.
 */
public class Grouper {
    private ArrayList<Message> messages;
    private ArrayList<Message> messagesGroup = new ArrayList<Message>();
    public Grouper(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessagesGroup() {
        return messagesGroup;
    }

    public void group(){
        sort(messages);
        int index = 0;
        while (index < messages.size()){
            String servicePhone = messages.get(index).getServicePhone();
            Message messageItem = messages.get(index);
            for (int i = index;;i++){
                Message message = messages.get(i);
                if(messageItem.isSame(message)){
                    messageItem.setDuplicte(message.getDuplicte()+messageItem.getDuplicte());
                    messages.remove(i);
                }
                if(!message.getServicePhone().equals(servicePhone)){
                    index++;
                    break;
                }
            }
        }
    }

    private void sort(ArrayList<Message> _messages){
        Collections.sort(_messages, new Comparator<Message>() {
            public int compare(Message message, Message t1) {
                return message.getServicePhone().compareTo(t1.getServicePhone());
            }
        });
    }
}
