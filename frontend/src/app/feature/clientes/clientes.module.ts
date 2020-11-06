import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrarComponent } from './components/registrar/registrar.component';
import { ListarComponent } from './components/listar/listar.component';
import { SharedModule } from '@shared/shared.module';
import { ClientesRoutingModule } from './clientes-routing.module';
import { ClienteService } from './shared/service/cliente.service';
import {NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [RegistrarComponent, ListarComponent],
  imports: [
    CommonModule,  
    ClientesRoutingModule,
    SharedModule,
    NgbAlertModule
  ],
  providers: [ClienteService]
})
export class ClientesModule { }
