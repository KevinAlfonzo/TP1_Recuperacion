<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="icon" type="image/x-icon" href="Image/Insignia-SRC.png">
    <title>Evento</title>
    <link href="css/styles.css" rel="stylesheet" />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
            crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<jsp:include page="navbar.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="barralateral.jsp"></jsp:include>
    <div id="layoutSidenav_content">
        <main>

            <div class="container-fluid px-4">
                <h1 class="mt-4">Eventos Activos</h1>
                <div class="card-body">
                    <form method="post" action="#">
                        <div class="mb-2 row">
                            <div class="col-sm d-none">
                                <button type="button" class="btn d-none" id="btnActualizar"
                                        name="btnActualizar">Actualizar</button>
                            </div>

                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="event_name" name="event_name"
                                       placeholder="Ingrese nombre">
                            </div>
                            <div class="col-sm-3">
                                <input type="date" class="form-control" id="date"
                                       name="date" placeholder="Seleccione la fecha">
                            </div>

                            <div class="col-sm-1 d-flex">
                                <button type="button" class="btn btn-light mb-2 me-2"
                                        id="btnBuscar" name="btnBuscar">Buscar</button>
                                <button type="button" class="btn btn-info float-end mb-2"
                                        id="btnNuevo" name="btnNuevo">Nuevo</button>
                            </div>

                        </div>
                    </form>
                </div>
                <div>
                    <button class="btn btn-outline-secondary" onclick="exportToCSV()">Exportar a CSV</button>
                    <button class="btn btn-outline-success" onclick="exportToExcel()">Exportar a Excel</button>
                    <button class="btn btn-outline-danger" onclick="exportToPDF()">Exportar a PDF</button>
                </div>
                <p></p>
                <div class="card mb-4" id="divResultado">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i> Registros
                    </div>
                    <div class="card-body">
                        <table class="table caption-top">
                            <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nombres</th>
                                <th scope="col">Descripcion</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Certificacion</th>
                                <!--	<th scope="col">Estado</th> -->
                            </tr>
                            </thead>
                            <tbody id="detalleTabla">
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="card" id="divRegistro" style="display: none;">
                    <div class="card-header">
                        <i class="fa-sharp fa-light fa-book-open-reader"></i> Formulario
                    </div>
                    <div class="card-body">
                        <form class="row g-3 needs-validation" novalidate>

                            <input type="hidden" id="accion" name="accion">

                            <div class="col-md-4 d-none">
                                <label for="frmId" class="form-label">ID</label> <input
                                    type="text" class="form-control" id="frmId" required>
                            </div>

                            <div class="col-md-4">
                                <label for="frmEvent_name" class="form-label">Nombres</label> <input
                                    type="text" class="form-control" id="frmEvent_name" value=""
                                    required>
                                <div class="valid-feedback">Se ve bien!</div>
                                <div class="invalid-feedback">Por favor, coloque algo
                                    valido.</div>
                            </div>

                            <div class="col-md-4">
                                <label for="frmDescription" class="form-label">Descripcion</label>
                                <input type="text" class="form-control" id="frmDescription"
                                       required>
                                <div class="valid-feedback">Se ve bien!</div>
                                <div class="invalid-feedback">Por favor, coloque algo valido.</div>
                            </div>
                            <div class="col-md-4">
                                <label for="frmDate" class="form-label">Fecha</label>
                                <input type="date" class="form-control" id="frmDate">
                            </div>
                            <div class="col-md-4">
                                <label for="frmCertificate" class="form-label">Precio de Certificacion</label>
                                <input type="number" class="form-control" id="frmCertificate">
                            </div>
                            <div class="col-md-4">
                                <label for="frmStatus" class="form-label">Estado</label>
                                <select class="form-select" id="frmStatus"
                                        required>
                                    <option selected disabled value="">Elige...</option>
                                    <option value="A">A</option>
                                    <option value="I">I</option>
                                </select>
                                <div class="invalid-feedback">Seleccione un Estado</div>
                            </div>
                            <div class="col-12">
                                <button class="btn btn-primary" id="btnProcesar" type="submit">Enviar
                                    formulario</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.25/jspdf.plugin.autotable.min.js"></script>

<script>

    // Constantes del CRUD
    const ACCION_NUEVO = "NUEVO";
    const ACCION_EDITAR = "EDITAR";
    const ACCION_ELIMINAR = "ELIMINAR";

    // Arreglo de registros
    let arreglo = [];

    // Acceder a los controles
    let btnBuscar = document.getElementById("btnBuscar");
    let btnNuevo = document.getElementById("btnNuevo");
    let btnProcesar = document.getElementById("btnProcesar");
    let btnActualizar = document.getElementById("btnActualizar");

    // Campos del formulario
    let accion = document.getElementById('accion');
    let frmId = document.getElementById('frmId');
    let frmEvent_name = document.getElementById('frmEvent_name');
    let frmDescription = document.getElementById("frmDescription");
    let frmDate = document.getElementById("frmDate");
    let frmCertificate = document.getElementById("frmCertificate");
    let frmStatus = document.getElementById("frmStatus");

    // Programar los controles
    btnBuscar.addEventListener("click", fnBtnBuscar);
    btnNuevo.addEventListener("click", fnBtnNuevo);
    btnProcesar.addEventListener("click", fnBtnProcesar);
    btnActualizar.addEventListener("click", fnBtnActualizar);

    // Funcion fnEditar
    function fnEditar(id) {
        document.getElementById("accion").value = ACCION_EDITAR; // Asegúrate de que ACCION_EDITAR esté definido
        fnCargarForm(id); // Cargar el formulario con los datos del registro
        document.getElementById("divResultado").style.display = "none";
        document.getElementById("divRegistro").style.display = "block";
    }

    // Función para manejar el envío del formulario
    document.getElementById("btnProcesar").addEventListener("click", function (event) {
        // Prevenir el envío automático del formulario
        event.preventDefault();
        // Verificar si el formulario está en modo de creación o edición
        const isEditMode = document.getElementById("accion").value === "EDITAR"; // Cambia "EDITAR" por el valor adecuado

        if (isEditMode) {
            // Modo edición: mostrar el diálogo de confirmación para editar
            Swal.fire({
                title: 'Confirmar cambios?',
                text: "Estas a punto de modificar un registro existente.",
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, actualizar'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Si el usuario confirma, proceder con el envío del formulario en modo edición
                    fnEnviarFormulario();
                }
            });
        } else {
            // Modo creación: mostrar el diálogo de confirmación para crear
            Swal.fire({
                title: 'Crear nuevo registro?',
                text: "Estas a punto de agregar un nuevo registro.",
                icon: 'info',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, crear'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Si el usuario confirma, proceder con el envío del formulario en modo creación
                    fnEnviarFormulario();
                }
            });
        }
    });

    // Función para enviar el formulario
    function fnEnviarFormulario() {
        // Ocultar el formulario de edición
        document.getElementById("divRegistro").style.display = "none";
        // Mostrar el contenedor de resultados
        document.getElementById("divResultado").style.display = "block";

        // Verificar si el formulario está en modo de creación o edición
        const isEditMode = document.getElementById("accion").value === "EDITAR"; // Cambia "EDITAR" por el valor adecuado para tu caso

        // Mostrar un mensaje de éxito dependiendo del modo (creación o edición)
        if (isEditMode) {
            // Mensaje para la edición
            Swal.fire(
                'Actualizado!',
                'El registro ha sido actualizado.',
                'success'
            ).then(() => {
                // Refrescar la página automáticamente después de que el usuario cierre el mensaje
                location.reload();
            });
        } else {
            // Mensaje para la creación
            Swal.fire(
                'Creado!',
                'El nuevo registro ha sido creado exitosamente.',
                'success'
            ).then(() => {
                // Refrescar la página automáticamente después de que el usuario cierre el mensaje
                location.reload();
            });
        }
    }

    // Funcion fnEliminar
    function fnEliminar(id) {
        Swal.fire({
            title: 'Estas Seguro?',
            text: "No podras revertir esto!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Estoy seguro'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire(
                    '!Borrado!',
                    'Su archivo ha sido eliminado.',
                    'exito'
                )
                document.getElementById("accion").value = ACCION_ELIMINAR;
                fnCargarForm(id);
                fnBtnProcesar();
                setTimeout(fnBtnActualizar, 1000);
            }
        })
    }

    // Funcion fnBtnProcesar
    function fnBtnProcesar() {
        if(!fnValidar()){
            return;
        }
        let datos = "accion=" + document.getElementById("accion").value;
        datos += "&id=" + document.getElementById("frmId").value;
        datos += "&event_name=" + document.getElementById("frmEvent_name").value;
        datos += "&description=" + document.getElementById("frmDescription").value;
        datos += "&date=" + document.getElementById("frmDate").value;
        datos += "&certificate=" + document.getElementById("frmCertificate").value;
        datos += "&status=" + document.getElementById("frmStatus").value;
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "EventoProcesar", true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log(xhr.responseText);
            }
        };
        xhr.send(datos);
        console.log(datos);
    }

    // Tu funcion fnBtnNuevo existente
    function fnBtnNuevo() {
        document.getElementById("accion").value = ACCION_NUEVO;
        fnEstadoFormulario(ACCION_NUEVO);
        document.getElementById("divResultado").style.display = "none";
        document.getElementById("divRegistro").style.display = "block";
    }

    function fnBtnBuscar() {
        let event_name = document.getElementById("event_name").value;
        let date = document.getElementById("date").value;
        let url = "EventoBuscar?event_name=" + event_name + "&date=" + date;
        let xhttp = new XMLHttpRequest();
        xhttp.open("GET", url, true);
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                let respuesta = xhttp.responseText;
                arreglo = JSON.parse(respuesta);
                let detalleTabla = "";
                arreglo.forEach(function(item) {
                    detalleTabla += "<tr>";
                    detalleTabla += "<td>" + item.id + "</td>";
                    detalleTabla += "<td>" + item.event_name + "</td>";
                    detalleTabla += "<td>" + item.description + "</td>";
                    detalleTabla += "<td>" + item.date + "</td>";
                    detalleTabla += "<td>" + item.certificate + "</td>";
                    detalleTabla += "<td>";
                    detalleTabla += "<a class='btn btn-success' href='javascript:fnEditar(" + item.id + ");'><i class='fa-solid fa-pen'></i></a> ";
                    detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.id + ");'><i class='fa-solid fa-trash'></i></a>";
                    detalleTabla += "</td>";
                    detalleTabla += "</tr>";
                });
                document.getElementById("detalleTabla").innerHTML = detalleTabla;
                document.getElementById("divResultado").style.display = "block";
                document.getElementById("divRegistro").style.display = "none";
            }
        };
        xhttp.send();
    }

    function fnBtnActualizar() {
        let xhttp = new XMLHttpRequest();
        xhttp.open("GET", "EventoActualizar", true);
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                let respuesta = xhttp.responseText;
                arreglo = JSON.parse(respuesta);
                let detalleTabla = "";
                arreglo.forEach(function(item) {
                    detalleTabla += "<tr>";
                    detalleTabla += "<td>" + item.id + "</td>";
                    detalleTabla += "<td>" + item.event_name + "</td>";
                    detalleTabla += "<td>" + item.description + "</td>";
                    detalleTabla += "<td>" + item.date + "</td>";
                    detalleTabla += "<td>" + item.certificate + "</td>";
                    detalleTabla += "<td>";
                    detalleTabla += "<a class='btn btn-success' href='javascript:fnEditar(" + item.id + ");'><i class='fa-solid fa-pen'></i></a> ";
                    detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.id + ");'><i class='fa-solid fa-trash'></i></a>";
                    detalleTabla += "</td>";
                    detalleTabla += "</tr>";
                });
                document.getElementById("detalleTabla").innerHTML = detalleTabla;
                document.getElementById("divResultado").style.display = "block";
                document.getElementById("divRegistro").style.display = "none";
            }
        };
        xhttp.send();
    }

    fnBtnActualizar();

    function fnCargarForm(id){
        arreglo.forEach(function(item) {
            if(item.id == id){
                frmId.value = item.id;
                frmEvent_name.value = item.event_name;
                frmDescription.value = item.description;
                frmDate.value = item.date;
                frmCertificate.value = item.certificate;
                frmStatus.value = item.status;
                return true;
            }
        });
    }

    function fnEstadoFormulario(estado){
        frmEvent_name.disabled = (estado==ACCION_ELIMINAR)
        frmDescription.disabled = (estado==ACCION_ELIMINAR)
        frmDate.disabled = (estado==ACCION_ELIMINAR)
        frmCertificate.disabled = (estado==ACCION_ELIMINAR)
        frmStatus.disabled = (estado==ACCION_ELIMINAR)
        if(estado==ACCION_NUEVO){
            frmId.value = "0";
            frmEvent_name.value = "";
            frmDescription.value = "";
            frmDate.value = "";
            frmCertificate.value = "";
            frmStatus.value = "";
        }
    }


    function fnValidar(){

        return true;
    }
</script>
<!-- VALIDACION CAMPO NOMBRE -->
<script>
    // Obtener el campo de entrada de nombres
    var frmEvent_nameInput = document.getElementById('frmEvent_name');

    // Agregar un event listener para el evento 'input'
    frmEvent_nameInput.addEventListener('input', function(event) {
        // Obtener el valor actual del campo de nombres
        var name = event.target.value;

        // Expresi�n regular para validar solo letras y espacios, incluyendo � y di�resis
        var regex = /^[A-Za-z\u00C0-\u00FF\u00F1\u00D1\s]+$/;

        // Validar el valor ingresado
        if (name === '') {
            // El campo est� vac�o
            frmEvent_nameInput.classList.remove('is-valid');
            frmEvent_nameInput.classList.remove('is-invalid');
        } else if (regex.test(name)) {
            // El valor es v�lido
            frmEvent_nameInput.classList.remove('is-invalid');
            frmEvent_nameInput.classList.add('is-valid');
        } else {
            // El valor es inv�lido
            frmEvent_nameInput.classList.remove('is-valid');
            frmEvent_nameInput.classList.add('is-invalid');
        }
    });
</script>




<!-- EXPORTAR TABLA EN FORMATO EXCEL -->
<script>
    function exportToExcel() {
        // Obtener la referencia a la tabla
        var table = document.querySelector('.table');

        // Obtener todas las filas de la tabla
        var rows = Array.from(table.querySelectorAll('tbody tr'));

        // Crear una matriz para almacenar los datos del archivo de Excel
        var excelData = [];

        // Iterar sobre cada fila y obtener los datos de las celdas
        rows.forEach(function(row) {
            var rowData = [];
            var cells = Array.from(row.querySelectorAll('td'));

            // Obtener el texto de cada celda y agregarlo a la matriz de datos
            cells.forEach(function(cell) {
                rowData.push(cell.textContent.trim());
            });

            // Agregar la fila de datos a la matriz del archivo de Excel
            excelData.push(rowData);
        });

        // Crear una nueva hoja de c�lculo de Excel
        var workbook = XLSX.utils.book_new();

        // Crear una hoja de trabajo
        var worksheet = XLSX.utils.aoa_to_sheet(excelData);

        // Agregar la hoja de trabajo al libro de Excel
        XLSX.utils.book_append_sheet(workbook, worksheet, 'Tabla');

        // Generar un archivo de Excel binario
        var excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });

        // Convertir el buffer de Excel a un blob
        var blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

        // Crear un enlace temporal para descargar el archivo de Excel
        var link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = 'ListadoEvento.xlsx';

        // Simular el clic en el enlace para descargar el archivo de Excel
        link.click();
    }
</script>

<!-- EXPORTAR TABLA EN FORMATO CSV -->
<script>
    function exportToCSV() {
        // Obtener la referencia a la tabla
        var table = document.querySelector('.table');

        // Obtener todas las filas de la tabla
        var rows = Array.from(table.querySelectorAll('tbody tr'));

        // Crear una matriz para almacenar los datos del CSV
        var csvData = [];

        // Iterar sobre cada fila y obtener los datos de las celdas
        rows.forEach(function(row) {
            var rowData = [];
            var cells = Array.from(row.querySelectorAll('td'));

            // Obtener el texto de cada celda y agregarlo a la matriz de datos
            cells.forEach(function(cell) {
                rowData.push(cell.textContent.trim());
            });

            // Agregar la fila de datos al CSV
            csvData.push(rowData.join(','));
        });

        // Crear el contenido del archivo CSV
        var csvContent = csvData.join('\n');

        // Crear un enlace temporal para descargar el archivo CSV
        var link = document.createElement('a');
        link.href = 'data:text/csv;charset=utf-8,' + encodeURI(csvContent);
        link.target = '_blank';
        link.download = 'ListadoEvento.csv';

        // Simular el clic en el enlace para descargar el archivo CSV
        link.click();
    }
</script>

<!-- EXPORTAR TABLE EN FORMATO PDF -->
<script>
    function exportToPDF() {
        // Verifica que jsPDF y autoTable estén cargados correctamente
        if (window.jspdf && window.jspdf.jsPDF) {
            const { jsPDF } = window.jspdf;

            // Crear una nueva instancia de jsPDF
            var doc = new jsPDF('landscape');

            // Verifica si autoTable está disponible en la instancia de jsPDF
            if (doc.autoTable) {
                // Selecciona la tabla HTML que deseas exportar
                var table = document.querySelector('.table');

                // Usar el complemento autoTable para generar la tabla en el PDF con configuraciones personalizadas
                doc.autoTable({
                    html: table,
                    startY: 5, // Espacio desde el margen superior del PDF
                    theme: 'grid', // Tema de la tabla ('striped', 'grid', 'plain')
                    headStyles: {
                        fillColor: [22, 160, 133], // Color de fondo del encabezado
                        textColor: 255, // Color del texto del encabezado
                        fontSize: 10, // Tamaño de fuente del encabezado
                    },
                    bodyStyles: {
                        fontSize: 9, // Tamaño de fuente del cuerpo
                        cellPadding: 2, // Espaciado interno de las celdas
                    },
                    margin: { top: 30, left: 8 }, // Margen superior del documento
                    styles: {
                        cellPadding: 3, // Espaciado dentro de las celdas para mayor claridad
                        halign: 'center', // Alinear horizontalmente las celdas (izquierda, centro, derecha)
                        valign: 'middle', // Alinear verticalmente las celdas (arriba, centro, abajo)
                    },
                    columnStyles: {
                        0: { cellWidth: 10 }, // Ajuste de ancho para la primera columna
                        1: { cellWidth: 10 }, // Ajuste de ancho para la segunda columna
                        2: { cellWidth: 30 }, // Ajuste de ancho para la tercera columna
                        3: { cellWidth: 30 }, // Ajuste de ancho para la primera columna
                        4: { cellWidth: 15 }, // Ajuste de ancho para la segunda columna
                        5: { cellWidth: 30 }, // Ajuste de ancho para la tercera columna
                        6: { cellWidth: 50 }, // Ajuste de ancho para la primera columna
                        7: { cellWidth: 60 }, // Ajuste de ancho para la segunda columna
                        8: { cellWidth: 50 }, // Ajuste de ancho para la primera columna
                        // Añade más columnas si es necesario
                    }
                });

                // Guardar el PDF generado con el nombre especificado
                doc.save('ListadoEvento.pdf');
            } else {
                console.error("autoTable no está disponible. Asegúrate de que el script esté cargado correctamente.");
            }
        } else {
            console.error("jsPDF no está disponible. Asegúrate de que el script esté cargado correctamente.");
        }
    }
</script>

<!-- IMPLEMENTACIÓN DE PAGINATOR EN LA TABLA -->
<script>
    let evento = []; // Array para almacenar los datos de las personas
    let currentPage = 1; // Página actual del paginador
    const recordsPerPage = 10; // Número de registros por página

    // Función para inicializar la carga de datos y mostrar la tabla
    async function inicializarTabla() {
        async function cargarDatosEvento() {
            return [];
        }

        evento = await cargarDatosEvento(); // Carga los datos desde el servidor
        displayTable(); // Muestra la tabla con los primeros registros
    }

    // Función para mostrar la tabla con paginación automática
    function displayTable() {
        const tableBody = document.querySelector("#evento-table tbody");

        // Calcula el índice de inicio y fin de los registros a mostrar
        const startIndex = (currentPage - 1) * recordsPerPage;
        const endIndex = startIndex + recordsPerPage;
        const paginatedItems = evento.slice(startIndex, endIndex);

        // Agrega los registros a la tabla
        paginatedItems.forEach(function (evento) {
            const row = document.createElement("tr");
            row.innerHTML = `
            <td>${evento.id}</td>
            <td>${evento.event_name}</td>
            <td>${evento.description}</td>
            <td>${evento.date}</td>
            <td>${evento.certificate}</td>
            <td>${evento.status}</td>
        `;
            tableBody.appendChild(row);
        });
    }

    // Detecta cuando llegamos al final del contenedor de la tabla y carga más registros
    window.addEventListener('scroll', () => {
        if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
            // Si llegamos al final de la página, cargamos más registros
            currentPage++;
            displayTable();
        }
    });

    // Llama a la función de inicialización cuando la página esté lista
    document.addEventListener('DOMContentLoaded', function () {
        inicializarTabla();
    });
</script>
</body>
</html>