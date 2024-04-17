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
        String initialFirstNameAndLastName = username.substring(0, 1).concat(firstName).concat(lastName);
        initialFirstNameAndLastName = Integer.toString(Objects.hash(initialFirstNameAndLastName));
        return isSignatureEnabled ? "uuid:".concat(initialFirstNameAndLastName) : initialFirstNameAndLastName;

    }
}
