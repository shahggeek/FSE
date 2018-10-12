package com.fse.mvc;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "BOOK",uniqueConstraints = { @UniqueConstraint(columnNames = { "bookid" }) })
public class Book implements Serializable, Comparable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookid", updatable = false, nullable = false)
	private long bookId;
	
	@Column(name = "title", length = 100, nullable = false)
	private String title;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "volume", nullable = false)
	private int volume;
	
	@Column(name = "publishdate", nullable = false)
	private Date publishDate;
	
	@ManyToOne
	@JoinColumn(name = "subjectid", nullable = false)
	private Subject subject;
	
	
	public Book() {
	}

	public Book(String title, double price, int volume, Date publishDate, Subject subject) {
		super();
		//this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.volume = volume;
		this.publishDate = publishDate;
		this.subject = subject;
	}

	public Book(String title, double price, int volume, Date publishDate) {
		super();
		//this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.volume = volume;
		this.publishDate = publishDate;
	}
	
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bookId ^ (bookId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId != other.bookId)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Object o) {
		return Long.compare(this.bookId, ((Book)o).getBookId());
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", price=" + price + ", volume=" + volume
				+ ", publishDate=" + publishDate + "]";
	}
	
	
}
