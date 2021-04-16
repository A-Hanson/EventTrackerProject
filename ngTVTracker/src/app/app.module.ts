import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TvwatchingsessionService } from './services/tvwatchingsession.service';
import { TvWatchingSessionListComponent } from './componenets/tv-watching-session-list/tv-watching-session-list.component';

@NgModule({
  declarations: [
    AppComponent,
    TvWatchingSessionListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    TvwatchingsessionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
