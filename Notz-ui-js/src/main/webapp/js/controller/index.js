$.getScript('js/controller/notz.js');

/*
 * LOGIN
 */

$("#loginForm").submit(function(event) {
  event.preventDefault();
  loginUser($('#loginLogin').val(), $('#loginPassword').val());
});

function loginUser(login, password) {
  console.log('loginUser');
  $.ajax({
    type: 'POST',
    crossDomain:true,
    url: userResource + '/login',
    data: {'login':login, 'password':password},
    success: connectUser,
    error: function(data) {
      alert('error');
    },
  });
}

/*
 * SIGNUP
 */

$("#signupForm").submit(function(event) {
  event.preventDefault();
  registerUser(signupFormToJSON());
});

function registerUser(data) {
  console.log('registerUser');
  $.ajax({
    type: 'POST',
    crossDomain:true,
    url: userResource,
    data: data,
    success: connectUser,
    error: function(data) {
      alert('error');
    },
    dataType: 'json',
    contentType: 'application/json'
  });
}

//Helper function to serialize all the form fields into a JSON string
function signupFormToJSON() {
  console.log('signupFormToJSON');
  return JSON.stringify({
      "completeName": $('#signupCompleteName').val(),
      "username": $('#signupUsername').val(),
      "email": $('#signupEmail').val(),
      "password": $('#signupPassword').val()
      });
}

//
function connectUser(user) {
  if (user != null) {
    var userId = user.id;
    if (userId != null && userId != '') {
      $.cookie('Notz-UserId', userId);
      $(location).attr('href', applicationUri + '/notes.html');
    }
  }
}