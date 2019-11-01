/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = angular.module("comentariosModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/comentarios/';
            var basePathHtml = 'src/modules/';
            $urlRouterProvider.otherwise("/comentariosList");
            $stateProvider
             .state('comentariosList', {
                        url: '/comentarios/list',
                        views:{
                            'listView':{
                                templateUrl: basePath + 'comentarios.list.html',
                                controller: 'comentarioCtrl',
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
                    .state('comentarioDetail',{
                        url: '/comentarios/{comentarioId:int}/detail',
                        param: {
                            comentarioId: null
                        },
                        views: {                         
                            'detailView': {
                                templateUrl: basePath + 'comentarios.detail.html',
                                controller: 'comentarioCtrl',
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
                    .state('comentariosCreate',{
                        url: '/comentarios/create',
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/new/comentarios.new.html',
                                controller: 'comentariosNewCtrl'
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
                    .state('comentarioUpdate', {
                        url: '/comentarios/update/{comentarioId:int}',
                        param: {
                            comentarioId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/update/comentarios.update.html',
                                controller: 'comentarioUpdateCtrl'
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
                    .state('comentarioDelete',{
                       url: '/comentarios/delete/{comentarioId:int}',
                        param: {
                            comentarioId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/delete/comentarios.delete.html',
                                controller: 'comentarioDeleteCtrl'
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

