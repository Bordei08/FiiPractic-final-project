package ro.fiipractic.FiiPracticFinalProject.models;

import org.springframework.lang.NonNull;
import java.sql.Timestamp;

public class Post {
    @NonNull
    private String id;
    @NonNull
    private String creatorId;
    @NonNull
    private String message;
    @NonNull
    private Timestamp timestamp;
    private String repostId;

    private String sharerId;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(@NonNull String creatorId) {
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

    public String getRepostId() {
        return repostId;
    }
    public void setRepostId(String repostId) {
        this.repostId = repostId;
    }

    public String getSharerId() {
        return sharerId;
    }

    public void setSharerId(String sharerId) {
        this.sharerId = sharerId;
    }
}
