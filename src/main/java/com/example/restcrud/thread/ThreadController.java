package com.example.restcrud.thread;

import com.example.restcrud.commentary.Commentary;
import com.example.restcrud.commentary.CommentaryRepository;
import com.example.restcrud.commentary.CommentaryService;
import com.example.restcrud.user.User;
import com.example.restcrud.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("https://cryptic-everglades-41257.herokuapp.com/")
@RestController
@RequestMapping("/api")
public class ThreadController {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private CommentaryService commentaryService;

    @GetMapping("/threads")
    public List<Thread> getAllThreads() {
        return threadRepository.findAll();
    }

    @GetMapping("/thread/{threadId}")
    public Thread getThread(@PathVariable String threadId) {
        return threadRepository.findById(Long.parseLong(threadId)).orElse(null);
    }

    @GetMapping("/thread/{threadId}/commentaries")
    public List<Commentary> getThreadCommentaries(@PathVariable String threadId) {
        return threadRepository.findById(Long.parseLong(threadId)).orElse(null).getCommentaries();
    }

    @PostMapping("/thread/new")
    public void createThread(@RequestBody Thread thread) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User author = userRepository.findByUserName(auth.getName());
        threadService.createNewThread(author, thread);
    }

    @PostMapping("/thread/{threadId}")
    public void addNewCommentary(@RequestBody Commentary commentary, @PathVariable String threadId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User author = userRepository.findByUserName(auth.getName());
        Thread thread = threadRepository.findById(Long.parseLong(threadId)).orElse(null);
        commentaryService.createCommentary(author, thread, commentary);
    }

    @DeleteMapping("/thread/{threadId}")
    public void deleteThread(@PathVariable String threadId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(auth.getName());
        if (user.getUserId() != threadRepository.findById(Long.parseLong(threadId)).orElse(null).getUser().getUserId()) {
            throw new IllegalStateException("Impossible to delete someone else's thread");
        }
        threadRepository.deleteById(Long.parseLong(threadId));
    }

    @DeleteMapping("/commentary/{commentaryId}")
    public void deleteCommentary(@PathVariable String commentaryId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(auth.getName());
        if (user.getUserId() != commentaryRepository.findById(Long.parseLong(commentaryId)).orElse(null).getUser().getUserId()) {
            throw new IllegalStateException("Impossible to delete someone else's commentary");
        }
        commentaryRepository.deleteById(Long.parseLong(commentaryId));
    }
}