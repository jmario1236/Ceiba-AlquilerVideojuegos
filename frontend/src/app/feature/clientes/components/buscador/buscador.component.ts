import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ClienteService } from '@clientes/shared/service/cliente.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'buscador-cliente',
  templateUrl: './buscador.component.html',
  styleUrls: ['./buscador.component.css']
})
export class BuscadorComponent implements OnInit {

  clienteBuscadorForm: FormGroup;
  clienteOptionForm: FormGroup;
  clientesResultado:Array<any>;
  cliente:any;

  @Output() onLoadCliente = new EventEmitter();
  

  @ViewChild('content', {static:false}) content: ElementRef;

  constructor(protected clienteService:ClienteService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.construirFormulario();
    this.clienteBuscadorForm.controls.nombre.disable();
    this.clienteBuscadorForm.controls.apellido.disable();
    this.clienteBuscadorForm.controls.direccion.disable();
    this.clienteBuscadorForm.controls.telefono.disable();
  }

  buscar(){
    if(this.clienteBuscadorForm.invalid){
      return;
    }
    let criterio = this.clienteBuscadorForm.value.criterio
    this.clienteService.buscar(criterio).subscribe({
      next:(data) => {
        this.cargar(data);
      }
    })
  }

  private cargarDatosACampos(dato:any){
    this.onLoadCliente.emit(dato);
    this.clienteBuscadorForm.setValue({
      nombre:dato.nombre,
      apellido:dato.apellido,
      direccion:dato.direccion,
      telefono: dato.telefono,
      criterio: ''
    });
  }

  private cargar(data:Array<any>){
    if(data.length === 1){
      this.cargarDatosACampos(data[0]);
    }else if(data.length > 1){
      this.clientesResultado = data;
      this.construirFormularioOption();
      this.modalService.open(this.content).result.then(() => {
        this.cargarDatosACampos(this.cliente);
      });
    }
  }

  setClienteClick(cliente){
    this.cliente = cliente;
  }

  private construirFormularioOption(){
    this.clienteOptionForm = new FormGroup({
      cliente: new FormControl('', []),     
    });
  }

  private construirFormulario(){
    this.clienteBuscadorForm = new FormGroup({
      criterio: new FormControl('', [Validators.required]),
      nombre: new FormControl('', []),
      apellido: new FormControl('', []),
      telefono: new FormControl('', []),
      direccion: new FormControl('', []),
    });
  }

}
