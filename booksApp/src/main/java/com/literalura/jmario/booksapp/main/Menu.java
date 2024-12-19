package com.literalura.jmario.booksapp.main;

import com.literalura.jmario.booksapp.model.BookClass;

import com.literalura.jmario.booksapp.model.GeneralDataRecord;
import com.literalura.jmario.booksapp.repository.BooksRepository;
import com.literalura.jmario.booksapp.service.ConvertData;
import com.literalura.jmario.booksapp.service.QueryAPI;
import com.literalura.jmario.booksapp.service.URLManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    //attributes

    Scanner userInput = new Scanner(System.in);

    private final BooksRepository repository;

    // constructor

    public Menu(BooksRepository repository){
        this.repository = repository;
    }

    // methods

    public void displayMenu(){
        URI url;
        String json;
        ConvertData convertData = new ConvertData();
        String OPTION_MENU =
                """
                PLEASE CHOOSE AN OPTION:
                    1- Search book by title
                """;
        int option = -1;

        while(option != 0) {

            System.out.println(OPTION_MENU);
            try {
                option = Integer.parseInt(userInput.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Enter only the numbers available in the menu");
                System.out.println("Closing App....");
                option = 0;
            }

            switch (option){
                case 1:
                    System.out.println("Enter the name of the book");
                    String bookTitle = userInput.nextLine();

                    url = URLManager.urlSearchByTitle(bookTitle);
                    json = QueryAPI.getDataFromApi(url);
                    GeneralDataRecord generalDataRecord = convertData.getData(json, GeneralDataRecord.class);
                    System.out.println(generalDataRecord);

                    Optional<BookClass> book = generalDataRecord.books()
                            .stream()
                            .findFirst().map(BookClass::new);
                    if (book.isPresent()) {
                        book.get().getAuthor().setBooks(book.get());
                        try {
                            repository.save(book.get().getAuthor());
                            System.out.println(book.get());
                        }catch (DataIntegrityViolationException e){
                            System.out.println(e.getMessage());
                            System.out.println("This book has been added already");
                        }

                    }else{
                        System.out.println("Book not found !!");
                    }

                    break;
                case 2:
                    List<BookClass> RegisteredBooks = repository.getAllTitles();
                    break;

                default:
                    break;
            }
        }


    }
}
