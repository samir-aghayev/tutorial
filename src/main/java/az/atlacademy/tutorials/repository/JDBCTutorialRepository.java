package az.atlacademy.tutorials.repository;

import az.atlacademy.tutorials.models.Tutorial;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCTutorialRepository implements TutorialRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCTutorialRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Tutorial tutorial) {
        return jdbcTemplate.update("INSERT INTO tutorial(TITLE, DESCRIPTION, PUBLISHED) VALUES (?,?,?)",
                tutorial.getTitle(), tutorial.getDescription(), tutorial.getPublished());
    }

    @Override
    public int update(Tutorial tutorial, long id) {
        return jdbcTemplate.update("UPDATE tutorial set title=?, description=?, published=? where id=?",
                tutorial.getTitle(), tutorial.getDescription(), tutorial.getPublished(), id);
    }

    @Override
    public Tutorial findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from tutorial where id=?",new BeanPropertyRowMapper<>(Tutorial.class) ,id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from tutorial where id=?", id);
    }

    @Override
    public List<Tutorial> findAll() {
        return jdbcTemplate.query("select * from tutorial", new BeanPropertyRowMapper(Tutorial.class));
    }

    @Override
    public List<Tutorial> findByPublished(boolean published) {
        return jdbcTemplate.queryForList("select * from tutorial where published=?", Tutorial.class, published);
    }

    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        return jdbcTemplate.queryForList("select * from tutorial where title like '%'+title+'%'", Tutorial.class);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE FROM tutorial");
    }
}
