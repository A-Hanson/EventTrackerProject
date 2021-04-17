import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TvWatchingSessionListComponent } from './tv-watching-session-list.component';

describe('TvWatchingSessionListComponent', () => {
  let component: TvWatchingSessionListComponent;
  let fixture: ComponentFixture<TvWatchingSessionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TvWatchingSessionListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TvWatchingSessionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
