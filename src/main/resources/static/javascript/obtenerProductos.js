const list_pokemons = document.getElementById("list_pokemons");
const buttons = document.getElementById("buttons");
let urlPokemon = "https://pokeapi.co/api/v2/pokemon";
let btnNext;
let btnPrevious;
let templateHtml;

const getPokemons = async (url) => {
  try {
    const response = await fetch(url);
    const results = await response.json();
    DataPokemons(results.results);
    console.log(results);
  } catch (error) {}
};

getPokemons(urlPokemon);

const DataPokemons = async(data) => {
  try {
    let count = 0;
    let row = document.createElement('div');
    row.classList.add('row');
    for(let index of data){
        const resp = await fetch(index.url);
        const resul = await resp.json();
        console.log(resul);

        templateHtml = `
          <div class="col-md-2 mb-3">
            <div class="card">
              <img src="${resul.sprites.other.dream_world.front_default}" class="card-img-top" alt="${resul.name}">
              <div class="card-body">
                <p class="card-text">${resul.name}</p>
              </div>
            </div>
          </div>
        `;

        row.innerHTML += templateHtml;
        count++;

        // Si ya hemos agregado 5 tarjetas, agregamos la fila al contenedor y creamos una nueva fila
        if (count === 5) {
          list_pokemons.appendChild(row);
          row = document.createElement('div');
          row.classList.add('row');
          count = 0;
        }
    }

    // Si quedan tarjetas en la fila sin agregar al contenedor, las agregamos
    if (count > 0) {
      list_pokemons.appendChild(row);
    }

  } catch (error) {
    console.log(error);
  }
};
