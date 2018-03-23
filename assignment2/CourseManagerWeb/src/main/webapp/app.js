var app = angular.module('app', ['ui.router', 'ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080',
    STUDENT_SERVICE_API: 'http://localhost:8080/duc/student/',
    COURSE_SERVICE_API: 'http://localhost:8080/duc/course/',
    REGISTRATION_SERVICE_API: 'http://localhost:8080/duc/registration'
});

app.config([
    '$stateProvider',
    '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider.state('student', {
            url: '/student',
            templateUrl: 'student/student.jsp',
            controller: 'StudentController',
            controllerAs: 'ctrl',
            resolve: {
                students: function($q, StudentService) {
                    console.log('Load all students');
                    var deferred = $q.defer();
                    StudentService.loadAll().then(deferred.resolve,
                        deferred.resolve);
                    return deferred.promise;
                }
            }
        });
        
        $stateProvider.state('course', {
            url: '/course',
            templateUrl: 'course/course.jsp',
            controller: 'CourseController',
            controllerAs: 'ctrl',
            resolve: {
                courses: function($q, CourseService) {
                    console.log('Load all course');
                    var deferred = $q.defer();
                    CourseService.loadAll().then(deferred.resolve,
                        deferred.resolve);
                    return deferred.promise;
                }
            }
        });
        $urlRouterProvider.otherwise('/');
    }
]);