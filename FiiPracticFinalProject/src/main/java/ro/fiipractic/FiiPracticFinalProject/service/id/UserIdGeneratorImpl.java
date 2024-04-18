package ro.fiipractic.FiiPracticFinalProject.service.id;


import ro.fiipractic.FiiPracticFinalProject.service.id.UserIdGenerator;

import java.util.Objects;

public class UserIdGeneratorImpl implements UserIdGenerator {

    private final boolean isSignatureEnabled;

    public UserIdGeneratorImpl(boolean isSignatureEnabled) {
        this.isSignatureEnabled = isSignatureEnabled;
    }

    @Override
    public String generateUserId(String username, String firstName, String lastName) {
        String result = username + firstName +lastName;
        result = Integer.toString(Objects.hash(result));
        return isSignatureEnabled ? "uuid:".concat(result) : result;

    }

}
