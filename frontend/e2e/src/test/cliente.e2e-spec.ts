import { AppPage } from '../app.po';
import { ClientePage } from '../page/cliente/cliente.po';
import { NavbarPage } from "../page/navbar/navbar.po";
import { ClienteTestDataBuilder } from './test-data-builder/cliente-testdatabuilder';


describe('Pruebas para gestion del clientes', () => {
    let navBar: NavbarPage;
    let page: AppPage;
    let clientePage: ClientePage;
    let cliente: ClienteTestDataBuilder;

    beforeAll(() => {
        cliente = new ClienteTestDataBuilder();
    })

    beforeEach(() => {
        navBar = new NavbarPage();
        page = new AppPage();
        clientePage = new ClientePage();
    });

    it('Deberia crear cliente', () => {
        //arrange
    
        //act
        page.navigateTo();
        navBar.clickLinkCliente();
        clientePage.clickBotonCrearCliente();
        clientePage.ingresarIdentificacion(cliente.identificacion);
        clientePage.ingresarNombre(cliente.nombre);
        clientePage.ingresarApellido(cliente.apellido);
        clientePage.ingresarTelefono(cliente.telefono);
        clientePage.ingresarDireccion(cliente.direccion);

        clientePage.clickBotonRegistrarCliente();

        //assert
        expect(clientePage.obtenerTextoMsgExito()).toEqual('Se ha registrado cliente');
    });


    it('Deberia modificar cliente', () => {
        //arrange
        
        //act
        page.navigateTo();
        navBar.clickLinkCliente();
        clientePage.clickModificarLista(cliente.identificacion);
        clientePage.ingresarIdentificacion(cliente.identificacion);
        clientePage.ingresarNombre(cliente.nombre);
        clientePage.ingresarApellido(cliente.apellido);
        clientePage.ingresarTelefono(cliente.telefono);
        clientePage.ingresarDireccion(cliente.direccion);

        clientePage.clickBotonModificarCliente();

        //assert
        expect(clientePage.obtenerTextoMsgExito()).toEqual('Se ha actualizado la información del cliente');
    });

    it('Deberia Eliminar un cliente', async () => {
        //arrange
       
        //act
        page.navigateTo();
        navBar.clickLinkCliente();
        let tamano = await clientePage.numeroFilasClientes();
        clientePage.clickEliminarLista(cliente.identificacion);
        let tamanoActual =  await clientePage.numeroFilasClientes();
        //assert
        expect(tamanoActual).toEqual(tamano -1);
    });

});