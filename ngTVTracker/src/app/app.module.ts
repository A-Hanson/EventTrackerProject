import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TvwatchingsessionService } from './services/tvwatchingsession.service';
import { TvWatchingSessionListComponent } from './components/tv-watching-session-list/tv-watching-session-list.component';
import { PlatformListComponent } from './components/platform-list/platform-list.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';


@NgModule({
  declarations: [
    AppComponent,
    TvWatchingSessionListComponent,
    PlatformListComponent,
    NotfoundComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,
    NavBarComponent
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
