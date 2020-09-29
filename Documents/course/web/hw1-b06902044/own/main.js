
const img_url = ["https://i.ytimg.com/vi/OTTWedyp37o/maxresdefault.jpg", "https://cdn0-manfashion.techbang.com/system/images/109898/original/f2858292d139e5680c53ce6b19df63aa.jpg?1585715740", "https://cdntwrunning.biji.co/800_8827cf3ece3d75065afb745c12fb7b29.jpg", "https://assets.juksy.com/files/articles/98997/800x_100_w-5e6f2f432f881.jpg", "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BBZmn09.img?h=0&w=600&m=6&q=60&u=t&o=f&l=f&x=672&y=186", "https://www.gannett-cdn.com/presto/2020/01/26/USAT/048dcaa6-9da8-422f-a57f-2af33fb7ef3e-sw01_reg_4_1202.JPG", "https://i1.wp.com/thehoopsfreak.com/wp-content/uploads/2020/05/Kobe-RELEASE-1.jpg?resize=529%2C777&ssl=1", "https://pgw.udn.com.tw/gw/photo.php?u=https://uc.udn.com.tw/photo/2020/01/27/99/7384388.jpg&x=0&y=0&sw=0&sh=0&sl=W&fw=1140"];

let img_idx = 2;

function display_image(idx){
    let imageDiv = document.getElementById("display");
    imageDiv.setAttribute("src", img_url[img_idx]);
    let url = document.getElementById("url");
    url.setAttribute("href", img_url[img_idx]);
    url.innerHTML = "Source: " + img_url[img_idx];
}


//display_image(img_idx);

let pre = document.getElementById("previous");
let next = document.getElementById("next");

pre.addEventListener("click", function(){
    if(img_idx === 0)   return 0;
    display_image(img_idx--);
    if(img_idx === 0){
        pre.classList.add("disabled");
    }
    if(img_idx === img_url.length - 2){
        next.classList.remove("disabled");
    }
});

next.addEventListener("click", function(){
    if(img_idx === img_url.length - 1)  return 0;
    display_image(img_idx++);
    if(img_idx === img_url.length - 1){
        next.classList.add("disabled");
    }
    if(img_idx === 1){
        pre.classList.remove("disabled");
    }
});
