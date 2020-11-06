import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarComponent } from './components/listar/listar.component';
import { RegistrarComponent } from "./components/registrar/registrar.component";

const routes: Routes = [
  {
    path: '',
    component: ListarComponent,    
  },      
    {
      path: 'crear',
      component: RegistrarComponent
    },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }