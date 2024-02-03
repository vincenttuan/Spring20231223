package com.mvc.psi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * URL 路徑設計
 * GET  /customer/            首頁
 * GET  /customer/edit/{id}   單筆查詢(修改用)
 * POST /customer/            新增
 * PUT  /customer/{id}        修改
 * GET  /customer/delete/{id} 刪除
 * */
@Controller
@RequestMapping("/customer")
public class CustomerController {

}
