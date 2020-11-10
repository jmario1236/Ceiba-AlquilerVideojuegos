import { AppPage } from '../app.po';
import { browser } from 'protractor';
import { AlquilerPage } from '../page/alquiler/alquiler.po';
import { ClientePage } from '../page/cliente/cliente.po';
import { NavbarPage } from "../page/navbar/navbar.po";
import { VideojuegoPage } from '../page/videojuego/videojuego.po';
import { ClienteTestDataBuilder } from './test-data-builder/cliente-testdatabuilder';
import { VideoJuegoTestDataBuilder } from './test-data-builder/videojuego-testdatabuilder';


describe('Pruebas para gestion de los alquileres', () => {
    let navBar: NavbarPage;
    let page: AppPage;
    let clientePage:ClientePage;
    let videojuegoPage:VideojuegoPage
    let alquilerPage: AlquilerPage;
    let cliente:ClienteTestDataBuilder;
    let videojuego: VideoJuegoTestDataBuilder;

    beforeEach(() => {
        navBar = new NavbarPage();
        page = new AppPage();
        clientePage = new ClientePage();
        videojuegoPage = new VideojuegoPage();
        alquilerPage = new AlquilerPage();

        cliente = new ClienteTestDataBuilder();
        videojuego = new VideoJuegoTestDataBuilder();

        // clientes       
        page.navigateTo();
        navBar.clickLinkCliente();
        clientePage.clickBotonCrearCliente();
        clientePage.ingresarIdentificacion(cliente.identificacion);
        clientePage.ingresarNombre(cliente.nombre);
        clientePage.ingresarApellido(cliente.apellido);
        clientePage.ingresarTelefono(cliente.telefono);
        clientePage.ingresarDireccion(cliente.direccion);
        clientePage.clickBotonRegistrarCliente();

        //videojuego      
        page.navigateTo();
        navBar.clickLinkVideoJuego();
        videojuegoPage.clickBotonIrCrearVideojuego();
        videojuegoPage.ingresarCodigo(videojuego.codigo);
        videojuegoPage.ingresarNombre(videojuego.nombre);
        videojuegoPage.ingresarGenero(videojuego.genero);
        videojuegoPage.ingresarPrecio(videojuego.precio);
        videojuegoPage.ingresarStock(videojuego.stock);
        videojuegoPage.clickBotonRegistrarVideoJuego();


    });

    

    

    it('Deberia validar alquiler con fecha minima', () => {
        //arrange       
        
        const fechaInicio = "2020-11-03";
        const fechaFin = "2020-11-05";
        const cantidad = 2;

        //act
        page.navigateTo();
        navBar.clickLinkAlquiler();
        alquilerPage.ingresarCriterioCliente(cliente.identificacion);
        browser.sleep(500);
        alquilerPage.clickBotonBuscarCliente();
        alquilerPage.ingresarFechaAlquiler(fechaInicio);
        alquilerPage.ingresarFechaEntregaMaxima(fechaFin);
        alquilerPage.ingresarCriterioVideojuego(videojuego.codigo);
        alquilerPage.clickBotonBuscarVideojuego();
        alquilerPage.ingresarCantidad(cantidad);
        alquilerPage.clickBotonAgregarCantidad();
        browser.sleep(1000);
        alquilerPage.clickBotonRegistrarAlquiler();
        browser.sleep(1000);

        //assert
        expect(alquilerPage.obtenerTextoMsgError()).toEqual('Fecha maxima ingresada no cumple con el minimo de dias (3)');

    });


    it('Deberia validar alquiler con fechas erroneas', () => {  

        //arrange      
        const fechaInicio = "2020-11-03";
        const fechaFin = "2020-11-01";
        const cantidad = 2;

        //act
        page.navigateTo();
        navBar.clickLinkAlquiler();
        alquilerPage.ingresarCriterioCliente(cliente.identificacion);
        alquilerPage.clickBotonBuscarCliente();
        alquilerPage.ingresarFechaAlquiler(fechaInicio);
        alquilerPage.ingresarFechaEntregaMaxima(fechaFin);
        alquilerPage.ingresarCriterioVideojuego(videojuego.codigo);
        alquilerPage.clickBotonBuscarVideojuego();
        alquilerPage.ingresarCantidad(cantidad);
        alquilerPage.clickBotonAgregarCantidad();
        alquilerPage.clickBotonRegistrarAlquiler();
        browser.sleep(1000);
        //assert
        expect(alquilerPage.obtenerTextoMsgError()).toEqual('Fechas son invalidas');

    });

    it('Deberia crear Alquiler', () => {
        

        //arrange
        const fechaInicio = "2020-11-03";
        const fechaFin = "2020-11-06";
        const cantidad = 2;

        //act
        page.navigateTo();
        navBar.clickLinkAlquiler();
        alquilerPage.ingresarCriterioCliente(cliente.identificacion);
        alquilerPage.clickBotonBuscarCliente();
        alquilerPage.ingresarFechaAlquiler(fechaInicio);
        alquilerPage.ingresarFechaEntregaMaxima(fechaFin);
        alquilerPage.ingresarCriterioVideojuego(videojuego.codigo);
        alquilerPage.clickBotonBuscarVideojuego();
        alquilerPage.ingresarCantidad(cantidad);
        alquilerPage.clickBotonAgregarCantidad();
        alquilerPage.clickBotonRegistrarAlquiler();
        browser.sleep(1000);
        //assert
        expect(alquilerPage.obtenerTextoMsgExito()).toEqual('Se ha registrado Alquiler');

    });

    it('Deberia Mostrar el Alquiler con generación de multas', () => {
        

        //arrange
        const fechaInicio = "2020-11-03";
        const fechaFin = "2020-11-06";
        const cantidad = 2;

        //act
        page.navigateTo();
        navBar.clickLinkAlquiler();
        alquilerPage.ingresarCriterioCliente(cliente.identificacion);
        alquilerPage.clickBotonBuscarCliente();
        alquilerPage.ingresarFechaAlquiler(fechaInicio);
        alquilerPage.ingresarFechaEntregaMaxima(fechaFin);
        alquilerPage.ingresarCriterioVideojuego(videojuego.codigo);
        alquilerPage.clickBotonBuscarVideojuego();
        alquilerPage.ingresarCantidad(cantidad);
        alquilerPage.clickBotonAgregarCantidad();
        alquilerPage.clickBotonRegistrarAlquiler();
        browser.sleep(1000);

        let total = parseFloat(videojuego.precio)*cantidad;

        let fechaFinal = new Date(fechaFin+" 00:00:00");
        let hoy = new Date();

        let diasPasados = Math.trunc((hoy.getTime() - fechaFinal.getTime()) /(1000*3600*24));

        let porcentajeCobroMulta = total * 0.2;

        let totalMulta = porcentajeCobroMulta * diasPasados;
        browser.sleep(1000);

        //assert
        expect(alquilerPage.obtenerTotalMulta()).toEqual(totalMulta);

    });
    

    it('Deberia Mostrar el Alquiler con generación de dias adicionales', () => {
        

        //arrange
        const fechaInicio = "2020-11-03";
        const fechaFin = "2020-11-10";
        const cantidad = 2;
        const DIAS_ALQUILER = 3;
        const PORCENTAJE_ADICIONAL = 0.1;

        //act
        page.navigateTo();
        navBar.clickLinkAlquiler();
        alquilerPage.ingresarCriterioCliente(cliente.identificacion);
        alquilerPage.clickBotonBuscarCliente();
        alquilerPage.ingresarFechaAlquiler(fechaInicio);
        alquilerPage.ingresarFechaEntregaMaxima(fechaFin);
        alquilerPage.ingresarCriterioVideojuego(videojuego.codigo);
        alquilerPage.clickBotonBuscarVideojuego();
        alquilerPage.ingresarCantidad(cantidad);
        alquilerPage.clickBotonAgregarCantidad();
        alquilerPage.clickBotonRegistrarAlquiler();
        browser.sleep(1000);

        let total = parseFloat(videojuego.precio)*cantidad;

        let fechaFinal = new Date(fechaFin+" 00:00:00");
        let fechaInicial = new Date(fechaInicio+" 00:00:00");

        let diasAdicionales = Math.trunc((fechaFinal.getTime()-fechaInicial.getTime()) /(1000*3600*24)) - DIAS_ALQUILER;

        let porcentajeCobroAdicional = total * PORCENTAJE_ADICIONAL;

        let totalAdicional = porcentajeCobroAdicional * diasAdicionales;
        browser.sleep(1000);

        //assert
        expect(alquilerPage.obtenerTotalAdicional()).toEqual(totalAdicional);

    });

});

