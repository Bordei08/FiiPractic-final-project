package ro.fiipractic.FiiPracticFinalProject.service.id;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ReplyIdGeneratorImpl implements ReplyIdGenerator {

    private boolean isSignatureEnabled;

    public ReplyIdGeneratorImpl(boolean isSignatureEnabled){
        this.isSignatureEnabled = isSignatureEnabled;
    }

    @Override
    public String generateReplyId(String userId, String postId, String parentId) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String result = userId + ((parentId != null ) ? parentId : "NoNe") + postId +(LocalDateTime.now()).format(dateFormat);
        result = Integer.toString(Objects.hash(result));
        return isSignatureEnabled ? "urid".concat(result) : result;
    }
}
