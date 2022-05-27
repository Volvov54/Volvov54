import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {Observable} from 'rxjs'
import {environment} from '../../environments/environment'
import {CompsWP} from '../models/comps-wp'

const url = environment.baseUrl

@Injectable({
  providedIn: 'root',
})
export class CompsWPService {
  constructor(private http: HttpClient) {}

  getCompsWP(): Observable<CompsWP> {
    return this.http.get<CompsWP>(`${url}/ping`)
  }
}
