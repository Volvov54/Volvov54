import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {Observable} from 'rxjs'
import {environment} from '../../environments/environment'
import {CompsWPS} from '../models/comps-wps'

const url = environment.baseUrl

@Injectable({
  providedIn: 'root',
})
export class CompsWPSService {
  constructor(private http: HttpClient) {}

  getCompsWPS(): Observable<CompsWPS> {
    return this.http.get<CompsWPS>(`${url}/ports`)
  }
}
