package ro.fiipractic.FiiPracticFinalProject.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ReplyIdGeneratorImpl implements ReplyIdGenerator{


    private boolean isSignatureEnabled;

    public ReplyIdGeneratorImpl(boolean isSignatureEnabled){
        this.isSignatureEnabled = isSignatureEnabled;
    }

    @Override
    public String generateReplyId(String userId, String postId, String parentId) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String result = userId.substring(0,1).concat((parentId != null ) ? parentId : "NoNe").concat(postId).concat((LocalTime.now()).format(dateFormat));
        result = Integer.toString(Objects.hash(result));
        return isSignatureEnabled ? "urid".concat(result) : result;
    }
}
