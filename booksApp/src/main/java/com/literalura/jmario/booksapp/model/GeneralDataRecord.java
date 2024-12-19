package com.literalura.jmario.booksapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneralDataRecord(
        @JsonAlias("results")List<BookRecord> books
        ) {
}
