<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<h1>Spring/AngularJS example</h1>

	<p>Test 2</p>

	<div ng-app="sudokuApp">
	 <div ng-controller="matrixCtrl">
      <form novalidate>
        <table>
            <tr ng-repeat="row in matrix.rows track by $index" id="matrix_row_{{$index}}">

                <td ng-repeat="cell in row.cells track by $index">
                    <input ng-model="cell"/>
                </td>
            </tr>
        </table>

        <button ng-click="submit()">GO</button>
      </form>
      <p>matrix =  {{matrix}}</p>
      <p>statusText = {{statusText}}</p>
    </div>

    <div ng-controller="nameCtrl">
      <form novalidate>
        First Name:<br>
        <input type="text" ng-model="user.firstName"><br>
        Last Name:<br>
        <input type="text" ng-model="user.lastName">
      </form>
      <p>Line 1 =  {{user.firstName}}</p>
      <p>Line 2 = {{user.lastName}}</p>

        <button ng-click="reset()">reset</button>
    </div>
    </div>


    <script>
    var app = angular.module('sudokuApp', []);
    app.controller('matrixCtrl', function($scope, $http) {
        $http.get("http://localhost:5000/rest")
            .then(function (response) {$scope.matrix = response.data;},
            function myError(response) {
            $scope.statusText = response.statusText;
        });
        $scope.submit = function() {
               var data = $.param({
                               form: $scope.matrix
                           });

                var config = {
                    headers : {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }

                $http.post("http://localhost:5000/rest", data, config)
                    .then(function (response) {$scope.matrix = response.data;},
                    function myError(response) {
                    $scope.statusText = response.statusText;
                });
        };
    });
    app.controller('nameCtrl', function($scope) {
        $scope.reset = function() {
               $scope.user.firstName = '';
               $scope.user.lastName = ''
        };
    });
    </script>

</body>
</html>