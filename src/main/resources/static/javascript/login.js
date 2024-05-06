function submitForm(event) {
    event.preventDefault();

    const user = document.getElementById('user').value;
    const password = document.getElementById('password').value;

    const data = {
        dni: user,
        password: password
    };

    axios.post('http://localhost:8090/api/login', data)
        .then(response => {
            const idUsuario = response.data.idUsuario;
            console.log(idUsuario);

            // Mostrar alerta de éxito con SweetAlert2
            Swal.fire({
                icon: 'success',
                title: 'Inicio de sesión exitoso',
                text: '¡Bienvenido!',
                timer: 2000, // Cerrar la alerta después de 2 segundos
                timerProgressBar: true,
                showConfirmButton: false
            });
            const url = `perfil.html?idUsuario=${idUsuario}`;
            console.log(url);

            window.location.href = url;


        })
        .catch(error => {
            // Mostrar alerta de error con SweetAlert2
            Swal.fire({
                icon: 'error',
                title: error.response.data.message,
                text: 'Por favor, verifica tus credenciales e intenta nuevamente.'
            });

            // Aquí puedes mostrar un mensaje de error al usuario o realizar otras acciones según sea necesario
        });
}