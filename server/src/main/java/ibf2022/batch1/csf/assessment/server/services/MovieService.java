package ibf2022.batch1.csf.assessment.server.services;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue.ValueType;;

@Service
public class MovieService {

	@Value("${movies.api.key}")
    private String MOVIES_API_KEY;

	public static final String MOVIES_API="https://api.nytimes.com/svc/movies/v2/reviews/search.json";
	
	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {

		String url = UriComponentsBuilder.fromUriString(MOVIES_API)
                .queryParam("query", query)
                .queryParam("api-key", MOVIES_API_KEY)
                .toUriString();
            
        RequestEntity<Void> req = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        // resttemplate to get info from api
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

		try {
            // exchange: sends request to api, in return for response
            resp =  template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
			// return empty list if any errors
            return Collections.emptyList();
        }

        String payload = resp.getBody();
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject movieResp = reader.readObject();
		JsonArray jsonArr = movieResp.getJsonArray("results");
        
		// For any values = null
        if(jsonArr.getValueType() == ValueType.NULL){
			return Collections.emptyList();
		}else{
			
			return jsonArr.stream()
                .map(v -> v.asJsonObject())
                .map(Review::toReview)
                .toList();
		}

        
        
		
    }



  
	

}
