import {Component, OnInit} from '@angular/core';
import {Category} from "../category";
import {CategoryService} from "../category.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-category-editor',
  templateUrl: './category-editor.component.html',
  styleUrls: ['./category-editor.component.css']
})
export class CategoryEditorComponent implements OnInit {
  category: Category;

  constructor(private route: ActivatedRoute, private router: Router, private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.getCategory();
  }

  getCategory(): void {
    if (+this.route.snapshot.paramMap.has("id")) {
      this.categoryService.get(+this.route.snapshot.paramMap.get("id"))
        .subscribe(category => this.category = category);
    }
    else {
      this.category = new Category(null, null, null);
    }
  }

  saveCategory(): void {
    this.categoryService.save(this.category);
    this.router.navigate([`/categories`], {relativeTo: this.route});
  }
}
