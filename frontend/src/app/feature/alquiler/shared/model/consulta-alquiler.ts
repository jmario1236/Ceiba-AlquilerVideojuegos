import { ConsultaAlquilerItem } from "./consulta-alquiler-item";
export class ConsultaAlquiler {
    id: number;
    fechaAlquiler: Date;
    fechaMaximaEntrega: Date;
    fechaEntrega: Date;
    estado: string;
    total: number;
    subtotal:number;
    totalAdiccional:number;
    totalMulta:number;
    cliente:any;
    items:Array<ConsultaAlquilerItem>;

    constructor(
        id: number,
        fechaAlquiler: Date,
        fechaMaximaEntrega: Date,
        fechaEntrega: Date,
        estado: string,
        total: number,
        subtotal:number,
        totalAdicional:number,
        totalMulta:number,
        cliente:any,
        items:Array<ConsultaAlquilerItem>
    ) {
        this.id = id;
        this.fechaMaximaEntrega = fechaMaximaEntrega;
        this.fechaEntrega = fechaEntrega;
        this.fechaAlquiler = fechaAlquiler;
        this.estado = estado;
        this.total = total;
        this.subtotal = subtotal;
        this.totalAdiccional = totalAdicional;
        this.totalMulta = totalMulta;
        this.cliente = cliente;
        this.items = items;
    }
}