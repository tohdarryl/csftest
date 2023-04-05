package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@Controller
@CrossOrigin(origins="*")
@RequestMapping(path="/api/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	// private Logger logger = LoggerFactory.getLogger(MovieController.class);
	// TODO: Task 3, Task 4, Task 8

	@Autowired
	MovieService mService;

	@Autowired
	MovieRepository mRepo;

	 // View 1: Display all reviews (Task 3 and 4)
	 @GetMapping
	 @ResponseBody
	 public ResponseEntity<String> getMovies(
			 @RequestParam(required = true) String query) {
		 JsonArray result = null;
		 // Get list of reviews using API
		 List<Review> listOfR = this.mService.searchReviews(query);
		 System.out.println(listOfR);
		
 
		 // Create JsonArray for ResponseBody
		 JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		 for (Review r : listOfR){
			// set comment count for all Reviews
		 	 r.setCommentCount(this.mRepo.countComments(r.getTitle()));
			 arrBuilder.add(r.toJSON());
			}
		 result = arrBuilder.build();
		 

		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .contentType(MediaType.APPLICATION_JSON)
				 .body(result.toString());
	 }



	 // View 2: Post Comments
	@PostMapping(path="/{title}")
    public ResponseEntity<String> saveMovieComment(@RequestBody Comment c, @PathVariable(required = true) String title){
        // logger.info("save comment > : " + title);
        Comment comment = new Comment();
        comment.setTitle(title);
		comment.setName(c.getName());
		comment.setRating(c.getRating());
        comment.setComment(c.getComment());
        

        Comment result = this.mRepo.insertComment(comment);
		System.out.println("Successfully saved: " + result.toString());

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toJSON().toString());
    }

}
