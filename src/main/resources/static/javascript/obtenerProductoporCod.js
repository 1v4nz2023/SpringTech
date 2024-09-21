import { urlServer } from "./url.js";
$(document).ready(function() {
    let product = $('#product-section');
    let counter = 1; // Inicializar el contador
    const IGV = 0.18; // Impuesto General a las Ventas
    let cart = JSON.parse(localStorage.getItem('cart')) || []; 
  
    const obtenerProductoPorPartNumber = async () => {
      try {
        // Tu código existente para obtener el producto...
        const url = window.location.href;
        const partNumber = obtenerValorDespuesDelIgual(url);
        const response = await fetch(`${urlServer}/api/productos/${partNumber}`);
        if (!response.ok) {
          throw new Error(`No se pudo obtener el producto con número de parte ${partNumber}`);
        }
        const producto = await response.json();
  
        // Template HTML para el producto...
        let innerHtml = `
          <div class="container my-5">
            <div class="card">
              <div class="card-body">
                <div class="row">
                  <div class="col-md-6">
                    <!-- Product Image -->
                    <div class="product-image">
                      <img id="productImage" src="${producto.url}" alt="${producto.nombreProducto}" class="img-thumbnail">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <!-- Product Details -->
                    <div class="product-details">
                      <h3 id="productName" class="card-title">${producto.nombreProducto}</h3>
                      <div class="product-info">
                        <p id="productBrand">Marca: ${producto.marca}</p>
                        <p id="productCategory">Categoría: ${producto.categoria}</p>
                        <p id="productStock">Stock: ${producto.stock}</p>
                        <p id="productPartNumber">Número de Parte: ${producto.partNumber}</p>
                      </div>
                      <div class="product-price mt-3">
                        <h4 id="productPrice">Precio del Producto: S/${producto.precio}</h4>
                        <div class="d-flex align-items-center">
                          <!-- Fin del contador de productos -->
                          <!-- Botón "Agregar al Carrito" -->
                          <button id="addToCartButton" class="btn btn-primary ms-3" data-product='${JSON.stringify(producto)}'>Agregar al Carrito</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- Product Description -->
                <div class="card mt-3">
                  <div class="card-body">
                    <h3 class="card-title">Descripción del Producto</h3>
                    <p id="productDescription">${producto.descripcion}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        `;
  
        // Añadir el HTML al DOM
        let $inner = $(innerHtml);
        product.append($inner);
  
        // Event listener para el botón de decremento
        $('#decrementButton').on('click', function() {
          if (counter > 1) { // Asegurar que el contador no sea menor que 1
            counter--;
            $('#counter').text(counter);
          }
        });
  
        // Event listener para el botón de incremento
        $('#incrementButton').on('click', function() {
          counter++;
          $('#counter').text(counter);
        });
  
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
                    <input type="text" class="form-control text-center quantity-input" value="${item.quantity}" readonly>
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
        $(document).on("click", "#addToCartButton", function () {
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
        console.error(error);
      }       
    };
  
    // Función para obtener el valor después del igual en una URL
    const obtenerValorDespuesDelIgual = (url) => {
      const igualIndex = url.indexOf('=');
      if (igualIndex !== -1) {
        return url.substring(igualIndex + 1);
      }
      return null;
    };
    
    // Llamada a la función para obtener información sobre el producto con número de parte específico
    obtenerProductoPorPartNumber();
  });
  