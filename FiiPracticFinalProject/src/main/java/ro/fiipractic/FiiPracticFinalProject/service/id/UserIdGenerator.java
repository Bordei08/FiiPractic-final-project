package ro.fiipractic.FiiPracticFinalProject.service.id;

public interface UserIdGenerator {

    String generateUserId(String username ,String firstName, String lastName);
}