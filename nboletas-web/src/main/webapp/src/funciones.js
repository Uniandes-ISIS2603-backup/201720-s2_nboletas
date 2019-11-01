function swap(div1, div2, button, text1, text2) {
    var bt = document.getElementById(button);
    var x = document.getElementById(div1);
    var y = document.getElementById(div2);
    if(bt.innerHTML === text1) {
        x.style.display = "none";
        y.style.display = "block"
        bt.innerHTML = text2;
    } else {
        x.style.display = "block";
        y.style.display = "none"
        bt.innerHTML = text1;
    }
}

function toggle(div, button, text1, text2) {
    var bt = document.getElementById(button);
    var x = document.getElementById(div);
    if(bt.innerHTML === text1) bt.innerHTML = text2;
    else bt.innerHTML = text1;
    
    if(x.style.display === "none") x.style.display = "block";
    else x.style.display = "none";
}