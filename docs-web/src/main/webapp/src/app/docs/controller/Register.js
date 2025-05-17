'use strict';

/**
 * Register controller.
 */
angular.module('docs').controller('Register', function($scope, $state, $dialog, Restangular) {
    $scope.user = {
        username: '',
        password: '',
        email: '',
        reason: ''
    };

    $scope.register = function () {
        Restangular.all('public/register')
            .post($scope.user)
            .then(function () {
                const btns = [{ result: 'ok', label: '确定', cssClass: 'btn-primary' }];
                $dialog.messageBox('注册成功', '您的注册请求已提交，等待管理员审批。', btns).open().then(function () {
                    $state.go('login');
                });
            }, function () {
                const btns = [{ result: 'ok', label: '确定', cssClass: 'btn-primary' }];
                $dialog.messageBox('注册失败', '提交失败，请稍后重试。', btns);
            });
    };
});
