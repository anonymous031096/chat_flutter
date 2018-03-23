'use strict';

angular.module('app').controller('CourseController', [
    'CourseService',
    '$scope',
    function(CourseService, $scope) {

        var self = this;
        self.course = {};
        self.courses = [];
        self.flag = false;
        self.view = view;
        self.back = back;

        self.submit = submit;
        self.getAll = getAll;
        self.create = create;
        self.update = update;
        self.remove = remove;
        self.edit = edit;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function view(id){
        	CourseService.getOne(id).then(function(course) {
        		self.flag = true;
        		self.course = course;
            },
            function(errResponse) {
                console.error('Error while removing course ' + id + ', Error :' + errResponse.data);
            });
        }
        
        function back(){
        	self.flag = false;
        }
        
        function submit() {
            console.log('Submitting');
            if (self.course.id === undefined ||
                self.course.id === null) {
                console.log('Saving New course',
                    self.course);
                create(self.course);
            } else {
                update(self.course, self.course.id);
                console.log('course updated with id ',
                    self.course.id);
            }
        }

        function create(course) {
            console.log('About to create course');
            CourseService
                .create(course)
                .then(
                    function(response) {
                        console
                            .log('course created successfully');
                        self.successMessage = 'course created successfully ';
                        self.errorMessage = '';
                        self.done = true;
                        self.course = {};
                        $scope.myForm
                            .$setPristine();
                    },
                    function(errResponse) {
                        console.error('Error while creating course');
                        self.errorMessage = 'Error while creating course: ' + errResponse.data.errorMessage;
                        self.successMessage = '';
                    });
        }

        function update(course, id) {
            console.log('About to update course');
            CourseService
                .update(course, id)
                .then(
                    function(response) {
                        console
                            .log('course updated successfully');
                        self.successMessage = 'course updated successfully ';
                        self.errorMessage = '';
                        self.done = true;
                        $scope.myForm
                            .$setPristine();
                    },
                    function(errResponse) {
                        console
                            .error('Error while updating course');
                        self.errorMessage = 'Error while updating course' + errResponse.data;
                        self.successMessage = '';
                    });
        }

        function remove(id) {
            console.log('About to remove course with id ' +
                id);
            CourseService
                .remove(id)
                .then(
                    function() {
                        console
                            .log('course ' +
                                id +
                                ' removed successfully');
                    },
                    function(errResponse) {
                        console
                            .error('Error while removing course ' +
                                id +
                                ', Error :' +
                                errResponse.data);
                    });
        }

        function getAll() {
            return CourseService.getAll();
        }

        function edit(id) {
            self.successMessage = '';
            self.errorMessage = '';
            CourseService
                .getOne(id)
                .then(
                    function(course) {
                        self.course = course;
                        self.course.timeStart = new Date(self.course.timeStart);
                        self.course.timeEnd = new Date(self.course.timeEnd);
                    },
                    function(errResponse) {
                        console
                            .error('Error while removing course ' +
                                id +
                                ', Error :' +
                                errResponse.data);
                    });
        }

        function reset() {
            self.successMessage = '';
            self.errorMessage = '';
            self.course = {};
            $scope.myForm.$setPristine(); // reset Form
        }
    }
]);