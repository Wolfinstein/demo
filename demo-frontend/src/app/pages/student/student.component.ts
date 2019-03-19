import {Component, ViewChild} from "@angular/core";
import {UserInfoService} from "../../services/user-info.service";
import {ClassService} from "../../services/class.service";
import {ActivatedRoute} from "@angular/router";
import {DatatableComponent} from "@swimlane/ngx-datatable";

@Component({
  selector: 'student-component',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss'],
})


export class StudentComponent {

  studentInfoList: any[];
  studentAbsencesList: any[];

  @ViewChild(DatatableComponent) private studentInfoTable: DatatableComponent;
  @ViewChild(DatatableComponent) private studentAbsencesTable: DatatableComponent;


  constructor(private userInfo: UserInfoService,
              private classService: ClassService,
              private route: ActivatedRoute) {

    this.classService.getStudentData(this.route.snapshot.params['id']).subscribe(resp => {
      this.studentInfoList = resp;
    });

    this.classService.getStudentAbsences(this.route.snapshot.params['id']).subscribe(resp => {
      this.studentAbsencesList = resp;
    })
  }

  getStats() {
    this.classService.getStats(this.route.snapshot.params['id']).subscribe(resp => {
        var downloadURL = window.URL.createObjectURL(resp);
        var link = document.createElement('a');
        link.href = downloadURL;
        link.download = "help.pdf";
        link.click();
      }
    );
  }


}
