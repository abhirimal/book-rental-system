package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.repository.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public void saveAuthorDetails(Author author) {
        Author newAuthor = new Author();
        newAuthor.setName(author.getName());
        newAuthor.setEmail(author.getEmail());
        newAuthor.setMobile_number(author.getMobile_number());
        authorRepo.save(newAuthor);
    }

    @Override
    public List<Author> getAllAuthors() {
        return null;
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Integer id) {

    }
}
