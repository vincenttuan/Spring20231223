package com.mvc.model;

// jdk 16 開始: record 是指定一個不可變的資料物件
// jdk 21 release
// 將當於傳統 Java 物件的 getter、equals、hashCode、toString 等方法都自動產生
public record Message(String content) {}
