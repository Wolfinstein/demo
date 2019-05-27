import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UserService} from "../../../services/user.service";
import {IMultiSelectOption, IMultiSelectSettings, IMultiSelectTexts} from "angular-2-dropdown-multiselect";
import {UserInfoService} from "../../../services/user-info.service";

@Component({
  selector: 'kids-edit-component',
  templateUrl: './edit.kids.component.html',
  styleUrls: ['./edit.kids.component.scss']
})


export class EditKidsComponent {

  myOptions?: IMultiSelectOption[];
  optionsModel?: number[];

  string: any[];

  constructor(private userService: UserService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<EditKidsComponent>) {
    this.optionsModel = data.data.kidsIds;
    this.userService.getPotentialKids(this.data.data.id).subscribe(resp => {
      this.myOptions = resp;
    })
  }

  onEdit(): void {
    this.userService.editKids(this.data.data.id, this.string).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }

  onChange(event) {
    this.string = event;
  }

  mySettings: IMultiSelectSettings = {
    enableSearch: false,
    checkedStyle: 'fontawesome',
    buttonClasses: 'btn btn-default btn-block',
    maxHeight: '45vh',
    fixedTitle: true,
    dynamicTitleMaxItems: 5,
    displayAllSelectedText: true
  };

// Text configuration
  myTexts: IMultiSelectTexts = {
    checkAll: 'Select all',
    uncheckAll: 'Unselect all',
    checked: 'item selected',
    checkedPlural: 'items selected',
    searchPlaceholder: 'Find',
    searchEmptyResult: 'Nothing found...',
    searchNoRenderText: 'Type in search box to see results...',
    defaultTitle: 'Choose kids',
    allSelected: 'All selected',
  };

}
