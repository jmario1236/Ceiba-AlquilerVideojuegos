import { by, element } from 'protractor';

export class NavbarPage {
    linkHome = element(by.xpath('/html/body/app-root/app-navbar/nav/a[1]'));
    linkCliente = element(by.xpath('/html/body/app-root/app-navbar/nav/a[2]'));
    linkVideoJuego = element(by.xpath('/html/body/app-root/app-navbar/nav/a[3]'));
    linkAlquiler = element(by.xpath('/html/body/app-root/app-navbar/nav/a[4]'));

    async clickLinkCliente() {
        await this.linkCliente.click();
    }

    async clickLinkVideoJuego() {
        await this.linkVideoJuego.click();
    }

    async clickLinkAlquiler() {
        await this.linkAlquiler.click();
    }

}
