function findAll() {
	console.log('findAll');
  $.ajax({
    url: "http://localhost:8080/Notz-ws-jersey/notes/40288090395cd57f01395cd582a80000",
    dataType: "json",
    success: renderList,
    error: function(data) {
      alert('error');
    }
  });
}

function renderList(data) {
 console.log('renderList');
  // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
  var list = (data == null || data.note == null) ? [] : (data.note instanceof Array ? data.note : [data]);

  $('#noteList li').remove();
  $.each(list, function(index, note) {
    $('#noteList').append('<li><strong>' + note.title + '</strong>' + note.description + '</li>');
  });
}

function registerUser() {
  console.log('register');
  $.ajax({
    type: 'PUT',
    url: 'http://localhost:8080/Notz-ws-jersey/users/',
    data: signupFormToJSON(),
    success: function(data) {
      alert('success' + data);
    },
    error: function(data) {
      alert('error');
    },
    dataType: "json"
  });
}

//Helper function to serialize all the form fields into a JSON string
function signupFormToJSON() {
  return JSON.stringify({"user": {
      "completeName": $('#signupCompleteName').val(),
      "email": $('#signupEmail').val(),
      "password": $('#signupPassword').val()
      }});
}