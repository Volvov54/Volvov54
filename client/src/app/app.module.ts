import {NgModule} from '@angular/core'
import {BrowserModule} from '@angular/platform-browser'

import {AppRoutingModule} from './app-routing.module'
import {AppComponent} from './app.component'
import {HeaderComponent} from './shared/header/header.component'
import {NavComponent} from './shared/nav/nav.component'
import {ListCompsComponent} from './main/list-comps/list-comps.component'
import {ListCompsWithPingComponent} from './main/list-comps-with-ping/list-comps-with-ping.component'
import {HttpClientModule} from '@angular/common/http'
import {TableModule} from 'primeng/table'
import {ProgressSpinnerModule} from 'primeng/progressspinner'
import {TagModule} from 'primeng/tag';
import { ListCompsWithPortsComponent } from './main/list-comps-with-ports/list-comps-with-ports.component'

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavComponent,
    ListCompsComponent,
    ListCompsWithPingComponent,
    ListCompsWithPortsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TableModule,
    ProgressSpinnerModule,
    TagModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
