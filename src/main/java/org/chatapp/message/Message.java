package org.chatapp.message;

public class Message {
    private String from;
    private String content;
    private String id;

    public Message(String from, String content) {
        this.from = from;
        this.content = content;
    }
    public Message(String from, String content, String id) {
        this.from = from;
        this.content = content;
        this.id = id;
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
