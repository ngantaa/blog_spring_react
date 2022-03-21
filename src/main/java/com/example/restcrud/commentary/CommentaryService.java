package com.example.restcrud.commentary;

import com.example.restcrud.thread.Thread;
import com.example.restcrud.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;

    public void createCommentary(User user, Thread thread, Commentary commentary) {
        Commentary newCommentary = new Commentary();
        newCommentary.setCommentaryContent(commentary.getCommentaryContent());
        newCommentary.setCreatedAt(LocalDateTime.now());
        newCommentary.setUser(user);
        newCommentary.setThread(thread);
        commentaryRepository.save(newCommentary);
    }
}
