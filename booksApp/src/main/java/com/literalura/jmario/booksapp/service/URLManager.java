package com.literalura.jmario.booksapp.service;

import java.net.URI;

public class URLManager {

    private static final String BASE_URL = "https://gutendex.com/books/";
    // query parameters
    private static final String AUTHOR_YEAR_START = "author_year_start=";
    private static final String AUTHOR_YEAR_END = "author_year_end=";
    private static final String SEARCH = "search=";
    private static final String LANGUAGES = "languages=";


    public static URI urlBooksFromTo(int startYear, int endYear) {
        return URI.create(BASE_URL+"?"+AUTHOR_YEAR_START+startYear+"&"+AUTHOR_YEAR_END+endYear);
    }

    public static URI urlAllBooks() {
        return URI.create(BASE_URL);
    }

    public static URI urlSearchByTitle(String title) {
        var keyword = title.replace(" ","+");
        return URI.create(BASE_URL+"?"+SEARCH+keyword);
    }
}
