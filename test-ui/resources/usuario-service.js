/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//var app = angular.module('mainApp', []);

app.service('UsuarioService', function($http){
            this.square = function(a) {
               return a * a ; 
            }
            
            var listaUsuarios = {
              async2: function() {
                var promise = $http.get('http://localhost:5000/api/user').then(function (response) {
                  console.log(response);
                  return response.data;
                });
                return promise;
              }
            };
            return listaUsuarios;    

            
    
});

