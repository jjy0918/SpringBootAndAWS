package com.jjy0918.spring.springboot.web.web;

import com.jjy0918.spring.springboot.web.service.posts.PoststService;
import com.jjy0918.spring.springboot.web.web.dto.PostsResponseDto;
import com.jjy0918.spring.springboot.web.web.dto.PostsSaveRequestDto;
import com.jjy0918.spring.springboot.web.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PoststService poststService;

    @PostMapping("/api/v1/posts")
    public Long Save(@RequestBody PostsSaveRequestDto requestDto){
        return poststService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable long id, @RequestBody PostsUpdateRequestDto requestDto){
        return poststService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return poststService.findById(id);
    }

}
