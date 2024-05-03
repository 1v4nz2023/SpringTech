

$(document).ready(function() {


    var isValid = true;

    //Validar DNI
    

    $('#dni').on('input', function() {
        var dniValue = $(this).val();
        // Validar si el valor es negativo o si no tiene exactamente 8 dígitos
        if (dniValue < 0 || dniValue.length !== 8) {
            $(this).addClass('is-invalid');
            $('#dniError').show();
            isValid = false;
        } else {
            $(this).removeClass('is-invalid');
            $('#dniError').hide();
            isValid = true;

        }
    });


    //VALIDAR EMAIL
    $('#email').on('input', function() {
        var email = $(this).val();
        // Validar el correo electrónico
        if (validateEmail(email)) {
            $(this).removeClass('is-invalid');
            $('#emailError').hide();
            isValid = true;

        } else {
            $(this).addClass('is-invalid');
            $('#emailError').show();
            isValid = false;

        }
    });

    //VALIDAR CONTRASEÑA
    $('#password').on('input', function() {
        var password = $(this).val();
        // Validar el correo electrónico
        if (password!=='') {
            $(this).removeClass('is-invalid');
            $('#passError').hide();
            isValid = true;

        } else {
            $(this).addClass('is-invalid');
            $('#passError').show();
            isValid = false;

        }
    });

    //VALIDAR NOMBRES Y APELLIDOS
    // Validación para el campo de nombres
    $('#nombres').on('input', function() {
        var input = $(this);
        var regex = /^[a-zA-Z\s]*$/; // Expresión regular para permitir solo letras y espacios

        if (!regex.test(input.val())) {
            input.addClass("is-invalid");
            isValid = false;

        } else {
            input.removeClass("is-invalid");
            isValid = true;

        }
    });

    // Validación para el campo de apellidos
    $('#apellidos').on('input', function() {
        var input = $(this);
        var regex = /^[a-zA-Z\s]*$/; // Expresión regular para permitir solo letras y espacios

        if (!regex.test(input.val())) {
            input.addClass("is-invalid");
            isValid = false;

        } else {
            input.removeClass("is-invalid");
            isValid = true;

        }
    });

    // Validación de campos vacíos:



    function validateEmail(email) {
        // Expresión regular para validar el formato de un correo electrónico
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }
    
    $('#registroForm').submit(function(event) {
        event.preventDefault();

        var nombres = $('#nombres').val().trim();
        var apellidos = $('#apellidos').val().trim();
        var dni = $('#dni').val().trim();
        var email = $('#email').val().trim();
        var password = $('#password').val().trim();

        if (nombres === '') {
            $('#nombres').addClass('is-invalid');
            isValid = false;
        } else {
            $('#nombres').removeClass('is-invalid');
        }

        if (apellidos === '') {
            $('#apellidos').addClass('is-invalid');
            isValid = false;
        } else {
            $('#apellidos').removeClass('is-invalid');
        }

        if (dni === '') {
            $('#dni').addClass('is-invalid');
            isValid = false;
        } else {
            $('#dni').removeClass('is-invalid');
        }

        if (email === '') {
            $('#email').addClass('is-invalid');
            isValid = false;
        } else {
            $('#email').removeClass('is-invalid');
        }

        if (password === '') {
            $('#password').addClass('is-invalid');
            isValid = false;
        } else {
            $('#password').removeClass('is-invalid');
        }

        console.log(isValid);
        if (isValid) {
            //this.submit();
            Swal.fire({
                title: "Registro exitoso",
                text: "Usuario creado correctamente",
                icon: "success"
              });
        }

    })


});
