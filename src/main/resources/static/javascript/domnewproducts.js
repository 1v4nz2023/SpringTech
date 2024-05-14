$(document).ready(function () {
  let urlProducto = "http://localhost:8090/api/productos";

  // Obtener el contenedor donde se agregarán las tarjetas
  var newproductContainer = $("#newproduct-container");
  var newproductContainer2 = $("#newproduct-container2");

  const getproductos = async (url) => {
    try {
      const response = await fetch(url);
      const results = await response.json();
      console.log(results);
      Dataproductos(results);

    } catch (error) {
      console.error(error);
    }
  };

  getproductos(urlProducto);

  const Dataproductos = async (data) => {
    try {

        for(let i=0; i<5 ;i++){
            const index = data[i];
            const index2 = data[i+5];
            console.log(index);
        // Plantilla de cadena con el HTML de la tarjeta
        var cardHtml = `
        <div class="card col-md-3 col-6 mx-1 my-3 mx-1" style="width: 15rem"> <!-- Cambiar col-md-2 a col-md-3 para mostrar 4 elementos por fila -->
          <a href="${index.categoria}s/producto?id=${index.partNumber}"><img src="${index.url}" class="d-block w-100 card-img-top" alt="${index.name}" style="height: 150px;"></a>
          <h6 class="card-title mt-3">${index.nombreProducto}</h6>

          <div class="card-body d-flex align-items-end justify-content-center">
              <div class="d-flex justify-content-between">
                  <div class="d-flex flex-column">
                      <h6 class="text-danger font-weight-bold">S/${index.precio}</h6>
                      <span>Stock: ${index.stock}</span>
                      <span>Marca: ${index.marca}</span>
                  </div>
                  <div class="d-flex align-items-center mx-2">
                      <a href="#" class="btn btn-success product-text">Comprar</a>
                  </div>
              </div>
          </div>
      </div>
      `;
        var card2Html = `
        <div class="card col-md-3 col-6 mx-1 my-3 mx-1" style="width: 15rem"> <!-- Cambiar col-md-2 a col-md-3 para mostrar 4 elementos por fila -->
          <a href="computadoras/producto?id=${index2.partNumber}"><img src="${index2.url}" class="d-block w-100 card-img-top" alt="${index2.name}" style="height: 150px;"></a>
          <h6 class="card-title mt-3">${index2.nombreProducto}</h6>

          <div class="card-body d-flex align-items-end justify-content-center">
              <div class="d-flex justify-content-between">
                  <div class="d-flex flex-column">
                      <h6 class="text-danger font-weight-bold">S/${index2.precio}</h6>
                      <span>Stock: ${index2.stock}</span>
                      <span>Marca: ${index2.marca}</span>
                  </div>
                  <div class="d-flex align-items-center mx-2">
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
        newproductContainer.append($card);
        newproductContainer2.append($card2);

        }




   

      
    } catch (error) {
      console.log(error);
    }
  };
});
