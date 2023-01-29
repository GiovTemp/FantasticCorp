package fantasticcorp;

public abstract class Message {

    private String content;
    private String date;

    public Message(String content) {

        this.content = content;
    }


    public String getContent() {
        return content;
    }
    
    public String getDate() {
        return date;
    }
}
