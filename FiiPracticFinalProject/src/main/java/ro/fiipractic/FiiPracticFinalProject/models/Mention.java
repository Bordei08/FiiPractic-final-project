package ro.fiipractic.FiiPracticFinalProject.models;

import org.springframework.lang.NonNull;

public class Mention {
    @NonNull
    private String id;
    @NonNull
    private String uerId;
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
    public String getUerId() {
        return uerId;
    }

    public void setUerId(@NonNull String uerId) {
        this.uerId = uerId;
    }

    @NonNull
    public String getPostId() {
        return postId;
    }

    public void setPostId(@NonNull String postId) {
        this.postId = postId;
    }
}
