package com.literalura.jmario.booksapp.main;

import com.literalura.jmario.booksapp.model.AuthorDataDTO;
import com.literalura.jmario.booksapp.model.BookClass;

import com.literalura.jmario.booksapp.model.GeneralDataRecord;
import com.literalura.jmario.booksapp.model.Language;
import com.literalura.jmario.booksapp.repository.BooksRepository;
import com.literalura.jmario.booksapp.service.ConvertData;
import com.literalura.jmario.booksapp.service.QueryAPI;
import com.literalura.jmario.booksapp.service.URLManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.net.URI;
import java.util.List;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                    2- Get all books registered
                    3- Get all authors registered
                    4- Get the authors alive until year
                    5- Get books by Language
                    0- Exit
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
                        try {
                            book.get().getAuthor().setBooks(book.get());
                            repository.save(book.get());
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
                    List<BookClass> registeredBooks = repository.findAllTitles();
                    System.out.println(registeredBooks);
                    break;

                case 3:
                    List<Object[]> registeredAuthors = repository.findAllAuthors();
                    castObjectToAuthorDataDTO(registeredAuthors).forEach(System.out::println);

                    break;

                case 4:
                    int year;

                    try {
                        System.out.println("Please enter a year to get the Authors alive until that year");
                        year = Integer.parseInt(userInput.nextLine());
                        List<Object[]> authorsAliveUntil = repository.findAllAuthors();
                        castObjectToAuthorDataDTO(authorsAliveUntil).stream()
                                .filter(a -> a.getDeath_year() != null && a.getDeath_year() >= year)
                                .forEach(System.out::println);
                    }catch (NumberFormatException e){
                        System.out.println("Invalid Input");
                        System.out.println("Enter a year --> ex. 2024");
                    }

                    break;

                case 5:
                    int category;
                    try {
                        System.out.println("Select the language category");
                        System.out.println(
                                """
                                        1- English  2- Spanish
                                        3- French   4- German
                                        5- Italian  0- Exit
                                        """);
                        category = Integer.parseInt(userInput.nextLine());
                        List<BookClass> buffer = repository.findAllTitles();
                        List<BookClass> books = buffer.stream()
                                .filter(b->b.getLanguages() == selectCategory(category))
                                .toList();

                        if(books.isEmpty()) System.out.println("Any book entered to the DB was wrote in "+ selectCategory(category));
                        else books.forEach(System.out::println);

                    }catch (NumberFormatException e){
                        System.out.println("Invalid Input");
                    }
                    break;

                default:
                    break;
            }

            }

        }

    public List<AuthorDataDTO> castObjectToAuthorDataDTO(List<Object[]> target) {
        return target.stream()
                .map(row -> new AuthorDataDTO(
                        (String) row[0], // author name
                        List.of(((String) row[1]).split(",")), // ids related to the author
                        (Integer) row[2], // birth year
                        (Integer) row[3], // death year
                        List.of(((String) row[4]).split(",")), // books ids related to the author
                        List.of(((String) row[5]).split(",")) // list of books related to the author
                ))
                .collect(Collectors.toList());
    }

    public Language selectCategory(int category){
        return switch (category) {
            case 1 -> Language.English;
            case 2 -> Language.Spanish;
            case 3 -> Language.French;
            case 4 -> Language.German;
            case 5 -> Language.Italian;
            default -> {
                System.out.println("Invalid option");
                yield Language.Russian;
            }
        };
    }
}
