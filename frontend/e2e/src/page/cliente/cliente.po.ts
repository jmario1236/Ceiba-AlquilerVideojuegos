import { by, element } from 'protractor';

export class ClientePage {
    private btnIrCrearCliente = element(by.id('btnAgregarCliente'));
    private btnRegistrarCliente = element(by.id('btnRegistrarCliente'));
    private btnModificarCliente = element(by.id('btnModificarCliente'));
    private txtIdentificacion = element(by.id('identificacionCliente'));
    private txtNombre = element(by.id('nombreCliente'));
    private txtApellido = element(by.id('apellidoCliente'));
    private txtTelefono = element(by.id('telefonoCliente'));
    private txtDireccion = element(by.id('direccionCliente'));

    async clickBotonCrearCliente() {
        await this.btnIrCrearCliente.click();
    }

    async obtenerTextoMsgExito(){
        let texto = await element(by.id('msgExito')).getText();
        return texto.split('×')[0].trim();
    }

    async clickBotonRegistrarCliente() {
        await this.btnRegistrarCliente.click();
    }

    async clickBotonModificarCliente() {
        await this.btnModificarCliente.click();
    }

    async ingresarIdentificacion(identificacion) {
        await this.txtIdentificacion.clear();
        await this.txtIdentificacion.sendKeys(identificacion);
    }

    async ingresarNombre(nombre) {
        await this.txtNombre.clear();
        await this.txtNombre.sendKeys(nombre);
    }

    async ingresarApellido(apellido) {
        await this.txtApellido.clear();
        await this.txtApellido.sendKeys(apellido);
    }

    async ingresarTelefono(telefono) {
        await this.txtTelefono.clear();
        await this.txtTelefono.sendKeys(telefono);
    }

    async ingresarDireccion(direccion) {
        await this.txtDireccion.clear();
        await this.txtDireccion.sendKeys(direccion);
    }

    async clickModificarLista(identificacion){
        await element(by.id('acciones-'+identificacion)).element(by.id('btn-editar')).click();
    }
    
    async clickEliminarLista(identificacion){
        await element(by.id('acciones-'+identificacion)).element(by.id('btn-eliminar')).click();
    }

    async numeroFilasClientes(){
        return await element.all(by.className('filaClientes')).count();
    }
}