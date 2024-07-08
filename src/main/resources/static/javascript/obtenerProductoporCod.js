$(document).ready(function() {
    let product = $('#product-section');
    let counter = 1; // Inicializar el contador
    const IGV = 0.18; // Impuesto General a las Ventas

    const obtenerProductoPorPartNumber = async () => {
        try {
            // Tu código existente para obtener el producto...
            const url = window.location.href;
            const partNumber = obtenerValorDespuesDelIgual(url);
            const response = await fetch(`http://ec2-13-59-233-23.us-east-2.compute.amazonaws.com:8090/api/productos/${partNumber}`);
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
                                                <!-- Contador de productos -->
                                                <div class="d-inline-flex align-items-center me-2">
                                                    <button id="decrementButton" class="btn btn-outline-secondary">-</button>
                                                    <span id="counter" class="px-3">${counter}</span>
                                                    <button id="incrementButton" class="btn btn-outline-secondary">+</button>
                                                </div>
                                                <!-- Fin del contador de productos -->
                                                <!-- Botón "Agregar al Carrito" -->
                                                <button id="addToCartButton" class="btn btn-primary ms-3">Agregar al Carrito</button>
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
            $('body').append(`
                <div id="cart-sidebar" class="offcanvas offcanvas-end" tabindex="-1" aria-labelledby="cart-sidebar-label">
                    <div class="offcanvas-header">
                        <h5 id="cart-sidebar-label">Carrito de Compras</h5>
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
                const subtotal = producto.precio * counter;
                const total = subtotal * (1 + IGV);
                $('#subtotal').text(`Subtotal: S/${subtotal.toFixed(2)}`);
                $('#total').text(`Total + Impuestos (IGV 18%): S/${total.toFixed(2)}`);
            };

            // Event listener para el botón "Agregar al Carrito"
            $('#addToCartButton').on('click', function() {
                // Detalles del Producto del Panel Lateral
                $('#cart-product-details').html(`
                    <div class="product-image">
                        <img src="${producto.url}" alt="${producto.nombreProducto}" class="img-thumbnail w-100">
                    </div>
                    <h3 class="card-title mt-3">${producto.nombreProducto}</h3>
                    <div class="product-info">
                        <p>Marca: ${producto.marca}</p>
                        <p>Categoría: ${producto.categoria}</p>
                        <p>Stock: ${producto.stock}</p>
                        <p>Número de Parte: ${producto.partNumber}</p>
                    </div>
                    <div class="product-price mt-3">
                        <h5>Precio del Producto: S/${producto.precio}</h5>
                    </div>
                    <hr style="border-top: 1px solid green;">
                    <p>Cantidad: ${counter}</p>
                `);

                // Actualizar el resumen del carrito
                updateCartSummary();

                // Mostrar el panel lateral
                let cartSidebar = new bootstrap.Offcanvas($('#cart-sidebar'));
                cartSidebar.show();
            });

            // Actualizar el resumen del carrito cuando cambie la cantidad
            $('#decrementButton, #incrementButton').on('click', function() {
                if ($('#cart-sidebar').hasClass('show')) {
                    updateCartSummary();
                }
            });

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