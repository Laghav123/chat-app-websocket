package org.chatapp.message;

public class Message {
    private String from;
    private String content;
    private String id;

    public Message(String from, String content) {
        this.from = from;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }
}
