var k = 1;

function openWindow() {
    console.log($(".open")[0]);
    $(".open")[0].style.display = "block";
}
function closeWindow() {
    $(".open")[0].style.display = "none";
}
function comment(name, email, message) {
    this.name = name;
    this.email = email;
    this.message = message;
}
function showComment(com) {
    var row = document.createElement('div');
    row.className = 'row';

    var cont = document.createElement('div');
    cont.className = 'container-fluid';
    
    var line = document.createElement('div');
    line.className = 'row';
    line.style.color = 'black';
    
    var firstPart = document.createElement('div');
    firstPart.classList.add('col-lg-12');
    firstPart.classList.add('col-md-12');
    firstPart.classList.add('col-sm-12');
    firstPart.classList.add('col-xs-12');

    var name = document.createElement('p');
    name.style.fontSize = 'large';
    name.innerHTML = (com.name);

    var email = document.createElement('p');
    email.style.fontSize = 'medium';
    email.innerHTML = (com.email);

    var message = document.createElement('p');
    message.style.fontSize = 'small';
    message.innerHTML = (com.message);

    var disLikeBtn = document.createElement("button");
    disLikeBtn.classList.add("btn");
    disLikeBtn.classList.add("btn-default");
    disLikeBtn.setAttribute("data", "dsl" + k);

    var disLikeBtnPic = document.createElement("span");
    disLikeBtnPic.classList.add("glyphicon");
    disLikeBtnPic.classList.add("glyphicon-minus");

    var disLikeBtnBadge = document.createElement("span");
    disLikeBtnBadge.classList.add("badge");
    disLikeBtnBadge.classList.add("badge-important");
    disLikeBtnBadge.innerHTML = " 0 ";
    disLikeBtnBadge.id = "dsl" + k;


    var LikeBtn = document.createElement("button");
    LikeBtn.classList.add("btn");
    LikeBtn.classList.add("btn-default");
    LikeBtn.classList.add("col-lg-offset-10");
    LikeBtn.classList.add("col-md-offset-10");
    LikeBtn.classList.add("col-sm-offset-10");
    LikeBtn.classList.add("col-xs-offset-10");
    LikeBtn.setAttribute("data", "l" + k);


    LikeBtn.onclick = function(event) {
        var id = event.srcElement.attributes.data.textContent;
        var badge = document.getElementById(id);
        badge.innerHTML = parseInt(badge.innerHTML) + 1;
    }

    disLikeBtn.onclick = function(event) {
        var id = event.srcElement.attributes.data.textContent;
        var badge = document.getElementById(id);
        badge.innerHTML = parseInt(badge.innerHTML) + 1;
    }


    var LikeBtnPic = document.createElement("span");
    LikeBtnPic.classList.add("glyphicon");
    LikeBtnPic.classList.add("glyphicon-heart-empty");

    var LikeBtnBadge = document.createElement("span");
    LikeBtnBadge.classList.add("badge");
    LikeBtnBadge.classList.add("badge-success");
    LikeBtnBadge.innerHTML = " 0 ";
    LikeBtnBadge.id = "l" + k;

    disLikeBtn.appendChild(disLikeBtnPic);
    LikeBtn.appendChild(LikeBtnPic);



    firstPart.appendChild(name);
    firstPart.appendChild(email);
    firstPart.appendChild(message);
    firstPart.appendChild(LikeBtn);
    firstPart.appendChild(LikeBtnBadge);
    firstPart.appendChild(disLikeBtn);
    firstPart.appendChild(disLikeBtnBadge);


    line.appendChild(firstPart);
    cont.appendChild(line);
    row.appendChild(cont);

    document.getElementById("com").appendChild(document.createElement("hr"));

    document.getElementById("com").appendChild(row);

    k++;

}
function sendComment() {
    var name = document.getElementById('name');
    var email = document.getElementById('email');
    var message = document.getElementById('text');

    var c = new comment(name.value, email.value, message.value);

    showComment(c);
    name.value = "";
    email.value = "";
    message.value = "";

    closeWindow();
}