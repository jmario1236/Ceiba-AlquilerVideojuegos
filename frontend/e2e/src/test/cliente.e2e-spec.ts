import { AppPage } from '../app.po';
import { ClientePage } from '../page/cliente/cliente.po';
import { NavbarPage } from "../page/navbar/navbar.po";


describe('Pruebas para gestion del clientes', () => {
    let navBar: NavbarPage;
    let page: AppPage;
    let clientePage: ClientePage;

    beforeEach(() => {
        navBar = new NavbarPage();
        page = new AppPage();
        clientePage = new ClientePage();
    });

    it('Deberia crear cliente', () => {
        //arrange
        const IDENTIFICACION = '1143354982';
        const NOMBRE = 'Julio';
        const APELLIDO = 'Del rio';
        const TELEFONO = '6614223';
        const DIRECCION = '6614223';
        //act
        page.navigateTo();
        navBar.clickLinkCliente();
        clientePage.clickBotonCrearCliente();
        clientePage.ingresarIdentificacion(IDENTIFICACION);
        clientePage.ingresarNombre(NOMBRE);
        clientePage.ingresarApellido(APELLIDO);
        clientePage.ingresarTelefono(TELEFONO);
        clientePage.ingresarDireccion(DIRECCION);

        clientePage.clickBotonRegistrarCliente();

        //assert
        expect(clientePage.obtenerTextoMsgExito()).toEqual('Se ha registrado cliente');
    });


    it('Deberia modificar cliente', () => {
        //arrange
        const IDENTIFICACION = '1143354982';
        const NOMBRE = 'Mario';
        const APELLIDO = 'Contreras';
        const TELEFONO = '6614223';
        const DIRECCION = '6614223';
        //act
        page.navigateTo();
        navBar.clickLinkCliente();
        clientePage.clickModificarLista(IDENTIFICACION);
        clientePage.ingresarIdentificacion(IDENTIFICACION);
        clientePage.ingresarNombre(NOMBRE);
        clientePage.ingresarApellido(APELLIDO);
        clientePage.ingresarTelefono(TELEFONO);
        clientePage.ingresarDireccion(DIRECCION);

        clientePage.clickBotonModificarCliente();

        //assert
        expect(clientePage.obtenerTextoMsgExito()).toEqual('Se ha actualizado la información del cliente');
    });

});