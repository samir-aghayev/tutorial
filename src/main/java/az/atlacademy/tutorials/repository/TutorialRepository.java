package az.atlacademy.tutorials.repository;

import az.atlacademy.tutorials.models.Tutorial;

import java.util.List;

public interface TutorialRepository {
    int save(Tutorial tutorial);

    int update(Tutorial tutorial, long id);

    Tutorial findById(Long id);

    int deleteById(Long id);

    List<Tutorial> findAll();

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

    int deleteAll();

}
