import { by, element } from 'protractor';

export class VideojuegoPage {
    private btnIrCrearVideojuego = element(by.id('btnAgregarVideojuego'));
    private btnRegistrarVideoJuego = element(by.id('btnRegistrarVideoJuego'));
    private btnModificarVideoJuego = element(by.id('btnModificarVideoJuego'));
    private txtCodigo = element(by.id('codigoVideojuego'));
    private txtNombre = element(by.id('nombreVideojuego'));
    private txtGenero = element(by.id('generoVideojuego'));
    private txtPrecio = element(by.id('precioVideojuego'));
    private txtStock = element(by.id('stockVideojuego'));

    async clickBotonIrCrearVideojuego() {
        await this.btnIrCrearVideojuego.click();
    }

    async obtenerTextoMsgExito(){
        let texto = await element(by.id('msgExito')).getText();
        return texto.split('×')[0].trim();
    }

    async clickBotonRegistrarVideoJuego() {
        await this.btnRegistrarVideoJuego.click();
    }

    async clickBotonModificarVideoJuego() {
        await this.btnModificarVideoJuego.click();
    }

    async ingresarCodigo(codigo) {
        await this.txtCodigo.clear();
        await this.txtCodigo.sendKeys(codigo);
    }

    async ingresarNombre(nombre) {
        await this.txtNombre.clear();
        await this.txtNombre.sendKeys(nombre);
    }

    async ingresarGenero(genero) {
        await this.txtGenero.clear();
        await this.txtGenero.sendKeys(genero);
    }

    async ingresarPrecio(precio) {
        await this.txtPrecio.clear();
        await this.txtPrecio.sendKeys(precio);
    }

    async ingresarStock(stock) {
        await this.txtStock.clear();
        await this.txtStock.sendKeys(stock);
    }

    async clickModificarLista(identificacion){
        await element(by.id('acciones-'+identificacion)).element(by.id('btn-editar')).click();
    }

    async clickEliminarLista(identificacion){
        await element(by.id('acciones-'+identificacion)).element(by.id('btn-eliminar')).click();
    }

    async numeroFilasVideojuegos(){
        return await element.all(by.className('filaVideojuego')).count();
    }
    
}