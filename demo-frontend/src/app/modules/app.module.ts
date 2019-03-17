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


@NgModule({
  declarations: [
    AppComponent
  ],
  entryComponents: [],
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
  ],
  bootstrap: [
    AppComponent
  ]

})
export class AppModule {
}

platformBrowserDynamic().bootstrapModule(AppModule);
