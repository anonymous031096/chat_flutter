'use strict';

angular.module('app').controller('StudentController', [
    'StudentService',
    '$scope',
    function(StudentService, $scope) {

        var self = this;
        self.student = {};
        self.students = [];
        
//        self.regist = regist;
        self.submit = submit;
        self.getAll = getAll;
        self.getAllCourse = getAllCourse
        self.create = create;
        self.update = update;
        self.remove = remove;
        self.edit = edit;
        self.reset = reset;
        self.regist = regist;
        self.idStudent = 0;
        self.idCourse = 0;
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function regist() {
            console.log('About to create regist');
            StudentService
                .regist(self.idStudent, self.idCourse)
                .then(
                    function(response) {
                        console.log('regist created successfully');
                        self.successMessage = 'regist created successfully ';
                        self.errorMessage = '';
                        self.done = true;
                        self.student = {};
                        $scope.myForm
                            .$setPristine();
                    },
                    function(errResponse) {
                        console.error('Error while creating Regist');
                        self.errorMessage = 'Error while creating Regist: ' + errResponse.data.errorMessage;
                        self.successMessage = '';
                    });
        }
        
        function submit() {
            console.log('Submitting');
            if (self.student.id === undefined ||
                self.student.id === null) {
                console.log('Saving New Student',
                    self.student);
                create(self.student);
            } else {
                update(self.student, self.student.id);
                console.log('Student updated with id ',
                    self.student.id);
            }
        }

        function create(student) {
            console.log('About to create student');
            StudentService
                .create(student)
                .then(
                    function(response) {
                        console
                            .log('Student created successfully');
                        self.successMessage = 'Student created successfully ';
                        self.errorMessage = '';
                        self.done = true;
                        self.student = {};
                        $scope.myForm
                            .$setPristine();
                    },
                    function(errResponse) {
                        console.error('Error while creating Student');
                        self.errorMessage = 'Error while creating Student: ' + errResponse.data.errorMessage;
                        self.successMessage = '';
                    });
        }

        function update(student, id) {
            console.log('About to update student');
            StudentService
                .update(student, id)
                .then(
                    function(response) {
                        console
                            .log('Student updated successfully');
                        self.successMessage = 'Student updated successfully ';
                        self.errorMessage = '';
                        self.done = true;
                        $scope.myForm
                            .$setPristine();
                    },
                    function(errResponse) {
                        console
                            .error('Error while updating Student');
                        self.errorMessage = 'Error while updating Student' + errResponse.data;
                        self.successMessage = '';
                    });
        }

        function remove(id) {
            console.log('About to remove Student with id ' +
                id);
            StudentService
                .remove(id)
                .then(
                    function() {
                        console
                            .log('Student ' +
                                id +
                                ' removed successfully');
                    },
                    function(errResponse) {
                        console
                            .error('Error while removing student ' +
                                id +
                                ', Error :' +
                                errResponse.data);
                    });
        }

        function getAll() {
            return StudentService.getAll();
        }

        function getAllCourse() {
            return StudentService.getAllCourse();
        }
        
        function edit(id) {
            self.successMessage = '';
            self.errorMessage = '';
            StudentService
                .getOne(id)
                .then(
                    function(student) {
                        self.student = student;
                    },
                    function(errResponse) {
                        console
                            .error('Error while removing student ' +
                                id +
                                ', Error :' +
                                errResponse.data);
                    });
        }

        function reset() {
            self.successMessage = '';
            self.errorMessage = '';
            self.student = {};
            $scope.myForm.$setPristine(); // reset Form
        }
    }
]);