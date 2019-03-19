import {NgModule} from "@angular/core";
import {AppComponent} from "../app.component";
import {BrowserModule} from "@angular/platform-browser";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatCheckboxModule} from "@angular/material";
import {MaterialModule} from "./material.module";
import {AuthGuard} from "../services/auth_guard.service";
import {UserInfoService} from "../services/user-info.service";
import {LoginService} from "../services/login.service";
import {ApiRequestService} from "../services/api-request.service";
import {AppConfig} from "../config/app-config";
import {platformBrowserDynamic} from "@angular/platform-browser-dynamic";
import {RouterModule} from "@angular/router";
import {CommonModule} from "@angular/common";
import {AppRoutingModule, routes} from "../config/app-routing.module";
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CdkTableModule} from "@angular/cdk/table";
import {OverlayModule} from "@angular/cdk/overlay";
import {FlexLayoutModule} from "@angular/flex-layout";
import {UserService} from "../services/user.service";
import {ChartsModule} from "ng2-charts";
import {NgxPaginationModule} from "ngx-pagination";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {MultiselectDropdownModule} from "angular-2-dropdown-multiselect";
import {CountdownModule} from "ngx-countdown";
import {LoginComponent} from "../pages/login/login.component";
import {LogoutComponent} from "../pages/logout/logout.component";
import {HomeComponent} from "../pages/home/home.component";
import {DashboardComponent} from "../pages/dashboard/dashboard.component";
import {UserAccountComponent} from "../pages/user/user.component";
import {UpdateTeacherComponent} from "../pages/user/update-teacher/update.teacher.component";
import {TeacherComponent} from "../pages/teacher/teacher.component";
import {ClassService} from "../services/class.service";
import {LessonComponent} from "../pages/teacher/teacher-lesson/lesson.component";
import {GradePopupComponent} from "../pages/teacher/teacher-lesson/grade-popup/grade.popup.component";
import {NewGradePopupComponent} from "../pages/teacher/teacher-lesson/new-grade-popup/new.grade.popup.component";
import {ChangeTopicPopupComponent} from "../pages/teacher/teacher-lesson/change-topic/change.topic.popup.component";
import {PresencesComponent} from "../pages/teacher/teacher-lesson/presences/presences.component";
import {ChangePresenceComponent} from "../pages/teacher/teacher-lesson/presences/change-presence/change.presence.component";
import {SendNotificationPopupComponent} from "../pages/teacher/teacher-lesson/send-notification/send.notification.popup.component";
import {NotificationsComponent} from "../pages/notifications/notifications.component";
import {ParentComponent} from "../pages/parent/parent.component";
import {StudentComponent} from "../pages/student/student.component";
import {AdminComponent} from "../pages/admin/admin.component";
import {EditClassComponent} from "../pages/admin/edit-class/edit.class.component";
import {EditKidsComponent} from "../pages/admin/edit-kids/edit.kids.component";
import {EditUserComponent} from "../pages/admin/edit-user/edit.user.component";
import {AddUserComponent} from "../pages/admin/add-user/add.user.component";
import {ScheduleComponent} from "../pages/schedule/schedule.component";
import {AddSubjectComponent} from "../pages/admin/add-subject/add.subject.component";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    HomeComponent,
    DashboardComponent,
    UserAccountComponent,
    UpdateTeacherComponent,
    TeacherComponent,
    LessonComponent,
    GradePopupComponent,
    NewGradePopupComponent,
    ChangeTopicPopupComponent,
    PresencesComponent,
    ChangePresenceComponent,
    SendNotificationPopupComponent,
    NotificationsComponent,
    ParentComponent,
    StudentComponent,
    AdminComponent,
    EditClassComponent,
    EditKidsComponent,
    EditUserComponent,
    AddUserComponent,
    ScheduleComponent,
    AddSubjectComponent
  ],
  entryComponents: [AddSubjectComponent, GradePopupComponent, LoginComponent, UpdateTeacherComponent, NewGradePopupComponent, ChangeTopicPopupComponent, ChangePresenceComponent, SendNotificationPopupComponent, EditClassComponent, EditKidsComponent, EditUserComponent, AddUserComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    AppRoutingModule,
    CommonModule,
    RouterModule.forRoot(routes),
    NgbModule,
    NoopAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MaterialModule,
    ReactiveFormsModule,
    CdkTableModule,
    OverlayModule,
    FlexLayoutModule,
    ChartsModule,
    NgxDatatableModule,
    NgxPaginationModule,
    MultiselectDropdownModule,
    CountdownModule
  ],
  providers: [
    AuthGuard,
    UserInfoService,
    ApiRequestService,
    LoginService,
    AppConfig,
    UserService,
    ClassService
  ],
  bootstrap: [
    AppComponent
  ]

})
export class AppModule {
}

platformBrowserDynamic().bootstrapModule(AppModule);
