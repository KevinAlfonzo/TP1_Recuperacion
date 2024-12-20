<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<link rel="icon" type="image/x-icon" href="Image/Insignia-SRC.png">
	<title>Personas</title>
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
				<h1 class="mt-4">Personas Inactivas</h1>
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
								<th scope="col">Tipo de Persona</th>
								<th scope="col">Nombres</th>
								<th scope="col">Apellidos</th>
								<th scope="col">Tipo de Documento</th>
								<th scope="col">Documento</th>
								<!--  	<th scope="col">Distrito</th> -->
								<th scope="col">Direccion</th>
								<th scope="col">Correo</th>
								<th scope="col">Celular</th>
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
						<form class="row g-3 needs-validation" novalidate>
							<input type="hidden" id="accion" name="accion">

							<div class="col-md-4 d-none">
								<label for="frmId" class="form-label">ID</label> <input
									type="text" class="form-control" id="frmId" required>
							</div>

							<div class="col-md-3">
								<label for="frmType_person" class="form-label">Tipo
									De Persona</label> <select class="form-select" id="frmType_person"
															   required>
								<option selected disabled value="">Elige...</option>
								<option value="C">C</option>
								<option value="A">A</option>
							</select>
								<div class="invalid-feedback">Seleccione un tipo de
									persona.</div>
							</div>

							<div class="col-md-4">
								<label for="frmNames" class="form-label">Nombres</label> <input
									type="text" class="form-control" id="frmNames" value=""
									required>
								<div class="valid-feedback">Se ve bien!</div>
								<div class="invalid-feedback">Por favor, coloque algo
									valido.</div>
							</div>

							<div class="col-md-4">
								<label for="frmLast_names" class="form-label">Apellido</label>
								<input type="text" class="form-control" id="frmLast_names"
									   required>
								<div class="valid-feedback">Se ve bien!</div>
								<div class="invalid-feedback">Por favor, coloque algo valido.</div>
							</div>
							<div class="col-md-3">
								<label for="frmType_document" class="form-label">Tipo
									Documento</label> <select class="form-select" id="frmType_document"
															  required>
								<option selected disabled value="">Elige...</option>
								<option value="DNI">DNI</option>
								<option value="CNE">CNE</option>
							</select>
								<div class="invalid-feedback">Seleccione un tipo de
									documento.</div>
							</div>
							<div class="col-md-3">
								<label for="frmNumber_document" class="form-label">N
									Documento</label> <input type="text" class="form-control"
															 id="frmNumber_document" maxlength="9" required>
								<div class="valid-feedback">El documento es correcto!</div>
								<div class="invalid-feedback">Por favor, coloque un numero de documento valido.</div>
							</div>
							<div class="col-md-4">
								<label for="frmAddress" class="form-label">Direccion</label> <input
									type="text" class="form-control" id="frmAddress" value=""
									required>
								<div class="valid-feedback">Se ve bien!</div>
								<div class="invalid-feedback">Por favor, coloque algo
									valido.</div>
							</div>
							<div class="col-md-4">
								<label for="frmEmail" class="form-label">Correo
									Electronico</label>
								<div class="input-group has-validation">
									<input type="text" class="form-control" id="frmEmail"
										   aria-describedby="inputGroupPrepend" required>
									<div class="valid-feedback">Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo valido.</div>
								</div>
							</div>
							<div class="col-md-4">
								<label for="frmCell_phone" class="form-label">
									Celular</label> <input type="text" class="form-control"
														   id="frmCell_phone" required>
								<div class="valid-feedback">Se ve bien!</div>
								<div class="invalid-feedback">Por favor, coloque algo valido.</div>
							</div>
							<div class="col-md-4">
								<label for="frmUser_name" class="form-label">Usuario</label> <input
									type="text" class="form-control" id="frmUser_name" required>
								<div class="valid-feedback">Se ve bien!</div>
								<div class="invalid-feedback">Por favor, coloque algo valido.</div>
							</div>
							<div class="col-md-4">
								<label for="frmPassword" class="form-label">Contrasena</label>
								<input type="text" class="form-control" id="frmPassword"
									   required>
								<div class="valid-feedback">Se ve bien!</div>
								<div class="invalid-feedback">Por favor, coloque algo valido.</div>
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
	let frmType_person = document.getElementById("frmType_person");
	let frmNames = document.getElementById('frmNames');
	let frmLast_names = document.getElementById("frmLast_names");
	let frmType_document = document.getElementById("frmType_document");
	let frmNumber_document = document.getElementById("frmNumber_document");
	let frmAddress = document.getElementById("frmAddress");
	let frmEmail = document.getElementById("frmEmail");
	let frmCell_phone = document.getElementById("frmCell_phone");
	let frmUser_name = document.getElementById("frmUser_name");
	let frmPassword = document.getElementById("frmPassword");

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
		datos += "&type_person=" + document.getElementById("frmType_person").value;
		datos += "&names=" + document.getElementById("frmNames").value;
		datos += "&last_names=" + document.getElementById("frmLast_names").value;
		datos += "&type_document=" + document.getElementById("frmType_document").value;
		datos += "&number_document=" + document.getElementById("frmNumber_document").value;
		datos += "&address=" + document.getElementById("frmAddress").value;
		datos += "&email=" + document.getElementById("frmEmail").value;
		datos += "&cell_phone=" + document.getElementById("frmCell_phone").value;
		datos += "&user_name=" + document.getElementById("frmUser_name").value;
		datos += "&password=" + document.getElementById("frmPassword").value;
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "PersonProcesar", true);
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
		xhttp.open("GET", "PersonHistorial", true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let respuesta = xhttp.responseText;
				arreglo = JSON.parse(respuesta);
				let detalleTabla = "";
				arreglo.forEach(function(item) {
					detalleTabla += "<tr>";
					detalleTabla += "<td>" + item.id + "</td>";
					detalleTabla += "<td>" + item.type_person+ "</td>";
					detalleTabla += "<td>" + item.names + "</td>";
					detalleTabla += "<td>" + item.last_names + "</td>";
					detalleTabla += "<td>" + item.type_document + "</td>";
					detalleTabla += "<td>" + item.number_document + "</td>";
					//	detalleTabla += "<td>" + item.district + "</td>";
					detalleTabla += "<td>" + item.address+ "</td>";
					detalleTabla += "<td>" + item.email + "</td>";
					detalleTabla += "<td>" + item.cell_phone + "</td>";
					//	detalleTabla += "<td>" + item.user_name + "</td>";
					//	detalleTabla += "<td>" + item.password + "</td>";
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
				frmType_person.value = item.type_person;
				frmNames.value = item.names;
				frmLast_names.value = item.last_names;
				frmType_document.value = item.type_document;
				frmNumber_document.value = item.number_document;
				frmAddress.value = item.address;
				frmEmail.value = item.email;
				frmCell_phone.value = item.cell_phone;
				frmUser_name.value = item.user_name;
				frmPassword.value = item.password;
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