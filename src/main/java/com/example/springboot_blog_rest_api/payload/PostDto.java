package com.example.springboot_blog_rest_api.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

    public PostDto() {
    }
}
