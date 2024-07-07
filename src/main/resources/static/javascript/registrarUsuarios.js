async function registrarUsuario() {
    var nombres = $("#nombres").val().trim();
    var apellidos = $("#apellidos").val().trim();
    var dni = $("#dni").val().trim();
    var email = $("#email").val().trim();
    var password = $("#password").val().trim();

    const data = {
        dni: dni,
        nombre: nombres,
        apellidos: apellidos,
        correo: email,
        password: password,
        rol: "usuario",
    };

    try {
        const response = await axios.post("http://ec2-13-59-233-23.us-east-2.compute.amazonaws.com:8090/api/usuarios", data);
        console.log("Usuario registrado exitosamente:", response.data);
        Swal.fire({
            title: "Registro exitoso",
            text: "Usuario creado correctamente",
            icon: "success",
            confirmButtonText: 'OK',

        }).then(() => {
            window.location = "./Login";
        });

    } catch (error) {
        console.error(error);
        if (error.response.status === 404) {
            // Bad Request (dni o email duplicados)
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

export { registrarUsuario };
