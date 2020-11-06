import { Component } from '@angular/core';
import { MenuItem } from '@core/modelo/menu-item';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  title = 'Alquiler de videojuegos';
  public companies: MenuItem[] = [
    { url: '/home', nombre: 'home' },
    { url: '/producto', nombre: 'producto' },
    { url: '/clientes', nombre: 'Clientes' }
    
  ];

  
}
