'use strict';

angular.module('app').factory('StudentService', ['$localStorage', '$http', '$q', 'urls',
            function($localStorage, $http, $q, urls) {

                var factory = {
                    loadAll: loadAll,
                    getAll: getAll,
                    getOne: getOne,
                    create: create,
                    update: update,
                    remove: remove,
                    loadAllCourse: loadAllCourse,
                    getAllCourse: getAllCourse,
                    regist: regist
                };

                return factory;

                function regist(idStudent, idCourse){
                	var data = new FormData();
                    data.append('idStudent', idStudent);
                    data.append('idCourse', idCourse);
                    var param = "?idStudent="+idStudent+"&idCourse="+idCourse;
                    console.log('Creating Student');
                    var deferred = $q.defer();
                    $http.post(urls.REGISTRATION_SERVICE_API+param)
                        .then(
                            function(response) {
                                deferred.resolve(response.data);
                            },
                            function(errResponse) {
                                console.error('Error while creating Student :'+errResponse.data.errorMessage);
                                    deferred.reject(errResponse);
                                }
                            );
                            return deferred.promise;
                }
                
                function getAllCourse(){
                	return $localStorage.courses;
                }
                
                function loadAllCourse(){
                	console.log('Fetching all courses');
                    var deferred = $q.defer();
                    $http.get(urls.COURSE_SERVICE_API)
                        .then(
                            function(response) {
                                console.log('Fetched successfully all studens');
                                $localStorage.courses = response.data;
                                deferred.resolve(response);
                            },
                            function(errResponse) {
                                console.error('Error while loading courses');
                                deferred.reject(errResponse);
                            }
                        );
                    return deferred.promise;
                }
                
                function loadAll() {
                    console.log('Fetching all students');
                    var deferred = $q.defer();
                    $http.get(urls.STUDENT_SERVICE_API)
                        .then(
                            function(response) {
                                console.log('Fetched successfully all studens');
                                $localStorage.students = response.data;
                                deferred.resolve(response);
                            },
                            function(errResponse) {
                                console.error('Error while loading students');
                                deferred.reject(errResponse);
                            }
                        );
                    return deferred.promise;
                }

                function getAll() {
                    return $localStorage.students;
                }

                function getOne(id) {
                    console.log('Fetching Student with id :' + id);
                    var deferred = $q.defer();
                    $http.get(urls.STUDENT_SERVICE_API + id)
                        .then(
                            function(response) {
                                console.log('Fetched successfully Student with id :' + id);
                                deferred.resolve(response.data);
                            },
                            function(errResponse) {
                                console.error('Error while loading Student with id :' + id);
                                deferred.reject(errResponse);
                            }
                        );
                    return deferred.promise;
                }

                function create(student) {
                	var data = new FormData();
                    data.append('firstName', student.firstName);
                    data.append('lastName', student.lastName);
                    data.append('gender', student.gender);
                    data.append('address', student.address);
                    console.log('Creating Student');
                    var deferred = $q.defer();
                    $http.post(urls.STUDENT_SERVICE_API, student)
                        .then(
                            function(response) {
                                loadAll();
                                deferred.resolve(response.data);
                            },
                            function(errResponse) {
                                console.error('Error while creating Student :'+errResponse.data.errorMessage);
                                    deferred.reject(errResponse);
                                }
                            );
                            return deferred.promise;
                        }

                    function update(student, id) {
                    	var data = new FormData();
                        data.append('firstName', student.firstName);
                        data.append('lastName', student.lastName);
                        data.append('gender', student.gender);
                        data.append('address', student.address);
                        console.log('Updating Student with id ' + data);
                        var deferred = $q.defer();
                        $http.put(urls.STUDENT_SERVICE_API + id, student)
                            .then(
                                function(response) {
                                    loadAll();
                                    deferred.resolve(response.data);
                                },
                                function(errResponse) {
                                    console.error('Error while updating Student with id :' + id);
                                    deferred.reject(errResponse);
                                }
                            );
                        return deferred.promise;
                    }

                    function remove(id) {
                        console.log('Removing Student with id ' + id);
                        var deferred = $q.defer();
                        $http.delete(urls.STUDENT_SERVICE_API + id)
                            .then(
                                function(response) {
                                    loadAll();
                                    deferred.resolve(response.data);
                                },
                                function(errResponse) {
                                    console.error('Error while removing Student with id :' + id);
                                    deferred.reject(errResponse);
                                }
                            );
                        return deferred.promise;
                    }
                }
            ]);