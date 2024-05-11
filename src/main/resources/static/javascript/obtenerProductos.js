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
    let container = document.createElement('div');
    container.classList.add('d-flex', 'justify-content-center'); // Agregar clases para centrar horizontalmente
    let row = document.createElement('div');
    row.classList.add('row', 'd-flex', 'justify-content-center'); // Agregar clases para centrar horizontalmente
    for(let index of data){
        const resp = await fetch(index.url);
        const resul = await resp.json();
        console.log(resul);

        templateHtml = `
          <div class="card col-md-2 col-4 mx-1 mt-2" style="width: 15rem" id="card${count+1}">
            <a href=""><img src="${resul.sprites.other.dream_world.front_default}" class="d-block w-100 card-img-top" alt="${resul.name}" style="height: 150px;"></a>
            <div class="card-body">
                <h6 class="card-title">Product ${count+1}</h6>
                <div class="d-flex justify-content-between">
                    <div class="d-flex flex-column product-text">
                        <span >Precio</span>
                        <span>Stock</span>
                        <span>Referencia</span>
                    </div>
                    <div class="d-flex align-items-center">
                        <a href="#" class="btn btn-success product-text">Comprar</a>
                    </div>
                </div>
            </div>
        </div>
        `;

        row.innerHTML += templateHtml;
        count++;

        if (count === 5) {
          container.appendChild(row);
          list_pokemons.appendChild(container);
          row = document.createElement('div');
          row.classList.add('row', 'd-flex', 'justify-content-center'); // Agregar clases para centrar horizontalmente
          count = 0;
        }
    }

    if (count > 0) {
      container.appendChild(row);
      list_pokemons.appendChild(container);
    }

  } catch (error) {
    console.log(error);
  }
};

