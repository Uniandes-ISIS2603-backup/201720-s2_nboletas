/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = angular.module("espectaculosModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/espectaculos/';
            var basePathHtml = 'src/modules/';
            $urlRouterProvider.otherwise("/espectaculosList");
            $stateProvider
                    .state('espectaculosList', {
                        url: '/espectaculos/list',
                        views:{
                            'listView':{
                                templateUrl: basePath + 'espectaculos.list.html',
                                controller: 'espectaculoCtrl',
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
                    .state('espectaculoDetail' ,{
                        url: '/espectaculos/{espectaculoId:int}/detail',
                        param: {
                            espectaculoId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'espectaculos.detail.html',
                                controller: 'espectaculoCtrl',
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
                    .state('espectaculosCreate',{
                        url: '/espectaculo/create',
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/new/espectaculos.new.html',
                                controller: 'espectaculoNewCtrl'
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
                    .state('espectaculoArtista',{
                        url: '/espectaculo/{espectaculoId:int}/createArtista',
                        param: {
                            espectaculoId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/new/newArtista/espectaculos.newArtista.html',
                                controller: 'espectaculoNewArtistaCtrl'
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
                    .state('espectaculoOrganizador',{
                        url: '/espectaculo/{espectaculoId:int}/createOrganizador',
                        param: {
                            espectaculoId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/new/newOrganizador/espectaculos.newOrganizador.html',
                                controller: 'espectaculoNewOrganizadorCtrl'
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
                    .state('espectaculoUpdate', {
                        url: '/espectaculo/update/{espectaculoId:int}',
                        param: {
                            espectaculoId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/update/espectaculos.update.html',
                                controller: 'espectaculoUpdateCtrl'
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
                    .state('espectaculosDelete',{
                       url: '/espectaculo/delete/{espectaculoId:int}',
                        param: {
                            espectaculoId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/delete/espectaculos.delete.html',
                                controller: 'espectaculoDeleteCtrl'
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


