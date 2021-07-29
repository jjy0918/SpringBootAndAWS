package com.jjy0918.spring.springboot.web.service.posts;

import com.jjy0918.spring.springboot.web.domain.posts.Posts;
import com.jjy0918.spring.springboot.web.domain.posts.PostsRepository;
import com.jjy0918.spring.springboot.web.web.dto.PostsResponseDto;
import com.jjy0918.spring.springboot.web.web.dto.PostsSaveRequestDto;
import com.jjy0918.spring.springboot.web.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PoststService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+"id"));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
