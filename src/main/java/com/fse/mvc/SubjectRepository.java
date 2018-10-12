package com.fse.mvc;

import java.io.Serializable;

public interface SubjectRepository extends CrudRepository<Subject, Serializable>{

	Subject findSubjectBySubtitle(String subtitle);
	Subject findSubjectBySubjectId(long subjectId);
}
