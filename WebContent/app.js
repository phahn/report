var deliveryPlanApp = angular.module('deliveryPlanApp', ['ngResource', 'ngRoute', 'ui.bootstrap', 'nvd3ChartDirectives', 'angularMoment']);

// our view controllers
deliveryPlanApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/report', {
            templateUrl: 'delivery-plan-report.html',
            controller: 'DeliveryPlanReportController'
        }).
        when('/margin', {
            templateUrl: 'delivery-plan-margin.html',
            controller: 'DeliveryPlanMarginController'
        }).
        when('/late-deliveries', {
            templateUrl: 'delivery-plan-late-deliveries.html',
            controller: 'DeliveryPlanLateDeliveriesController'
        }).
        otherwise({
            redirectTo: '/report'
        });
    }
]);

// rest-client service for getting our delivery plan report data from the server
deliveryPlanApp.factory('DeliveryPlan', function($resource) {
    var DeliveryPlan = $resource('/deliveryplan', {}, {
        query: {
            method: 'GET',
            isArray: false,
            url: '/deliveryplan/report'
        },
        margins: {
            method: 'GET',
            isArray: true,
            url: '/deliveryplan/margins'
        },
        latedeliveries: {
            method: 'GET',
            isArray: true,
            url: '/deliveryplan/latedeliveries'
        },
    });
    return DeliveryPlan;
});

// controller for the delivery plan report
deliveryPlanApp.controller('DeliveryPlanReportController', function($scope, DeliveryPlan, $filter) {

	// our search request for the delivery plan report, contains all filters
    $scope.searchRequest = {
        page: 1,
        size: 10,
        orderBy: 'deliveryNumber',
        orderDir: 'ASC'
    };

    // get the delivery plan report from the webservice
    $scope.refreshDeliveryPlan = function() {
        DeliveryPlan.query($scope.searchRequest).$promise.then(function(report) {
            $scope.deliveryPlan = report.content;
            $scope.totalItems = report.totalElements;
        });
    };

    // whenever our search request changes, get the updated delivery plan report from the server
    $scope.$watch('searchRequest', function() {
        $scope.refreshDeliveryPlan();
    }, true);

    // we need to convert the date from the datepicker in the right format
    $scope.$watch('dueDateFrom', function(date) {
        if (date) {
            $scope.searchRequest.dueDateFrom = moment(date).format("YYYY-MM-DD");
        } else {
            $scope.searchRequest.dueDateFrom = null;
        }
    });

    // we need to convert the date from the datepicker in the right format
    $scope.$watch('dueDateTo', function(date) {
        if (date) {
            $scope.searchRequest.dueDateTo = moment(date).format("YYYY-MM-DD");
        } else {
            $scope.searchRequest.dueDateTo = null;
        }
    });

});

// controller for the margin report
deliveryPlanApp.controller('DeliveryPlanMarginController', function($scope, DeliveryPlan, $filter) {

    // selects customer as x-axis
    $scope.xFunction = function() {
        return function(d) {
            return d.customer;
        };
    };

    // selects margin as y axis
    $scope.yFunction = function() {
        return function(d) {
            return d.margin;
        };
    };

    // formats the values of the y-axis
    $scope.valueFormat = function() {
        return function(d) {
            return $filter('currency')(d, '€');
        }
    }

    // formats the y-axis
    $scope.yAxisTickFormat = function() {
        return function(d) {
            return $filter('number')(d, 0);
        }
    };

    // get margins from webservice
    DeliveryPlan.margins().$promise.then(function(result) {
        $scope.marginData = [{
            key: "Margin",
            values: result
        }];
    });
});

// controller for the late deliveries report
deliveryPlanApp.controller('DeliveryPlanLateDeliveriesController', function($scope, DeliveryPlan, $filter) {

    // selects  customer as x-axis
    $scope.xFunction = function() {
        return function(d) {
            return d.customer;
        };
    };

    // selects late deliveries count as y-axis
    $scope.yFunction = function() {
        return function(d) {
            return d.count;
        };
    };

    // get late deliveries from webservice
    DeliveryPlan.latedeliveries().$promise.then(function(result) {
        $scope.lateDeliveries = result;
    });
});
