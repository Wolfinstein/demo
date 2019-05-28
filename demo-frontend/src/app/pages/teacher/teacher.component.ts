import {Component, ViewChild} from "@angular/core";
import {DatatableComponent} from "@swimlane/ngx-datatable";
import {ClassService} from "../../services/class.service";
import {UserInfoService} from "../../services/user-info.service";

@Component({
  selector: 'teacher-component',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.scss']
})


export class TeacherComponent {

  @ViewChild(DatatableComponent) private subjectsTable: DatatableComponent;
  subjectsList: any;


  constructor(private classService: ClassService,
              private userInfo: UserInfoService) {

    this.classService.getSubjects(Number(userInfo.getUserInfo().userId)).subscribe(resp => {
      this.subjectsList = resp;
    })
  }

  getReport(id: number) {
    this.classService.getTeacherStats(id).subscribe(resp => {
        var downloadURL = window.URL.createObjectURL(resp);
        var link = document.createElement('a');
        link.href = downloadURL;
        link.download = "teacherReport.pdf";
        link.click();
      }
    );
  }

}
