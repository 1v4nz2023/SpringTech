import { urlServer } from "./url.js";

document.getElementById('guardarBtn').addEventListener('click', function() {
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
    

    // Enviamos al back via Fetch API
    fetch(`${urlServer}/upload/productos`, {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {

        // Manejamos las respuestas
        console.log('Respuesta del servidor:', data);

        if(data.status != 400 && data.status != 404 && data.status != 500){
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Producto guardado correctamente'
            }).then(() => {
                // Limpiamos los campos
                document.getElementById('productoForm').reset();
            });
        }

        else{
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: data.message
            });
        }


    })
    .catch(error => {
        // Mostrar SweetAlert de error
        let errorMessage = 'Se produjo un error al procesar la solicitud';
        if (error.response && error.response.data && error.response.data.message) {
            errorMessage = error.response.data.message;
        }
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: errorMessage
        });
    });

});
