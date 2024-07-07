$(document).ready(function() {
    let product = $('#product-section');
    let counter = 1; // Inicializar el contador

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
                                        <img src="${producto.url}" alt="${producto.nombreProducto}" class="img-thumbnail">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <!-- Product Details -->
                                    <div class="product-details">
                                        <h3 class="card-title">${producto.nombreProducto}</h3>
                                        <div class="product-info">
                                            <p>Marca: ${producto.marca}</p>
                                            <p>Categoría: ${producto.categoria}</p>
                                            <p>Stock: ${producto.stock}</p>
                                            <p>Número de Parte:${producto.partNumber}</p>
                                        </div>
                                        <div class="product-price mt-3">
                                            <h4>Precio del Producto: S/${producto.precio}</h4>
                                            <div class="d-flex align-items-center">
                                                <!-- Contador de productos -->
                                                <div class="d-inline-flex align-items-center me-2">
                                                    <button id="decrementButton">-</button>
                                                    <span id="counter">${counter}</span>
                                                    <button id="incrementButton">+</button>
                                                </div>
                                                <!-- Fin del contador de productos -->
                                                <!-- Botón "Agregar al Carrito" -->
                                                <button class="btn btn-primary">Agregar al Carrito</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Product Description -->
                            <div class="card mt-3">
                                <div class="card-body">
                                    <h3 class="card-title">Descripción del Producto</h3>
                                    <p>${producto.descripcion}</p>
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