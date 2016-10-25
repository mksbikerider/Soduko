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

	<div ng-app="myApp" ng-controller="formCtrl">
      <form novalidate>
        First Name:<br>
        <input type="text" ng-model="user.firstName"><br>
        Last Name:<br>
        <input type="text" ng-model="user.lastName">
        <br><br>
        <table>
            <tr ng-repeat="row in matrix.rows track by $index" id="matrix_row_{{$index}}">

                <td ng-repeat="cell in row.cells track by $index">
                 Cell = <input ng-model="cell"/>
                </td>
            </tr>
        </table>

        <button ng-click="reset()">RESET</button>
      </form>
      <p>matrix =  {{matrix}}</p>
      <p>master = {{master}}</p>
    </div>


    <script>
    var app = angular.module('myApp', []);
    app.controller('formCtrl', function($scope, $http) {
        $http.get("http://localhost:5000/rest")
            .then(function (response) {$scope.matrix = response.data;},
            function myError(response) {
            $scope.master = response.statusText;
        });
        $scope.reset = function() {
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
                    $scope.master = response.statusText;
                });
        };
    });
    </script>

</body>
</html>