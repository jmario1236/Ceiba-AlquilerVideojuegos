import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SecurityGuard } from '@core/guard/security.guard';
import { HomeComponent } from '@home/home.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [SecurityGuard]  },
  { path: 'clientes', loadChildren: () => import('@clientes/clientes.module').then(mod => mod.ClientesModule) },
  { path: 'videojuegos', loadChildren: () => import('@videojuegos/videojuegos.module').then(mod => mod.VideojuegosModule) },
  { path: 'alquiler', loadChildren: () => import('@alquiler/alquiler.module').then(mod => mod.AlquilerModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
