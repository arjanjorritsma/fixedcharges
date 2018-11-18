import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RouterModule, Routes} from '@angular/router';
import {CategoriesComponent} from "./categories/categories.component";
import {TodayComponent} from "./today/today.component";
import {CostsComponent} from "./costs/costs.component";
import {CategoryEditorComponent} from "./category-editor/category-editor.component";

const routes: Routes = [{
  path: 'today',
  component: TodayComponent
},
  {
    path: 'costs',
    component: CostsComponent
  },
  {
    path: 'categories',
    component: CategoriesComponent
  },
  {
    path: 'categories/create',
    component: CategoryEditorComponent
  },
  {
    path: 'categories/update/:id',
    component: CategoryEditorComponent
  },
  {
    path: '',
    redirectTo: '/today',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/today',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}


