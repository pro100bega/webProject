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
							<input type="hidden" name="patientId"
									value="${requestScope.selectedPatient.id}">
							<h5>
								<strong> <c:out value="${appointmentTypeMessage}:"></c:out>
								</strong>
							</h5>
							<select class="form-control" name="type">
								<option value="процедура" selected>
									<c:out value="${procedureMessage}"></c:out>
								</option>
								<option value="укол">
									<c:out value="${injectionMessage}"></c:out>
								</option>
								<option value="операция">
									<c:out value="${operationMessage}"></c:out>
								</option>
							</select>
							<h5>
								<strong> <c:out value="${appointmentNameMessage}:"></c:out>
								</strong>
							</h5>
							<input type="text" class="form-control" name="name"
								placeholder="${appointmentNamePlaceholder}" maxlength="20"
								pattern="^[А-Яа-я ]{2,20}$" required>
							<h5>
								<strong> <c:out value="${executionPeriodMessage}:"></c:out>
								</strong>
							</h5>
							<input type="date" class="form-control" name="termDate"
								required>
							<h5>
								<strong> <c:out value="${timeMessage}:"></c:out>
								</strong>
							</h5>
							<select class="form-control" name="termTime">
								<option value="8:00">8:00</option>
								<option value="8:30">8:30</option>
								<option value="9:00">9:00</option>
								<option value="9:30">9:30</option>
								<option value="10:00">10:00</option>
								<option value="10:30">10:30</option>
								<option value="11:00">11:00</option>
								<option value="11:30">11:30</option>
								<option value="12:00">12:00</option>
								<option value="12:30">12:30</option>
								<option value="14:00">14:00</option>
								<option value="14:30">14:30</option>
								<option value="15:00">15:00</option>
								<option value="15:30">15:30</option>
								<option value="16:00">16:00</option>
								<option value="16:30">16:30</option>
								<option value="17:00">17:00</option>
								<option value="17:30">17:30</option>
								<option value="18:00">18:00</option>
								<option value="18:30">18:30</option>
								<option value="19:00">19:00</option>
								<option value="19:30">19:30</option>
								<option value="20:00">20:00</option>
								<option value="20:30">20:30</option>
								<option value="21:00">21:00</option>
								<option value="21:30">21:30</option>
								<option value="22:00">22:00</option>
								<option value="22:30">22:30</option>
							</select><br>
							<button type="button" class="btn btn-danger" data-dismiss="modal">
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