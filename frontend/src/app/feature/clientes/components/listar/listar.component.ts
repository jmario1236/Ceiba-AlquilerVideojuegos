import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../../shared/model/cliente';
import { ClienteService } from '../../shared/service/cliente.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {

  clientes:Observable<Cliente[]>;

  constructor(protected clienteService:ClienteService, private route: Router) { }

  ngOnInit(): void {
    this.cargarDatos();
  }

  private cargarDatos(){
    this.clientes = this.clienteService.listar();
  }

  irAgregar(){
    this.route.navigateByUrl('/clientes/crear');
  }

  editar(cliente){
    console.log(cliente)
    this.route.navigate(['/clientes/crear'], { state: { cliente: cliente } });
  }

  eliminar(id){
    console.log(id);
    this.clienteService.eliminar(id).subscribe({
      next: () => {
        this.cargarDatos();
      },
      error: error => {
        console.error('There was an error!', error);
      }
  })
  }
}
