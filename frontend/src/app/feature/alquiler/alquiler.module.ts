import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RegistrarComponent } from './components/registrar/registrar.component';
import { VideojuegosModule } from '@videojuegos/videojuegos.module';
import { ClientesModule } from '@clientes/clientes.module';
import { AlquilerRoutingModule } from './alquiler-rounting.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AlquilerService } from './shared/service/alquiler.service';


@NgModule({
  declarations: [RegistrarComponent],
  imports: [
    CommonModule,
    AlquilerRoutingModule,
    VideojuegosModule,
    ClientesModule,
    NgbModule,
    FormsModule
  ],
  providers:[AlquilerService]
})
export class AlquilerModule { } 
