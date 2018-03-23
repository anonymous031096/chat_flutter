<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Specific Students </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.student.id" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="firstName">First name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.student.firstName" id="uname" class="username form-control input-sm" placeholder="Enter your first name" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="lastName">Last name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.student.lastName" id="uname" class="username form-control input-sm" placeholder="Enter your last name" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
     
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="gender">gender</label>
                            <div class="col-md-7">
                                <label class="radio-inline"><input type="radio" name="optradio" value="1" ng-model="ctrl.student.gender">Male</label>
								<label class="radio-inline"><input type="radio" name="optradio" value="0" ng-model="ctrl.student.gender">Female</label>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="address">Address</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.student.address" id="uname" class="username form-control input-sm" placeholder="Enter your address" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.student.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>    
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Users </span></div>
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
                    <tr ng-repeat="s in ctrl.getAll()">
                        <td>{{s.id}}</td>
                        <td>{{s.firstName}}</td>
                        <td>{{s.lastName}}</td>
                        <td>{{s.gender}}</td>
                        <td>{{s.address}}</td>
                        <td><button type="button" ng-click="ctrl.editUser(u.id)" class="btn btn-success custom-width">Edit</button></td>
                        <td><button type="button" ng-click="ctrl.removeUser(u.id)" class="btn btn-danger custom-width">Remove</button></td>
                        <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" ng-click="ctrl.idStudent = u.id">Regist</button></td>
                    </tr>
                    </tbody>
                </table>      
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Regist course</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<!-- Modal body -->
			<div class="modal-body">
				<form>
					<div class="form-group">
						<label for="sel1">Select list Course:</label> <select
							class="form-control" id="sel1" ng-model="ctrl.idCourse">
							<option ng-repeat="c in ctrl.getAllCourse()" value={{c.id}}>{{c.name}}</option>
						</select> {{ctrl.idCourse}}
						<button type="button" class="btn btn-primary"
							ng-click="ctrl.regist()">Regist</button>
					</div>
				</form>
			</div>
			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>