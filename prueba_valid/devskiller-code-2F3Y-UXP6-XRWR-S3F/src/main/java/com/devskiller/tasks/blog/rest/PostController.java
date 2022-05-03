package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.PostService;

@Controller
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;
	private final CommentService commentService;


	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

	@PostMapping(value = "/{id}/comments")
	public ResponseEntity<Long> addNewComment(@PathVariable Long id, @RequestBody NewCommentDto newCommentDto) {
		if(commentService.addComment(id, newCommentDto) == 0){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(commentService.addComment(id, newCommentDto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}/comments")
	public ResponseEntity<Object> getCommentsById(@PathVariable Long id){
		return new ResponseEntity<>(commentService.getCommentsForPost(id), HttpStatus.FOUND);
	}

}
