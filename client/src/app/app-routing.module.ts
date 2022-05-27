import {NgModule} from '@angular/core'
import {RouterModule, Routes} from '@angular/router'
import {ListCompsComponent} from './main/list-comps/list-comps.component'
import {ListCompsWithPingComponent} from './main/list-comps-with-ping/list-comps-with-ping.component'

const routes: Routes = [
  {path: '', component: ListCompsComponent},
  {path: 'ping', component: ListCompsWithPingComponent},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
