export class VideoJuego {
    id: number;
    codigo: string;
    nombre: string;
    genero: string;
    precio: number;
    stock: number;

    constructor(
        id: number,
        codigo: string,
        nombre: string,
        genero: string,
        precio: number,
        stock: number,
    ) {
        this.id = id;
        this.codigo = codigo;
        this.nombre =  nombre;
        this.genero = genero;
        this.precio =  precio;
        this.stock = stock;
    }
}