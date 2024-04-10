package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptionServiceImpl implements  PasswordEncryptionService{

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PasswordEncryptionServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String eencryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
