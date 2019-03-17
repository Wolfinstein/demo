import {RouterModule, Routes} from "@angular/router";
import {PageNotFoundComponent} from "../pages/404/page-not-found.component";
import {PageAccessDeniedComponent} from "../pages/401/page-access-denied.component";
import {NgModule} from "@angular/core";

export const routes: Routes = [
  {path: '', redirectTo: '/', pathMatch: 'full'},

  // {
  //   path: 'logout',
  //   canActivate: [AuthGuard],
  //   component: LogoutComponent,
  //   data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1}]
  // },

  {
    path: '**',
    redirectTo: '404',
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1}]
  },

  {
    path: '404',
    component: PageNotFoundComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1}]
  },

  {
    path: '401',
    component: PageAccessDeniedComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1}]
  },


];

@NgModule({
  exports: [RouterModule],
  declarations: [PageNotFoundComponent, PageAccessDeniedComponent],
  imports: [RouterModule.forRoot(routes, {useHash: false})],

})
export class AppRoutingModule {
}

