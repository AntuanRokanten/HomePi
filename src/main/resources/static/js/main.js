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

        // $("#last-detection-time").text(currentDate.getHours() + ':' + currentDate.getMinutes() + ':' + currentDate.getSeconds());
        // $("#last-detection-date").text(currentDate.getDate() + '.' + currentDate.getMonth() + '.' + currentDate.getFullYear());

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
        console.log("Temp: " + data)
    });
}

$(function () {
    var promise = connect();
    promise.then(function () {
        subscribeToTempAndHumUpdates();
    });


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

// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     console.log("Disconnected");
// }