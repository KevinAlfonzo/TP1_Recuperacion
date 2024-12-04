<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="icon" type="image/x-icon" href="Image/Insignia-SRC.png">
    <title>Eventos</title>
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
        <div class="container-fluid px-4">
            <h1 class="mt-4">Eventos Inactivos</h1>
            <div class="card-body">
                <form method="post" action="#">
                    <div class="mb-2 row">
                        <div class="col-sm d-none">
                            <button type="button" class="btn d-none" id="btnActualizar"
                                    name="btnActualizar">Actualizar</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card mb-4" id="divResultado">
                <div class="card-header">
                    <i class="fa-sharp fa-light fa-book-open-reader"></i> Registros
                </div>
                <div class="card-body">
                    <table class="table caption-top">
                        <thead>
                        <tr>
                            <th scope="col">id</th>
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
                    <i class="fa-solid fa-list"></i> Formulario
                </div>
                <div class="card-body">
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
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>

        // Constantes del CRUD
        const ACCION_RESTAURAR = "RESTAURAR";
        // const ACCION_ELIMINAR = "ELIMINAR";

        // Arreglo de registros
        let arreglo = [];

        // Acceder a los controles
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
        btnProcesar.addEventListener("click", fnBtnProcesar);
        btnActualizar.addEventListener("click", fnBtnActualizar);

        // Funcion fnEditar
        function fnRestaurar(id) {
            Swal.fire(
                'Buen trabajo!',
                'Haz clic en el boton!',
                'exito'
            )
            document.getElementById("accion").value = ACCION_RESTAURAR;
            fnCargarForm(id);
            fnBtnProcesar();
            setTimeout(fnBtnActualizar, 1000);
        }

        /*// Funcion fnEliminar
        function fnEliminar(id) {
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                    )
                    document.getElementById("accion").value = ACCION_ELIMINAR;
                    fnCargarForm(id);
                    fnBtnProcesar();
                    setTimeout(fnBtnActualizar, 1000);
                }
            })
        }*/

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
        }

        function fnBtnActualizar() {
            let xhttp = new XMLHttpRequest();
            xhttp.open("GET", "EventoHistorial", true);
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
                        detalleTabla += "<a class='btn btn-success' href='javascript:fnRestaurar(" + item.id + ");'><i class='fa-solid fa-trash-arrow-up'></i></a> ";
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

        function fnValidar(){

            return true;
        }
    </script>
</body>
</html>