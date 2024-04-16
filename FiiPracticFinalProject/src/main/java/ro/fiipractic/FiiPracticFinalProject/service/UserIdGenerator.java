package ro.fiipractic.FiiPracticFinalProject.service;

public interface UserIdGenerator {

    String generateUserId(String username ,String firstName, String lastName);
}