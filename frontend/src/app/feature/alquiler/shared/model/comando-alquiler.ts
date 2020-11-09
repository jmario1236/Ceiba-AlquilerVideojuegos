
import {ComandoAlquilerItem} from './comando-alquiler-item';

export class ComandoAlquiler {
    id:number;
    fechaAlquiler:string;
    fechaMaximaEntrega:string;
    cliente:number;
    items:Array<ComandoAlquilerItem>;
    constructor(
        id:number,
        fechaAlquiler:string,
        fechaMaximaEntrega:string,
        cliente:number,
        items:Array<ComandoAlquilerItem>
        ) {
        
        this.id = id;
        this.fechaAlquiler= fechaAlquiler;
        this.fechaMaximaEntrega= fechaMaximaEntrega;
        this.cliente = cliente;
        this.items = items;        
    }
}