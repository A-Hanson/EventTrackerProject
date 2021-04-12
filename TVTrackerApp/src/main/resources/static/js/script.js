window.addEventListener('load', function(event) {
  console.log("script.js loaded");
  init();
});

function testArea(){
  console.log("DEBUGGING AREA");
}

function init() {
  document.addSession.add.addEventListener('click', function (event) {
    event.preventDefault();
    createTVSession();
  });
  document.showAll.show.addEventListener('click', function (event) {
    event.preventDefault();
    loadTVSessions();
  });
  document.findSession.find.addEventListener('click', function (event) {
    event.preventDefault();
    getTVSessionById(document.findSession.id.value);
    document.findSession.reset();
  });
  
}

function createTVSession(){
  let session = {};
  session['deleted'] = 0;
  session['start'] = document.addSession.start.value;
  session['stop'] = document.addSession.stop.value;
  session['platform'] = {id: Number(document.addSession.platform.value)};
  session['user'] = {id: Number(document.addSession.user.value)};
  sendTVSessionToApi(session);
  document.addSession.reset();
}

function sendTVSessionToApi(session){
  let xhr = new XMLHttpRequest();
  xhr.open('POST', 'api/tv_watching_sessions', true);

  xhr.onreadystatechange = function(){
    if (xhr.readyState === 4) {
      if (xhr.status === 201 || xhr.status === 200){
        let finalSession = JSON.parse(xhr.responseText);
        console.log("Response object Id from POST: " + finalSession['id']);
        getTVSessionById(finalSession['id']);
      } else if (xhr.status === 400){
        displayError("Error creating session, error: " + xhr.status);
      } else {
        displayError("Error creating session... that's really wrong, error: " + xhr.status);
      }
    }
  };
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.send(JSON.stringify(session));
}

function getTVSessionById(sessionId){
  let xhr = new XMLHttpRequest();
  xhr.open('GET', 'api/tv_watching_sessions/' + sessionId);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4){
      if (xhr.status === 200){
        let tvSession = JSON.parse(xhr.responseText);
        displayTVSession(tvSession);
      } else {
        displayError("Error retrieving TV Session: " + xhr.status);
      }
    }
  }
  xhr.send();
}

function displayTVSession(session){
  let div = document.getElementById('tvSessionDetails');
  div.textContent = '';
  let labels = ['Id', 'Start', 'Stop', "User's Name", 'Platform'];
  let values = [session['id'], 
              session['start'], 
              session['stop'],
              session['user']['firstName'] + " " + session['user']['lastName'],
              session['platform']['name']];
  let ul = document.createElement('ul');
  labels.forEach(function(value, index){
    let li = document.createElement('li');
    li.textContent = value + ": " + values[index];
    ul.appendChild(li);
  })
  div.appendChild(ul);
  let form = document.createElement('form');
  form.name = "editOrDelete";
  let button = document.createElement('button');
  button.className = "btn btn-primary";
  button.name = "edit";
  button.textContent = "Edit TV Watching Session";
  let delButton = document.createElement('button');
  delButton.className = "btn btn-warning";
  delButton.name = "delete";
  delButton.textContent = "Delete TV Watching Session";
  form.appendChild(button);
  form.appendChild(delButton);
  div.append(form);
  document.editOrDelete.edit.addEventListener('click', function (event) {
    event.preventDefault();
    displayEditSession(session);
  });
  document.editOrDelete.delete.addEventListener('click', function (event) {
    event.preventDefault();
    sendSessionToApiToBeDeleted(session['id']);
  });
}

function sendSessionToApiToBeDeleted(sessionId){
  let xhr = new XMLHttpRequest();
  xhr.open('DELETE', 'api/tv_watching_sessions/' + sessionId);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4){
      if (xhr.status === 204){
        displayError("Delete Successfull!");
        let div = document.getElementById('tvSessionDetails');
        div.textContent = '';
      } else if (xhr.status === 404){
        displayError("Could not find TV Session to delete: " + xhr.status);
      } else {
        displayError("Error deleting TV Session: " + xhr.status);
      }
    }
  }
  xhr.send();  
}

function displayEditSession(session) {
  let parentDiv = document.getElementById('tvSessionDetails').parentElement;
  let div = document.createElement('div');
  div.className = "col container px-4";
  div.textContent = '';  
  let header = document.createElement('h5');
  header.textContent = "Edit Session Here";
  div.appendChild(header);
  loadAllUsers(div, session); 
  parentDiv.appendChild(div);
}

function loadAllUsers(div, session) {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', 'api/users');
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4){
      if (xhr.status === 200){
        let users = JSON.parse(xhr.responseText);
        loadAllPlatforms(div, session, users);
      } else {
        displayError("Error retrieving users: " + xhr.status);
      }
    }
  }
  xhr.send();  
}

function loadAllPlatforms(div, session, users) {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', 'api/platforms');
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4){
      if (xhr.status === 200){
        let platforms = JSON.parse(xhr.responseText);
        updateFormForSessions(div, session, users, platforms);
      } else {
        displayError("Error retrieving Platform Options: " + xhr.status);
      }
    }
  }
  xhr.send();
}

function updateFormForSessions(div, session, users, platforms){
  console.log("Value of session within updateFormForSessions")
  console.log(session);
  let form = document.createElement('form');
  form.name = "updatedSession";
  let row1 = document.createElement('div');
  row1.className = "row gx-5";
  let platformLabel = document.createElement('h6');
  platformLabel.textContent = "Platform";
  row1.appendChild(platformLabel);
// Choose platform
  for (let i = 0; i < platforms.length; i++) {
    let col = document.createElement('div');
    col.textContent = ''; 
    col.className = "col";
    let input = document.createElement('input');
    input.className = "form-check-input";
    input.type = "radio";
    input.name = "platform";
    input.value = platforms[i]['id'];
    input.id = "radio" + platforms[i]['id'];
    if (Number(platforms[i]['id']) === Number(session['platform']['id'])) {
      input.checked = true;
    }
    col.appendChild(input);
    let label = document.createElement('label');
    label.className = "form-check-label";
    label.for = input.id;
    label.textContent = platforms[i]['name'];
    col.appendChild(label);
    row1.appendChild(col); 
  }
  form.appendChild(row1);
  // Choose Start
  let row2 = document.createElement('div');
  row2.className = "row gx-5";
  let colStart = document.createElement('div');
  colStart.className = "col";
  let input1 = document.createElement('input');
  input1.type = "datetime-local";
  input1.name = "start";
  input1.value = session['start'];
  input1.id = "startUpdate";
  colStart.appendChild(input1);
  let label1 = document.createElement('label');
  label1.for = input1.id;
  label1.textContent = 'Start';
  colStart.appendChild(label1);
  row2.appendChild(colStart);
  form.appendChild(row2);
  // Choose Stop
  let row3 = document.createElement('div');
  row3.className = "row gx-5";
  let colEnd = document.createElement('div');
  colEnd.className = "col";
  let input2 = document.createElement('input');
  input2.type = "datetime-local";
  input2.name = "stop";
  input2.value = session['stop'];
  input2.id = "stopUpdate";
  colEnd.appendChild(input2);
  let label2 = document.createElement('label');
  label2.for = input2.id;
  label2.textContent = 'Stop';
  colEnd.appendChild(label2);
  row3.appendChild(colEnd);
  form.appendChild(row3); 
  // Choose User
  let row4 = document.createElement('div');
  row4.className = "row gx-5";
  let userLabel = document.createElement('h6');
  userLabel.textContent = "User";
  row4.appendChild(userLabel);
  for (let i = 0; i < users.length; i++) {
    let col3 = document.createElement('div');
    col3.textContent = ''; 
    col3.className = "col";
    let inputUser = document.createElement('input');
    inputUser.className = "form-check-input";
    inputUser.type = "radio";
    inputUser.name = "user";
    inputUser.value = users[i]['id'];
    inputUser.id = "radioUser" + users[i]['id'];
    if (Number(users[i]['id']) === Number(session['user']['id'])) {
      inputUser.checked = true;
    }
    col3.appendChild(inputUser);
    let labelUser = document.createElement('label');
    labelUser.className = "form-check-label";
    labelUser.for = inputUser.id;
    labelUser.textContent = users[i]['firstName'] + " " + users[i]['lastName'];
    col3.appendChild(labelUser);
    row4.appendChild(col3); 
  }
  form.appendChild(row4);
  let button = document.createElement('button');
  button.className = "btn btn-primary";
  button.name = "edit";
  button.textContent = "Edit";
  form.appendChild(button);
  div.appendChild(form);
  document.updatedSession.edit.addEventListener('click', function (event) {
    event.preventDefault();
    createUpdatedSession(session['id']);
  });
}

function createUpdatedSession(id){
  let session = {};
  session['deleted'] = 0;
  session['start'] = updatedSession.start.value;
  session['stop'] = updatedSession.stop.value;
  session['platform'] = {id: Number(updatedSession.platform.value)};
  session['user'] = {id: Number(updatedSession.user.value)};
  sendUpdatedSessionToApi(id, session);
  updatedSession.reset();
}

function sendUpdatedSessionToApi(id, updatedSesion){
  let xhr = new XMLHttpRequest();
  xhr.open('PUT', 'api/tv_watching_sessions/' + id);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4){
      if (xhr.status === 200){
        getTVSessionById(id);
      } else if (xhr.status === 404){
        displayError("Could not find TV Session to update: " + xhr.status);
      } else {
        displayError("Error updating TV Session: " + xhr.status);
      }
    }
  }
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.send(JSON.stringify(updatedSesion)); 
}

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
  div.textContent = '';
  let title = document.createElement('h3');
  title.textContent = "All Sessions that haven't been deleted";
  div.appendChild(title);
  let labels = ['Id', 'Start', 'Stop', "User Name", 'Platform'];
  let values = ['id', 'start', 'stop'];
  let table = document.createElement('table');
  table.className = 'table';
  let tableHeader = document.createElement('thead');
  let headerRow = document.createElement('tr');
  labels.forEach(function (value){
    let th = document.createElement('th');
    th.textContent = value;
    headerRow.appendChild(th);
  });
  tableHeader.appendChild(headerRow);
  table.appendChild(tableHeader);
  let tBody = document.createElement('tbody');
  for (let i = 0; i < list.length; i++) {
    let row = document.createElement('tr');
    values.forEach(function (value) {
      let cell = document.createElement('td');
      cell.textContent = list[i][value];
      row.appendChild(cell);
    });
    // 'user.userName', 'platform.name'
    let cellUserName = document.createElement('td');
    cellUserName.textContent = list[i]['user']['firstName'] + " " + list[i]['user']['lastName'];
    row.appendChild(cellUserName);
    let cellPlatformName = document.createElement('td');
    cellPlatformName.textContent = list[i]['platform']['name'];
    row.appendChild(cellPlatformName);
    tBody.appendChild(row);
  }

  table.appendChild(tBody);
  div.appendChild(table);
}

function displayError(message) {
  let div = document.getElementById('errors');
  div.textContent = '';
  let h3 = document.createElement('h3');
  h3.textContent = message;
  div.appendChild(h3);
}