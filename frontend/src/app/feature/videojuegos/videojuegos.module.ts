import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrarComponent } from './components/registrar/registrar.component';
import { ListarComponent } from './components/listar/listar.component';
import { SharedModule } from '@shared/shared.module';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { VideoJuegosRoutingModule } from './videojuegos-routing.module';
import { VideojuegoService } from './shared/service/videojuego.service';



@NgModule({
  declarations: [RegistrarComponent, ListarComponent],
  imports: [
    CommonModule,
    VideoJuegosRoutingModule,
    SharedModule,
    NgbAlertModule
  ],
  providers: [VideojuegoService]
})
export class VideojuegosModule { }
