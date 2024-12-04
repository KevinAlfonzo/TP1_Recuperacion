/**


 function cargarPagina(pagina) {

 let elemento = document.getElementById("workspace");
 let http = new XMLHttpRequest();
 http.onreadystatechange = function() {
 if (this.readyState == 4 && this.status == 200) {
 elemento.innerHTML = this.responseText;
 }
 };
 http.open('GET', pagina);
 http.send();
 }
 * Archivo JS vinculado a main.jsp
 */