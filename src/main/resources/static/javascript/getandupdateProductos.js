import { urlServer } from "./url.js";
// Llenar la tabla de productos cuando la página se carga
$(document).ready(function () {
    llenarTablaProductos();
});

// Función para manejar la edición de un producto
export async function editarProducto(idProducto) {
    // Obtener los detalles del producto por su ID
    const producto = await obtenerProductoPorId(idProducto);

    // Verificar si se obtuvieron los detalles del producto correctamente
    if (producto) {
        // Mostrar el modal de edición con los detalles del producto
        mostrarModalEdicion(producto);
    }
}

// Función para manejar borrar un producto
export async function borrarProducto(idProducto) {
    // Obtener los detalles del producto por su ID
    const producto = await obtenerProductoPorId(idProducto);

    // Verificar si se obtuvieron los detalles del producto correctamente
    if (producto) {
        try {
            const response = await axios.delete(
                `${urlServer}/api/productos/${producto.idProducto}`
            );
            Swal.fire({
                title: "Eliminación exitosa",
                text: "Producto eliminado correctamente",
                icon: "success",
            }).then(() => {
                location.reload();
            });
        } catch (error) {
            console.error(error);
            if (error.response.status === 404) {
                Swal.fire({
                    title: "Error",
                    text: error.response.data.message,
                    icon: "error",
                });
            } else {
                Swal.fire({
                    title: "Error",
                    text: "Ocurrió un error al procesar la solicitud",
                    icon: "error",
                });
            }
        }
    }
}

// Función para llenar la tabla de productos con DataTables
async function llenarTablaProductos() {
    // Obtener la tabla donde se mostrarán los productos
    const tabla = $("#myTable").DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/2.0.6/i18n/es-MX.json",
        },
        responsive: true, // Habilitar la funcionalidad responsive
    });

    // Obtener la lista de productos
    const productos = await obtenerProductos();

    // Verificar si se obtuvieron productos correctamente
    if (productos) {
        // Limpiar el contenido existente de la tabla
        tabla.clear().draw();

        // Iterar sobre la lista de productos y agregar filas a la tabla
        productos.forEach((producto) => {
            tabla.row
                .add([
                    producto.idProducto,
                    `<div class="d-flex align-content-between justify-content-center"> <h6 class="text-responsive">${producto.nombreProducto}</h6>
                    </div>`,
                    producto.stock,
                    `<div class="d-flex align-content-between justify-content-center"> <img src="${producto.url}" class="d-block" alt="${producto.nombreProducto}" style="height: 50px;">
                    </div>`,
                    `<div>  <button onclick="editarProducto(${producto.idProducto})" class="btn btn-success">Editar</button>
                        </div>`,
                ])
                .draw();
        });
    }
}


// Función para obtener la lista de productos
async function obtenerProductos() {
    try {
        // Realizar una solicitud GET a la ruta /api/productos
        const response = await axios.get(`${urlServer}/api/productos`);

        // Devolver los datos de la respuesta
        return response.data;
    } catch (error) {
        // Manejar errores si la solicitud falla
        console.error("Error al obtener la lista de productos:", error);
        return null;
    }
}

// Función para obtener los detalles de un producto por su ID
async function obtenerProductoPorId(idProducto) {
    try {
        // Realizar una solicitud GET a la ruta /api/productos/{id}
        const response = await axios.get(`${urlServer}/api/producto/${idProducto}`);

        // Devolver los datos de la respuesta
        return response.data;
    } catch (error) {
        // Manejar errores si la solicitud falla
        if (error.response && error.response.status === 404) {
            // Si el producto no se encuentra, mostrar un mensaje
            console.error("Producto no encontrado");
        } else {
            console.error("Error al obtener el producto:", error);
        }
        return null;
    }
}

// Función para mostrar el modal de edición
function mostrarModalEdicion(producto) {
    // Obtener el modal de edición
    const modal = document.getElementById("modal-edicion");

    // Llenar el modal con los detalles del producto
    modal.innerHTML = `
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-success">
                    <h5 class="modal-title text-light">Editar Producto</h5>
                </div>
                <div class="modal-body">
                    <form id="form-edicion">
                        <label for="nombreProducto">Nombre del producto:</label><br>
                        <input type="text" id="nombreProducto" name="nombreProducto" value="${producto.nombreProducto}" class="form-control"><br>
                        <label for="partNumber">Número de parte:</label><br>
                        <input type="text" id="partNumber" name="partNumber" value="${producto.partNumber}" class="form-control"><br>
                        <label for="categoria">Categoria:</label><br>
                        <select class="form-select" id="categoria" required class="form-control">
                        <option value="${producto.categoria}">PREDETERMINADA: ${producto.categoria}</option>
                        <option value="pc-oficina">COMPUTADORAS DE OFICINA</option>
                        <option value="pc-ingenieriadiseño">COMPUTADORAS DE INGENIERÍA Y DISEÑO</option>
                        <option value="pc-gamer">COMPUTADORAS GAMER</option>
                        <option value="laptop">LAPTOPS</option>
                        <option value="impresora">IMPRESORAS</option>
                          </select><br>
                        <label for="precio">Precio:</label><br>
                        <input type="number" id="precio" name="precio" value="${producto.precio}" class="form-control"><br>
                        <label for="stock">Stock:</label><br>
                        <input type="number" id="stock" name="stock" value="${producto.stock}" class="form-control"><br>
                        <label for="descripcion">Descripción:</label><br>
                        <input type="text" id="descripcion" name="descripcion" value="${producto.descripcion}" class="form-control"><br>  
                        <label for="marca">Marca:</label><br>
                        <select class="form-select text-center" id="marca">
                        <option value="${producto.marca}">PREDETERMINADA: ${producto.marca}</option>
                        <option value="HP">HP</option>
                        <option value="ASUS">ASUS</option>
                        <option value="PROPIA">PROPIA</option>
                        <option value="RAZER">RAZER</option>
                        <option value="BROTHER">BROTHER</option>
                        <option value="EPSON">EPSON</option>
                        <option value="MSI">MSI</option>
                        <option value="LENOVO">LENOVO</option>
                        <option value="TOSHIBA">TOSHIBA</option>
                        <option value="TP-LINK">TP-LINK</option>
                        </select> <br>
                        <label for="imagen" class="form-label">Imagen</label>
                        <input type="file" class="form-control" id="imagenProducto"> <br>
                        <label for="garantia">Garantía:</label><br>
                        <input type="text" id="garantia" name="garantia" value="${producto.garantia}" class="form-control"><br>  
                        <div class="container text-center"><br>
                            <button type="submit" class="btn btn-success">Guardar Cambios</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    `;


    
    // Mostrar el modal
    $("#modal-edicion").modal("show");

    // Manejar la envío del formulario
    const form = document.getElementById("form-edicion");
    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        Swal.fire({
            title: '¿Guardar cambios?',
            text: "¿Estás seguro de que quieres guardar los cambios?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, guardar',
            cancelButtonText: 'Cancelar'
        }).then(async (result) => {
            if (result.isConfirmed) {
                const nombreProducto = document.getElementById('nombreProducto').value;
                const partNumber = document.getElementById('partNumber').value;
                const categoria = document.getElementById('categoria').value;
                const precio = document.getElementById('precio').value;
                const stock = document.getElementById('stock').value;
                const descripcion = document.getElementById('descripcion').value;
                const marca = document.getElementById('marca').value;
                const garantia = document.getElementById('garantia').value;
                const imagenProducto = document.getElementById('imagenProducto').files[0];
            
                // Creamos el objeto FormData
                const formData = new FormData();
                formData.append('producto', new Blob([JSON.stringify({
                    nombreProducto: nombreProducto,
                    partNumber: partNumber,
                    categoria: categoria,
                    precio: parseFloat(precio),
                    stock: parseInt(stock),
                    descripcion: descripcion,
                    marca: marca,
                    url:"temporal",
                    garantia: garantia
                })], { type: "application/json" }));
                formData.append('imagen', imagenProducto);
                

                try {
                    const response = await axios.put(
                        `${urlServer}/upload/productos/${producto.idProducto}`,
                        formData
                    );
                    console.log("Producto actualizado exitosamente:", response.data);
                    Swal.fire({
                        title: "Actualización exitosa",
                        text: "Producto actualizado correctamente",
                        icon: "success",
                    }).then(() => {
                        location.reload();
                    });
                } catch (error) {
                    console.error(error);
                    if (error.response.status === 404) {
                        Swal.fire({
                            title: "Error",
                            text: error.response.data.message,
                            icon: "error",
                        });
                    } else {
                        // Otro tipo de error
                        Swal.fire({
                            title: "Error",
                            text: "Ocurrió un error al procesar la solicitud",
                            icon: "error",
                        });
                    }
                }
            }
        });
    });
}
