var stompClient = null;

function connect() {
    console.log("Start connecting via websocket");


    var socket = new SockJS('/pi-websocket');
    stompClient = Stomp.over(socket);

    return new Promise(function(resolve, reject) {
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            resolve();
        });
    })
}

function subscribeToMotionDetection() {
    stompClient.send("/app/motion-subscribe");

    stompClient.subscribe('/topic/motion', function (data) {
        console.log("Motion! " + data)
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
    })

    // $("#motion-subscribe").click(function () {
    //     subscribeToMotionDetection();
    // });
});

// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     console.log("Disconnected");
// }