package ibf2022.batch1.csf.assessment.server.models;

import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    private String title;
    private String name;
    private Integer rating;
    private String comment;

    @Override
    public String toString() {
        return "Comment [title=" + title + ", name=" + name + ", rating=" + rating + ", comment=" + comment + "]";
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

     public Document toDocument(){
        Document doc = new Document();
        doc.put("title", getTitle());
        doc.put("name", getName());
        doc.put("rating", getRating());
        doc.put("comment", getComment());
        return doc;
    }

     public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("title", getTitle())
                .add("name", getComment())
                .add("rating", getRating())
                .add("comment", getComment())
                .build();
    }
    
    public static Comment create(Document d) {
        Comment c = new Comment();
        c.setTitle(d.getString("title"));
        c.setName(d.getString("name"));
        c.setRating(d.getInteger("rating"));
        c.setComment(d.getString("comment"));
        return c;
    }
}
