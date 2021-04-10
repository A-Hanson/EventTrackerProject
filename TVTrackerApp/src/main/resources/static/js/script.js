window.addEventListener('load', function(event) {
  console.log("script.js loaded");
  init();
});

function init() {
  document.addSession.add.addEventListener('click', function (event) {
    event.preventDefault();
    createTVSession();
  });
  document.showAll.show.addEventListener('click', function (event) {
    event.preventDefault();
    loadTVSessions();
  });
  
}

function createTVSession(){
  let session = {};
  session['start'] = document.addSession.start.value;
  session['stop'] = document.addSession.stop.value;
  session['platform'] = {id: Number(document.addSession.platform.value)};
  session['user'] = {id: Number(document.addSession.user.value)};
  sendTVSessionToApi(session);
  addSession.reset();
}

function sendTVSessionToApi(session){

}

function displayTVSession(session){
// TODO
}

// function loadUserById(id){
//   let xhr = new XMLHttpRequest();
//   xhr.open('GET', 'api/users/' + id);
//   xhr.onreadystatechange = function () {
//     if (xhr.readyState === 4){
//       if (xhr.status === 200){
//         let user = JSON.parse(xhr.responseText);
//         return user;
//       } else {
//         displayError("Error retrieving user: " + xhr.status);
//       }
//     }
//   }
//   xhr.send();  
// }

// function loadPlatformById(id){
//   let xhr = new XMLHttpRequest();
//   xhr.open('GET', 'api/platforms/' + id);
//   xhr.onreadystatechange = function () {
//     if (xhr.readyState === 4){
//       if (xhr.status === 200){
//         let platform = JSON.parse(xhr.responseText);
//         console.log(platform);
//         return platform;
//       } else {
//         displayError("Error retrieving platform: " + xhr.status);
//       }
//     }
//   }
//   xhr.send();  
// }

function loadTVSessions() {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', 'api/tv_watching_sessions');
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4){
      if (xhr.status === 200){
        let tvSessions = JSON.parse(xhr.responseText);
        displayTVSessions(tvSessions);
      } else {
        displayError("Error retrieving TV Sessions: " + xhr.status);
      }
    }
  }
  xhr.send();
}

function displayTVSessions(list) {
  let div = document.getElementById('tvSessionTable');
  let ul = document.createElement('ul');
  for (const session of list) {
    let li = document.createElement('li');
    li.textContent = session.platform.name;
    ul.appendChild(li);
  }
  div.appendChild(ul);
}

function displayError(message) {
  let div = document.getElementById('errors');
  let h3 = document.createElement('h3');
  h3.textContent = message;
  div.appendChild(h3);
}