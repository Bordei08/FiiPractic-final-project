package ro.fiipractic.FiiPracticFinalProject.service.id;

import ro.fiipractic.FiiPracticFinalProject.service.id.PostIdGenerator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class PostIdGeneratorImpl implements PostIdGenerator {

    private boolean isSignatureEnabled;

    public PostIdGeneratorImpl(boolean isSignatureEnabled){
        this.isSignatureEnabled = isSignatureEnabled;
    }
    @Override
    public String generatePostId(String creatorId, Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = creatorId.substring(0,1).concat(dateFormat.format(timestamp));
        result = Integer.toString(Objects.hash(result));
        return isSignatureEnabled ? "upid:".concat(result) : result;
    }
}
