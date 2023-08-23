package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "book")
@Table(name = "`book`")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookname")
    private String bookName;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable=true) // thông qua khóa ngoại address_id
    @JsonManagedReference
    private Producer producer;

    public Book() {

    }

    public Book(Long id, String bookName, String author) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
    }
    public Book updateWith(Book item) {
        return new Book(
                this.id,
                item.bookName,
                item.author
        );
    }
}
