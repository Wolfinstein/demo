import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ClassService} from "../../../../services/class.service";

@Component({
  selector: 'topic-edit-component',
  templateUrl: './change.topic.popup.component.html',
  styleUrls: ['./change.topic.popup.component.scss']
})


export class ChangeTopicPopupComponent {

  constructor(private classService: ClassService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<ChangeTopicPopupComponent>) {
  }

  onEdit(): void {
    this.classService.editTopic(this.data.lessonId, this.data.topic).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }


}
