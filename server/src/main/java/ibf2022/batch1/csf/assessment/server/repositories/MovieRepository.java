package ibf2022.batch1.csf.assessment.server.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;

@Repository
public class MovieRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	// db.comments.find({ title : "Emily" });


	public int countComments(String title) {

		Criteria c = Criteria.where("title").is(title);

        Query q = Query.query(c);

        List<Comment> listOfC = mongoTemplate.find(q, Document.class, "comments")
		.stream()
		.map(a -> Comment.create(a))
		.toList();;

		return listOfC.size();
	}


	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below

	/*
	 db.comments.insert({
   	title: "Emily",
    name: "Darryl",
    rating: 2,
    comment: "ok"
	})
	 */


	public Comment insertComment(Comment c){
        Document doc = c.toDocument();
        // Collection name = "comments"
        mongoTemplate.insert(doc, "comments");
        return c;
    }
}
