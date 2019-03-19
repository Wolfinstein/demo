import {Component} from "@angular/core";
import {ClassService} from "../../services/class.service";
import {MatDialog} from "@angular/material";
import {EditClassComponent} from "./edit-class/edit.class.component";

@Component({
  selector: 'admin-component',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
})

export class AdminComponent {

  classesList: any[];

  constructor(private dialog: MatDialog,private classService: ClassService) {
    this.getClasses();
  }

  getClasses() {
    this.classService.getClasses().subscribe(resp => {
      console.log(resp);
      this.classesList = [...resp]
    })
  };

  deleteClass(id : number){
    this.classService.deleteClass(id).subscribe(resp =>{
      this.getClasses();
    })
  }

  editClassPopup(data : any) {

    let dialogRef = this.dialog.open(EditClassComponent, {
      panelClass: 'class-edit-component',
      data: {model: data}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getClasses();
    });
  }

  addClass(){
    this.classService.createClass().subscribe(resp =>{
      this.getClasses();
    })
  }

}
