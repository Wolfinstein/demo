import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ClassService} from "../../../../../services/class.service";

@Component({
  selector: 'presence-edit-component',
  templateUrl: './change.presence.component.html',
  styleUrls: ['./change.presence.component.scss']
})


export class ChangePresenceComponent {

  constructor(private classService: ClassService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<ChangePresenceComponent>) {
  }

  onEdit(): void {
    this.classService.editPresence(this.data.presenceId, this.data.presenceType).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }

}
