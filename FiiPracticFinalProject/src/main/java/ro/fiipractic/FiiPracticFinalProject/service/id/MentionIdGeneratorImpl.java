package ro.fiipractic.FiiPracticFinalProject.service.id;

import java.util.Objects;

public class MentionIdGeneratorImpl implements  MentionIdGenerator{

    private boolean isSignatureEnabled;

    public MentionIdGeneratorImpl(boolean isSignatureEnabled){
        this.isSignatureEnabled = isSignatureEnabled;
    }

    @Override
    public String generateMentionId(String userId, String postId) {
        String result = userId  + postId;
        result = Integer.toString(Objects.hash(result));
        return isSignatureEnabled ? "umid:" + result : result;
    }
}
