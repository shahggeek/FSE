package com.fse.mvc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FSEController {

	@Resource(name = "jpaRepository")
	private JPARepository jpaRepository;
	

	private static List<Book> references = new ArrayList<Book>();

	static {
		references.add(new Book(" ", 0, 0, new Date()));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome(Model model) {
		System.out.println("Entering default method");
		setDefault(model);
		System.out.println("Returning home");
		return "home";
	}

	private void setDefault(Model model) {
		Book book = new Book();
		model.addAttribute(book);
		Subject subject = new Subject();
		subject.setReferences(references);
		model.addAttribute(subject);
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") Book book, Model model) {
		System.out.println(book.toString());
		jpaRepository.addBook(book, book.getBookId());
		setDefault(model);
		return "home";
	}

	@RequestMapping(value = "/addSubject", method = RequestMethod.POST)
	public String addSubject(@ModelAttribute("subject") Subject subject, Model model) {
		System.out.println(subject.toString());
		jpaRepository.addSubject(subject);
		setDefault(model);
		return "home";
	}

	@RequestMapping(value = "/DeleteSubject", method = RequestMethod.POST)
	public String deleteSubject(@ModelAttribute("subject") Subject subject, Model model) throws SQLException {
		System.out.println(subject.getSubjectId());
		jpaRepository.deleteSubject(jpaRepository.findSubject(subject.getSubjectId()));
		setDefault(model);
		return "home";
	}

	@RequestMapping(value = "/DeleteBook", method = RequestMethod.POST)
	public String deleteBook(@ModelAttribute("book") Book book, Model model) throws SQLException {
		System.out.println(book.getBookId());
		jpaRepository.deleteBook(jpaRepository.findBook(book.getBookId()));
		setDefault(model);
		return "home";
	}

	@RequestMapping(value = "/SearchBook", method = RequestMethod.POST)
	public String searchBook(@ModelAttribute("book") Book book, Model model) throws SQLException {
		System.out.println(book.getTitle());
		Book searchBook = jpaRepository.searchBook(book.getTitle());
		System.out.println("Book Found "+searchBook.toString());
		model.addAttribute("searchBook",searchBook);
		Subject subject = new Subject();
		subject.setReferences(references);
		model.addAttribute(subject);
		return "home";
	}

	@RequestMapping(value = "/SearchSubject", method = RequestMethod.POST)
	public String searchSubject(@ModelAttribute("subject") Subject subject, Model model) throws SQLException {
		System.out.println(subject.getSubtitle());
		Subject searchSubject = jpaRepository.searchSubject(subject.getSubtitle());
		Book book = new Book();
		model.addAttribute(book);
		model.addAttribute("searchSubject",searchSubject);
		return "home";
	}

	@ModelAttribute("allSubjects")
	public List<Subject> populateSubjects() {
		List<Subject> allSubjects = null;
		try {
			allSubjects = jpaRepository.getAllSubjects();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allSubjects;

	}

	@ModelAttribute("allBooks")
	public List<Book> populateBooks() {
		List<Subject> allSubjects = null;
		List<Book> allBooks = new ArrayList<Book>();
		try {
			allSubjects = jpaRepository.getAllSubjects();
			for (Subject subject : allSubjects) {
				List<Book> references = subject.getReferences();
				for (Book book : references) {
					allBooks.add(book);
				}
			}
			System.out.println("allBooks" + allSubjects.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allBooks;

	}

}