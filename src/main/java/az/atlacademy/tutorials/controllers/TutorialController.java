package az.atlacademy.tutorials.controllers;

import az.atlacademy.tutorials.models.Tutorial;
import az.atlacademy.tutorials.services.TutorialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {

    private final TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @PostMapping("/tutorials")
    public ResponseEntity<String> createTutorial(@RequestBody Tutorial tutorial) {
        tutorialService.save(tutorial);
        return new ResponseEntity<>("Tutorial created", HttpStatus.CREATED);
    }


    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        return new ResponseEntity<>(tutorialService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Tutorial tutorial = tutorialService.findById(id);
        if (tutorial != null) {
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<String> updateTutorialById(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        try {
            tutorialService.update(tutorial, id);
            return new ResponseEntity<>("Tutorial was updated successfully deleted", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Cannot find Tutorial with id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("tutorials/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable("id") long id) {
        try {
            int i = tutorialService.deleteById(id);

            if (i == 0) {
                return new ResponseEntity<>("Cannot find Tutorial with id " + id, HttpStatus.NOT_FOUND);
            } else return new ResponseEntity<>("Tutorial was successfully.", HttpStatus.OK);
        } catch (HttpServerErrorException.InternalServerError e) {
            return new ResponseEntity<>("Cannot delete Tutorial", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<String> deleteAllTutorials() {
        try {
            int i = tutorialService.deleteAll();
            return new ResponseEntity<>("Deleted " + i + " Tutorial was successfully ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete tutorials", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished(@RequestParam(value = "published") boolean published) {
        try {
            List<Tutorial> byPublished = tutorialService.findByPublished(published);
            if (byPublished.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(byPublished, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("tutorials/{title}")
    public ResponseEntity<List<Tutorial>> findByTitleContaining(@PathVariable("title") String title) {
        try {
            List<Tutorial> byTitleContaining = tutorialService.findByTitleContaining(title);
            if (byTitleContaining.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(byTitleContaining, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
