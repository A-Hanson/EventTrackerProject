import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Platform } from 'src/app/models/platform';
import { TvWatchingSession } from 'src/app/models/tv-watching-session';
import { PlatformService } from 'src/app/services/platform.service';
import { TvwatchingsessionService } from 'src/app/services/tvwatchingsession.service';

@Component({
  selector: 'app-tv-watching-session-list',
  templateUrl: './tv-watching-session-list.component.html',
  styleUrls: ['./tv-watching-session-list.component.css']
})
export class TvWatchingSessionListComponent implements OnInit {

  sessions: TvWatchingSession[] = [];
  platforms: Platform[] = [];
  selected: TvWatchingSession = null;
  sessionToEdit: TvWatchingSession = null;
  newSession: TvWatchingSession = new TvWatchingSession();
  addNew: boolean = false;
  editSession: boolean = false;

  constructor(
    private sessionService: TvwatchingsessionService,
    private platformService: PlatformService
  ) { }

  ngOnInit(): void {
    this.loadSessions();
    this.loadPlatforms();
  }

  loadSessions(): void {
    this.sessionService.index().subscribe(
      data => {
        this.sessions = data;
      },
      fail => {
        console.error('SessionComponent.loadSession() failed: ' + fail);

      }
    );
  }

  loadPlatforms(): void {
    this.platformService.index().subscribe(
      data => {
        this.platforms = data;
      },
      fail => {
        console.error('SessionComponent.loadPlatforms() failed: ' + fail);

      }
    );
  }

  addNewSession(session: TvWatchingSession){
    this.sessionService.create(session).subscribe(
      data => {
        this.loadSessions();
        this.clearAddNew();
      },
      fail => {
        console.error("SessionComponent.addNewSession() got a error: " + fail);

      }
    );
  }

  clearAddNew() {
    this.addNew = false;
    this.newSession = new TvWatchingSession();
  }

  setPlatform(session: TvWatchingSession, platform: Platform){
    session.platform = platform;
  }

  updateSession(session: TvWatchingSession){
    console.log(session);
    this.sessionService.update(session.id, session).subscribe(
      data => {
        this.selected = session;
        this.sessionToEdit = null;
        this.loadSessions();
      },
      fail => console.error("SessionComponent.UpdateSession() got an error: " + fail)

    );
  }

  delete(id: number) {
    this.sessionService.destroy(id).subscribe(
      data => {
        this.loadSessions();
      },
      fail => console.error("SessionComponent.delete() got an error: " + fail)
    );
  }

  duration(start: any, stop:any) {
    let dur = Date.parse(stop) - Date.parse(start);
    let minutes = (dur/60000); // converts to minutes
    if (minutes >= 60) {
      let hours = Math.floor(minutes / 60);
      minutes %= 60;
      return hours + " hours, " + minutes + " minutes.";
    }
    return minutes + " minutes.";
  }

}
