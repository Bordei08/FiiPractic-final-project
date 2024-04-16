package ro.fiipractic.FiiPracticFinalProject.service;

import java.sql.Timestamp;

public interface PostIdGenerator {
    String generatePostId(String creatorId, Timestamp timestamp);
}
