import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ClassService} from "../../../../services/class.service";

@Component({
  selector: 'send-notification-component',
  templateUrl: './send.notification.popup.component.html',
  styleUrls: ['./send.notification.popup.component.scss']
})


export class SendNotificationPopupComponent {

  constructor(private classService: ClassService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<SendNotificationPopupComponent>) {
  }

  onSend(): void {
    this.classService.sendNotification(this.data.studentId, this.data.message).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }
}
