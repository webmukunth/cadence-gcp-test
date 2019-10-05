var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "121617",
        "ok": "121594",
        "ko": "23"
    },
    "minResponseTime": {
        "total": "11",
        "ok": "11",
        "ko": "1107"
    },
    "maxResponseTime": {
        "total": "6420",
        "ok": "6420",
        "ko": "4708"
    },
    "meanResponseTime": {
        "total": "106",
        "ok": "105",
        "ko": "2945"
    },
    "standardDeviation": {
        "total": "151",
        "ok": "145",
        "ko": "1284"
    },
    "percentiles1": {
        "total": "83",
        "ok": "83",
        "ko": "3228"
    },
    "percentiles2": {
        "total": "145",
        "ok": "145",
        "ko": "4302"
    },
    "percentiles3": {
        "total": "259",
        "ok": "259",
        "ko": "4613"
    },
    "percentiles4": {
        "total": "384",
        "ok": "382",
        "ko": "4689"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 121491,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 16,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 87,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 23,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "39.927",
        "ok": "39.919",
        "ko": "0.008"
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
        "total": "121617",
        "ok": "121594",
        "ko": "23"
    },
    "minResponseTime": {
        "total": "11",
        "ok": "11",
        "ko": "1107"
    },
    "maxResponseTime": {
        "total": "6420",
        "ok": "6420",
        "ko": "4708"
    },
    "meanResponseTime": {
        "total": "106",
        "ok": "105",
        "ko": "2945"
    },
    "standardDeviation": {
        "total": "151",
        "ok": "145",
        "ko": "1284"
    },
    "percentiles1": {
        "total": "83",
        "ok": "83",
        "ko": "3228"
    },
    "percentiles2": {
        "total": "145",
        "ok": "145",
        "ko": "4302"
    },
    "percentiles3": {
        "total": "259",
        "ok": "259",
        "ko": "4613"
    },
    "percentiles4": {
        "total": "384",
        "ok": "382",
        "ko": "4689"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 121491,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 16,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 87,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 23,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "39.927",
        "ok": "39.919",
        "ko": "0.008"
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
