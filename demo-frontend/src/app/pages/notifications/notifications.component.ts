import {Component, ViewChild} from "@angular/core";
import {DatatableComponent} from "@swimlane/ngx-datatable";
import {ClassService} from "../../services/class.service";
import {UserInfoService} from "../../services/user-info.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'notification-component',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss'],
})


export class NotificationsComponent {

  notificationsList: any[];
  @ViewChild(DatatableComponent) private notificationsTable: DatatableComponent;

  constructor(private userInfo: UserInfoService,
              private classService: ClassService,
              private route: ActivatedRoute) {
    this.getNotifications();
  }

  getNotifications() {
    this.classService.getNotifications(this.route.snapshot.params['id']).subscribe(resp => {
      this.notificationsList = [...resp];
    })
  }

  changeStatus(id: number) {
    this.classService.changeStatus(id).subscribe(resp => {
      this.getNotifications();
    })
  }

}
