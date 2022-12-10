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
        return authorRepo.findAll();
    }

    @Override
    public Author editAuthor(Integer id) {
        return authorRepo.findById(id).orElse(null);
    }

    @Override
    public Author updateAuthor(Integer id, Author author) {
        Author existingAuthor = authorRepo.findById(id).orElse(null);
        existingAuthor.setName(author.getName());
        existingAuthor.setEmail(author.getEmail());
        existingAuthor.setMobile_number(author.getMobile_number());
        authorRepo.save(existingAuthor);
        return existingAuthor;
    }

    @Override
    public void deleteAuthorById(Integer id) {
        authorRepo.deleteById(id);
    }
}