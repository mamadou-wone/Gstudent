<%@page import="wone.sn.DataBase"%>
<%@page import="wone.sn.Etudiant"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%
	ArrayList<Etudiant> studentList = DataBase.getEtudiant();
%>

<div class="p-4">
	<table class="table table-hover table-striped ">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Prénom</th>
				<th scope="col">Nom</th>
				<th scope="col">Email</th>
				<th scope="col">Address</th>
				<th scope="col">Date de naissance</th>
				<th scope="col">Modifier</th>
				<th scope="col">Supprimer</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Etudiant student : studentList) {
			%>
			<tr>
				<th scope="row"><%=student.getId()%></th>
				<td><%=student.getFirstName()%></td>
				<td><%=student.getName()%></td>
				<td><%=student.getEmail()%></td>
				<td><%=student.getAddress()%></td>
				<td><%=student.getEmail()%></td>
				<td><button class="btn btn-outline-success" type="button"
						data-bs-toggle="modal"
						data-bs-target="#staticBackdrop<%=student.getId()%>">Modifier</button></td>
				<td><button class="btn btn-outline-danger"
						data-bs-toggle="modal"
						data-bs-target="#exampleModal<%=student.getId()%>">Supprimer</button></td>
			</tr>
			<div class="modal fade" id="exampleModal<%=student.getId()%>"
				tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">
								Confirmer la supression de
								<%=student.getFirstName() + " " + student.getName()%>
								!
							</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">🥴</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<a href="DeleteServLet?id=<%=student.getId()%>"
								class="btn btn-outline-danger">Supprimer</a>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="staticBackdrop<%=student.getId()%>"
				data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">Mettre à
								jour les informations !</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form class="user" method="post"
								action="UpdateServlet?id=<%=student.getId()%>" id="user<%=student.getId()%>">
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											id="exampleFirstName" name="firstName" required
											value="<%=student.getFirstName()%>">
									</div>
									<div class="col-sm-6">
										<input type="text" class="form-control form-control-user"
											id="exampleLastName" name="name" required
											value="<%=student.getName()%>">
									</div>
								</div>
								<div class="form-group">
									<input type="email" class="form-control form-control-user"
										id="exampleInputEmail" name="email" required
										value="<%=student.getEmail()%>">
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="date" class="form-control form-control-user"
											id="exampleDate" name="dateOfBirth" required
											value="<%=student.getDateOfBirth()%>">
									</div>
									<div class="col-sm-6">
										<input type="text" class="form-control form-control-user"
											id="exampleAddress" name="address" required
											value="<%=student.getAddress()%>">
										<p color="red" id="warningDate" style="display: none;"></p>
									</div>
								</div>
								<button type="submit" class="btn btn-primary">Modifier</button>
							</form>
							<hr>
							<form method="post" action="OtherStudent?id=<%=student.getId()%>">
								<div class="form-group">
									<select class="form-control" name="select" id="select"
										 required>
										<option disabled selected>Informations</option>
										<option value="Etudiant Sportif">Etudiant Sportif</option>
										<option value="Etudiant Etranger">Etudiant Etranger</option>
									</select>
								</div>
								<button type="submit" class="btn btn-outline-primary">Modifier
									le statut</button>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>

						</div>
					</div>
				</div>
			</div>

			<%
				}
			%>
		</tbody>
	</table>
</div>

<script src="app.js"></script>
</body>
</html>

