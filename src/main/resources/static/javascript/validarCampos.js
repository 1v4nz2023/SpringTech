import { registrarUsuario } from "./registrarUsuarios.js";

$(document).ready(function() {
    // Variable para rastrear la validez global del formulario
    var isValid = true;

    // Validar DNI
    $('#dni').on('input', function() {
        var dniValue = $(this).val();
        // Validar si el valor es negativo o si no tiene exactamente 8 dígitos
        if (dniValue < 0 || dniValue.length !== 8) {
            $(this).addClass('is-invalid');
            $('#dniError').show();
            isValid = false; // Establecer isValid en false si hay un error
        } else {
            $(this).removeClass('is-invalid');
            $('#dniError').hide();
        }
    });

    // VALIDAR EMAIL
    $('#email').on('input', function() {
        var email = $(this).val();
        // Validar el correo electrónico
        if (validateEmail(email)) {
            $(this).removeClass('is-invalid');
            $('#emailError').hide();
        } else {
            $(this).addClass('is-invalid');
            $('#emailError').show();
            isValid = false; // Establecer isValid en false si hay un error
        }
    });

    // VALIDAR CONTRASEÑA
    $('#password').on('input', function() {
        var password = $(this).val();
        // Validar si la contraseña está vacía
        if (password !== '') {
            $(this).removeClass('is-invalid');
            $('#passError').hide();
        } else {
            $(this).addClass('is-invalid');
            $('#passError').show();
            isValid = false; // Establecer isValid en false si hay un error
        }
    });

    // VALIDAR NOMBRES Y APELLIDOS
    // Validación para el campo de nombres
    $('#nombres').on('input', function() {
        var input = $(this);
        var regex = /^[a-zA-Z\s]*$/; // Expresión regular para permitir solo letras y espacios

        if (!regex.test(input.val())) {
            input.addClass("is-invalid");
            isValid = false; // Establecer isValid en false si hay un error
        } else {
            input.removeClass("is-invalid");
        }
    });

    // Validación para el campo de apellidos
    $('#apellidos').on('input', function() {
        var input = $(this);
        var regex = /^[a-zA-Z\s]*$/; // Expresión regular para permitir solo letras y espacios

        if (!regex.test(input.val())) {
            input.addClass("is-invalid");
            isValid = false; // Establecer isValid en false si hay un error
        } else {
            input.removeClass("is-invalid");
        }
    });

    // Validación de campos vacíos al enviar el formulario
    $('#registroForm').submit(function(event) {
        event.preventDefault();

        // Restablecer isValid a true antes de verificar los campos
        isValid = true;

        // Realizar validaciones nuevamente
        $('#dni, #email, #password, #nombres, #apellidos').trigger('input');

        // Verificar si todos los campos son válidos
        if (isValid) {
            console.log("datos válidos");
            registrarUsuario();
        } else {
            console.log("datos inválidos");
        }
    });
});

function validateEmail(email) {
    // Expresión regular para validar el formato de un correo electrónico
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}
