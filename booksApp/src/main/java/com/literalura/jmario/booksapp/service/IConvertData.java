package com.literalura.jmario.booksapp.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> tClass);
}
