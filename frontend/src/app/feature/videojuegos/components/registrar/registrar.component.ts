import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbAlert } from '@ng-bootstrap/ng-bootstrap';
import { VideoJuego } from '@videojuegos/shared/model/videojuego';
import { VideojuegoService } from '@videojuegos/shared/service/videojuego.service';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {
  private _error = new Subject<string>();
  private _success = new Subject<string>();
  public videojuego:VideoJuego;
  videojuegoForm: FormGroup;
  errorMensaje;
  exitoMensaje;

  @ViewChild('errorAlert', {static: false}) errorAlert: NgbAlert;
  @ViewChild('successAlert', {static: false}) successAlert: NgbAlert;

  constructor(private route: Router, protected videojuegoService:VideojuegoService) {
    let state = this.route.getCurrentNavigation().extras.state;
    if(state){
      this.videojuego = state.videojuego;
    }    
  }

  ngOnInit(): void {
    this.construirFormulario();
    this.cargarDatosFormulario();
    this._error.subscribe(message => this.errorMensaje = message);
    this._error.pipe(debounceTime(5000)).subscribe(() => {
      if (this.errorAlert) {
        this.errorMensaje = '';
      }
    });
    this._success.subscribe(message => this.exitoMensaje = message);
    this._success.pipe(debounceTime(5000)).subscribe(() => {
      if (this.successAlert) {
        this.exitoMensaje = '';
        this.irListado();
      }
    });    
  }

  private cargarDatosFormulario(){
    if(this.videojuego){
      this.videojuegoForm.setValue(this.videojuego);
    }
  }

  ejecutar(){
    if(!this.videojuegoForm.invalid){
      if(this.videojuego){
        this.actualizar();
      }else{
        this.guardar();
      }
    }
  }

  guardar(){    
      this.videojuegoService.guardar(this.videojuegoForm.value).subscribe({
        next: data => {
          this._success.next(`Se ha registrado videojuego ID:${data.valor}`);
          this.videojuegoForm.disable();
        },
        error: error => {
          this._error.next(error.error.mensaje);
        }
      })    
  }

  actualizar(){
    this.videojuegoService.actualizar(this.videojuegoForm.value).subscribe({
      next: () => {        
        this._success.next(`Se ha Actualizado la información del videojuego`);
        this.videojuegoForm.disable();        
      },
      error: error => {
        this._error.next(error.error.mensaje);
      }
    })
  }

  irListado(){
    this.videojuego = undefined;
    this.route.navigateByUrl('/videojuegos');    
  }

  private construirFormulario(){
    this.videojuegoForm = new FormGroup({
      codigo: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      genero: new FormControl('', []),
      precio: new FormControl('', [Validators.required]),
      stock: new FormControl('', [Validators.required]),
      id: new FormControl('', []),
    });
  }


}
