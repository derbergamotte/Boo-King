package com.ak.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "bookdetail")
@Data
@EqualsAndHashCode(callSuper = true)
public class Bookdetail extends AEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "cover")
    private String cover;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @OneToOne(mappedBy = "bookdetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Book book;

}
