document.getElementById('guardarBtn').addEventListener('click', function() {
    const nombreProducto = document.getElementById('nombreProducto').value;
    const partNumber = document.getElementById('partNumber').value;
    const categoria = document.getElementById('categoria').value;
    const precio = document.getElementById('precio').value;
    const stock = document.getElementById('stock').value;
    const descripcion = document.getElementById('descripcion').value;
    const urlProducto = document.getElementById('urlProducto').value;
    const marca = document.getElementById('marca').value;
    const garantia = document.getElementById('garantia').value;

    // Crear objeto con los datos del formulario
    const requestData = {
        nombreProducto: nombreProducto,
        partNumber: partNumber,
        categoria: categoria,
        precio: parseFloat(precio),
        stock: parseInt(stock),
        descripcion: descripcion,
        url: urlProducto,
        marca: marca,
        garantia: garantia
    };

    // Enviar la solicitud POST al servidor usando Axios
    axios.post('/api/productos', requestData)
        .then(response => {
            // Mostrar SweetAlert de éxito
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Producto guardado correctamente'
            }).then(() => {
                // Limpiar los campos del formulario
                document.getElementById('nombreProducto').value = '';
                document.getElementById('partNumber').value = '';
                document.getElementById('categoria').value = '';
                document.getElementById('precio').value = '';
                document.getElementById('stock').value = '';
                document.getElementById('descripcion').value = '';
                document.getElementById('urlProducto').value = '';
                document.getElementById('marca').value = '';
                document.getElementById('garantia').value = '';
            });
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
