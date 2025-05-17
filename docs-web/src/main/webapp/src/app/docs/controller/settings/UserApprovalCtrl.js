'use strict';

angular.module('docs').controller('UserApprovalCtrl', function($scope, $http, Restangular, $dialog) {
    $scope.users = [];    function loadUsers() {
        $http.get('api/user/approval')
            .then(function(response) {
                console.log('Users waiting for approval:', response.data.users);
                $scope.users = response.data.users;
            });
    }

    $scope.approve = function(username) {
        $http.post('api/user/' + username + '/approve')
            .then(function() {
                const btns = [{ result: 'ok', label: '确定', cssClass: 'btn-primary' }];
                $dialog.messageBox('审批成功', '用户 ' + username + ' 已通过审批', btns).open();
                loadUsers(); // 重新加载
            }, function() {
                const btns = [{ result: 'ok', label: '确定', cssClass: 'btn-primary' }];
                $dialog.messageBox('审批失败', '操作失败，请稍后重试', btns).open();
            });
    };

    $scope.reject = function(username) {
        $http.post('api/user/' + username + '/reject')
            .then(function() {
                const btns = [{ result: 'ok', label: '确定', cssClass: 'btn-primary' }];
                $dialog.messageBox('拒绝成功', '已拒绝用户 ' + username + ' 的注册请求', btns).open();
                loadUsers(); // 重新加载
            }, function() {
                const btns = [{ result: 'ok', label: '确定', cssClass: 'btn-primary' }];
                $dialog.messageBox('操作失败', '请稍后重试', btns).open();
            });
    };

    loadUsers();
});
