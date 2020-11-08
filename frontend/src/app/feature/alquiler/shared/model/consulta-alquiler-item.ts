export class ConsultaAlquilerItem {
    id:number;
    videoJuego:any;
    precio:any;
    cantidad:any;
    constructor(
        id:number,
        videoJuego:any,
        precio:any,
        cantidad:any) {

            this.id = id;
            this.videoJuego = videoJuego;
            this.precio = precio;
            this.cantidad = cantidad;
    }
}