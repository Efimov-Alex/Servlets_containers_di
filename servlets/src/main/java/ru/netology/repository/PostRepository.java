package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


public class PostRepository {
    private List<Post> posts = new ArrayList<>();

    private AtomicLong idCounter = new AtomicLong(0L); // 0 в типе long

    public List<Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        Optional<Post> optionalPost = null;
        for (Post post : posts) {
            if (post.getId() == (id)) {
                optionalPost = Optional.of(post);
            }
        }
        return optionalPost;
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(idCounter.incrementAndGet());
            posts.add(post);
            return post;
        } else {
            for (Post postPast : posts) {
                if (postPast.getId() == (post.getId())) {
                    postPast.setContent(post.getContent());
                    return postPast;
                }
            }

            posts.add(post);

        }
        return post;
    }

    public void removeById(long id) {
        for (Post post : posts) {
            if (post.getId() == (id)) {
                posts.remove(post);
                break;
            }
        }
    }
}
