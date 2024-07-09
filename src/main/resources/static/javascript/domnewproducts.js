const IGV = 0.18; // Impuesto General a las Ventas

$(document).ready(function () {
  let urlProducto = "http://ec2-13-59-233-23.us-east-2.compute.amazonaws.com:8090/api/productos";

  // Obtener el contenedor donde se agregarán las tarjetas
  var newproductContainer = $("#newproduct-container");
  var newproductContainer2 = $("#newproduct-container2");

  let cart = JSON.parse(localStorage.getItem('cart')) || []; // Array to store cart items, initialize from localStorage if available

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
      for (let i = 0; i < 5; i++) {
        const index = data[i];
        const index2 = data[i + 5];
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
                  <a class="btn btn-success product-text addToCartButton" data-product='${JSON.stringify(index)}'>Comprar</a>
                </div>
              </div>
            </div>
          </div>
        `;
        var card2Html = `
          <div class="card col-md-3 col-6 mx-1 my-3 mx-1" style="width: 15rem"> <!-- Cambiar col-md-2 a col-md-3 para mostrar 4 elementos por fila -->
            <a href="${index2.categoria}s/producto?id=${index2.partNumber}"><img src="${index2.url}" class="d-block w-100 card-img-top" alt="${index2.name}" style="height: 150px;"></a>
            <h6 class="card-title mt-3">${index2.nombreProducto}</h6>
            <div class="card-body d-flex align-items-end justify-content-center">
              <div class="d-flex justify-content-between">
                <div class="d-flex flex-column">
                  <h6 class="text-danger font-weight-bold">S/${index2.precio}</h6>
                  <span>Stock: ${index2.stock}</span>
                  <span>Marca: ${index2.marca}</span>
                </div>
                <div class="d-flex align-items-center mx-2">
                  <a class="btn btn-success product-text addToCartButton" data-product='${JSON.stringify(index2)}'>Comprar</a>
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

      // Panel lateral
      $("body").append(`
        <div id="cart-sidebar" class="offcanvas offcanvas-end" tabindex="-1" aria-labelledby="cart-sidebar-label">
          <div class="offcanvas-header bg-success">
            <h5 id="cart-sidebar-label" style="color: white;">Carrito de Compras</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
          </div>
          <div class="offcanvas-body">
            <div id="cart-product-details">
              <!-- Aquí se insertarán los detalles del producto -->
            </div>
            <hr style="border-top: 1px solid green;">
            <div id="cart-summary" class="mt-3 text-center">
              <p id="subtotal">Subtotal: S/0.00</p>
              <p id="total" style="color: green;">Total + Impuestos (IGV 18%): S/0.00</p>
              <button id="checkoutButton" class="btn btn-success mt-3">Tramitar Pedido</button>
            </div>
          </div>
        </div>
      `);

      const updateCartSummary = () => {
        let subtotal = 0;
        cart.forEach((item) => {
          subtotal += item.precio * item.quantity;
        });
        const total = subtotal * (1 + IGV);
        $("#subtotal").text(`Subtotal: S/${subtotal.toFixed(2)}`);
        $("#total").text(`Total + Impuestos (IGV 18%): S/${total.toFixed(2)}`);
        $("#carrito").text(`$ ${total.toFixed(2)}`);

      };
  // Event listener para el botón de carrito en la barra de navegación
  $("#cartButton").on("click", function () {
    let cartSidebar = new bootstrap.Offcanvas($("#cart-sidebar"));
    cartSidebar.show();
  });
      const renderCartItems = () => {
        $("#cart-product-details").empty();
        cart.forEach((item, index) => {
          $("#cart-product-details").append(`
            <div class="cart-item" data-index="${index}">
              <div class="product-image d-flex align-content-between justify-content-center">
                <img src="${item.url}" alt="${item.nombreProducto}" class="d-block" style="height: 50px;">
                <h6 class="card-title mt-3">${item.nombreProducto}</h6>
              </div>
              <br />
              <div class="quantity-control d-flex align-items-center justify-content-center">
                <button class="btn btn-outline-secondary decrementButton">-</button>
                <div class="col-auto px-0">
                  <input type="text" class="form-control text-center" value="${item.quantity}" readonly style="width: 60px;">
                </div>
                <button class="btn btn-outline-secondary incrementButton">+</button>
                <button class="btn btn-danger remove-from-cart mx-1"><i class="fa-solid fa-trash"></i></button>
              </div>
              <hr style="border-top: 1px solid green;">
              <div class="product-price mt-3 d-flex align-items-center justify-content-center">
                <h6 class="text text-danger mx-1">S/${item.precio}</h6>
                <h6 class="text">x ${item.quantity}</h6>
              </div>
            </div>
          `);
        });

        // Attach event listeners to remove buttons
        $(".remove-from-cart").on("click", function () {
          const itemIndex = $(this).closest(".cart-item").data("index");
          cart.splice(itemIndex, 1);
          renderCartItems();
          updateCartSummary();
          saveCartToLocalStorage(); // Save updated cart to localStorage
        });

        // Attach event listeners to increment buttons
        $(".incrementButton").on("click", function () {
          const itemIndex = $(this).closest(".cart-item").data("index");
          cart[itemIndex].quantity += 1;
          renderCartItems();
          updateCartSummary();
          saveCartToLocalStorage(); // Save updated cart to localStorage
        });

        // Attach event listeners to decrement buttons
        $(".decrementButton").on("click", function () {
          const itemIndex = $(this).closest(".cart-item").data("index");
          if (cart[itemIndex].quantity > 1) {
            cart[itemIndex].quantity -= 1;
          } else {
            cart.splice(itemIndex, 1); // Remove item if quantity is 1 and decrement button is pressed
          }
          renderCartItems();
          updateCartSummary();
          saveCartToLocalStorage(); // Save updated cart to localStorage
        });
      };

      // Event listener para el botón "Agregar al Carrito"
      $(".addToCartButton").on("click", function () {
        const product = $(this).data("product");

        // Check if product already in cart
        const existingProduct = cart.find(
          (item) => item.partNumber === product.partNumber
        );
        if (existingProduct) {
          existingProduct.quantity += 1;
        } else {
          product.quantity = 1;
          cart.push(product);
        }

        // Render cart items
        renderCartItems();

        // Actualizar el resumen del carrito
        updateCartSummary();

        // Mostrar el panel lateral
        let cartSidebar = new bootstrap.Offcanvas($("#cart-sidebar"));
        cartSidebar.show();

        saveCartToLocalStorage(); // Save updated cart to localStorage
      });

      // Función para guardar el carrito en localStorage
      const saveCartToLocalStorage = () => {
        localStorage.setItem('cart', JSON.stringify(cart));
      };

      // Render inicial del carrito al cargar la página
      renderCartItems();
      updateCartSummary();
    } catch (error) {
      console.log(error);
    }
  };
});
