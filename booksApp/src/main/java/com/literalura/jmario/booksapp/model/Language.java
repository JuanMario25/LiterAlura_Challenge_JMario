package com.literalura.jmario.booksapp.model;

public enum Language {
    English("en"),
    Spanish("es"),
    French("fr"),
    German("de"),
    Italian("it"),
    Portuguese("pt"),
    Dutch("nl"),
    Russian("ru"),
    Chinese("zh"),
    Japanese("ja"),
    Korean("ko"),
    Arabic("ar"),
    Hindi("hi"),
    Greek("el"),
    Turkish("tr"),
    Swedish("sv"),
    Danish("da"),
    Finnish("fi"),
    Polish("pl"),
    Romanian("ro"),
    Czech("cs"),
    Hungarian("hu");
    
    private final String lang;
    
    Language(String language) {
        this.lang = language;
    }

    public static Language fromString(String text) {
        for (Language language : Language.values()) {
            if (language.lang.equalsIgnoreCase(text)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Any language founded: " + text);
    }
}
