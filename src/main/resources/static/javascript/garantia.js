document.addEventListener('DOMContentLoaded', function () {
    const reporteForm = document.getElementById('reporteForm');
    // Validación de Nombre Completo (solo letras)
    const nombreCompleto = document.getElementById('nombreCompleto');
    const nombreError = document.getElementById('nombreError');
    nombreCompleto.addEventListener('input', function () {
        if (!/^[a-zA-Z\s]+$/.test(nombreCompleto.value)) {
            nombreError.textContent = 'El nombre completo solo puede contener letras.';
        } else {
            nombreError.textContent = '';
        }
    });

    // Validación de Correo Electrónico
    const correo = document.getElementById('correo');
    const correoError = document.getElementById('correoError');
    correo.addEventListener('input', function () {
        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo.value)) {
            correoError.textContent = 'Por favor, ingrese un correo electrónico válido.';
        } else {
            correoError.textContent = '';
        }
    });

    // Validación de Teléfono (solo números y 9 dígitos)
    const telefono = document.getElementById('telefono');
    const telefonoError = document.getElementById('telefonoError');
    telefono.addEventListener('input', function () {
        if (!/^\d{0,9}$/.test(telefono.value)) {
            telefono.value = telefono.value.slice(0, -1);
            telefonoError.textContent = 'El teléfono debe contener exactamente 9 números.';
        } else {
            telefonoError.textContent = '';
        }
    });

    // Validación de Nombre del Producto (puede ser llenado desde la base de datos)
    const nombreProducto = document.getElementById('nombreProducto');
    const productoError = document.getElementById('productoError');
    nombreProducto.addEventListener('input', function () {
        if (nombreProducto.value.trim() === '') {
            productoError.textContent = 'El nombre del producto es requerido.';
        } else {
            productoError.textContent = '';
        }
    });

    // Validación de Número de Serie (15 letras o números)
    const numSerie = document.getElementById('numSerie');
    const numSerieError = document.getElementById('numSerieError');
    numSerie.addEventListener('input', function () {
        if (!/^[a-zA-Z0-9]{0,15}$/.test(numSerie.value)) {
            numSerie.value = numSerie.value.slice(0, -1);
        }
        if (numSerie.value.length !== 15) {
            numSerieError.textContent = 'El número de serie debe contener exactamente 15 letras o números.';
        } else {
            numSerieError.textContent = '';
        }
    });

    // Validación de Fecha de Compra
    const fechaCompra = document.getElementById('fechaCompra');
    const fechaCompraError = document.getElementById('fechaCompraError');
    fechaCompra.addEventListener('input', function () {
        if (fechaCompra.value.trim() === '') {
            fechaCompraError.textContent = 'La fecha de compra es requerida.';
        } else {
            fechaCompraError.textContent = '';
        }
    });

    // Validación de Detalles del Problema (no se permiten correos)
    const detallesProblema = document.getElementById('detallesProblema');
    const detallesError = document.getElementById('detallesError');
    detallesProblema.addEventListener('input', function () {
        if (/\S+@\S+\.\S+/.test(detallesProblema.value)) {
            detallesError.textContent = 'Los detalles del problema no pueden contener correos electrónicos.';
        } else {
            detallesError.textContent = '';
        }
    });

    // Validación de Adjuntar Archivo
    const adjuntarArchivo = document.getElementById('adjuntarArchivo');
    const archivoError = document.getElementById('archivoError');
    adjuntarArchivo.addEventListener('change', function () {
        if (adjuntarArchivo.files.length === 0) {
            archivoError.textContent = 'Debe adjuntar un archivo.';
        } else {
            archivoError.textContent = '';
        }
    });

    reporteForm.addEventListener('submit', function (event) {
        event.preventDefault();
        let isValid = true;

        // Revalidar todo al enviar
        if (!/^[a-zA-Z\s]+$/.test(nombreCompleto.value)) {
            isValid = false;
            nombreError.textContent = 'El nombre completo solo puede contener letras.';
        } else {
            nombreError.textContent = '';
        }

        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo.value)) {
            isValid = false;
            correoError.textContent = 'Por favor, ingrese un correo electrónico válido.';
        } else {
            correoError.textContent = '';
        }

        if (!/^\d{9}$/.test(telefono.value)) {
            isValid = false;
            telefonoError.textContent = 'El teléfono debe contener exactamente 9 números.';
        } else {
            telefonoError.textContent = '';
        }

        if (nombreProducto.value.trim() === '') {
            isValid = false;
            productoError.textContent = 'El nombre del producto es requerido.';
        } else {
            productoError.textContent = '';
        }

        if (!/^[a-zA-Z0-9]{15}$/.test(numSerie.value)) {
            isValid = false;
            numSerieError.textContent = 'El número de serie debe contener exactamente 15 letras o números.';
        } else {
            numSerieError.textContent = '';
        }

        if (fechaCompra.value.trim() === '') {
            isValid = false;
            fechaCompraError.textContent = 'La fecha de compra es requerida.';
        } else {
            fechaCompraError.textContent = '';
        }

        if (/\S+@\S+\.\S+/.test(detallesProblema.value)) {
            isValid = false;
            detallesError.textContent = 'Los detalles del problema no pueden contener correos electrónicos.';
        } else {
            detallesError.textContent = '';
        }

        if (adjuntarArchivo.files.length === 0) {
            isValid = false;
            archivoError.textContent = 'Debe adjuntar un archivo.';
        } else {
            archivoError.textContent = '';
        }

        if (isValid) {
            this.submit();
        }
    });
});