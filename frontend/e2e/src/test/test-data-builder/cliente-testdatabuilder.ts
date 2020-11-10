import { randomString } from "./utils/random-string";
export class ClienteTestDataBuilder {
    identificacion: string;
    nombre: string;
    apellido: string;
    telefono: string;
    direccion: string;
    constructor(){
        this.identificacion = randomString(8,'0123456789');
        this.nombre=  randomString(6,'0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
        this.apellido=  randomString(6,'0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
        this.telefono =  randomString(10,'0123456789');
        this.direccion=  randomString(16,'0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
    }
}