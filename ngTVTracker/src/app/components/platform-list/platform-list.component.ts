import { Component, OnInit } from '@angular/core';
import { Platform } from 'src/app/models/platform';
import { PlatformService } from 'src/app/services/platform.service';

@Component({
  selector: 'app-platform-list',
  templateUrl: './platform-list.component.html',
  styleUrls: ['./platform-list.component.css']
})
export class PlatformListComponent implements OnInit {
  platforms: Platform[] = [];
  selected: Platform = null;
  newPlatform: Platform = new Platform();
  addNew: boolean = false;
  editSession:boolean = false;

  constructor(
    private platformService: PlatformService
  ) { }

  ngOnInit(): void {
    this.loadPlatforms();
  }

  loadPlatforms(): void {
    this.platformService.index().subscribe(
      data => {
        this.platforms = data;
      },
      fail => {
        console.error('PlatformComponent.loadPlatforms() failed: ' + fail);

      }
    );
  }

}
