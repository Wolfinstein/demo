import {RouterModule, Routes} from "@angular/router";
import {PageNotFoundComponent} from "../pages/404/page-not-found.component";
import {PageAccessDeniedComponent} from "../pages/401/page-access-denied.component";
import {NgModule} from "@angular/core";
import {AuthGuard} from "../services/auth_guard.service";
import {LogoutComponent} from "../pages/logout/logout.component";
import {DashboardComponent} from "../pages/dashboard/dashboard.component";
import {HomeComponent} from "../pages/home/home.component";
import {UserAccountComponent} from "../pages/user/user.component";
import {TeacherComponent} from "../pages/teacher/teacher.component";
import {LessonComponent} from "../pages/teacher/teacher-lesson/lesson.component";
import {PresencesComponent} from "../pages/teacher/teacher-lesson/presences/presences.component";
import {NotificationsComponent} from "../pages/notifications/notifications.component";
import {ParentComponent} from "../pages/parent/parent.component";
import {StudentComponent} from "../pages/student/student.component";
import {AdminComponent} from "../pages/admin/admin.component";
import {ScheduleComponent} from "../pages/schedule/schedule.component";

export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},

  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full',
        data: [{selectedHeaderItemIndex: 1, selectedSubNavItemIndex: -1},{expectedRole: 'ALL'}]
      },
    ]
  },

  {
    path: 'account/:name',
    canActivate: [AuthGuard],
    component: UserAccountComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'ALL'}]
  },

  {
    path: 'dashboard',
    canActivate: [AuthGuard],
    component: DashboardComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'ALL'}]
  },

  {
    path: 'notifications/:id',
    canActivate: [AuthGuard],
    component: NotificationsComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1}, {expectedRole: 'ALL'}]
  },

  {
    path: 'schedule/:id',
    canActivate: [AuthGuard],
    component: ScheduleComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1}, {expectedRole: 'ADMIN'}]
  },

  {
    path: 'teacher',
    canActivate: [AuthGuard],
    component: TeacherComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1}, {expectedRole: 'TEACHER'}]
  },

  {
    path: 'admin',
    canActivate: [AuthGuard],
    component: AdminComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1}, {expectedRole: 'ADMIN'}]
  },

  {
    path: 'lesson/:id',
    canActivate: [AuthGuard],
    component: LessonComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'TEACHER'}]
  },

  {
    path: 'parent/:id',
    canActivate: [AuthGuard],
    component: ParentComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'ALL'}]
  },

  {
    path: 'student/:id',
    canActivate: [AuthGuard],
    component: StudentComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'STUDENT'}]
  },

  {
    path: 'presences/:id',
    canActivate: [AuthGuard],
    component: PresencesComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'TEACHER'}]
  },

  {
    path: 'logout',
    canActivate: [AuthGuard],
    component: LogoutComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'ALL'}]
  },

  {
    path: '**',
    redirectTo: '404',
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'ALL'}]
  },

  {
    path: '404',
    component: PageNotFoundComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'ALL'}]
  },

  {
    path: '401',
    component: PageAccessDeniedComponent,
    data: [{selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1},{expectedRole: 'ALL'}]
  },


];

@NgModule({
  exports: [RouterModule],
  declarations: [PageNotFoundComponent, PageAccessDeniedComponent],
  imports: [RouterModule.forRoot(routes, {useHash: false})],

})
export class AppRoutingModule {
}

