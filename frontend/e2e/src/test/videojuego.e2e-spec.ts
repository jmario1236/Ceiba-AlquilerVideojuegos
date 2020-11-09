import { AppPage } from '../app.po';
import { NavbarPage } from "../page/navbar/navbar.po";
import { VideojuegoPage } from '../page/videojuego/videojuego.po';


describe('Pruebas para gestion del videojuego', () => {
    let navBar: NavbarPage;
    let page: AppPage;
    let videojuegoPage: VideojuegoPage;

    beforeEach(() => {
        navBar = new NavbarPage();
        page = new AppPage();
        videojuegoPage = new VideojuegoPage();
    });

    it('Deberia crear Videojuego', () => {
        //arrange
        const CODIGO = 'PMN-0001';
        const NOMBRE = 'Mario Bros';
        const GENERO = 'Plataforma';
        const PRECIO = '1000';
        const STOCK = '100';
        //act
        page.navigateTo();
        navBar.clickLinkVideoJuego();
        videojuegoPage.clickBotonIrCrearVideojuego();
        videojuegoPage.ingresarCodigo(CODIGO);
        videojuegoPage.ingresarNombre(NOMBRE);
        videojuegoPage.ingresarGenero(GENERO);
        videojuegoPage.ingresarPrecio(PRECIO);
        videojuegoPage.ingresarStock(STOCK);

        videojuegoPage.clickBotonRegistrarVideoJuego();

        //assert
        expect(videojuegoPage.obtenerTextoMsgExito()).toEqual('Se ha registrado videojuego');
    });


    it('Deberia modificar cliente', () => {
        //arrange
        const CODIGO = 'PMN-0001';
        const NOMBRE = 'Mario Bros 2';
        const GENERO = 'Plataforma';
        const PRECIO = '12000';
        const STOCK = '100';
        //act
        page.navigateTo();
        navBar.clickLinkVideoJuego();
        videojuegoPage.clickModificarLista(CODIGO);
        videojuegoPage.ingresarCodigo(CODIGO);
        videojuegoPage.ingresarNombre(NOMBRE);
        videojuegoPage.ingresarGenero(GENERO);
        videojuegoPage.ingresarPrecio(PRECIO);
        videojuegoPage.ingresarStock(STOCK);

        videojuegoPage.clickBotonModificarVideoJuego();

        //assert
        expect(videojuegoPage.obtenerTextoMsgExito()).toEqual('Se ha actualizado la información del videojuego');
    });

});