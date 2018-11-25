import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../category.service";
import {Category} from "../category";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  search: string;
  categories: Category[];

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.getCategories();
  }

  getCategories() {
    this.categoryService.getAll()
      .subscribe(categories => this.categories = categories);
  }

  searchCategory() {
    this.categoryService.getAllByDescription(this.search).subscribe(categories => this.categories = categories);
  }
}
