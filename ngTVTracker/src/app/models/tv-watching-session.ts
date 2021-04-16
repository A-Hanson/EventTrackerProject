export class TvWatchingSession {
  id: number;
  start: string;
  stop: string;
  deleted: boolean;

  constructor(id?: number, start?: string, stop?: string, deleted?: boolean){
    this.id = id;
    this.start = start;
    this.stop = stop;
    this.deleted = deleted;
  }
}
