package ro.fiipractic.FiiPracticFinalProject.service;

import ro.fiipractic.FiiPracticFinalProject.models.Mention;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;

import java.util.List;


public interface MentionService {
  public void createMention(Mention mention);
  public void deleteMention(String id);
  public Mention getMentionById(String id);
  public List<User> getAllUsersByPostMentions(String postId);
  public List<Post> getAllPostsByUserMentions(String userId);
}
