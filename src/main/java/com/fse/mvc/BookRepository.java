package com.fse.mvc;

import java.io.Serializable;

public interface BookRepository extends CrudRepository<Book, Serializable>{

	Book findBookByTitle(String title);
	Book findBookByBookId(long bookId);
}
