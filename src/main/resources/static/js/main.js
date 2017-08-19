var stompClient = null;

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

    stompClient.subscribe('/topic/motion', function (data) {
        console.log("Motion event is received: " + data);

        $("#motion-message").text("Motion is detected");
        var currentDate = new Date();

        $("#last-detection-time").text(currentDate.toLocaleTimeString());
        $("#last-detection-date").text(currentDate.toLocaleDateString());

        var motionDetectionBlock = $("#motion-detection-block");

        var intervalId = setInterval(function () {
            motionDetectionBlock.toggleClass("motion-detected");
        }, 500);

        setTimeout(function () {
            $("#motion-message").text("No motion detected");
            motionDetectionBlock.removeClass("motion-detected");
            clearInterval(intervalId);
        }, 30000);
    });
}

function subscribeToTempAndHumUpdates() {
    stompClient.send("/app/temp-and-hum-subscribe");

    stompClient.subscribe('/topic/temp-and-hum', function (data) {
        var json = JSON.parse(data.body);

        var temp = json.temperature.celcius;
        var hum = json.humidity.value;
        $("#temp-indoors").text(temp);
        $("#hum-indoors").text(hum);
    });
}

$(function () {
    var promise = connect();
    promise.then(function () {
        subscribeToTempAndHumUpdates();
    });

    if ("geolocation" in navigator) {
        console.log("Geolocation is supported by browser. Checking permission for accessing location.");
        navigator.geolocation.getCurrentPosition(
        function(position) { // on granted
            var location = position.coords.latitude+','+position.coords.longitude;
            console.log("Location permission is granted. Requesting weather for: " + location);
            loadWeather(location);
        },
        function() { // on declined
            console.log("Location permission is declined.");
            loadWeatherDefaultLocation();
        });

    } else {
        console.log("Browser doesn't support geolocation");
        loadWeatherDefaultLocation();
    }

    $('#motion-toggle-switch').change(function () {
        var telNotificationSwitch = $('#motion-telegram-notification-switch-label')[0];
        if ($(this).is(':checked')) {
            subscribeToMotionDetection();
            telNotificationSwitch.MaterialSwitch.enable();
        } else {
            // todo unsubscribe
            telNotificationSwitch.MaterialSwitch.off();
            telNotificationSwitch.MaterialSwitch.disable();
        }
    });
});

function loadWeatherDefaultLocation() {
    var defaultCity = 'KHARKIV'; // todo can it be injected with spring?
    console.log("Requesting weather for default location: " + defaultCity);
    loadWeather(defaultCity, '');
}

// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     console.log("Disconnected");
// }

function loadWeather(location, woeid) {
  $.simpleWeather({
    location: location,
    woeid: woeid,
    unit: 'c',
    success: function(weather) {
//      html = '<h2><i class="icon-'+weather.code+'"></i> '+weather.temp+'&deg;'+weather.units.temp+'</h2>';
//      html += '<ul><li>'+weather.city+', '+weather.region+'</li>';
//      html += '<li class="currently">'+weather.currently+'</li>';
//      html += '<li>'+weather.alt.temp+'&deg;C</li></ul>';

      $("#temp-outdoors").text(weather.temp);
      $("#hum-outdoors").text(weather.humidity);
    },
    error: function(error) {
      $("#weather").html('<p>'+error+'</p>');
    }
  });
}