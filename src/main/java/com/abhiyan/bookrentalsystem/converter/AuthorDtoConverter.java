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
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setEmail(author.getEmail());
        authorDto.setMobileNumber(author.getMobileNumber());

        return authorDto;
    }

    public Author dtoToEntity(AuthorDto authorDto){
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
        author.setMobileNumber(authorDto.getMobileNumber());

        return author;
    }

    public List<AuthorDto> entityToDto(List<Author> authors){
        return authors.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

}
