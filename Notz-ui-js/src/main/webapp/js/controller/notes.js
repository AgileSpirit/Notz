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

   $('#noteList .note').remove();
   $.each(list, function(index, note) {
     var title = note.title;
     var description = note.description;
     var modificationDate = new Date(note.modificationDate);
     var modificationDateHtml = modificationDate.format("dd-mm-yyyy hh:MM:ss")

     var noteHtml = '';
     noteHtml = noteHtml.concat('<div id="note_' + index + '" class="note">').concat("\n");
     noteHtml = noteHtml.concat('  <div class="noteHeader">').concat("\n");
     noteHtml = noteHtml.concat('    <div class="title"><strong>' + title + '</strong></div>').concat("\n");
     noteHtml = noteHtml.concat('    <div class="actions">').concat("\n");
     noteHtml = noteHtml.concat('      <a href="#" class="editLink" title="Editer" ></a>').concat("\n");
     noteHtml = noteHtml.concat('      <a href="#" class="deleteLink" title="Supprimer" ></a>').concat("\n");
     noteHtml = noteHtml.concat('    </div>').concat("\n");
     noteHtml = noteHtml.concat('  </div>').concat("\n");
     noteHtml = noteHtml.concat('  <div class="noteBody">').concat("\n");
     noteHtml = noteHtml.concat('    <div class="noteDescription">').concat("\n");
     noteHtml = noteHtml.concat('      <span class="description">' + description + '</span>').concat("\n");
     noteHtml = noteHtml.concat('    </div>').concat("\n");
     noteHtml = noteHtml.concat('  </div>').concat("\n");
     noteHtml = noteHtml.concat('  <div class="noteFooter">').concat("\n");
     noteHtml = noteHtml.concat('    <div class="metadata">').concat("\n");
     noteHtml = noteHtml.concat('      <span>' + modificationDateHtml + '</span>').concat("\n");
     noteHtml = noteHtml.concat('    </div>').concat("\n");
     noteHtml = noteHtml.concat('    <div class="links">').concat("\n");
     noteHtml = noteHtml.concat('    </div>').concat("\n");
     noteHtml = noteHtml.concat('  </div>').concat("\n");
     noteHtml = noteHtml.concat('</div>').concat("\n");
     
     $('#noteList').append(noteHtml);
   });
}

/*
 * NOTE CREATION
 */

$("#saveNewNoteButton").click(function() {
  $("#noteCreationForm").submit();
});

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