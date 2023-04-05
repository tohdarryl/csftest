import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Comment } from '../model/comment';
import { Review } from '../model/review';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private http: HttpClient) { }

   // View 1: Show all movie review details
   getMovieReview(title: string): Promise<any> {
    const params = new HttpParams()
        .set("query", title);
    

    // For this particular case, if you dont set then it will be blank
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    return lastValueFrom(
      // this.http.get<Review[]>('api/search', {params: params})
      this.http.get<Review[]>('api/search', {params: params, headers: headers})
    );
  }

  // View 1: Get comments for individual movie
  getMovieComments(title: string): Promise<Comment[]>{
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    console.log("get all comments !");
    return lastValueFrom(this.http
        .get<Comment[]>('api/characters'+'/comments/' + title, {headers: headers}));
  
  }

  // View 2: To insert comments for individual movie
  saveComment(c:Comment) : Promise<any>{
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const body=JSON.stringify(c);
    console.log("save comment !");
    return lastValueFrom(this.http.post<Comment>('api/search'+"/" + c.title, body, {headers: headers}));
  }
}
