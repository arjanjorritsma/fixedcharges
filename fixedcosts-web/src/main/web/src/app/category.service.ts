import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Rx";
import {Category} from "./category";
import {of} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor() {
  }

  get(id: number): Observable<Category> {
    return of(new Category(0, "test", 0));
  }

  save(category: Category): void {
    console.log("save", category);
  }
}
