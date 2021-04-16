import { Component, OnInit } from '@angular/core';
import { TvWatchingSession } from 'src/app/models/tv-watching-session';
import { TvwatchingsessionService } from 'src/app/services/tvwatchingsession.service';

@Component({
  selector: 'app-tv-watching-session-list',
  templateUrl: './tv-watching-session-list.component.html',
  styleUrls: ['./tv-watching-session-list.component.css']
})
export class TvWatchingSessionListComponent implements OnInit {

  sessions: TvWatchingSession[] = [];

  constructor(
    private sessionService: TvwatchingsessionService
  ) { }

  ngOnInit(): void {
    this.loadSessions();
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

}
