package com.mvc.model;

// 自訂 WebSocket 回傳的資料格式
public record Greeting(Boolean status, String content) {}