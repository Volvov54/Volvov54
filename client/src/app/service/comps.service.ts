import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {Comps} from '../models/comps'
import {environment} from '../../environments/environment'
import {Observable} from 'rxjs'

const url = environment.baseUrl

@Injectable({
  providedIn: 'root',
})
export class CompsService {
  constructor(private http: HttpClient) {}

  getComps(): Observable<Comps> {
    return this.http.get<Comps>(url)
  }
}
