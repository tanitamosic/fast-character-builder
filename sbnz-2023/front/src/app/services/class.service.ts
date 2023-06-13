import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  constructor(private httpClient: HttpClient) { }

  postParty(party: any) {
    return this.httpClient.post('/dnd/class/party', party);
  }
}
