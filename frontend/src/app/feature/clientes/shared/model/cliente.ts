export class Cliente {
    id: number;
    identificacion: string;
    nombre: string;
    apellido: string;
    telefono: string;
    direccion: string;

    constructor(
        id: number,
        identificacion: string,
        nombre: string,
        apellido: string,
        telefono: string,
        direccion: string,
    ) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre =  nombre;
        this.apellido = apellido;
        this.telefono =  telefono;
        this.direccion = direccion;
    }
}