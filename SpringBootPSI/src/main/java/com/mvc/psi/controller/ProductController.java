package com.mvc.psi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mvc.psi.model.dto.ProductDto;
import com.mvc.psi.service.ProductService;

import java.util.List;

/*
 * URL 路徑設計
 * GET  /               商品資料首頁
 * GET  /edit/{id}      單筆查詢(修改用)
 * POST /               新增
 * PUT  /{id}           修改
 * GET  /delete/{id}    刪除
 * */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(@ModelAttribute ProductDto productDto, Model model) { // ModelAttribute 可以不寫, 在 Spring 4 後也還是會丟進頁面
        List<ProductDto> productDtos = productService.findAll();
        model.addAttribute("productDtos", productDtos);
        return "product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        ProductDto productDto = productService.getProductDtoById(id);
        model.addAttribute("productDto", productDto);
        return "product-edit";
    }

    @PostMapping("/")
    public String create(ProductDto productDto) {
        productService.add(productDto);
        return "redirect:/product/";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, ProductDto productDto) {
        productService.update(productDto, id);
        return "redirect:/product/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/product/";
    }

}
