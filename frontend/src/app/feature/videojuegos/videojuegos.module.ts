import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrarComponent } from './components/registrar/registrar.component';
import { ListarComponent } from './components/listar/listar.component';
import { SharedModule } from '@shared/shared.module';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { VideoJuegosRoutingModule } from './videojuegos-routing.module';
import { VideojuegoService } from './shared/service/videojuego.service';
import { BuscadorComponent } from './components/buscador/buscador.component';



@NgModule({
  declarations: [RegistrarComponent, ListarComponent, BuscadorComponent],
  imports: [
    CommonModule,
    VideoJuegosRoutingModule,
    SharedModule,
    NgbAlertModule
  ],
  exports:[BuscadorComponent],
  providers: [VideojuegoService]
})
export class VideojuegosModule { }
