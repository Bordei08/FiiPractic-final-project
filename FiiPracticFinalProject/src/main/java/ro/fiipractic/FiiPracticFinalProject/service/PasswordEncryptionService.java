package ro.fiipractic.FiiPracticFinalProject.service;

public interface PasswordEncryptionService {
    public String eencryptPassword(String password);
    public boolean verifyPassword(String rawPassword, String encodedPassword);
}
