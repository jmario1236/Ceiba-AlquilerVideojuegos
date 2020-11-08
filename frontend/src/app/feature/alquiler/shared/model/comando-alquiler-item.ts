export class ComandoAlquilerItem {
    id: number;
    cantidad: number;
    precio: number;
    videoJuego: number;
    constructor(
        id: number,
        cantidad: number,
        precio: number,
        videoJuego: number,) {
        
            this.id = id;
            this.cantidad = cantidad;
            this.precio = precio;
            this.videoJuego = videoJuego;
    }
}