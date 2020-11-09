import { AppPage } from '../app.po';
import { AlquilerPage } from '../page/alquiler/alquiler.po';
import { ClientePage } from '../page/cliente/cliente.po';
import { NavbarPage } from "../page/navbar/navbar.po";
import { VideojuegoPage } from '../page/videojuego/videojuego.po';


describe('Pruebas para gestion de los alquileres', () => {
    let navBar: NavbarPage;
    let page: AppPage;
    let clientePage:ClientePage;
    let videojuegoPage:VideojuegoPage
    let alquilerPage: AlquilerPage;

    beforeEach(() => {
        navBar = new NavbarPage();
        page = new AppPage();
        clientePage = new ClientePage();
        videojuegoPage = new VideojuegoPage();
        alquilerPage = new AlquilerPage();
    });

    

    

    it('Deberia validar alquiler con fecha minima', () => {  

        //arrange
        // clientes
        const IDENTIFICACION = '1143354985';
        const NOMBRE = 'Pepito';
        const APELLIDO = 'Del rio';
        const TELEFONO = '6614223';
        const DIRECCION = '6614223';
        page.navigateTo();
        navBar.clickLinkCliente();
        clientePage.clickBotonCrearCliente();
        clientePage.ingresarIdentificacion(IDENTIFICACION);
        clientePage.ingresarNombre(NOMBRE);
        clientePage.ingresarApellido(APELLIDO);
        clientePage.ingresarTelefono(TELEFONO);
        clientePage.ingresarDireccion(DIRECCION);

        clientePage.clickBotonRegistrarCliente();

       //videojuego
       const CODIGO = 'PMN-0002';
       const NOMBREV = 'Mario Bros';
       const GENERO = 'Plataforma';
       const PRECIO = '1000';
       const STOCK = '100';
       page.navigateTo();
       navBar.clickLinkVideoJuego();
       videojuegoPage.clickBotonIrCrearVideojuego();
       videojuegoPage.ingresarCodigo(CODIGO);
       videojuegoPage.ingresarNombre(NOMBREV);
       videojuegoPage.ingresarGenero(GENERO);
       videojuegoPage.ingresarPrecio(PRECIO);
       videojuegoPage.ingresarStock(STOCK);

       videojuegoPage.clickBotonRegistrarVideoJuego();


        navBar.clickLinkAlquiler();
        const criterioCliente = "1143354985";
        const criterioVideoJuego = "PMN-0002";
        const fechaInicio = "2020-11-03";
        const fechaFin = "2020-11-05";
        const cantidad = 2;

        //act
        alquilerPage.ingresarCriterioCliente(criterioCliente);
        alquilerPage.clickBotonBuscarCliente();
        alquilerPage.ingresarFechaAlquiler(fechaInicio);
        alquilerPage.ingresarFechaEntregaMaxima(fechaFin);
        alquilerPage.ingresarCriterioVideojuego(criterioVideoJuego);
        alquilerPage.clickBotonBuscarVideojuego();
        alquilerPage.ingresarCantidad(cantidad);
        alquilerPage.clickBotonAgregarCantidad();
        alquilerPage.clickBotonRegistrarAlquiler();

        //assert
        expect(alquilerPage.obtenerTextoMsgError()).toEqual('Fecha maxima ingresada no cumple con el minimo de dias (3)');

    });

    it('Deberia crear Alquiler', () => {
        

        navBar.clickLinkAlquiler();
        const criterioCliente = "1143354985";
        const criterioVideoJuego = "PMN-0002";
        const fechaInicio = "2020-11-03";
        const fechaFin = "2020-11-06";
        const cantidad = 2;

        //act
        alquilerPage.ingresarCriterioCliente(criterioCliente);
        alquilerPage.clickBotonBuscarCliente();
        alquilerPage.ingresarFechaAlquiler(fechaInicio);
        alquilerPage.ingresarFechaEntregaMaxima(fechaFin);
        alquilerPage.ingresarCriterioVideojuego(criterioVideoJuego);
        alquilerPage.clickBotonBuscarVideojuego();
        alquilerPage.ingresarCantidad(cantidad);
        alquilerPage.clickBotonAgregarCantidad();
        alquilerPage.clickBotonRegistrarAlquiler();

        //assert
        expect(alquilerPage.obtenerTextoMsgExito()).toEqual('Se ha registrado Alquiler');

    });
    

});

