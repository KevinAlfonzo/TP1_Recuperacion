<div id="layoutSidenav_nav">
	<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
		<div class="sb-sidenav-menu">
			<div class="nav">
				<div class="sb-sidenav-menu-heading">Maestros</div>
				<!-- Estudiantes -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
				   data-bs-target="#collapseEstudiantes" aria-expanded="false"
				   aria-controls="#collapseEstudiantes">
					<div class="sb-nav-link-icon">
						<i class="fa-solid fa-user"></i>
					</div> Persona
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseEstudiantes"
					 aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="personas.jsp">Activos</a> <a
							class="nav-link" href="personaseliminadas.jsp">Inactivos</a>
					</nav>
				</div>
				<!-- Usuarios -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
				   data-bs-target="#collapseUsuarios" aria-expanded="false"
				   aria-controls="#collapseUsuarios">
					<div class="sb-nav-link-icon">
						<i class="fa-solid fa-store"></i>
					</div> Producto
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseUsuarios"
					 aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="producto.jsp">Activos</a> <a
							class="nav-link" href="productosEliminados.jsp">Inactivos</a>
					</nav>
				</div>

				<!-- Transaccionales -->
				<div class="sb-sidenav-menu-heading">Transaccionales</div>
				<!-- Notas -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
				   data-bs-target="#collapseCategorias" aria-expanded="false"
				   aria-controls="#collapseCategorias">
					<div class="sb-nav-link-icon">
						<i class="fa-solid fa-credit-card"></i>
					</div> Transaccional #1
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseCategorias"
					 aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="venta.jsp">Venta</a>
					</nav>
				</div>
				<!-- Nota_detalle -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
				   data-bs-target="#collapseAutores" aria-expanded="false"
				   aria-controls="#collapseAutores">
					<div class="sb-nav-link-icon">
						<i class="fa-solid fa-cart-shopping"></i>
					</div> Transaccional #2
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseAutores"
					 aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="VentaDetalle.jsp">Venta Detalle</a>
					</nav>
				</div>
			</div>
		</div>
	</nav>
</div>