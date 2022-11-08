package com.example.springbootidea.controller;
import com.example.springbootidea.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController   //将对象数据直接以 JSON 形式写入 HTTP 响应(Response)中
@RequestMapping("/api")
public class BookController {

    private List<Book> books = new ArrayList<>();


    //ResponseEntity: 表示整个HTTP Response：状态码，标头和正文内容。我们可以使用它来自定义HTTP Response 的内容。
    @PostMapping("/book")
    public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
        //@RequestBody:可以将 HttpRequest body 中的 JSON 类型数据反序列化为合适的 Java 类型
        books.add(book);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBookById(@PathVariable("id") int id) {
        //@PathVariable:取url地址中的参数
        books.remove(id);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book")
    public ResponseEntity getBookByName(@RequestParam("name") String name) {
        //@RequestParam url的查询参数值
        List<Book> results = books.stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }
}
