
$(document).ready(function() {

    // Obtener el contenedor donde se agregarán las tarjetas
    var laptopContainer = $('#laptop-container');
    var laptopContainer2 = $('#laptop-container2');

    // Crear 5 tarjetas con ids distintos
    for (var i = 1; i <= 5; i++) {
        // Plantilla de cadena con el HTML de la tarjeta
        var cardHtml = `
            <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card${i}">
                <a href=""><img src="https://oechsle.vteximg.com.br/arquivos/ids/13560071-1000-1000/2156312-01.jpg?v=638100832783900000" class="d-block w-100 card-img-top" alt="Producto ${i}"></a>
                <div class="card-body">
                    <h6 class="card-title">Product ${i}</h6>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-column product-text">
                            <span >Precio</span>
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
        var card2Html = `
        <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card${i+5}">
            <a href=""><img src="https://oechsle.vteximg.com.br/arquivos/ids/13560071-1000-1000/2156312-01.jpg?v=638100832783900000" class="d-block w-100 card-img-top" alt="Producto ${i+5}"></a>
            <div class="card-body">
                <h6 class="card-title">Product ${i+5}</h6>
                <div class="d-flex justify-content-between">
                    <div class="d-flex flex-column product-text">
                        <span >Precio</span>
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
        laptopContainer.append($card);
        laptopContainer2.append($card2);

    }
});
