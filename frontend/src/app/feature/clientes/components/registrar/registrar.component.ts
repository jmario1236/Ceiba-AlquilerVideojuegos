import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClienteService } from '@clientes/shared/service/cliente.service';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';
import {NgbAlert} from '@ng-bootstrap/ng-bootstrap';
import { Cliente } from '@clientes/shared/model/cliente';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {
  private _error = new Subject<string>();
  private _success = new Subject<string>();
  public cliente:Cliente;
  clienteForm: FormGroup;
  errorMensaje;
  exitoMensaje;

  @ViewChild('errorAlert', {static: false}) errorAlert: NgbAlert;
  @ViewChild('successAlert', {static: false}) successAlert: NgbAlert;

  constructor(private route: Router, protected clienteService:ClienteService) {
    let state = this.route.getCurrentNavigation().extras.state;
    if(state){
      this.cliente = state.cliente;
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
    if(this.cliente){
      this.clienteForm.setValue(this.cliente);
    }
  }

  ejecutar(){
    if(!this.clienteForm.invalid){
      if(this.cliente){
        this.actualizar();
      }else{
        this.guardar();
      }
    }
  }

  guardar(){    
      this.clienteService.guardar(this.clienteForm.value).subscribe({
        next: data => {
          this._success.next(`Se ha registrado cliente ID:${data.valor}`);
          this.clienteForm.disable();
        },
        error: error => {
          this._error.next(error.error.mensaje);
        }
      })    
  }

  actualizar(){
    this.clienteService.actualizar(this.clienteForm.value).subscribe({
      next: () => {        
        this._success.next(`Se ha Actualizado la información del cliente`);
        this.clienteForm.disable();        
      },
      error: error => {
        this._error.next(error.error.mensaje);
      }
    })
  }

  irListado(){
    this.cliente = undefined;
    this.route.navigateByUrl('/clientes');    
  }

  private construirFormulario(){
    this.clienteForm = new FormGroup({
      identificacion: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      apellido: new FormControl('', []),
      id: new FormControl('', []),
      telefono: new FormControl('', [Validators.required]),
      direccion: new FormControl('', []),
    });
  }

}
