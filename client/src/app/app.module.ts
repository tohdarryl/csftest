import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http'
import { SearchReviewComponent } from './components/search-review.component';
import { MovieReviewListComponent } from './components/movie-review-list.component';
import { PostCommentComponent } from './components/post-comment.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchReviewComponent,
    MovieReviewListComponent,
    PostCommentComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule

    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
