$.getScript('js/controller/notz.js');

loadNotes();

/*
 * NOTE LIST
 */

function loadNotes() {
  console.log('loadNotes');
  $.ajax({
    type: 'GET',
    url: noteResource + '/' + getUserId(),
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
   var list = (data == null || data.note == null) ? [] : (data.note instanceof Array ? data.note : [data.note]);

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
    url: noteResource,
    data: note,
    success: function(data) {
      $('#noteCreationModal').modal('hide');
      loadNotes();
    },
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
  
  var user = new Object();
  user.id = getUserId();
  
  var note = new Object();
  note.title = $('#noteCreationTitle').val();
  note.description = $('#noteCreationDescription').val();
  note.user = user;
  
  return JSON.stringify(note);
}

function getUserId() {
  return $.cookie('Notz-UserId');
}
