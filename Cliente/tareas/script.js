const nombre = document.querySelector("#texto");
const saludo = document.querySelector("#saludo");
nombre.addEventListener("input",e => saludo.textContent = `Hola, ${e.target.value}`);


const limpiar = document.querySelector("#limpiar");
limpiar.addEventListener("click",()=>{
    saludo.textContent="";
    nombre.value="";
});