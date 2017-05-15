/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var app = angular.module('mainApp', []);

app.controller('usuarioController',function($scope, $http){
    
    
    function Usuario(){
            id:null;
            nome : null;
            email:null;
            telefone:null;
            sexo:null;
    }

    $scope.resetMensagem = function(){
        $scope.messageSucesso=false;
        $scope.messageErro=false;
        $scope.message='';
    }
    
    $scope.resetLista=function(){
        $scope.showListaUsuarios = true ; 
        $scope.showEditUsuario = false ; 
    }
    


    $scope.getUsuarios = function(){

        $http.get('http://localhost:5000/api/user')
        .success(function(data){
                console.log(data);
                $scope.listaUsuarios = data ; 

        }).error(function(data){
                console.log('Erro no Servidor' + data);
        });

    };


    $scope.init=function(){
        $scope.resetLista();
        $scope.resetMensagem();
        $scope.getUsuarios();
    }

    $scope.init();

        
    $scope.editarUsuario= function(usuario){
        $scope.usuario = usuario ; 
        $scope.showEditUsuario = true;
        $scope.showListaUsuarios = false ;
    }

    $scope.adicionarUsuario= function(){
        $scope.usuario = new Usuario();
        $scope.showEditUsuario = true;
        $scope.showListaUsuarios = false ;
    }

    
    
    
        
    $scope.salvarUsuario = function(usuario){

        //atualizar 
        if ( $scope.usuario.id != null  ){
            $scope.atualizarUsuario(usuario);
        } else {
            $scope.criarUsuario(usuario);
        }

    }


    $scope.criarUsuario = function(usuario){

        $http.post('http://localhost:5000/api/user',usuario )
        .success(function(data){
                console.log(data);
                $scope.messageSucesso=true;
                $scope.message='Usuario criado com sucesso';

        }).error(function(data){
                console.log('Erro no Servidor' + data);
                $scope.messageErro=false;
                $scope.message='Erro ao criar Usuario';
        });

    };

    $scope.atualizarUsuario = function(usuario){

        $http.put('http://localhost:5000/api/user/'+usuario.id ,usuario )
        .success(function(data){
                console.log(data);
                $scope.messageSucesso=true;
                $scope.message='Usuario atualizado com sucesso';

        }).error(function(data){
                console.log('Erro no Servidor' + data);
                $scope.messageErro=false;
                $scope.message='Erro ao atualizar Usuario';

        });

    };
    
    
    $scope.excluirUsuario = function(usuario){
      
        $http.delete('http://localhost:5000/api/user/'+usuario.id  )
        .success(function(data){
                console.log(data);
                $scope.messageSucesso=true;
                $scope.message='Usuario deletado com sucesso';
                $scope.init();

        }).error(function(data){
                console.log('Erro no Servidor' + data);
                $scope.messageErro=false;
                $scope.message='Erro ao deletar Usuario';

        });
        
    };
    

    $scope.voltar = function(){
        $scope.init();
    }


});
