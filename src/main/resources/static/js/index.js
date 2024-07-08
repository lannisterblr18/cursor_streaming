$(document).ready(function() {
    console.log("Hello world");
    const stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8080/ws'
    });
    stompClient.activate();
    const canvas = $('#rectangle')[0];
    const ctx = canvas.getContext('2d');
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    let isDrawing = false;
    let lastX = 0;
    let lastY = 0;
    $(canvas).on('mousedown', function(e) {
        console.log("Came to mousedown")
        isDrawing = true;
        [lastX, lastY] = [e.offsetX, e.offsetY];
    });
    $(canvas).on('mousemove', function(e) {
        console.log("Came to mousemove");
        if (isDrawing) {
            console.log("Came to mousemove inside");

            // Draw a line from last recorded coordinates to current coordinates
            ctx.beginPath();
            ctx.moveTo(lastX, lastY);
            ctx.lineTo(e.offsetX, e.offsetY);
            ctx.strokeStyle = "#000000";
            ctx.lineWidth = 5;
            ctx.lineJoin = 'round';
            ctx.stroke();
            ctx.closePath();
            [lastX, lastY] = [e.offsetX, e.offsetY];
            stompClient.publish({
                destination: "/app/stream-test",
                body: JSON.stringify({
                    xPosition: e.clientX,
                    yPosition: e.clientY,
                    action: "draw"
                })
            });
        }
    });
    $(canvas).on('mouseup', function(e) {
        isDrawing = false;
        console.log("Mouse up")
        stompClient.publish({
            destination: "/app/stream-test",
            body: JSON.stringify({
                xPosition: e.clientX,
                yPosition: e.clientY,
                action: "stop"
            })
        });
    });

    // Event listener for mouse out event (optional)
    $(canvas).on('mouseout', function(e) {
        console.log("Mouse out")
        isDrawing = false;
        stompClient.publish({
            destination: "/app/stream-test",
            body: JSON.stringify({
                xPosition: e.clientX,
                yPosition: e.clientY,
                action: "stop"
            })
        });
    });
    // let count = 0;
    // const limit = 10;
    // $("#rectangle").mousedown(function () {
    //     $("#rectangle").mousemove(function (event) {
    //         console.log("OK Moved!");
    //         console.log(event);
    //         console.log(ctx)
    //     });
    // }).mouseup(function () {
    //     $("#rectangle").unbind('mousemove');
    // }).mouseout(function () {
    //     $("#rectangle").unbind('mousemove');
    // });
    // $("#rectangle").mousemove(function (event) {
    //     console.log("Came here");
    //     console.log(event)
    //     if (count >= limit) {
    //         stompClient.publish({
    //             destination: "/app/stream-test",
    //             body: JSON.stringify({
    //                 xPosition: event.clientX,
    //                 yPosition: event.clientY
    //             })
    //         });
    //         count = 0;
    //     } else {
    //         count += 1;
    //     }
    // })
});
