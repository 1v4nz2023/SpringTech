async function resetPassword() {
    // Obtener los valores del formulario
    const email = document.getElementById('email').value;
    const newPassword = document.getElementById('newPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    // Validar que las contraseñas coincidan
    if (newPassword !== confirmPassword) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Las contraseñas no coinciden'
      });
      return;
    }else{
        try {

                // Crear objeto con los datos del formulario
    const data = {
        email: email,
        newPassword: newPassword,
      };
            const response = await axios.post(
              `${urlServer}/api/reset-password`,
              data
            );
            console.log("Contraseña restablecida exitosamente", response.data);
            Swal.fire({
              title: "Éxito",
              text: "Contraseña restablecida exitosamente",
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