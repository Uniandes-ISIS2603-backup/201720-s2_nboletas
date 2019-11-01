/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = angular.module("rellenarModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
           var basePath = 'src/modules/';
           $urlRouterProvider.otherwise("/display");
            $stateProvider
                    .state('display', {
                        url: "/display",
                        views: {
                            'listView':{
                                templateUrl: basePath + 'espectaculos/espectaculos.list.html',
                                controller: 'espectaculoCtrl'
                            },
                            'miniPostView':{
                                templateUrl: basePath + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePath +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }
                    });
        }]);
})(window.angular);


