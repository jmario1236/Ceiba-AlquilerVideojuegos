import { AppPage } from '../app.po';
import { NavbarPage } from "../page/navbar/navbar.po";
import { VideojuegoPage } from '../page/videojuego/videojuego.po';
import { VideoJuegoTestDataBuilder } from './test-data-builder/videojuego-testdatabuilder';


describe('Pruebas para gestion del videojuego', () => {
    let navBar: NavbarPage;
    let page: AppPage;
    let videojuegoPage: VideojuegoPage;
    let videoJuego: VideoJuegoTestDataBuilder;

    beforeAll(() => {
        videoJuego = new VideoJuegoTestDataBuilder();
    })

    beforeEach(() => {
        navBar = new NavbarPage();
        page = new AppPage();
        videojuegoPage = new VideojuegoPage();
    });


    it('Deberia crear Videojuego', () => {
        //arrange
        
        //act
        page.navigateTo();
        navBar.clickLinkVideoJuego();
        videojuegoPage.clickBotonIrCrearVideojuego();
        videojuegoPage.ingresarCodigo(videoJuego.codigo);
        videojuegoPage.ingresarNombre(videoJuego.nombre);
        videojuegoPage.ingresarGenero(videoJuego.genero);
        videojuegoPage.ingresarPrecio(videoJuego.precio);
        videojuegoPage.ingresarStock(videoJuego.stock);

        videojuegoPage.clickBotonRegistrarVideoJuego();

        //assert
        expect(videojuegoPage.obtenerTextoMsgExito()).toEqual('Se ha registrado videojuego');
    });


    it('Deberia modificar cliente', () => {
        //arrange
       
        //act
        page.navigateTo();
        navBar.clickLinkVideoJuego();
        videojuegoPage.clickModificarLista(videoJuego.codigo);
        videojuegoPage.ingresarCodigo(videoJuego.codigo);
        videojuegoPage.ingresarNombre(videoJuego.nombre);
        videojuegoPage.ingresarGenero(videoJuego.genero);
        videojuegoPage.ingresarPrecio(videoJuego.precio);
        videojuegoPage.ingresarStock(videoJuego.stock);

        videojuegoPage.clickBotonModificarVideoJuego();

        //assert
        expect(videojuegoPage.obtenerTextoMsgExito()).toEqual('Se ha actualizado la información del videojuego');
    });

    it('Deberia Eliminar un Videojuego', async () => {
        //arrange
       
        //act
        page.navigateTo();
        navBar.clickLinkVideoJuego();
        let tamano = await videojuegoPage.numeroFilasVideojuegos();
        videojuegoPage.clickEliminarLista(videoJuego.codigo);
        let tamanoActual =  await videojuegoPage.numeroFilasVideojuegos();
        //assert
        expect(tamanoActual).toEqual(tamano -1);
    });

});