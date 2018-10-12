package com.fse.mvc;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "SUBJECT",uniqueConstraints = { @UniqueConstraint(columnNames = { "subjectid" }) })
public class Subject implements Serializable, Comparable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subjectid", updatable = false)
	private long subjectId;
	
	@Column(name = "subtitle", length = 100, nullable = false)
	private String subtitle;
	
	@Column(name = "durationinhours", nullable = false)
	private int durationInHrs;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "subject")
	private List<Book> references;
	
	
	public Subject() {
	}

	public Subject(String subtitle, int durationInHrs) {
		super();
		//this.subjectId = subjectId;
		this.subtitle = subtitle;
		this.durationInHrs = durationInHrs;
	}

	public Subject(String subtitle, int durationInHrs, List<Book> references) {
		//this.subjectId = subjectId;
		this.subtitle = subtitle;
		this.durationInHrs = durationInHrs;
		this.references = references;
	}

	
	public long getSubjectId() {
		return subjectId;
	}

	
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	
	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	
	public int getDurationInHrs() {
		return durationInHrs;
	}

	public void setDurationInHrs(int durationInHrs) {
		this.durationInHrs = durationInHrs;
	}

	
	public List<Book> getReferences() {
		return references;
	}

	public void setReferences(List<Book> references) {
		this.references = references;
	}
	
	@Override
	public int compareTo(Object o) {
		return Long.compare(this.subjectId, ((Subject)o).getSubjectId());
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subtitle + ", durationInHrs=" + durationInHrs
				+ ", references=" + references + "]";
	}
	
	
}
