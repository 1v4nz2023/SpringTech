
$(document).ready(function() {

    // Obtener el contenedor donde se agregarán las tarjetas
    var impresoraContainer = $('#impresora-container');
    var impresoraContainer2 = $('#impresora-container2');

    // Crear 4 tarjetas con ids distintos
    for (var i = 1; i <= 1; i++) {
        // Plantilla de cadena con el HTML de la tarjeta
        var cardHtml = `
            <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card1">
                <a href=""><img src="https://www.infotec.com.pe/45765-home_default/impresora-laser-brother-hl-1202-imprime.jpg" class="d-block w-100 card-img-top" alt="Producto 1"></a>
                <div class="card-body">
                    <h6 class="card-title">IMPRESORA LASER BROTHER</h6>
                    <h7 class="card-title">(HL-1202)</h7>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-column product-text">
                            <span >S/.  305</span>
                            <span>Stock</span>
                            <span>Referencia</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <a href="#" class="btn btn-success product-text">Comprar</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card2">
                <a href=""><img src="https://www.infotec.com.pe/48976-home_default/impresora-multifuncional-laser-brother-dcp-1602.jpg" class="d-block w-100 card-img-top" alt="Producto ${i}"></a>
                <div class="card-body">
                    <h6 class="card-title">IMPRESORA LASER BROTHER</h6>
                    <h7 class="card-title">(DCP-1602)</h7>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-column product-text">
                            <span >S/.  515</span>
                            <span>Stock</span>
                            <span>Referencia</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <a href="#" class="btn btn-success product-text">Comprar</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card3">
                <a href=""><img src="https://www.infotec.com.pe/81919-home_default/impresora-multifuncional-hp-smar-tank-580-wifi-1f3y2a-aky.jpg" class="d-block w-100 card-img-top" alt="Producto ${i}"></a>
                <div class="card-body">
                    <h6 class="card-title">IMPRESORA HP</h6>
                    <h7 class="card-title">(SMAR TANK 580 WIFI 1F3Y2A-AKY)</h7>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-column product-text">
                            <span >S/.  569</span>
                            <span>Stock</span>
                            <span>Referencia</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <a href="#" class="btn btn-success product-text">Comprar</a>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card4">
                <a href=""><img src="https://www.infotec.com.pe/77564-home_default/impresora-epson-ecotank-l1250-c11cj71303-wifi-usb.jpg" class="d-block w-100 card-img-top" alt="Producto ${i}"></a>
                <div class="card-body">
                    <h6 class="card-title">IMPRESORA HP</h6>
                    <h7 class="card-title">(SMAR TANK 580 WIFI 1F3Y2A-AKY)</h7>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-column product-text">
                            <span >S/.  549</span>
                            <span>Stock</span>
                            <span>Referencia</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <a href="#" class="btn btn-success product-text">Comprar</a>
                        </div>
                    </div>
                </div>
            </div>
        `;}
    
    for (var x = 1; x <= 1; x++) {
        var card2Html = `
            <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card5">
                <a href=""><img src="https://www.infotec.com.pe/84140-home_default/impresora-laser-hp-laserjet-pro-3003dw-35pm-1200dpi-3g654a.jpg" class="d-block w-100 card-img-top" alt="Producto 1"></a>
                <div class="card-body">
                    <h6 class="card-title">IMPRESORA LASER HP</h6>
                    <h7 class="card-title">(LASERJET PRO 3003DW 35PM 1200DPI 3G654A)</h7>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-column product-text">
                            <span >S/.  809</span>
                            <span>Stock</span>
                            <span>Referencia</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <a href="#" class="btn btn-success product-text">Comprar</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card6">
                <a href=""><img src="https://www.infotec.com.pe/67000-home_default/impresora-hp-smart-tank-720-6uu46a-aky-multifuncional-wifi.jpg" class="d-block w-100 card-img-top" alt="Producto 1"></a>
                <div class="card-body">
                    <h6 class="card-title">IMPRESORA HP </h6>
                    <h7 class="card-title">(SMART TANK 720 6UU46A-AKY MULTIFUNCIONAL)</h7>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-column product-text">
                            <span >S/.  869</span>
                            <span>Stock</span>
                            <span>Referencia</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <a href="#" class="btn btn-success product-text">Comprar</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card7">
                <a href=""><img src="https://www.infotec.com.pe/52947-home_default/impresora-multifuncional-epson-l4260-wifi-ecotank-imprime-escanea-copia.jpg" class="d-block w-100 card-img-top" alt="Producto 1"></a>
                <div class="card-body">
                    <h6 class="card-title">IMPRESORA EPSON</h6>
                    <h7 class="card-title">(L4260 WIFI  ECOTANK | IMPRIME | ESCANEA | COPIA)</h7>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-column product-text">
                            <span >S/.  879</span>
                            <span>Stock</span>
                            <span>Referencia</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <a href="#" class="btn btn-success product-text">Comprar</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card8">
                <a href=""><img src="https://www.infotec.com.pe/53707-home_default/impresora-multifuncional-brother-dcp-t820dw-wifi-duplex.jpg" class="d-block w-100 card-img-top" alt="Producto 1"></a>
                <div class="card-body">
                    <h6 class="card-title">IMPRESORA BROTHER</h6>
                    <h7 class="card-title">(DCP-T820DW WIFI /DUPLEX)</h7>
                    <br></br>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-column product-text">
                            <span >S/.  979</span>
                            <span>Stock</span>
                            <span>Referencia</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <a href="#" class="btn btn-success product-text">Comprar</a>
                        </div>
                    </div>
                </div>
            </div>
    `;

        // Convertir la cadena HTML en un objeto jQuery
        var $card = $(cardHtml);
        var $card2 = $(card2Html);

        // Agregar la tarjeta al contenedor
        impresoraContainer.append($card);
        impresoraContainer2.append($card2);

    }
});
