package com.example.demo.Service;

import com.example.demo.Model.Book;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

//    @Autowired
//    public BookService(BookRepository repository) {
//        this.repository = repository;
//        this.repository.saveAll(defaultBooks());
//    }


    private static List<Book> defaultBooks() {
        return List.of(
                new Book(1L,"Book 1","Author 1"),
                new Book(2L,"Book 2","Author 2"),
                new Book(3L,"Book 3","Author 3")
        );
    }

//    public List<Book> findAll() {
//        List<Book> list = new ArrayList<>();
//        Iterable<Book> items = repository.findAll();
//        items.forEach(list::add);
//        return list;
//    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        repository.findAll().forEach(books::add);
        return books;
    }

    public Optional<Book> find(Long id) {
        return repository.findById(id);
    }
    public Book create(Book item) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        Book copy = new Book(
                new Date().getTime(),
                item.getBookName(),
                item.getAuthor()
        );
        return repository.save(copy);
    }
    public Optional<Book> update( Long id, Book newItem) {
        // Only update an item if it can be found first.
        return repository.findById(id)
                .map(oldItem -> {
                    Book updated = oldItem.updateWith(newItem);
                    return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
