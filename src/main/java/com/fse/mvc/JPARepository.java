package com.fse.mvc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value="jpaRepository")
@Transactional
public class JPARepository  {

	public JPARepository() {
	}
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private DataSource dataSource;


	
	public Subject findSubject(long subjectId) {
		return subjectRepository.findSubjectBySubjectId(subjectId);
	}
	
	public Book findBook(long bookId) {
		return bookRepository.findBookByBookId(bookId);
	}

	public long addBook(Book book, long subjectId) {
		try {
			Subject subject = findSubject(subjectId);
			book.setSubject(subject);
			bookRepository.save(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long addSubject(Subject subject) {
		try {
			subject.getReferences().get(0).setSubject(subject);
			subjectRepository.save(subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Subject searchSubject(String subtitle) throws SQLException{
		return subjectRepository.findSubjectBySubtitle(subtitle);
	}
	
	public Book searchBook(String title) throws SQLException{
		return bookRepository.findBookByTitle(title);
	}
	
	public Subject deleteSubject(Subject subject) throws SQLException {
		try {
			subjectRepository.delete(subject);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public Book deleteBook(Book book) throws SQLException {
		try {
			bookRepository.delete(book);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	public List<Subject> getAllSubjects() throws SQLException{
		List<Subject> allSubjects = new ArrayList<Subject>();
		try {
			Iterable<Subject> subjects = subjectRepository.findAll();
			Iterator<Subject> iterator = subjects.iterator();
			while(iterator.hasNext()){
				allSubjects.add(iterator.next());
			}
		} catch (Exception e) {
	           e.printStackTrace();
	           return null;
	     }
		return allSubjects;
	}
		
}
