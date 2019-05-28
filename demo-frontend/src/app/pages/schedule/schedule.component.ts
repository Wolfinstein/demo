import {Component} from "@angular/core";
import {ClassService} from "../../services/class.service";
import {ActivatedRoute} from "@angular/router";
import {SubjectModel} from "../../models/subject.model";

@Component({
  selector: 'schedule-component',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss'],
})

export class ScheduleComponent {

  map = new Map<number, SubjectModel[]>();
  class: any;

  constructor(private classService: ClassService,
              private route: ActivatedRoute) {
    this.classService.getSchedule(this.route.snapshot.params['id']).subscribe(resp => {
      console.log(resp);
      this.map = resp;
    });

    this.classService.getClass(this.route.snapshot.params['id']).subscribe(resp => {
      this.class = resp;
    })

  }
}
