import { ComandoAlquiler } from '@alquiler/shared/model/comando-alquiler';
import { ComandoAlquilerItem } from '@alquiler/shared/model/comando-alquiler-item';
import { ConsultaAlquiler } from '@alquiler/shared/model/consulta-alquiler';
import { ConsultaAlquilerItem } from '@alquiler/shared/model/consulta-alquiler-item';
import { AlquilerService } from '@alquiler/shared/service/alquiler.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbAlert, NgbDatepicker, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import * as moment from 'moment';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {
  errorMensaje;
  exitoMensaje;
  fechaInicio: NgbDateStruct;
  fechaFin: NgbDateStruct;
  cliente:any;
  videojuego:any;
  items:Array<ConsultaAlquilerItem> = [];
  cantidadVideojuego:any;
  totalAdicional:number = 0;
  totalMulta:number = 0;
  total:number = 0;
  estadoAlquiler:string = "VIGENTE";
  alquiler:ConsultaAlquiler;

  @ViewChild('di', { read: NgbDatepicker }) di: NgbDatepicker;
  @ViewChild('df', { read: NgbDatepicker }) df: NgbDatepicker;
  @ViewChild('errorAlert', {static: false}) errorAlert: NgbAlert;
  @ViewChild('successAlert', {static: false}) successAlert: NgbAlert;
  constructor(protected alquilerService:AlquilerService) { }

  ngOnInit(): void {   
  }

  onLoadCliente(cliente){
    this.cliente = cliente;
    this.cargarAlquiler();
  }

  agregarLista(){
    if(this.cantidadVideojuego > this.videojuego.stock || !this.cantidadVideojuego){
      return;
    }
    this.items.push(new ConsultaAlquilerItem(null,this.videojuego,this.videojuego.precio,this.cantidadVideojuego))
  }

  guardar(){
    let itemsComando = this.items.map(i => new ComandoAlquilerItem(i.id,i.cantidad,i.precio,i.videoJuego.id));
    let fechaInicio = new Date(this.fechaInicio.year,this.fechaInicio.month-1,this.fechaInicio.day);
    let fechaFin = new Date(this.fechaFin.year,this.fechaFin.month-1,this.fechaFin.day);    
    let alquiler = new ComandoAlquiler(null,moment(fechaInicio).format('YYYY-MM-DD'),moment(fechaFin).format('YYYY-MM-DD'),this.cliente.id,itemsComando);
    console.log(alquiler);
    this.alquilerService.registrarAlquiler(alquiler).subscribe({
      next: () => {
        this.exitoMensaje = `Se ha registrado Alquiler`;
        this.cargarAlquiler();
      },
      error: error => {        
        this.errorMensaje = error.error.mensaje;
      }
    }) 
  }

  private cargarAlquiler(){
    this.alquilerService.buscarPorCliente(this.cliente.id).subscribe({
      next:(data) => {
        if(!data){
          return;
        }
        this.alquiler = data;
        this.total = data.total;
        this.totalAdicional = data.totalAdiccional;
        this.totalMulta = data.totalMulta;
        this.fechaInicio = this.loadFechas(data.fechaAlquiler);
        this.fechaFin = this.loadFechas(data.fechaMaximaEntrega);
        this.items = data.items;
        this.estadoAlquiler = data.estado;
      }
    })
  }

  private loadFechas(fechaIn:Date){    
    return {
      day: moment(fechaIn).date(),
      month: moment(fechaIn).month(),
      year: moment(fechaIn).year()
    }    
  }

  nuevo(){
    this.alquiler = undefined;
    this.total = 0;
    this.totalAdicional = 0;
    this.totalMulta = 0;
    this.fechaInicio = undefined;
    this.fechaFin = undefined;
    this.items = [];
    this.estadoAlquiler = "VIGENTE";
    this.cliente = undefined;
  }

  onLoadVideojuego(videojuego){
    this.videojuego = videojuego;
  }
}
