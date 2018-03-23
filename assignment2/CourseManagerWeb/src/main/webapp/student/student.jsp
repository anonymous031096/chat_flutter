<div class="generic-container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="lead">Specific Student </span>
		</div>
		<div class="panel-body">
			<div class="formcontainer">
				<div class="alert alert-success" role="alert"
					ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
				<div class="alert alert-danger" role="alert"
					ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.student.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="firstName">First
								Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.student.firstName"
									id="firstName" class="username form-control input-sm"
									placeholder="Enter your first name" required ng-minlength="3" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="lastName">Last
								Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.student.lastName"
									id="lastName" class="form-control input-sm"
									placeholder="Enter your last name" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="gender">Gender</label>
							<div class="col-md-7">
								<label class="radio-inline"><input type="radio"
									name="optradio" value="1" ng-model="ctrl.student.gender">Male</label>
								<label class="radio-inline"><input type="radio"
									name="optradio" value="0" ng-model="ctrl.student.gender">Female</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="address">Address</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.student.address" id="address"
									class="form-control input-sm" placeholder="Enter your address" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.student.id ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm"
								ng-disabled="myForm.$invalid || myForm.$pristine">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
								Form</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="lead">List of Users </span>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>FIRST NAME</th>
							<th>LAST NAME</th>
							<th>GENDER</th>
							<th>ADDRESS</th>
							<th width="100"></th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.getAll()">
							<td>{{u.id}}</td>
							<td>{{u.firstName}}</td>
							<td>{{u.lastName}}</td>
							<td>{{u.gender}}</td>
							<td>{{u.address}}</td>
							<td><button type="button" ng-click="ctrl.edit(u.id)"
									class="btn btn-success custom-width">Edit</button></td>
							<td><button type="button" ng-click="ctrl.remove(u.id)"
									class="btn btn-danger custom-width">Remove</button></td>
							<td><button type="button" class="btn btn-info"
									data-toggle="modal" data-target="#myModal"
									ng-click="ctrl.idStudent = u.id">Regist</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Register course</h4>
			</div>

			<div class="modal-body">
				<form>
					<div class="form-group">
						<label for="sel1">Select course (select one):</label>
						<div class="row">
							<div class="col-md-10">
								<select class="form-control" id="sel1" ng-model="ctrl.idCourse">
									<option ng-repeat="c in ctrl.getAllCourse()" value={{c.id}}>{{c.name}}</option>
								</select>
							</div>
							<div class="col-md-2">
								<button type="button" class="btn btn-primary"
									ng-click="ctrl.regist()">Regist</button>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>