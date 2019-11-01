(function (ng) {
    var mod = angular.module('usuarioModule', ['ui.router']);
    mod.config(['$stateProvider', function ($stateProvider) {
            var basePath = 'src/modules/usuarios/';
            var htmlPath = 'src/modules/html/';
            var basePathHtml = 'src/modules/';
            $stateProvider
                    .state('usuarioList', {
                        url: '/usuarios/todos',
                        views: {
                            'listView': {
                                templateUrl: basePath + '/list/usuarios.list.html',
                                controller: 'usuarioCtrl',
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
                    })
                    .state('usuarioRegister', {
                        url: '/usuario/registro',
                        views: {
                            'masterView': {
                                templateUrl: basePath + '/register/usuarios.register.html',
                                controller: 'usuariosRegisterCtrl'
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
                    .state('usuarioRegisterSuccess', {
                        url: '/usuario/registro/exitoso',
                        param: {
                            userName: null
                        },
                        'views': {
                            'masterView': {
                                templateUrl: basePath + '/register/usuarios.register.success.html',
                                controller: 'usuariosRegisterSuccessCtrl'
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
                    .state('usuarioLoginSuccess', {
                        url: '/espectaculos/list',
                        views:{
                            'masterView': {
                                templateUrl: basePath + '/login/usuarios.login.success.html'
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
                    .state('usuarioLogin', {
                        url: '/usuario/ingreso',
                        views: {
                            'masterView': {
                                templateUrl: basePath + '/login/usuarios.login.html',
                                controller: 'usuarioLoginCtrl',
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
                    .state('usuarioDetail', {
                        url: '/usuario/{usuarioId:int}/informacion',
                        param: {
                            usuarioId: null
                        },
                        views: {
                            'masterView': {
                                templateUrl: htmlPath + 'cleanMaster.html'
                            },
                            'listView': {
                                templateUrl: basePath + 'list/usuarios.list.html',
                                controller: 'usuarioCtrl',
                                controllerAs: 'ctrl'
                            },
                            'detailView': {
                                templateUrl: basePath + 'usuario.info.html',
                                controller: 'usuarioCtrl',
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
                    })
                    .state('myAccount', {
                        url: '/{usuarioId:int}/account',
                        param: {
                            usuarioId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'usuario.info.html',
                                controller: 'usuarioCtrl',
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
                    })
                    .state('usuarioDelete', {
                        url: '/usuarios/delete/{usuarioId:int}',
                        param: {
                            usuarioId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/delete/usuarios.delete.html',
                                controller: 'usuarioDeleteCtrl'
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
                    })
                    .state('usuarioUpdate', {
                        url: '/usuarios/update/{usuarioId:int}',
                        param: {
                            usuarioId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + '/update/usuarios.update.html',
                                controller: 'usuarioUpdateCtrl'
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
                    });
        }
    ]);
})(window.angular);

