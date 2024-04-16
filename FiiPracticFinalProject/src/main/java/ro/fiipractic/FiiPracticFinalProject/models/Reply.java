package ro.fiipractic.FiiPracticFinalProject.models;

import org.springframework.lang.NonNull;

public class Reply {
    @NonNull
    private String id;
    @NonNull
    private String postId;
    private String parentId;
    @NonNull
    private String message;
    @NonNull
    private String userId;

    @NonNull
    private Boolean varPublic;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getPostId() {
        return postId;
    }

    public void setPostId(@NonNull String postId) {
        this.postId = postId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    public void setMessage(@NonNull String message) {
        this.message = message;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @NonNull
    public Boolean getVarPublic() {
        return varPublic;
    }

    public void setVarPublic(@NonNull Boolean varPublic) {
        this.varPublic = varPublic;
    }
}
