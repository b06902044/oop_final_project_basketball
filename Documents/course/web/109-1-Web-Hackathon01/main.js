var cells = document.getElementsByClassName("date")
var input = document.getElementById("cal-input")
var color = document.getElementById("cal-color")
var button = document.getElementById("cal-button")
input.value = ''
color.value = '#b0b0b0'
var select_cell = -1;

for(let i = 0; i < cells.length; i++){
    cells[i].addEventListener("click", event => {
        for(let j = 0; j < cells.length; j++){
            cells[j].classList.remove('highlight');
        }
        cells[i].classList.add('highlight');
        select_cell = i;
    });
}

button.addEventListener("click", event => {
    get_input();
});

input.addEventListener('keyup', event => {
    if(event.keyCode === 13 && event.target.value !== ''){
        get_input();
    }
});

function get_input(){
    s = input.value;
    c = color.value;
    console.log(s);
    console.log(c);
    if(select_cell >= 0){
        cells[select_cell].innerHTML = `${select_cell + 4}<br><span style="color:${c}">${s} </span>`;
        input.value = "";
    }
}








//Sets the page's theme. No need to modify
var themeButton = document.getElementsByClassName("ChooseTheme")
for(var i=0; i<themeButton.length; ++i) {
    themeButton[i].addEventListener('click', e => {
        document.body.setAttribute('class', e.target.id)
    }, false)
}
