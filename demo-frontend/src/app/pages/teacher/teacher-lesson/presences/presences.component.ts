import {Component, ViewChild} from "@angular/core";
import {ClassService} from "../../../../services/class.service";
import {ActivatedRoute} from "@angular/router";
import {MatDialog} from "@angular/material";
import {DatatableComponent} from "@swimlane/ngx-datatable";
import {ChangePresenceComponent} from "./change-presence/change.presence.component";

@Component({
  selector: 'presences-component',
  templateUrl: './presences.component.html',
  styleUrls: ['./presences.component.scss']
})


export class PresencesComponent {

  @ViewChild(DatatableComponent) private presencesTable: DatatableComponent;


  presencesList: any[];

  constructor(private classService: ClassService,
              private route: ActivatedRoute,
              private dialog: MatDialog) {

    this.getPresences();
  }

  getPresences() {
    this.classService.getPresences(Number(this.route.snapshot.params['id'])).subscribe(resp => {
      this.presencesList = [...resp];
    })
  }

  editPresence(presenceId: String, presenceType: String) {
    let dialogRef = this.dialog.open(ChangePresenceComponent, {
      panelClass: 'presence-edit-component',
      data: {presenceId: presenceId, presenceType: presenceType}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getPresences();
    });
  }

}
