import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Comment } from '../model/comment';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit, OnDestroy{

  form!:FormGroup
  queryParams$! : Subscription
  movieParam!: any
  title = ''
  query = ''

  constructor(private activatedRoute: ActivatedRoute, private fb: FormBuilder,
    private movieSvc: MovieService,  private router: Router){}

ngOnInit(): void {
console.log("post-comment component")

this.form = this.fb.group({
name : this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
rating: this.fb.control<number>(1, [Validators.required, Validators.min(1), Validators.max(5)]),
comment: this.fb.control<string>('', Validators.required)
})

 // subscribe to queryParams from previous moviereviewlist.component
 this.queryParams$ = this.activatedRoute.queryParams.subscribe(
  (queryParams) => {
    console.log(queryParams)
    this.movieParam = queryParams['movieParam'].split('|')
    console.log(this.movieParam[0]);
    console.log(this.movieParam[1]);
    this.title = this.movieParam[0];
    this.query = this.movieParam[1];
  }
)
}

ngOnDestroy(): void {
this.queryParams$.unsubscribe()
}
// save comment to MongoDB
saveComment(){
const nameFormVal = this.form?.value['name'];
const ratingFormVal = this.form?.value['rating'];
const commentFormVal = this.form?.value['comment'];
// instantiate a new Comment interface
const c = {} as Comment;
c.title = this.title;
c.name = nameFormVal;
c.rating = ratingFormVal;
c.comment = commentFormVal;

console.log(">>> Comment to save :", c)


this.movieSvc.saveComment(c);
this.router.navigate(['/list', this.query]);
}

cancel(){
this.router.navigate(['/list', this.query]);
}
}
