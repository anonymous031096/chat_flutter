'use strict';

angular.module('app').factory('CourseService', ['$localStorage', '$http', '$q', 'urls',
            function($localStorage, $http, $q, urls) {

                var factory = {
                    loadAll: loadAll,
                    getAll: getAll,
                    getOne: getOne,
                    create: create,
                    update: update,
                    remove: remove
                };

                return factory;

                function loadAll() {
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

                function getAll() {
                    return $localStorage.courses;
                }

                function getOne(id) {
                    console.log('Fetching course with id :' + id);
                    var deferred = $q.defer();
                    $http.get(urls.COURSE_SERVICE_API + id)
                        .then(
                            function(response) {
                                console.log('Fetched successfully course with id :' + id);
                                deferred.resolve(response.data);
                            },
                            function(errResponse) {
                                console.error('Error while loading course with id :' + id);
                                deferred.reject(errResponse);
                            }
                        );
                    return deferred.promise;
                }

                function create(course) {
                    console.log('Creating course');
                    var deferred = $q.defer();
                    $http.post(urls.COURSE_SERVICE_API, course)
                        .then(
                            function(response) {
                                loadAll();
                                deferred.resolve(response.data);
                            },
                            function(errResponse) {
                                console.error('Error while creating course :'+errResponse.data.errorMessage);
                                    deferred.reject(errResponse);
                                }
                            );
                            return deferred.promise;
                        }

                    function update(course, id) {
                        console.log('Updating course with id ' + course);
                        var deferred = $q.defer();
                        $http.put(urls.COURSE_SERVICE_API + id, course)
                            .then(
                                function(response) {
                                    loadAll();
                                    deferred.resolve(response.data);
                                },
                                function(errResponse) {
                                    console.error('Error while updating course with id :' + id);
                                    deferred.reject(errResponse);
                                }
                            );
                        return deferred.promise;
                    }

                    function remove(id) {
                        console.log('Removing course with id ' + id);
                        var deferred = $q.defer();
                        $http.delete(urls.COURSE_SERVICE_API + id)
                            .then(
                                function(response) {
                                    loadAll();
                                    deferred.resolve(response.data);
                                },
                                function(errResponse) {
                                    console.error('Error while removing course with id :' + id);
                                    deferred.reject(errResponse);
                                }
                            );
                        return deferred.promise;
                    }
                }
            ]);