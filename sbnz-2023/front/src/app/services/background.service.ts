import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BackgroundService {

  constructor(private httpClient: HttpClient) { }

  getNewOptions(backgroundWrapper: any) {
    return this.httpClient.post('/dnd/get-next-background-option', backgroundWrapper)
  }

}
