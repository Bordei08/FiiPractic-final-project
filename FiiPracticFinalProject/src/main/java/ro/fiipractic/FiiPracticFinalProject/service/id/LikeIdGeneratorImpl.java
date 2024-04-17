package ro.fiipractic.FiiPracticFinalProject.service.id;

import ro.fiipractic.FiiPracticFinalProject.service.id.LikeIdGenerator;

import java.util.Objects;

public class LikeIdGeneratorImpl implements LikeIdGenerator {
    private boolean isSignatureEnabled;

    public LikeIdGeneratorImpl(boolean isSignatureEnabled){
        this.isSignatureEnabled = isSignatureEnabled;
    }


    @Override
    public String generateLikeId(String userId, String postId) {
        String result = userId.substring(0,1).concat(postId);
        result = Integer.toString(Objects.hash(result));
        return isSignatureEnabled ? "ulid".concat(result) : result;
    }
}
