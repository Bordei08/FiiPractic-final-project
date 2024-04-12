package ro.fiipractic.FiiPracticFinalProject.models;

import org.springframework.lang.NonNull;

public class Like {
    @NonNull
    private Integer id;
    @NonNull
    private Integer userid;
    @NonNull
    private Integer postId;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(@NonNull Integer userid) {
        this.userid = userid;
    }

    @NonNull
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(@NonNull Integer postId) {
        this.postId = postId;
    }
}
