import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ClassService} from "../../../services/class.service";
import {ClassEditModel} from "../../../models/class.edit.model";

@Component({
  selector: 'class-edit-component',
  templateUrl: './edit.class.component.html',
  styleUrls: ['./edit.class.component.scss']
})


export class EditClassComponent {
  teachers: any[];

  constructor(private classService: ClassService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<EditClassComponent>) {
    this.classService.getTeachers().subscribe(resp => {
      this.teachers = resp;
    })
  }

  onEdit(): void {
    let model = new ClassEditModel(this.data.model.preceptorId, this.data.model.year, this.data.model.sign);
    this.classService.editClass(this.data.model.id, model).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }

}
