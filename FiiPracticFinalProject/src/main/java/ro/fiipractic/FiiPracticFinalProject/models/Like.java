package ro.fiipractic.FiiPracticFinalProject.models;

import org.springframework.lang.NonNull;

public class Like {
    @NonNull
    private String id;
    @NonNull
    private String userId;
    @NonNull
    private String postId;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @NonNull
    public String getPostId() {
        return postId;
    }

    public void setPostId(@NonNull String postId) {
        this.postId = postId;
    }
}
