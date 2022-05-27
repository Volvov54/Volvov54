import {Component, OnDestroy, OnInit} from '@angular/core'
import {CompsService} from '../../service/comps.service'
import {Subject, takeUntil} from 'rxjs'

class Comp {}

@Component({
  selector: 'app-list-comps',
  templateUrl: './list-comps.component.html',
  styles: [],
})
export class ListCompsComponent implements OnInit, OnDestroy {
  data: Comp[] = []
  private endSubs$: Subject<any> = new Subject()

  constructor(private service: CompsService) {}

  ngOnInit(): void {
    this.service
      .getComps()
      .pipe(takeUntil(this.endSubs$))
      .subscribe(comps => {
        this.data = comps.data
        console.log(JSON.stringify(comps))
      })
  }

  ngOnDestroy(): void {
    this.endSubs$.next(null)
    this.endSubs$.complete()
  }
}
