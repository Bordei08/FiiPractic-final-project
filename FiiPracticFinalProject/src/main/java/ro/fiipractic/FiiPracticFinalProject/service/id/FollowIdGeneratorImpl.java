package ro.fiipractic.FiiPracticFinalProject.service.id;

import ro.fiipractic.FiiPracticFinalProject.service.id.FollowIdGenerator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class FollowIdGeneratorImpl implements FollowIdGenerator {


    private boolean isSignatureEnabled;

    public FollowIdGeneratorImpl(boolean isSignatureEnabled){
        this.isSignatureEnabled = isSignatureEnabled;
    }

    @Override
    public String generateFollowId(String user1Id, String user2Id, Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = user1Id + user2Id + dateFormat.format(timestamp);
        result = Integer.toString(Objects.hash(result));
        return isSignatureEnabled ? "ufid".concat(result) : result;
    }
}
