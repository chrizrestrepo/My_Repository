package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

@Service
public class CommentService {

	private PostRepository postRepository;

	public CommentService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		List<CommentDto> listComments = new ArrayList<>();

		postRepository.findById(postId)
			.orElseThrow(() -> new NoSuchElementException("the post with id: ".concat(postId.toString()).concat(" NOT FOUND")))
			.getComment().stream().forEach(comment -> listComments.add(
				new CommentDto(
					comment.getId(),
					comment.getComment(),
					comment.getAuthor(),
					comment.getCreationDate())));

		return listComments;
	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public Long addComment(Long postId, NewCommentDto newCommentDto) {

		return postRepository.findById(postId)
			.map(post -> {
				Comment comment = new Comment(newCommentDto.getPostId(), newCommentDto.getContent(), newCommentDto.getAuthor(), LocalDateTime.now());
				post.getComment().add(comment);
				return postRepository.save(post).getId();
			}).orElse(0L);
	}
}
