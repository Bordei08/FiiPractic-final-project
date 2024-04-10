package ro.fiipractic.FiiPracticFinalProject.util;

import org.springframework.lang.NonNull;

public class UserLoginRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
