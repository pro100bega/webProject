<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Modal -->
	<div class="modal fade" id="newAppointmentModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<c:out value="${newAppointmentTitle}"></c:out>
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-6">
							<form class="form-horizontal" action="controller" method="post">
								<input type="hidden" name="command" value="ADD_APPOINTMENT">
								<h5>
									<c:out value="${appointmentTypeMessage}:"></c:out>
								</h5>
								<input type="text" class="form-control" name="type"
									placeholder="${namePlaceholder}" maxlength="20">
								<h5>
									<c:out value="${appointmentNameMessage}:"></c:out>
								</h5>
								<input type="text" class="form-control" name="name"
									placeholder="${surnamePlaceholder}" maxlength="20">
								<h5>
									<c:out value="${executionPeriodMessage}:"></c:out>
								</h5>
								<input type="date" class="form-control" name="appointmentTerm">
								
								<br />
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">
									<c:out value="${cancelButton}"></c:out>
								</button>
								<button type="submit" class="btn btn-success">
									<c:out value="${addAppointmentButton}"></c:out>
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>