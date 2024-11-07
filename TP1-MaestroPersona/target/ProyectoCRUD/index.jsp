<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<title>INICIO DE SESION</title>
	<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
			crossorigin="anonymous">
</head>

<body>
<section class="vh-100 gradient-custom">
	<div class="container py-5 h-100">
		<div
				class="row d-flex justify-content-center align-items-center h-100">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card bg-dark text-white" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">
						<div class="mb-md-5 mt-md-4 pb-5">
							<h2 class="fw-bold mb-2 text-uppercase">Login</h2>
							<p class="text-white-50 mb-5">Please enter your login and
								password!</p>
							<form method="post" action="InicioSession">
								<div class="form-outline form-white mb-4">
									<input type="text" name="usuario"
										   class="form-control form-control-lg" /> <label
										class="form-label">Usuario</label>
								</div>
								<div class="form-outline form-white mb-4">
									<input type="password" name="clave"
										   class="form-control form-control-lg" /> <label
										class="form-label">Password</label>
								</div>

								<button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
							</form>
							<c:if test="${not empty error}">
								<div style="padding: 15px; color: red;">${error}</div>
							</c:if>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</body>

</html>