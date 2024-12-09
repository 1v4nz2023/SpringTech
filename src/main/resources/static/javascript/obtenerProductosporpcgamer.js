import { urlServer } from "./url.js";
const list_productos = document.getElementById("list_productos");
const buttons = document.getElementById("buttons");
let mostrando = document.getElementById("mostrando");
let urlProducto = `${urlServer}/api/producto/pc-gamer`;
let btnNext;
let btnPrev;
let templateHtml;
let start = 1;


const getproductos = async (url) => {
  try {
    const response = await fetch(url);
    const results = await response.json();
    console.log(results);
    Dataproductos(results.results);
    
    console.log(results.results);
    btnNext = results.next ? `<button class="btn btn-success" data-url=${results.next}>🢂</button>` : '';
    btnPrev = results.previous ? `<button class="btn btn-success mx-1" data-url=${results.previous}>🢀</button>` : '';
    
    buttons.innerHTML = btnPrev + " " + btnNext;
    mostrando.innerHTML = `Mostrando ${start}-${start + results.results.length-1} de ${results.count} productos`;

  } catch (error) {
    console.error(error);
  }
};

getproductos(urlProducto);

const Dataproductos = async (data) => {
  try {

    let container = document.createElement('div');
    container.classList.add('d-flex', 'flex-wrap', 'justify-content-center'); // Eliminar 'justify-content-center' para que los elementos no se centren horizontalmente
    let row = document.createElement('div');
    row.classList.add('row', 'd-flex', 'justify-content-center'); // Agregar clases para centrar horizontalmente

    for (let i = 0; i < data.length; i++) {
      if (i % 4 === 0 && i !== 0) {
        // Crear una nueva fila después de cada cuarto elemento
        container.appendChild(row);
        row = document.createElement('div');
        row.classList.add('row', 'd-flex', 'justify-content-center');
      }

      const index = data[i];
      // Obtén la URL de la imagen local
      // const imageUrl = `/images/${index.url}`; // Ajusta la ruta según tu estructura de archivos
      templateHtml = `
        <div class="card col-md-3 col-6 mx-1 my-3 mx-3" style="width: 15rem"> <!-- Cambiar col-md-2 a col-md-3 para mostrar 4 elementos por fila -->
          <a href="producto?id=${index.partNumber}"><img src="${index.url}" class="d-block w-100 card-img-top" alt="${index.name}" style="height: 150px;"></a>
          <h6 class="card-title mt-3">${index.nombreProducto}</h6>

          <div class="card-body d-flex align-items-end justify-content-center">
              <div class="d-flex justify-content-between">
                  <div class="d-flex flex-column">
                      <h6 class="text-danger font-weight-bold">S/${index.precio}</h6>
                      <span>Stock: ${index.stock}</span>
                      <span>Marca: ${index.marca}</span>
                  </div>
                  <div class="d-flex align-items-center mx-2">
                      <a href="#" class="btn btn-success product-text">Comprar</a>
                  </div>
              </div>
          </div>
      </div>
      `;
      row.innerHTML += templateHtml;
    }

    // Agregar la última fila al contenedor
    container.appendChild(row);

    list_productos.innerHTML = ''; // Limpiar antes de agregar los nuevos elementos
    list_productos.appendChild(container);

  } catch (error) {
    console.log(error);
  }
};


buttons.addEventListener('click', (e) => {
  if (e.target.classList.contains('btn-success')) {
    let value = e.target.dataset.url;
    if (e.target.classList.contains('mx-1')) {
      // Si el botón presionado es el botón de retroceso, resta 8 a start
      start -= 8;
    } else {
      // Si el botón presionado es el botón de avance, suma 8 a start
      start += 8;
    }
    console.log(start);
    console.log(value);
    getproductos(value);
  }
});

