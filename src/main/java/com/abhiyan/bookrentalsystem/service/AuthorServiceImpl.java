package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.converter.AuthorDtoConverter;
import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.repository.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final AuthorDtoConverter authorDtoConverter;

    public AuthorServiceImpl(AuthorRepo authorRepo, AuthorDtoConverter authorDtoConverter) {
        this.authorRepo = authorRepo;
        this.authorDtoConverter = authorDtoConverter;
    }

    @Override
    public void saveAuthorDetails(AuthorDto authorDto) {
        Author newAuthor = authorDtoConverter.dtoToEntity(authorDto);
        authorRepo.save(newAuthor);

        // or we can do this way
        // Author newAuthor = new Author();
        // newAuthor.setName(authorDto.getName());
        // newAuthor.setEmail(authorDto.getEmail());
        // newAuthor.setMobile_number(authorDto.getMobile_number());
        // authorRepo.save(newAuthor);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public AuthorDto editAuthor(Integer id) {
        Author author = authorRepo.findById(id).orElse(null);
        return authorDtoConverter.entityToDto(author);
    }

    @Override
    public AuthorDto updateAuthor(Integer id, AuthorDto authorDto) {
        Author existingAuthor = authorRepo.findById(id).orElse(null);
        existingAuthor.setName(authorDto.getName());
        existingAuthor.setEmail(authorDto.getEmail());
        existingAuthor.setMobileNumber(authorDto.getMobileNumber());
        authorRepo.save(existingAuthor);

        return authorDtoConverter.entityToDto(existingAuthor);
    }

    @Override
    public void deleteAuthorById(Integer id) {
        authorRepo.deleteById(id);
    }
}