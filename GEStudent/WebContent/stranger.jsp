<%@page import="wone.sn.Etudiant"%>
<%@page import="wone.sn.DataBase"%>
<%@page import="wone.sn.EtudiantEtranger"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%
	ArrayList<EtudiantEtranger> strangerList = DataBase.getStranger();
	ArrayList<Etudiant> studentList = DataBase.getEtudiant();
%>

<div class="p-4">
	<button class="btn btn-outline-secondary" data-bs-toggle="modal"
		data-bs-target="#exampleModal">Ajouter</button>

	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Choisissez
						l'étudiant !</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form method="post" action="AddStranger">
						<select class="form-control" name="student" id="select" required>
							<option disabled selected>Informations</option>
							<%
								for (Etudiant student : studentList) {
							%>
							<option value="<%=student.getId()%>"><%=student.getId()%>
								<%=student.getFirstName()%>
								<%=student.getName()%></option>
							<%
								}
							%>
						</select>
						<div class="form-group">
							<select class="form-control" name="country" id="country" required>
								<option disabled selected>Pays</option>
								<option value="USA">USA</option>
								<option value="Canada">Canada</option>
								<option value="Allemagne">Allemagne</option>
							</select>
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					</button>
					<button class="btn btn-outline-success">Confirmer</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<table class="table table-hover table-striped ">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Prénom</th>
				<th scope="col">Nom</th>
				<th scope="col">Email</th>
				<th scope="col">Address</th>
				<th scope="col">Date de naissance</th>
				<th scope="col">Pays</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (EtudiantEtranger student : strangerList) {
			%>
			<tr>
				<th scope="row" id="id"><%=student.getId()%></th>
				<td><%=student.getFirstName()%></td>
				<td><%=student.getName()%></td>
				<td><%=student.getEmail()%></td>
				<td><%=student.getAddress()%></td>
				<td><%=student.getEmail()%></td>
				<td><%=student.getPays()%></td>
			</tr>

			<%
				}
			%>
		</tbody>
	</table>
</div>

</body>
</html>