import {Component, OnInit} from '@angular/core';
import {Category} from "../category";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  selectedCategory: Category;
  categories: Category[] = [{
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

  ngOnInit() {
  }

  onSelect(category: Category) {
    this.selectedCategory = category;
  }
}
