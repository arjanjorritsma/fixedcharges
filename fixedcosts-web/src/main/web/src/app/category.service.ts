import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Rx";
import {Category} from "./category";
import {of} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private CATEGORIES: Category[] = [{
    "id": 1,
    "description": "Wegenbelasting Up",
    "dayOfDebit": 25
  }, {
    "id": 2,
    "description": "Wegenbelasting Volvo",
    "dayOfDebit": 25
  }, {
    "id": 3,
    "description": "Sparen kinderen",
    "dayOfDebit": 24
  }, {
    "id": 4,
    "description": "Sparen spaarrekening",
    "dayOfDebit": 24
  }
  ];
  constructor() {
  }

  get(id: number): Observable<Category> {
    return of(new Category(id, "test", 0));
  }

  save(category: Category): void {
    console.log("save", category);
  }

  getAll() : Observable<Category[]> {
    return of(this.CATEGORIES);
  }

  getAllByDescription(search: string) : Observable<Category[]>  {
    console.log("search", search);
    return of(this.CATEGORIES);

  }
}
