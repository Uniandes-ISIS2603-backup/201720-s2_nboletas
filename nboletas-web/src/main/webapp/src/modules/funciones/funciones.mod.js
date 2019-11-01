/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("funcionesModule", []);
    mod.constant("funcionesContext", "api/funciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/funciones/';
            var basePathHtml = 'src/modules/';
            $urlRouterProvider.otherwise("/funcionesList");

            $stateProvider.state('funcionesList', {
                url: '/funciones/list',
                views: {
                    'listView': {
                        templateUrl: basePath + 'funciones.list.html',
                        controller: 'funcionesCtrl',
                        controllerAs: 'ctrl'
                    },
                    'miniPostView': {
                        templateUrl: basePathHtml + 'funciones/miniPosts.html',
                        controller: 'funcionesCtrl'
                    },
                    'postsListView': {
                        templateUrl: basePathHtml + 'artistas/postsList.html',
                        controller: 'artistaCtrl'
                    }
                }
            }).state('funcionesUpdate', {
                url: '/funciones/update/{funcionId:int}',
                param: {
                    funcionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/funciones.update.html',
                        controller: 'funcionesUpdateCtrl'
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
            }).state('funcionesCreate', {
                url: '/funciones/create',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/funciones.new.html',
                        controller: 'funcionesNewCtrl'
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
            }).state('funcionesDelete', {
                url: '/funciones/delete/{funcionId:int}',
                param: {
                    funcionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/funciones.delete.html',
                        controller: 'funcionesDeleteCtrl'
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
            }).state('funcionesDetail', {
                url: '/funciones/{funcionId:int}/detail',
                param: {
                    funcionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'funciones.detail.html',
                        controller: 'funcionesCtrl',
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
            });
        }]);
})(window.angular);



