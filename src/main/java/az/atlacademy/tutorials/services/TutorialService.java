package az.atlacademy.tutorials.services;

import az.atlacademy.tutorials.models.Tutorial;
import az.atlacademy.tutorials.repository.JDBCTutorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {
    private final JDBCTutorialRepository repository;

    public TutorialService(JDBCTutorialRepository repository) {
        this.repository = repository;
    }

    public int save(Tutorial tutorial) {
        return repository.save(tutorial);
    }

    public int update(Tutorial tutorial, long id) {
        return repository.update(tutorial, id);
    }

    public Tutorial findById(Long id) {
        return repository.findById(id);
    }

    public int deleteById(Long id) {
        return repository.deleteById(id);
    }

    public List<Tutorial> findAll() {
        return repository.findAll();
    }

    public List<Tutorial> findByPublished(boolean published) {
        return repository.findByPublished(published);
    }

    public List<Tutorial> findByTitleContaining(String title) {
        return repository.findByTitleContaining(title);
    }

    public int deleteAll() {
        return repository.deleteAll();
    }
}