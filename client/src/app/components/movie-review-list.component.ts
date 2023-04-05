import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Review } from '../model/review';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-movie-review-list',
  templateUrl: './movie-review-list.component.html',
  styleUrls: ['./movie-review-list.component.css']
})
export class MovieReviewListComponent implements OnInit, OnDestroy{

  title = "" 
  query = ""
  param$! : Subscription
  reviews! : Review[]


  constructor(private activatedRoute: ActivatedRoute, private movieSvc: MovieService,
    private router: Router){}

  
  ngOnInit(): void {
    console.log("movie-review-list component")
    // to get value from /list/<charName> from search.component.ts
    this.param$ = this.activatedRoute.params.subscribe(
      async (params) => {
        // app-routing.module.ts;  path: "list/:title", component: MovieReviewComponent
        this.query = params['title']
        console.log(">>> query", this.query)
        const l = await this.movieSvc.getMovieReview(this.query)
      
        console.log(l)

        if(l == undefined || l.length == 0){
          // route back to search.component if result is undefined or empty
          this.router.navigate(['/'])
        } else {
          // set as result as Review[] if ok
          this.reviews = l

      
        }
      }
    )
  }

  ngOnDestroy(): void {
    this.param$.unsubscribe()
  }

  back(){
    console.log("Return to View 0")
    this.router.navigate(['/'])
  }

  addComment(title: string){
  const t = title
  const queryParams: Params = { movieParam: title + '|' + this.query };
  console.log(">>> movieParam", queryParams)
  this.router.navigate(['/addcomment', t], {queryParams : queryParams})
  }
}
