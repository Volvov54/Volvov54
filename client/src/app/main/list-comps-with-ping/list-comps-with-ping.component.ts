import {Component, OnDestroy, OnInit} from '@angular/core'
import {Subject, takeUntil} from 'rxjs'
import {CompWP} from '../../models/comps-wp'
import {CompsWPService} from '../../service/comps-wp.service'

@Component({
  selector: 'app-list-comps-with-ping',
  templateUrl: './list-comps-with-ping.component.html',
  styles: [],
})
export class ListCompsWithPingComponent implements OnInit, OnDestroy {
  data: CompWP[] = []
  private endSubs$: Subject<any> = new Subject()
  loaded: boolean = false

  constructor(private service: CompsWPService) {}

  ngOnInit(): void {
    this.service
      .getCompsWP()
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
