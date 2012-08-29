loadNotes();

/*
 * NOTE LIST
 */

function loadNotes() {
  console.log('loadNotes');
  var userId = $.cookie('Notz-UserId');
  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/Notz-ws-jersey/notes/' + userId,
    success: function(data) {
      renderNotes(data);
    },
    error: function(data) {
      alert('error');
    },
    dataType: 'json',
    contentType: 'application/json'
  });
}

function renderNotes(data) {
  console.log('renderList');
   // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
   var list = (data == null || data.note == null) ? [] : (data.note instanceof Array ? data.note : [data]);

   $('#noteList li').remove();
   $.each(list, function(index, note) {
     $('#noteList').append('<li><strong>' + note.title + '</strong>' + note.description + '</li>');
   });
}

/*
 * NOTE CREATION
 */

$("#noteCreationForm").submit(function(event) {
  event.preventDefault();
  createNote(noteCreationFormToJSON());
});

function createNote(note) {
  console.log('createNote');
  $.ajax({
    type: 'PUT',
    url: servicesUri + 'notes/',
    data: note,
    success: renderNotes,
    error: function(data) {
      alert('error');
    },
    dataType: 'json',
    contentType: 'application/json'
  });
}

//Helper function to serialize all the form fields into a JSON string
function noteCreationFormToJSON() {
  console.log('noteCreationFormToJSON()');
  return JSON.stringify({
      "title": $('#signupCompleteName').val(),
      "description": $('#signupUsername').val()
      });
}

