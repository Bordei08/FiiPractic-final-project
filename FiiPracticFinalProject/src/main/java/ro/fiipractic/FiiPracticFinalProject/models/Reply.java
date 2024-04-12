package ro.fiipractic.FiiPracticFinalProject.models;

import org.springframework.lang.NonNull;

public class Reply {
    @NonNull
    private Integer id;
    @NonNull
    private Integer postId;
    private Integer parentId;
    @NonNull
    private String message;
    @NonNull
    private Integer userId;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(@NonNull Integer postId) {
        this.postId = postId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
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
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Integer userId) {
        this.userId = userId;
    }
}
