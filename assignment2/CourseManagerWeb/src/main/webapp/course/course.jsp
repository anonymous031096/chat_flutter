<div class="generic-container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="lead">Specific Course </span>
		</div>
		<div class="panel-body">
			<div class="formcontainer">
				<div class="alert alert-success" role="alert"
					ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
				<div class="alert alert-danger" role="alert"
					ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.course.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="name">Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.course.name" id="name"
									class="username form-control input-sm"
									placeholder="Enter your name" required ng-minlength="3" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="timeStart">Time
								start</label>
							<div class="col-md-7">
								<input type="date" ng-model="ctrl.course.timeStart"
									id="timeStart" class="form-control input-sm"
									placeholder="Enter your Age." />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="timeEnd">Time
								end</label>
							<div class="col-md-7">
								<input type="date" ng-model="ctrl.course.timeEnd" id="gender"
									class="form-control input-sm" placeholder="Enter your Salary." />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.course.id ? 'Add' : 'Update'}}"
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
	<div class="panel panel-default" ng-if="!ctrl.flag">
		<div class="panel-heading">
			<span class="lead">List of Course </span>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>NAME</th>
							<th>TIME START</th>
							<th>TIME END</th>
							<th width="100"></th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="c in ctrl.getAll()">
							<td>{{c.id}}</td>
							<td>{{c.name}}</td>
							<td>{{c.timeStart}}</td>
							<td>{{c.timeEnd}}</td>
							<td><button type="button" ng-click="ctrl.edit(c.id)"
									class="btn btn-success custom-width">Edit</button></td>
							<td><button type="button" ng-click="ctrl.remove(c.id)"
									class="btn btn-danger custom-width">Remove</button></td>
							<td><button type="button" ng-click="ctrl.view(c.id)"
									class="btn btn-primary custom-width">View</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="panel panel-default" ng-if="ctrl.flag">
		<div class="panel-heading">
			<span class="lead">List of Users ({{ctrl.course.name}})</span>
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
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="s in ctrl.course.students">
							<td>{{s.id}}</td>
							<td>{{s.firstName}}</td>
							<td>{{s.lastName}}</td>
							<td>{{s.gender}}</td>
							<td>{{s.address}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row">
				<div class="form-actions floatRight">
					<button type="button" ng-click="ctrl.back()"
						class="btn btn-danger btn-sm">Back</button>
				</div>
			</div>
		</div>
	</div>
</div>