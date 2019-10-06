var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "136520",
        "ok": "136515",
        "ko": "5"
    },
    "minResponseTime": {
        "total": "13",
        "ok": "13",
        "ko": "1173"
    },
    "maxResponseTime": {
        "total": "10277",
        "ok": "10277",
        "ko": "4436"
    },
    "meanResponseTime": {
        "total": "76",
        "ok": "76",
        "ko": "2304"
    },
    "standardDeviation": {
        "total": "134",
        "ok": "133",
        "ko": "1374"
    },
    "percentiles1": {
        "total": "49",
        "ok": "49",
        "ko": "1280"
    },
    "percentiles2": {
        "total": "90",
        "ok": "90",
        "ko": "3448"
    },
    "percentiles3": {
        "total": "211",
        "ok": "211",
        "ko": "4238"
    },
    "percentiles4": {
        "total": "340",
        "ok": "339",
        "ko": "4396"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 136404,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 22,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 89,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 5,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "44.819",
        "ok": "44.818",
        "ko": "0.002"
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
        "total": "136520",
        "ok": "136515",
        "ko": "5"
    },
    "minResponseTime": {
        "total": "13",
        "ok": "13",
        "ko": "1173"
    },
    "maxResponseTime": {
        "total": "10277",
        "ok": "10277",
        "ko": "4436"
    },
    "meanResponseTime": {
        "total": "76",
        "ok": "76",
        "ko": "2304"
    },
    "standardDeviation": {
        "total": "134",
        "ok": "133",
        "ko": "1374"
    },
    "percentiles1": {
        "total": "49",
        "ok": "49",
        "ko": "1280"
    },
    "percentiles2": {
        "total": "90",
        "ok": "90",
        "ko": "3448"
    },
    "percentiles3": {
        "total": "211",
        "ok": "211",
        "ko": "4238"
    },
    "percentiles4": {
        "total": "340",
        "ok": "339",
        "ko": "4396"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 136404,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 22,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 89,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 5,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "44.819",
        "ok": "44.818",
        "ko": "0.002"
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
