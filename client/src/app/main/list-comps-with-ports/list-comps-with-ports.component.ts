import {Component, OnDestroy, OnInit} from '@angular/core'
import {Subject, takeUntil} from 'rxjs'
import {CompsWPSService} from '../../service/comps-wps.service'
import {CompWPS} from '../../models/comps-wps'

@Component({
  selector: 'app-list-comps-with-ports',
  templateUrl: './list-comps-with-ports.component.html',
})
export class ListCompsWithPortsComponent implements OnInit, OnDestroy {
  data: CompWPS[] = []
  private endSubs$: Subject<any> = new Subject()
  loaded: boolean = false

  constructor(private service: CompsWPSService) {}

  ngOnInit(): void {
    this.service
      .getCompsWPS()
      .pipe(takeUntil(this.endSubs$))
      .subscribe(comps => {
        this.data = comps.data
        this.loaded = true
        console.log(JSON.stringify(comps))
      })
  }

  ngOnDestroy(): void {
    this.endSubs$.next(null)
    this.endSubs$.complete()
  }
}
