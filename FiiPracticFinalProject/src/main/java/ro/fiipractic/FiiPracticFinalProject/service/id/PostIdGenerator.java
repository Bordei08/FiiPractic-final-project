package ro.fiipractic.FiiPracticFinalProject.service.id;

import java.sql.Timestamp;

public interface PostIdGenerator {
    String generatePostId(String creatorId, Timestamp timestamp);
}
