window.addEventListener('load', function(event) {
  console.log("script.js loaded");
  init();
});

function init() {
  loadTVSessions();
}

function loadTVSessions() {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', 'api/tv_watching_sessions');
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4){
      console.log("pass readyState")
      if (xhr.status === 200){
        let tvSessions = JSON.parse(xhr.responseText);
        console.log(tvSessions);
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
    li.textContent = session;
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