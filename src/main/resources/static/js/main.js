var stompClient = null;
var motionSubscription;

function connect() {
    console.log("Start connecting via websocket");


    var socket = new SockJS('/pi-websocket');
    stompClient = Stomp.over(socket);

    return new Promise(function (resolve, reject) {
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            resolve();
        });
    })
}

function subscribeToMotionDetection() {
    stompClient.send("/app/motion-subscribe");

    motionSubscription = stompClient.subscribe('/topic/motion', function (data) {
        console.log("Motion event is received: " + data);

        var currentDate = new Date();

        // setting motion messages
        $("#motion-message").text("Motion is detected");

        $("#last-detection-time").text(formatTime(currentDate));
        $("#last-detection-date").text(currentDate.toLocaleDateString());
        $("#motion-last-detect").show();

        // motion block blinking
        var motionDetectionBlock = $("#motion-detection-block");

        var intervalId = setInterval(function () {
            motionDetectionBlock.toggleClass("motion-detected");
            motionDetectionBlock.toggleClass("mdl-color--white");
        }, 500);

        // resetting block state
        setTimeout(function () {
            clearInterval(intervalId);
            $("#motion-message").text("No motion detected");

            if (motionDetectionBlock.hasClass('motion-detected')) {
                motionDetectionBlock.removeClass("motion-detected");
            }
            if (!motionDetectionBlock.hasClass('mdl-color--white')) {
                motionDetectionBlock.addClass("mdl-color--white");
            }
        }, 10000);
    });
}

function subscribeToTempAndHumUpdates() {
    stompClient.send("/app/temp-and-hum-subscribe");

    stompClient.subscribe('/topic/temp-and-hum', function (data) {
        var json = JSON.parse(data.body);

        var temp = json.temperature.celcius;
        var hum = json.humidity.value;

        $("#indoor-conditions").text(getConditionsSign(temp));

        $("#temp-indoors").text(temp);
        $("#hum-indoors").text(hum);
    });
}

$(function () {
    startTime();
    updateDate(new Date());

    var promise = connect();
    promise.then(function () {
        subscribeToTempAndHumUpdates();
    });

    if ("geolocation" in navigator) {
        console.log("Geolocation is supported by browser. Checking permission for accessing location.");
        navigator.geolocation.getCurrentPosition(
            function (position) { // on granted
                var location = position.coords.latitude + ',' + position.coords.longitude;
                console.log("Location permission is granted. Requesting weather for: " + location);
                subscribeWeatherUpdates(location);
            },
            function () { // on declined
                console.log("Location permission is declined.");
                subscribeWeatherDefaultLocation();
            });

    } else {
        console.log("Browser doesn't support geolocation");
        subscribeWeatherDefaultLocation();
    }

    $('#motion-toggle-switch').change(function () {
        var telNotificationSwitch = $('#motion-telegram-notification-switch-label')[0];
        if ($(this).is(':checked')) {
            subscribeToMotionDetection();
            telNotificationSwitch.MaterialSwitch.enable();
        } else {
            if (motionSubscription) {
                console.log("Unsubscribing from motion detection");

                motionSubscription.unsubscribe();
                motionSubscription = null;
            } else {
                console.warn("Cannot unsubscribe from motion detection. Seems like subscription object was not initialized.")
            }

            telNotificationSwitch.MaterialSwitch.off();
            telNotificationSwitch.MaterialSwitch.disable();
        }
    });

});

function startTime() {
    var today = new Date();
    var currentTime = formatTime(today);

    if (currentTime === "00:00:00") {
        updateDate(today);
    }

    $("#time").text(currentTime);
    t = setTimeout(function () {
        startTime()
    }, 500);
}

function formatTime(date) {
    var h = date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    // add a zero in front of numbers<10
    m = checkTime(m);
    s = checkTime(s);
    return h + ":" + m + ":" + s;
}

function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function getConditionsSign(temp) {
    var conditionsSign;
    if (temp > 30) {
        conditionsSign = "🔥";
    } else if (temp > 25) {
        conditionsSign = "😓";
    } else if (temp > 18) {
        conditionsSign = "🙂";
    } else if (temp > 3) {
        conditionsSign = "😓";
    } else {
        conditionsSign = "☃";
    }
    return conditionsSign;
}

function updateDate(date) {
    var weekday = new Array(7);
    weekday[0] = "Sunday";
    weekday[1] = "Monday";
    weekday[2] = "Tuesday";
    weekday[3] = "Wednesday";
    weekday[4] = "Thursday";
    weekday[5] = "Friday";
    weekday[6] = "Saturday";

    var n = weekday[date.getDay()];
    $("#week-day").text(n);
    $("#date").text(date.toLocaleDateString());
}

function subscribeWeatherDefaultLocation() {
    var defaultCity = 'KHARKIV';
    console.log("Requesting weather for default location: " + defaultCity);
    subscribeWeatherUpdates(defaultCity, '');
}

function updateWeather(location, woeid) {
    console.log("Updating weather...");
    $.simpleWeather({
        location: location,
        woeid: woeid,
        unit: 'c',
        success: function (weather) {

            $('#outdoors-icon').removeClass().addClass('icon-' + weather.code);
            $("#temp-outdoors").text(weather.temp);
            $("#outdoor-conditions").text(getConditionsSign(weather.temp));
            $("#hum-outdoors").text(weather.humidity);
        },
        error: function (error) {
            $("#weather").html('<p>' + error + '</p>');
        }
    });
}

function subscribeWeatherUpdates(location, woeid) {
    updateWeather(location, woeid);

    setTimeout(function () {
        updateWeather(location, woeid);
    }, 1800000); // updating weather every half an hour

}

// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     console.log("Disconnected");
// }
