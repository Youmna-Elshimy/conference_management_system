package CSCI334.ConferencePro.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "ratings")
public class Rating {
    @Id
    private String id;

    private int rating;
    private String authorEmail;


    public Rating() {
    }

    // without id, DB will create one
    public Rating(int rating, String authorEmail) {
        this.rating = rating;
        this.authorEmail = authorEmail;
    }

    public Rating(String id, int rating, String authorEmail) {
        this.id = id;
        this.rating = rating;
        this.authorEmail = authorEmail;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAuthorEmail() {
        return this.authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public Rating id(String id) {
        setId(id);
        return this;
    }

    public Rating rating(int rating) {
        setRating(rating);
        return this;
    }

    public Rating authorEmail(String authorEmail) {
        setAuthorEmail(authorEmail);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rating)) {
            return false;
        }
        Rating reviewRating = (Rating) o;
        return Objects.equals(id, reviewRating.id) && rating == reviewRating.rating && Objects.equals(authorEmail, reviewRating.authorEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, authorEmail);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", rating='" + getRating() + "'" +
            ", authorEmail='" + getAuthorEmail() + "'" +
            "}";
    }
    
}
