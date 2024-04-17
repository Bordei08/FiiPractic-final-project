package ro.fiipractic.FiiPracticFinalProject.service.id;

import java.sql.Timestamp;

public interface FollowIdGenerator {
    String generateFollowId(String user1Id, String user2Id, Timestamp timestamp);
}
