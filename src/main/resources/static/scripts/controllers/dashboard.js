'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('yapp')
  .controller('DashboardCtrl', function($scope, $state, $http, $location) {

    $scope.$state = $state;

    $scope.logout = function() {
      console.log("Logout ran");
      $http.post('/logout', {}).success(function() {
        self.authenticated = false;
        $location.path("/");
      }).error(function(data) {
        console.log("Logout failed");
        self.authenticated = false;
      });
    };
  })

  .controller("AdminTabViewCtrl", function ($http, $scope) {
    $scope.isAdmin = checkAdmin();
    console.log($scope.isAdmin);

    function checkAdmin() {
      return $http.get("/user/isAdmin").success(function (data) {
        $scope.isAdmin = data;
        console.log($scope.isAdmin);
        return true;
      });
    }
  });
