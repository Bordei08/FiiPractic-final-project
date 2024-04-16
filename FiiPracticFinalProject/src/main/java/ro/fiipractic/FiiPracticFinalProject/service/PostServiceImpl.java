package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.repository.PostDAO;

@Service
public class PostServiceImpl implements PostService {
    private PostDAO postRepository;

    @Autowired
    public PostServiceImpl(PostDAO postRepository){
        this.postRepository = postRepository;
    }

    public void addPost(Post post){
        postRepository.createPost(post.getCreatorId(), post.getMessage(), post.getTimestamp());
    }

}
