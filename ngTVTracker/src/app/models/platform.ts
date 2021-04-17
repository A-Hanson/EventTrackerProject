export class Platform {
  id: number;
  name: string;
  imageUrl: string;
  deleted: boolean;

  constructor(id?: number, name?: string, imageUrl?: string, deleted?: boolean){
    this.id = id;
    this.name = name;
    this.imageUrl = imageUrl;
    this.deleted = deleted;
  }
}
