import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsernameValidator } from '../username.validator';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit{

form!:FormGroup


constructor(private fb: FormBuilder, private router : Router){}

ngOnInit(): void {
  this.form = this.fb.group({
    title: this.fb.control<string>('', [Validators.required, Validators.minLength(2), UsernameValidator.cannotContainSpace])
  })
}

search(){
  // to take value from formControlName="title"
  const t = this.form?.value['title']
  console.log(">>> title", t)
  // same as in app-routing.module.ts to route to movie-review-list component
  this.router.navigate(['/list', t])
}



}
