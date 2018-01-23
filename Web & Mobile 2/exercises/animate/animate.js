/* This is a little animation demo */
/*	Week 7 ISTE240 */
var moving = "";
function $(id){
    return document.getElementById(id);
}
function right(id){
        stopMoving();
        $(id).style.left = parseInt($(id).style.left) + 1 + 'px';
        moving = setInterval(function(){right(id);},10);
        if($(id).style.left > (window.innerWidth)-50){
            left(id);
        }
}

function left(id){
        stopMoving();
        $(id).style.left = parseInt($(id).style.left) - 1 + 'px';
        moving = setInterval(function(){left(id);},10);
        if($(id).style.left < 0 + 'px'){
            right(id);
        }
}

function stopMoving(){
        window.clearInterval(moving);
}
