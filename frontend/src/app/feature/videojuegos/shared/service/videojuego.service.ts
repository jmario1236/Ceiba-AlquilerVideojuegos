import { Injectable } from '@angular/core';
import { HttpService } from '@core/services/http.service';
import { environment } from 'src/environments/environment';
import { VideoJuego } from '../model/videojuego';

@Injectable()
export class VideojuegoService {
  private videojuegos = "/videojuegos"
  constructor(protected http: HttpService) { }

  public listar(){
    return this.http.doGet<VideoJuego[]>(`${environment.endpoint}${this.videojuegos}`);
  }

  public guardar(videojuegos:VideoJuego){
    return this.http.doPost<VideoJuego,any>(`${environment.endpoint}${this.videojuegos}`, videojuegos);
  }

  public eliminar(id:any){
    return this.http.doDelete<any>(`${environment.endpoint}${this.videojuegos}/${id}`);
  }

  public actualizar(videojuegos:VideoJuego){
    return this.http.doPut<VideoJuego,any>(`${environment.endpoint}${this.videojuegos}/${videojuegos.id}`,videojuegos);
  }

  public buscar(criterio:string){
    return this.http.doGet<VideoJuego[]>(`${environment.endpoint}${this.videojuegos}/consultar/${criterio}`);
  }

}
