package com.literalura.jmario.booksapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookRecord(
        String title,
        List<AuthorDataRecord> authors,
        List<String> languages,
        Integer download_count
) {
    @JsonCreator
    public BookRecord(
            @JsonProperty("title") String title,
            @JsonProperty("authors") List<AuthorDataRecord> authors,
            @JsonProperty("languages")List<String> languages,
            @JsonProperty("download_count") String downloads_count
    ){
        this(title,authors,languages,parseDownloads(downloads_count));
    }

    private static Integer parseDownloads(String counter) {
        if ("N/A".equalsIgnoreCase(counter)) {
            return 0; // Default value for "N/A"
        }
        try {
            return Integer.valueOf(counter);
        } catch (NumberFormatException e) {
            return 0; // Fallback value for invalid ratings
        }
    }
}
