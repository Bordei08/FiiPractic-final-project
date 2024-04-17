package ro.fiipractic.FiiPracticFinalProject.models;

import org.springframework.lang.NonNull;

import java.sql.Timestamp;

public class Follow {
    @NonNull
    private String id;
    @NonNull
    private String user1Id;
    @NonNull
    private String user2Id;
    @NonNull
    private Timestamp timestamp;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(@NonNull String user1Id) {
        this.user1Id = user1Id;
    }

    @NonNull
    public String getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(@NonNull String user2Id) {
        this.user2Id = user2Id;
    }

    @NonNull
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
