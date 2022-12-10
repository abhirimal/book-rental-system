package com.abhiyan.bookrentalsystem.converter;

import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorDtoConverter {

    public AuthorDto entityToDto(Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getName());
        authorDto.setEmail(author.getEmail());
        authorDto.setMobile_number(author.getMobile_number());

        return authorDto;
    }

    public Author dtoToEntity(AuthorDto authorDto){
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
        author.setMobile_number(authorDto.getMobile_number());

        return author;
    }

    public List<AuthorDto> entityToDto(List<Author> authors){
        return authors.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

}
