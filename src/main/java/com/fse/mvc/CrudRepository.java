package com.fse.mvc;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> {

	<S extends T> S save(S entity);

	Iterable<T> findAll();

	void delete(ID id);
}
