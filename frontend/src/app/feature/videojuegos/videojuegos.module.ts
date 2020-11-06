import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrarComponent } from './components/registrar/registrar.component';
import { ListarComponent } from './components/listar/listar.component';



@NgModule({
  declarations: [RegistrarComponent, ListarComponent],
  imports: [
    CommonModule
  ]
})
export class VideojuegosModule { }
