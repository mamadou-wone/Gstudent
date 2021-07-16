<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<div class="container p-4">
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8 login-box">
			<div class="col-lg-12 login-key">
				<i class="fa fa-key" aria-hidden="true"></i>
			</div>
			<div class="col-lg-12 login-title">Register Page !</div>

			<div class="col-lg-12 login-form">
				<div class="col-lg-12 login-form">
					<form action="Admin" method="post">
						<div class="form-group">
							<label class="form-control-label">USERNAME</label> <input
								type="text" class="form-control" required="" name="username">
						</div>
						<div class="form-group">
							<label class="form-control-label">PASSWORD</label> <input
								type="password" class="form-control" required="" name="password">
						</div>

						<div class="col-lg-12 loginbttm">
							<div class="col-lg-6 login-btm login-text">
								<!-- Error Message -->
							</div>
							<div class="col-lg-6 login-btm login-button">
								<button type="submit" class="btn btn-outline-primary">Register</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-lg-3 col-md-2"></div>
		</div>
	</div>
	</body>
	</html>