package com.nagarro.a5.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.nagarro.a5.entity.Author;
import com.nagarro.a5.entity.Book;
import com.nagarro.a5.service.UserAuthService;

@Controller
@RequestMapping("/book")
public class BookController {

	private RestTemplate restTemplate;
	private String url = "http://localhost:8080/";

	public BookController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping("")
	public String book(Model model) {

		ResponseEntity<Book[]> response = restTemplate.getForEntity(url + "book", Book[].class);

		Book[] books = response.getBody();

		List<Book> list = new ArrayList<Book>();

		for (Book book : books)
			list.add(book);

		model.addAttribute("books", list);

		return "book-list.jsp";

	}
	
	@RequestMapping("/register")
	public String registerBook(@ModelAttribute("book") Book book, HttpServletRequest req, Model model) {

		System.out.println(book);

		ResponseEntity<Author> response = restTemplate.getForEntity(url + "author/" + book.getAuthor().getId(),
				Author.class);

		System.out.println(book.getId());
		
		int bookId = Integer.parseInt(req.getParameter("id"));
		book.setId(bookId);
		book.setAuthor(response.getBody());
		book.setDate("" + java.time.LocalDate.now());

		System.out.println(">> " + book);

		if(bookId == 0)
			restTemplate.postForEntity(url + "book", book, Book.class);
		else
			restTemplate.postForEntity(url + "updateBook", book, Book.class);
		String path = req.getContextPath();

		return path + "/book";

	}


	@RequestMapping("/showFormForUpdate/{id}")
	public String showForUpdate(HttpServletRequest req, @PathVariable int id, Model model) {
		
		ResponseEntity<Book> bookResponse = restTemplate.getForEntity(url + "book/" + id , Book.class);
		
		Book book = bookResponse.getBody();
		
		ResponseEntity<Author[]> response = restTemplate.getForEntity(url + "author", Author[].class);

		Author[] author = response.getBody();

		HashMap<Integer, String> hm = new HashMap<Integer, String>();

		for (Author auth : author)
			hm.put(auth.getId(), auth.getName());

		model.addAttribute("authorMap", hm);
		
		model.addAttribute("book", book);		
		
		model.addAttribute("id", id);
		
		String path = req.getContextPath();

		return path + "/book-form.jsp";
	}
	
	@RequestMapping("/formForNewBook")
	public String bookForm(HttpServletRequest req, Model model) {

		ResponseEntity<Author[]> response = restTemplate.getForEntity(url + "author", Author[].class);

		Author[] author = response.getBody();

		HashMap<Integer, String> hm = new HashMap<Integer, String>();

		for (Author auth : author)
			hm.put(auth.getId(), auth.getName());

		model.addAttribute("authorMap", hm);
		model.addAttribute("book", new Book());
		model.addAttribute("id", 0);

		String path = req.getContextPath();

		return path + "/book-form.jsp";

	}

	@RequestMapping("/delete/{id}")
	public String deleteBook(HttpServletRequest req, Model model, @PathVariable int id) {

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url + "deleteBook/" + id, String.class);

		String string = responseEntity.getBody();
		System.out.println(">> " + string);

		ResponseEntity<Book[]> response = restTemplate.getForEntity(url + "book", Book[].class);

		Book[] books = response.getBody();

		List<Book> list = new ArrayList<Book>();

		for (Book book : books)
			list.add(book);

		model.addAttribute("books", list);

		String path = req.getContextPath();

		return path + "/book-list.jsp";
	}
	
	
	
}
