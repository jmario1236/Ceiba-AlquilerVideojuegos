import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClienteService } from '@clientes/shared/service/cliente.service';
import {NgbAlert} from '@ng-bootstrap/ng-bootstrap';
import { Cliente } from '@clientes/shared/model/cliente';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {
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
        next: () => {
          this.exitoMensaje = `Se ha registrado cliente`;
          this.clienteForm.disable();
        },
        error: error => {
          this.errorMensaje =  error.error.mensaje;
        }
      })    
  }

  actualizar(){
    this.clienteService.actualizar(this.clienteForm.value).subscribe({
      next: () => {        
        this.exitoMensaje =  `Se ha actualizado la información del cliente`;
        this.clienteForm.disable();        
      },
      error: error => {
        this.errorMensaje =  error.error.mensaje ;
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
