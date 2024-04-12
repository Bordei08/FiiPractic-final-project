package ro.fiipractic.FiiPracticFinalProject.models;

import org.springframework.lang.NonNull;
import java.sql.Timestamp;

public class Post {
    @NonNull
    private Integer id;
    @NonNull
    private Integer creatorId;
    @NonNull
    private String message;
    @NonNull
    private Timestamp timestamp;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(@NonNull Integer creatorId) {
        this.creatorId = creatorId;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    public void setMessage(@NonNull String message) {
        this.message = message;
    }

    @NonNull
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
