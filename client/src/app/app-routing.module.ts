import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieReviewListComponent } from './components/movie-review-list.component';
import { PostCommentComponent } from './components/post-comment.component';
import { SearchReviewComponent } from './components/search-review.component';

const routes: Routes = [
  {path: "", component:SearchReviewComponent},
  {path: "list/:title", component:MovieReviewListComponent},
  {path: "addcomment/:title", component:PostCommentComponent},
  {path: "", redirectTo: "/", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
