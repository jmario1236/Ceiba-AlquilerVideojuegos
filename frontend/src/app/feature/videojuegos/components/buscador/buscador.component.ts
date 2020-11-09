import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { VideojuegoService } from '@videojuegos/shared/service/videojuego.service';

@Component({
  selector: 'buscador-videojuego',
  templateUrl: './buscador.component.html',
  styleUrls: ['./buscador.component.css']
})
export class BuscadorComponent implements OnInit {

  videojuegoBuscadorForm: FormGroup;
  videojuegoOptionForm: FormGroup;
  videojuegosResultado:Array<any>;
  videojuego:any;
  
  @Output() onLoadVideojuego = new EventEmitter();

  @ViewChild('content', {static:false}) content: ElementRef;

  constructor(protected videojuegoService:VideojuegoService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.construirFormulario();
    this.videojuegoBuscadorForm.controls.nombre.disable();
    this.videojuegoBuscadorForm.controls.codigo.disable();
    this.videojuegoBuscadorForm.controls.precio.disable();
  }

  buscar(){
    if(this.videojuegoBuscadorForm.invalid){
      return;
    }
    let criterio = this.videojuegoBuscadorForm.value.criterio
    this.videojuegoService.buscar(criterio).subscribe({
      next:(data) => {
        this.cargar(data);
      }
    })
  }

  private cargarDatosACampos(dato:any){
    this.onLoadVideojuego.emit(dato);
    this.videojuegoBuscadorForm.setValue({
      nombre:dato.nombre,
      codigo:dato.codigo,
      precio:dato.precio,
      criterio: ''
    });
    
  }

  private cargar(data:Array<any>){
    if(data.length === 1){
      this.cargarDatosACampos(data[0]);
    }else if(data.length > 1){
      this.videojuegosResultado = data;
      this.construirFormularioOption();
      this.modalService.open(this.content).result.then(() => {
        this.cargarDatosACampos(this.videojuego);
      });
    }
  }

  setClienteClick(videojuego){
    this.videojuego = videojuego;
  }

  private construirFormularioOption(){
    this.videojuegoOptionForm = new FormGroup({
      videojuego: new FormControl('', []),     
    });
  }

  private construirFormulario(){
    this.videojuegoBuscadorForm = new FormGroup({
      criterio: new FormControl('', [Validators.required]),
      nombre: new FormControl('', []),
      codigo: new FormControl('', []),
      precio: new FormControl('', [])
    });
  }

}
