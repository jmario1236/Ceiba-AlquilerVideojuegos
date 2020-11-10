import { randomString } from "./utils/random-string";
export class VideoJuegoTestDataBuilder {
    codigo: string;
    nombre: string;
    genero: string;
    precio: string;
    stock: string;
    constructor(){
        this.codigo = randomString(6,'0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
        this.nombre=  randomString(6,'0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
        this.genero=  randomString(6,'0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
        this.precio =  randomString(4,'123456789');
        this.stock = randomString(2,'123456789');
    }
}