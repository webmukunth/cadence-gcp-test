var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "137580",
        "ok": "137473",
        "ko": "107"
    },
    "minResponseTime": {
        "total": "10",
        "ok": "10",
        "ko": "1150"
    },
    "maxResponseTime": {
        "total": "6542",
        "ok": "6542",
        "ko": "4879"
    },
    "meanResponseTime": {
        "total": "73",
        "ok": "72",
        "ko": "2104"
    },
    "standardDeviation": {
        "total": "120",
        "ok": "103",
        "ko": "811"
    },
    "percentiles1": {
        "total": "34",
        "ok": "34",
        "ko": "1808"
    },
    "percentiles2": {
        "total": "74",
        "ok": "73",
        "ko": "2470"
    },
    "percentiles3": {
        "total": "271",
        "ok": "269",
        "ko": "3924"
    },
    "percentiles4": {
        "total": "526",
        "ok": "513",
        "ko": "4798"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 137260,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 210,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 3,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 107,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "45.153",
        "ok": "45.117",
        "ko": "0.035"
    }
},
contents: {
"req_submitpayment-e1b5b": {
        type: "REQUEST",
        name: "submitPayment",
path: "submitPayment",
pathFormatted: "req_submitpayment-e1b5b",
stats: {
    "name": "submitPayment",
    "numberOfRequests": {
        "total": "137580",
        "ok": "137473",
        "ko": "107"
    },
    "minResponseTime": {
        "total": "10",
        "ok": "10",
        "ko": "1150"
    },
    "maxResponseTime": {
        "total": "6542",
        "ok": "6542",
        "ko": "4879"
    },
    "meanResponseTime": {
        "total": "73",
        "ok": "72",
        "ko": "2104"
    },
    "standardDeviation": {
        "total": "120",
        "ok": "103",
        "ko": "811"
    },
    "percentiles1": {
        "total": "34",
        "ok": "34",
        "ko": "1808"
    },
    "percentiles2": {
        "total": "74",
        "ok": "73",
        "ko": "2470"
    },
    "percentiles3": {
        "total": "271",
        "ok": "269",
        "ko": "3924"
    },
    "percentiles4": {
        "total": "526",
        "ok": "513",
        "ko": "4798"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 137260,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 210,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 3,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 107,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "45.153",
        "ok": "45.117",
        "ko": "0.035"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
