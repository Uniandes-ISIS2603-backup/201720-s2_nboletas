/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = angular.module("boletasModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/boletas/';
            var basePathHtml = 'src/modules/';
            $urlRouterProvider.otherwise("/boletasList");
            $stateProvider
                    .state('boletasList', {
                        url: '/boletas/list',
                        views:{
                            'listView':{
                                templateUrl: basePath + 'boletas.list.html',
                                controller:'boletaCtrl'
                            },
                            'miniPostView':{
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }
                    })
                    .state('boletaDetail' ,{
                        url: '/boletas/{boletaId:int}/detail',
                        param: {
                            boletaId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'boletas.detail.html',
                                controller: 'boletaCtrl',
                                controllerAs: 'ctrl'
                            },
                            'miniPostView':{
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }
                    })
                    .state('boletasCreate',{
                        url: '/boletas/create',
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/new/boletas.new.html',
                                controller: 'boletaNewCtrl'
                            },
                            'miniPostView':{
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }        
                    })
                    .state('boletaUpdate', {
                        url: '/boletas/update/{boletaId:int}',
                        param: {
                            boletaId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/update/boletas.update.html',
                                controller: 'boletaUpdateCtrl'
                            },
                            'miniPostView':{
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }
                    })
                    .state('boletaDelete',{
                       url: '/boletas/delete/{boletaId:int}',
                        param: {
                            boletaId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/delete/boletas.delete.html',
                                controller: 'boletaDeleteCtrl'
                            },
                            'miniPostView':{
                                templateUrl: basePathHtml + 'funciones/miniPosts.html',
                                controller: 'funcionesCtrl'
                            },
                            'postsListView':{
                                templateUrl: basePathHtml +'artistas/postsList.html',
                                controller: 'artistaCtrl'
                            }
                        }         
                    });
        }]);
})(window.angular);


