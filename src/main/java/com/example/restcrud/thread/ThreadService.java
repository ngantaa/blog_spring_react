package com.example.restcrud.thread;

import com.example.restcrud.user.User;
import com.example.restcrud.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class ThreadService {

    @Autowired
    private ThreadRepository threadRepository;

    public void createNewThread(User user, Thread thread) {
        Thread newThread = new Thread();
        newThread.setThreadName(thread.getThreadName());
        newThread.setContent(thread.getContent());
        newThread.setCreatedAt(LocalDateTime.now());
        newThread.setUser(user);
        threadRepository.save(newThread);
    }

}
