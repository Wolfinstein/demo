import {Component} from "@angular/core";
import {UserInfoService} from "../../services/user-info.service";
import {ClassService} from "../../services/class.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'parent-component',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.scss'],
})


export class ParentComponent {


  studentInfoList: any[];
  studentAbsencesList: any[];


  constructor(private userInfo: UserInfoService,
              private classService: ClassService,
              private route: ActivatedRoute) {

    this.classService.getStudentDataForParent(this.route.snapshot.params['id']).subscribe(resp => {
      this.studentInfoList = resp;
    });

    this.classService.getStudentAbsencesForParent(this.route.snapshot.params['id']).subscribe(resp => {
      this.studentAbsencesList = resp;
    })
  }

  getStats(id: number) {
    this.classService.getStats(id).subscribe(resp => {
        var downloadURL = window.URL.createObjectURL(resp);
        var link = document.createElement('a');
        link.href = downloadURL;
        link.download = "help.pdf";
        link.click();
      }
    );
  }

}
