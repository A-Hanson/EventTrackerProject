import { Platform } from "./platform";

export class TvWatchingSession {
  id: number;
  start: string;
  stop: string;
  deleted: boolean;
  platform: Platform;

  constructor(id?: number, start?: string, stop?: string, deleted?: boolean, platform?: Platform){
    this.id = id;
    this.start = start;
    this.stop = stop;
    this.deleted = deleted;
    this.platform = platform;
  }
}
