package com.example.restcrud.thread;

import com.example.restcrud.commentary.Commentary;
import com.example.restcrud.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "threads")
public class Thread {
    @Id
    @SequenceGenerator(
            name = "thread_sequence",
            sequenceName = "thread_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "thread_sequence"
    )
    @Column(name = "thread_id")
    private Long threadId;
    @Column(name = "thread_name")
    private String threadName;
    @Column(columnDefinition = "text")
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    @JsonBackReference(value = "comments_thread")
    private List<Commentary> commentaries;

    public Thread() {
    }

    public Thread(String threadName,
                  String content,
                  LocalDateTime createdAt,
                  User user) {
        this.threadName = threadName;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Long getId() {
        return threadId;
    }

    public void setId(Long threadId) {
        this.threadId = threadId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "threadId=" + threadId +
                ", threadName='" + threadName + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", comments=" + commentaries +
                '}';
    }
}
