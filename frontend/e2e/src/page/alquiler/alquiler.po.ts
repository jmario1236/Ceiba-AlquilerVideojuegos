import { by, element } from 'protractor';

export class AlquilerPage {
    private btnRegistrarAlquiler = element(by.id('btnRegistrarAlquiler'));

    private txtCriterioCliente = element(by.id('criterioCliente'));
    private btnCriterioCliente = element(by.id('btnCriterioCliente'));

    private txtCriterioVideojuego = element(by.id('criterioVideojuego'));
    private btnCriterioVideojuego = element(by.id('btnCriterioVideojuego'));

    private dbxFechaInicial = element(by.id('dbxFechaInicial'));
    private dbxFechaFinal = element(by.id('dbxFechaFinal'));

    private totalMulta = element(by.id('totalMulta'));

    private totalAdicional = element(by.id('totalAdicional'));

    private txtCantidadVideojuego = element(by.id('txtCantidadVideojuego'));
    private btnAgregarCantidadVideojuego = element(by.id('btnAgregarCantidadVideojuego'));

    async clickBotonRegistrarAlquiler() {
        await this.btnRegistrarAlquiler.click();
    }

    async obtenerTotalMulta(){
        let multa = await this.totalMulta.getAttribute('value');
        return parseFloat(multa);
    }

    async obtenerTotalAdicional(){
        let totalAdicional = await this.totalAdicional.getAttribute('value');
        return parseFloat(totalAdicional);
    }

    async obtenerTextoMsgExito(){
        let texto = await element(by.id('msgExito')).getText();
        return texto.split('×')[0].trim();
    }

    async obtenerTextoMsgError(){
        let texto = await element(by.id('msgError')).getText();
        return texto.split('×')[0].trim();
    }

    async clickBotonBuscarCliente() {
        await this.btnCriterioCliente.click();
    }

    async clickBotonBuscarVideojuego() {
        await this.btnCriterioVideojuego.click();
    }

    async clickBotonAgregarCantidad() {
        await this.btnAgregarCantidadVideojuego.click();
    }

    async ingresarCriterioCliente(criterio) {
        await this.txtCriterioCliente.clear();
        await this.txtCriterioCliente.sendKeys(criterio);
    }

    async ingresarCriterioVideojuego(criterio) {
        await this.txtCriterioVideojuego.clear();
        await this.txtCriterioVideojuego.sendKeys(criterio);
    }

    async ingresarFechaAlquiler(fecha) {
        await this.dbxFechaInicial.clear();
        await this.dbxFechaInicial.sendKeys(fecha);
    }

    async ingresarFechaEntregaMaxima(fecha) {
        await this.dbxFechaFinal.clear();
        await this.dbxFechaFinal.sendKeys(fecha);
    }

    async ingresarCantidad(cantidad) {
        await this.txtCantidadVideojuego.clear();
        await this.txtCantidadVideojuego.sendKeys(cantidad);
    }

    
}