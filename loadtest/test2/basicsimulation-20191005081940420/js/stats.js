var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "132311",
        "ok": "132140",
        "ko": "171"
    },
    "minResponseTime": {
        "total": "11",
        "ok": "11",
        "ko": "1061"
    },
    "maxResponseTime": {
        "total": "10033",
        "ok": "10033",
        "ko": "9676"
    },
    "meanResponseTime": {
        "total": "84",
        "ok": "81",
        "ko": "2247"
    },
    "standardDeviation": {
        "total": "218",
        "ok": "198",
        "ko": "1216"
    },
    "percentiles1": {
        "total": "39",
        "ok": "39",
        "ko": "1850"
    },
    "percentiles2": {
        "total": "88",
        "ok": "88",
        "ko": "2651"
    },
    "percentiles3": {
        "total": "254",
        "ok": "251",
        "ko": "3922"
    },
    "percentiles4": {
        "total": "568",
        "ok": "539",
        "ko": "7481"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 131751,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 246,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 143,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 171,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "43.438",
        "ok": "43.381",
        "ko": "0.056"
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
        "total": "132311",
        "ok": "132140",
        "ko": "171"
    },
    "minResponseTime": {
        "total": "11",
        "ok": "11",
        "ko": "1061"
    },
    "maxResponseTime": {
        "total": "10033",
        "ok": "10033",
        "ko": "9676"
    },
    "meanResponseTime": {
        "total": "84",
        "ok": "81",
        "ko": "2247"
    },
    "standardDeviation": {
        "total": "218",
        "ok": "198",
        "ko": "1216"
    },
    "percentiles1": {
        "total": "39",
        "ok": "39",
        "ko": "1850"
    },
    "percentiles2": {
        "total": "88",
        "ok": "88",
        "ko": "2651"
    },
    "percentiles3": {
        "total": "254",
        "ok": "251",
        "ko": "3922"
    },
    "percentiles4": {
        "total": "568",
        "ok": "539",
        "ko": "7481"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 131751,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 246,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 143,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 171,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "43.438",
        "ok": "43.381",
        "ko": "0.056"
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
