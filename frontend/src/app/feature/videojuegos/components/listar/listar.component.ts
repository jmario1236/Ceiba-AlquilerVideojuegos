import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VideoJuego } from '@videojuegos/shared/model/videojuego';
import { VideojuegoService } from '@videojuegos/shared/service/videojuego.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {

  videojuegos:Observable<VideoJuego[]>;

  constructor(protected videouegoService:VideojuegoService, private route: Router) { }

  ngOnInit(): void {
    this.cargarDatos();
  }

  private cargarDatos(){
    this.videojuegos = this.videouegoService.listar();
  }

  irAgregar(){
    this.route.navigateByUrl('/videojuegos/crear');
  }

  editar(videojuego){
    this.route.navigate(['/videojuegos/crear'], { state: { videojuego: videojuego } });
  }

  eliminar(id){
    console.log(id);
    this.videouegoService.eliminar(id).subscribe({
      next: () => {
        this.cargarDatos();
      },
      error: error => {
        console.error('There was an error!', error);
      }
  })
  }

}
