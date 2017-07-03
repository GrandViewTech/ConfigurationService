var app = angular.module('myApp', []);
app.controller('formCtrl', ['$scope', '$http', function($scope, $http)
    {
       
        var baseUrl="http://localhost:9000/config";
        $scope.setting={};
        $scope.settings=[];
        $scope.setting.envoirnment="Development";
        $scope.setting.application="Common";
        $scope.applications=["Common","DataMart"]; 
        $scope.envoirnments=["Development","Testing","Production"];    
        $scope.updateApp=function(application)
              {
                $scope.setting.application=application;
              }
        
         $scope.updateEnv=function(envoirnment)
              {
                $scope.setting.envoirnment=envoirnment;
              }
        
        $scope.add=function()
            {
                var url=baseUrl+"/setting/add/version/1";
                $http.post(url,$scope.setting).success(function(successResponse)
                {
                   console.log(successResponse);  
                   $scope.findAll();
                }).error(function(errorResponse)
                {
                    console.log(errorResponse);
                });
            }
        $scope.init=function()
            {
                 console.log("hi");
                 $scope.findAll();
            }
        
        $scope.findAll=function()
                {
                var url=baseUrl+"/setting/findAll/version/1";
                $http.get(url).success(function(successResponse)
                {
                   $scope.settings=successResponse;
                }).error(function(errorResponse)
                {
                    console.log(errorResponse);
                }); 
                }
    }]);